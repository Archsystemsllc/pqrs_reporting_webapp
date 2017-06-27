package com.archsystemsinc.pqrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archsystemsinc.pqrs.model.DataAnalysis;
import com.archsystemsinc.pqrs.repository.DataAnalaysisRepository;
import com.archsystemsinc.pqrs.service.DataAnalysisService;

@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {
	
	@Autowired
	private DataAnalaysisRepository dataAnalaysisRepository;

	@Override
	public List<DataAnalysis> findAll() {
		return dataAnalaysisRepository.findAll();
	}

}
