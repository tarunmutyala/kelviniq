package com.kelviniq.server.service;

import com.kelviniq.server.entity.Device;
import com.kelviniq.server.entity.Org;
import com.kelviniq.server.entity.Site;
import com.kelviniq.server.repository.DeviceRepository;
import com.kelviniq.server.repository.OrgRepository;
import com.kelviniq.server.repository.SiteRepository;
import com.kelviniq.server.request.DeviceRequest;
import com.kelviniq.server.response.DeviceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class DeviceService {
    @Autowired
    private  DeviceRepository deviceRepository;
    @Autowired
    private  OrgRepository orgRepository;
    @Autowired
    private  SiteRepository siteRepository;

    public DeviceResponse createDevice(DeviceRequest request) {
        if (request == null || request.getDeviceId() == null || request.getDeviceId().isBlank()) {
            throw new IllegalArgumentException("Device id is required");
        }
        if (request.getOrgId() == null) {
            throw new IllegalArgumentException("Org id is required");
        }
      /*  if (request.getClaimCode() == null || request.getClaimCode().isBlank()) {
            throw new IllegalArgumentException("Claim code is required");
        }
        if (request.getSecretHash() == null || request.getSecretHash().isBlank()) {
            throw new IllegalArgumentException("Secret hash is required");
        }*/

        Org org = orgRepository.findById(request.getOrgId())
                .orElseThrow(() -> new IllegalArgumentException("Org not found"));

        Site site = null;
        if (request.getSiteId() != null) {
            site = siteRepository.findById(request.getSiteId())
                    .orElseThrow(() -> new IllegalArgumentException("Site not found"));
        }

        Device device = new Device();
        device.setDeviceId(request.getDeviceId());
        device.setOrg(org);
        device.setSite(site);
        device.setLabel(request.getLabel());
        device.setClaimCode(request.getClaimCode());
        device.setSecretHash(request.getSecretHash());
        device.setFwVersion(request.getFwVersion());
        device.setReportIntervalS(request.getReportIntervalS() == null ? 900 : request.getReportIntervalS());
        device.setCreatedAt(OffsetDateTime.now());

        Device saved = deviceRepository.save(device);
        return new DeviceResponse(
                saved.getId(),
                saved.getDeviceId(),
                saved.getOrg() != null ? saved.getOrg().getId() : null,
                saved.getSite() != null ? saved.getSite().getId() : null,
                saved.getLabel(),
                saved.getClaimCode(),
                saved.getFwVersion(),
                saved.getReportIntervalS()
        );
    }
}
