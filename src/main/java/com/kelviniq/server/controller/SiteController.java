package com.kelviniq.server.controller;

import com.kelviniq.server.request.SiteRequest;
import com.kelviniq.server.response.SiteResponse;
import com.kelviniq.server.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kelviniq/v1")
public class SiteController {

    @Autowired
    private SiteService siteService;

    @PostMapping("/site")
    public ResponseEntity<SiteResponse> createSite(@RequestBody SiteRequest request) {
        try {
            return ResponseEntity.ok(siteService.createSite(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
