package com.kelviniq.server.service;

import com.kelviniq.server.entity.Device;
import com.kelviniq.server.entity.DeviceState;
import com.kelviniq.server.repository.DeviceStateRepository;
import com.kelviniq.server.repository.LastWillTestamentRepository;
import com.kelviniq.server.request.Lwt;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class LastWillTestamentService {
    private final LastWillTestamentRepository lastWillTestamentRepository;
    private final DeviceStateRepository deviceStateRepository;

    public LastWillTestamentService(
            LastWillTestamentRepository lastWillTestamentRepository,
            DeviceStateRepository deviceStateRepository) {
        this.lastWillTestamentRepository = lastWillTestamentRepository;
        this.deviceStateRepository = deviceStateRepository;
    }

    public String updateDeviceStatus(String deviceId, Lwt lwt) {
        if (deviceId == null || deviceId.isBlank()) {
            throw new IllegalArgumentException("deviceId is required");
        }
        if (lwt == null || lwt.state == null || lwt.state.isBlank()) {
            throw new IllegalArgumentException("state is required");
        }

        Device device = lastWillTestamentRepository.findByDeviceId(deviceId);
        if (device == null) {
            throw new IllegalArgumentException("Device not found: " + deviceId);
        }

        DeviceState deviceState = deviceStateRepository.findByDevice(device);
        if (deviceState == null) {
            deviceState = new DeviceState(device, lwt.state, OffsetDateTime.now());
        } else {
            deviceState.setState(lwt.state);
            deviceState.setUpdatedAt(OffsetDateTime.now());
        }

        deviceStateRepository.save(deviceState);
        return "State saved for device " + deviceId;
    }

    public DeviceState getDeviceState(String deviceId) {
        if (deviceId == null || deviceId.isBlank()) {
            throw new IllegalArgumentException("deviceId is required");
        }
        Device device = lastWillTestamentRepository.findByDeviceId(deviceId);
        if (device == null) {
            throw new IllegalArgumentException("Device not found: " + deviceId);
        }
        return deviceStateRepository.findByDevice(device);
    }
}
