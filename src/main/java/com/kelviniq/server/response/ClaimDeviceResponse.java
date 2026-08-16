package com.kelviniq.server.response;

public record ClaimDeviceResponse(boolean accepted, String message, String deviceId) {
}
