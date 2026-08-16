package com.kelviniq.server.service;

import com.kelviniq.server.entity.Org;
import com.kelviniq.server.repository.OrgRepository;
import com.kelviniq.server.request.OrgRequest;
import com.kelviniq.server.response.OrgResponse;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class OrgService {

    private final OrgRepository orgRepository;

    public OrgService(OrgRepository orgRepository) {
        this.orgRepository = orgRepository;
    }

    public OrgResponse createOrg(OrgRequest request) {
        if (request == null || request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("Org name is required");
        }

        Org org = new Org();
        org.setName(request.getName());
        org.setCreatedAt(OffsetDateTime.now());

        Org saved = orgRepository.save(org);
        return new OrgResponse(saved.getId(), saved.getName());
    }
}
