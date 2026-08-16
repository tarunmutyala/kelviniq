package com.kelviniq.server.controller;

import com.kelviniq.server.request.OrgRequest;
import com.kelviniq.server.response.OrgResponse;
import com.kelviniq.server.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kelviniq/v1/org")
public class OrgController {
@Autowired
    private  OrgService orgService;

    @PostMapping
    public ResponseEntity<OrgResponse> createOrg(@RequestBody OrgRequest request) {
        try {
            return ResponseEntity.ok(orgService.createOrg(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
