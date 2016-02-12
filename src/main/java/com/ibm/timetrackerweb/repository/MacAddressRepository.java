package com.ibm.timetrackerweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.timetrackerweb.model.MacAndIp;

public interface MacAddressRepository extends JpaRepository<MacAndIp, Integer> {

}
