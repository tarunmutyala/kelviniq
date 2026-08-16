package com.kelviniq.server.response;

public record TelemetryResponse(boolean accepted, String message, String deviceId, int readingCount) {
}
