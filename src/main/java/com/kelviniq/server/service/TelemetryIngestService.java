package com.kelviniq.server.service;

import com.kelviniq.server.entity.Device;
import com.kelviniq.server.repository.DeviceRepository;
import com.kelviniq.server.request.Reading;
import com.kelviniq.server.request.TelemetryRequest;
import com.kelviniq.server.response.TelemetryResponse;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class TelemetryIngestService {

    private final JdbcTemplate jdbc;
    private final DeviceRepository deviceRepository;

    public TelemetryIngestService(JdbcTemplate jdbc, DeviceRepository deviceRepository) {
        this.jdbc = jdbc;
        this.deviceRepository = deviceRepository;
    }

    @Transactional
    public TelemetryResponse ingest(String deviceId, TelemetryRequest request) {
        if (deviceId == null || deviceId.isBlank()) {
            throw new IllegalArgumentException("deviceId is required");
        }
        if (request == null || request.getReadings() == null || request.getReadings().isEmpty()) {
            throw new IllegalArgumentException("at least one reading is required");
        }
//need to check with team for it
        Device device = deviceRepository.findByDeviceId(deviceId);
              //  .orElseThrow(() -> new IllegalArgumentException("Device not found: " + deviceId));

        if (device.getOrg() == null) {
            throw new IllegalArgumentException("Device is not claimed to an org");
        }

        long deviceTs = request.getDevice_ts();
        Instant serverNow = Instant.now();
        boolean clockSuspect = Math.abs(serverNow.getEpochSecond() - deviceTs) > 300; // 5 minutes

        List<Reading> readings = request.getReadings();

        final String sql = "INSERT INTO readings (org_id, device_id, channel, celsius, device_ts, server_ts, clock_suspect) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbc.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(java.sql.PreparedStatement ps, int i) throws java.sql.SQLException {
                Reading r = readings.get(i);
                ps.setLong(1, device.getOrg().getId());
                ps.setString(2, deviceId);
                ps.setString(3, r.getCh());
                ps.setBigDecimal(4, BigDecimal.valueOf(r.getC()));
                // device_ts is provided as epoch seconds in the payload
                Instant readingInstant = Instant.ofEpochSecond(r.getTs());
                ps.setTimestamp(5, Timestamp.from(readingInstant));
                ps.setTimestamp(6, Timestamp.from(serverNow));
                ps.setBoolean(7, clockSuspect);
            }

            @Override
            public int getBatchSize() {
                return readings.size();
            }
        });

        return new TelemetryResponse(
                true,
                "Telemetry accepted",
                deviceId,
                readings.size()
        );
    }
}