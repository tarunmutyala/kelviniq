
package com.kelviniq.server.controller;

import com.kelviniq.server.request.Lwt;
import com.kelviniq.server.service.LastWillTestamentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/kelviniq/v1"})
public class LastWillTestamentController {

    private final LastWillTestamentService lastWillTestamentService;

    public LastWillTestamentController(LastWillTestamentService lastWillTestamentService) {
        this.lastWillTestamentService = lastWillTestamentService;
    }

    @PostMapping("/{device_id}/lwt")
    public ResponseEntity<String> ingest(
            @PathVariable("device_id") String deviceId,
            @RequestBody Lwt lwt) {
        try {
            String result = lastWillTestamentService.updateDeviceStatus(deviceId, lwt);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}



