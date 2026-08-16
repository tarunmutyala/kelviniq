package com.kelviniq.server.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

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
@Table(name = "readings")
public class Reading {

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

    @Column(nullable = false, precision = 6, scale = 3)
    private BigDecimal celsius;

    @Column(name = "device_ts", nullable = false)
    private OffsetDateTime deviceTs;

    @Column(name = "server_ts", nullable = false)
    private OffsetDateTime serverTs;

    @Column(name = "clock_suspect", nullable = false)
    private Boolean clockSuspect = false;

    public Reading() {
    }

    public Reading(Org org, Device device, String channel, BigDecimal celsius,
                  OffsetDateTime deviceTs, OffsetDateTime serverTs, Boolean clockSuspect) {
        this.org = org;
        this.device = device;
        this.channel = channel;
        this.celsius = celsius;
        this.deviceTs = deviceTs;
        this.serverTs = serverTs;
        this.clockSuspect = clockSuspect;
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

    public BigDecimal getCelsius() {
        return celsius;
    }

    public void setCelsius(BigDecimal celsius) {
        this.celsius = celsius;
    }

    public OffsetDateTime getDeviceTs() {
        return deviceTs;
    }

    public void setDeviceTs(OffsetDateTime deviceTs) {
        this.deviceTs = deviceTs;
    }

    public OffsetDateTime getServerTs() {
        return serverTs;
    }

    public void setServerTs(OffsetDateTime serverTs) {
        this.serverTs = serverTs;
    }

    public Boolean getClockSuspect() {
        return clockSuspect;
    }

    public void setClockSuspect(Boolean clockSuspect) {
        this.clockSuspect = clockSuspect;
    }
}
