package com.kelviniq.server.request;

import lombok.Data;

import java.util.Date;

@Data
public class SiteRequest{
    public Long orgId;
    public String name;
    public String timezone;
}

