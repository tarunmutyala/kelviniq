package com.kelviniq.server.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelemetryRequest {
    private Integer schema;
    private long device_ts;
    private long uptime_s;
    private List<Reading> readings;
    private Integer battery_mv;
    private Integer rssi;
    private String fw;
}
