package com.kelviniq.server.repository;

import com.kelviniq.server.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LastWillTestamentRepository extends JpaRepository<Device, Long> {
    Device findByDeviceId(String deviceId);
}
