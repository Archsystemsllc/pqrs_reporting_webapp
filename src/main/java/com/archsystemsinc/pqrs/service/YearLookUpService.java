/**
 * 
 */
package com.archsystemsinc.pqrs.service;

import com.archsystemsinc.pqrs.model.YearLookup;

/**
 * This is the Service interface for year_lookup database table.
 * 
 * @author Murugaraj Kandaswamy
 * @since 6/19/2017
 * 
 */
public interface YearLookUpService {
	
	YearLookup findByYearName(final String yearName);

}
