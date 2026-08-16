package com.kelviniq.server.response;

public record DeviceResponse(
        Long id,
        String deviceId,
        Long orgId,
        Long siteId,
        String label,
        String claimCode,
        String fwVersion,
        Integer reportIntervalS
) {
}
