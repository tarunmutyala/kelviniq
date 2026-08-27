package com.kelviniq.server.repository;

import com.kelviniq.server.entity.Device;
import com.kelviniq.server.entity.DeviceState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceStateRepository extends JpaRepository<DeviceState, Long> {
    DeviceState findByDevice(Device device);
}