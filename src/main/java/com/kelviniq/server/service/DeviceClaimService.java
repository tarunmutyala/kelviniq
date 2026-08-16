package com.kelviniq.server.service;

import org.springframework.stereotype.Service;

import com.kelviniq.server.request.ClaimDeviceRequest;
import com.kelviniq.server.response.ClaimDeviceResponse;

@Service
public class DeviceClaimService {

    public ClaimDeviceResponse claimDevice(ClaimDeviceRequest request) {
        if (request == null || request.getDeviceId() == null || request.getDeviceId().isBlank()
                || request.getClaimCode() == null || request.getClaimCode().isBlank()) {
            throw new IllegalArgumentException("deviceId and claimCode are required");
        }

        return new ClaimDeviceResponse(true, "Device claim request accepted", request.getDeviceId());
    }
}
