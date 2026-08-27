package com.kelviniq.server.response;

public record DeviceResponse(
        Long id,
        String deviceId,
        OrgResponse orgId,
        Long siteId,
        String label,
        String claimCode,
        String fwVersion,
        Integer reportIntervalS
) {
}
