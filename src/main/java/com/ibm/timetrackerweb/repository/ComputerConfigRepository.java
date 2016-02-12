package com.ibm.timetrackerweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.timetrackerweb.model.ComputerConfig;

public interface ComputerConfigRepository extends JpaRepository<ComputerConfig, Integer> {
	
	List<ComputerConfig> findByComputerNameAndDateOfData(String computerName, String currentDate);
	
	List<ComputerConfig> findByDateOfData(String currentDate);
	
	List<ComputerConfig> findByComputerName(String computerName);

}
