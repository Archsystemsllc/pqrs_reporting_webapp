/**
 * 
 */
package com.archsystemsinc.pqrs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archsystemsinc.pqrs.model.YearLookup;
import com.archsystemsinc.pqrs.repository.YearLookUpRepository;
import com.archsystemsinc.pqrs.service.YearLookUpService;

/**
 * This is the implementation class of the Service interface for year_lookup database table.
 * 
 * @author Murugaraj Kandaswamy
 * @since 6/19/2017
 * 
 */
@Service
public class YearLookUpServiceImpl implements YearLookUpService {

	
	@Autowired
	private YearLookUpRepository yearLookUpRepository;
	
	/* (non-Javadoc)
	 * @see com.archsystemsinc.pqrs.service.YearLookUpservice#findByYearName(java.lang.String)
	 */
	@Override
	public YearLookup findByYearName(String yearName) {
		// TODO Auto-generated method stub
		return yearLookUpRepository.findByYearName(yearName);
	}

}
