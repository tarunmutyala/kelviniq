package com.kelviniq.server.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "thresholds")
public class Threshold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", nullable = false)
    private Org org;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", referencedColumnName = "device_id", nullable = false)
    private Device device;

    @Column(nullable = false)
    private String channel;

    @Column(name = "min_c", precision = 6, scale = 3)
    private BigDecimal minC;

    @Column(name = "max_c", precision = 6, scale = 3)
    private BigDecimal maxC;

    @Column(name = "grace_seconds", nullable = false)
    private Integer graceSeconds = 900;

    @Column(nullable = false)
    private Boolean active = true;

    public Threshold() {
    }

    public Threshold(Org org, Device device, String channel, BigDecimal minC, BigDecimal maxC,
                     Integer graceSeconds, Boolean active) {
        this.org = org;
        this.device = device;
        this.channel = channel;
        this.minC = minC;
        this.maxC = maxC;
        this.graceSeconds = graceSeconds;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public BigDecimal getMinC() {
        return minC;
    }

    public void setMinC(BigDecimal minC) {
        this.minC = minC;
    }

    public BigDecimal getMaxC() {
        return maxC;
    }

    public void setMaxC(BigDecimal maxC) {
        this.maxC = maxC;
    }

    public Integer getGraceSeconds() {
        return graceSeconds;
    }

    public void setGraceSeconds(Integer graceSeconds) {
        this.graceSeconds = graceSeconds;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
