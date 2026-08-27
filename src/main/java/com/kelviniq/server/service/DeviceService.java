package com.kelviniq.server.service;

import com.kelviniq.server.entity.Device;
import com.kelviniq.server.entity.Org;
import com.kelviniq.server.entity.Site;
import com.kelviniq.server.repository.DeviceRepository;
import com.kelviniq.server.repository.OrgRepository;
import com.kelviniq.server.repository.SiteRepository;
import com.kelviniq.server.request.DeviceRequest;
import com.kelviniq.server.response.DeviceResponse;
import com.kelviniq.server.response.OrgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private OrgRepository orgRepository;
    @Autowired
    private SiteRepository siteRepository;

    public DeviceResponse getDeviceByDeviceId(String deviceId) {
        if (deviceId == null || deviceId.isBlank()) {
            throw new IllegalArgumentException("Device id is required");
        }

        Device device = deviceRepository.findByDeviceId(deviceId);
        if (device == null) {
            throw new IllegalArgumentException("Device not found");
        }

        return toResponse(device);
    }

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
        return toResponse(saved);
    }

    private DeviceResponse toResponse(Device device) {
        return new DeviceResponse(
                device.getId(),
                device.getDeviceId(),
                device.getOrg() != null ? new OrgResponse(device.getOrg().getId(), device.getOrg().getName()) : null,
                device.getSite() != null ? device.getSite().getId() : null,
                device.getLabel(),
                device.getClaimCode(),
                device.getFwVersion(),
                device.getReportIntervalS()
        );
    }
}
