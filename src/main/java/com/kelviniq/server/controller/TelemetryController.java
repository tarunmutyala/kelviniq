package com.kelviniq.server.controller;

import com.kelviniq.server.request.TelemetryRequest;
import com.kelviniq.server.response.TelemetryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelviniq.server.service.TelemetryIngestService;

@RestController
@RequestMapping("/kelviniq/v1")
public class TelemetryController {
    @Autowired
    private  TelemetryIngestService telemetryIngestService;


    @PostMapping("/{deviceId}/telemetry")
    public ResponseEntity<TelemetryResponse> ingest(
            @PathVariable String deviceId,
            @RequestBody TelemetryRequest request) {
        try {
            return ResponseEntity.accepted().body(telemetryIngestService.ingest(deviceId, request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
