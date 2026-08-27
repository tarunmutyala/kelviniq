package com.kelviniq.server.controller;

import com.kelviniq.server.request.DeviceRequest;
import com.kelviniq.server.response.DeviceResponse;
import com.kelviniq.server.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/kelviniq/v1"})
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/devices/{deviceId}")
    public ResponseEntity<DeviceResponse> getDevice(@PathVariable String deviceId) {
        try {
            return ResponseEntity.ok(deviceService.getDeviceByDeviceId(deviceId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/devices")
    public ResponseEntity<DeviceResponse> createDevice(@RequestBody DeviceRequest request) {
        try {
            return ResponseEntity.ok(deviceService.createDevice(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
