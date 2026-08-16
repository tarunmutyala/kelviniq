package com.kelviniq.server.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRequest {
    private String deviceId;
    private Long orgId;
    private Long siteId;
    private String label;
    private String claimCode;
    private String secretHash;
    private String fwVersion;
    private Integer reportIntervalS;
}
