package com.kelviniq.server.service;

import com.kelviniq.server.entity.Org;
import com.kelviniq.server.entity.Site;
import com.kelviniq.server.repository.OrgRepository;
import com.kelviniq.server.repository.SiteRepository;
import com.kelviniq.server.request.SiteRequest;
import com.kelviniq.server.response.SiteResponse;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class SiteService {

    private final SiteRepository siteRepository;
    private final OrgRepository orgRepository;

    public SiteService(SiteRepository siteRepository, OrgRepository orgRepository) {
        this.siteRepository = siteRepository;
        this.orgRepository = orgRepository;
    }

    public SiteResponse createSite(SiteRequest request) {
        if (request == null || request.getOrgId() == null) {
            throw new IllegalArgumentException("Org id is required");
        }
        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("Site name is required");
        }

        Org org = orgRepository.findById(request.getOrgId())
                .orElseThrow(() -> new IllegalArgumentException("Org not found"));

        String timezone = (request.getTimezone() == null || request.getTimezone().isBlank()) ? "UTC" : request.getTimezone();

        Site site = new Site();
        site.setOrg(org);
        site.setName(request.getName());
        site.setTimezone(timezone);

        Site saved = siteRepository.save(site);
        return new SiteResponse(saved.getId(), saved.getOrg().getId(), saved.getName(), saved.getTimezone());
    }
}
