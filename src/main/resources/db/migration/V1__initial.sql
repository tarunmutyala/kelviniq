CREATE TABLE orgs (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE sites (
    id BIGSERIAL PRIMARY KEY,
    org_id BIGINT NOT NULL REFERENCES orgs(id),
    name TEXT NOT NULL,
    timezone TEXT NOT NULL DEFAULT 'UTC'
);

CREATE TABLE devices (
    id BIGSERIAL PRIMARY KEY,
    device_id TEXT UNIQUE NOT NULL,
    org_id BIGINT REFERENCES orgs(id),
    site_id BIGINT REFERENCES sites(id),
    label TEXT,
    claim_code TEXT UNIQUE NOT NULL,
    secret_hash TEXT NOT NULL,
    fw_version TEXT,
    report_interval_s INT NOT NULL DEFAULT 900,
    last_seen_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE readings (
    id BIGSERIAL PRIMARY KEY,
    org_id BIGINT NOT NULL,
    device_id TEXT NOT NULL REFERENCES devices(device_id),
    channel TEXT NOT NULL,
    celsius NUMERIC(6,3) NOT NULL,
    device_ts TIMESTAMPTZ NOT NULL,
    server_ts TIMESTAMPTZ NOT NULL DEFAULT now(),
    clock_suspect BOOLEAN NOT NULL DEFAULT false
);

CREATE INDEX readings_device_ts_brin
    ON readings USING BRIN (server_ts)
    WITH (pages_per_range = 64);

CREATE INDEX readings_device_lookup
    ON readings (device_id, server_ts DESC);

CREATE TABLE thresholds (
    id BIGSERIAL PRIMARY KEY,
    org_id BIGINT NOT NULL REFERENCES orgs(id),
    device_id TEXT NOT NULL REFERENCES devices(device_id),
    channel TEXT NOT NULL,
    min_c NUMERIC(6,3),
    max_c NUMERIC(6,3),
    grace_seconds INT NOT NULL DEFAULT 900,
    active BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE alerts (
    id BIGSERIAL PRIMARY KEY,
    org_id BIGINT NOT NULL REFERENCES orgs(id),
    device_id TEXT NOT NULL REFERENCES devices(device_id),
    kind TEXT NOT NULL,
    opened_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    closed_at TIMESTAMPTZ,
    acknowledged_at TIMESTAMPTZ,
    acknowledged_by BIGINT,
    peak_value NUMERIC(6,3),
    escalation_step INT NOT NULL DEFAULT 0
);

CREATE TABLE audit_log (
    id BIGSERIAL PRIMARY KEY,
    org_id BIGINT NOT NULL,
    actor_id BIGINT,
    action TEXT NOT NULL,
    subject TEXT NOT NULL,
    detail JSONB,
    at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE device_state (
    id BIGSERIAL PRIMARY KEY,
    device_id TEXT NOT NULL UNIQUE REFERENCES devices(device_id),
    state TEXT NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);
