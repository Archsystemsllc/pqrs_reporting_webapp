package com.archsystemsinc.pqrs;

import org.junit.Before;
import org.junit.Test;

public class BarChartImplTest {

    @Before
    public void init() {

    }
	
    @Test
    public void testProviderHypothesis() {
    	try {
    		
/*    		List<ProviderHypothesis> providerHypothesisList =  providerHypothesisService.findByYearLookupAndReportingOptionLookup(year, reportingOption);
    		
    		for (ProviderHypothesis providerHypo : providerHypothesisList) {
    			//System.out.println("providerHypo.getParameter()"+providerHypo.getParameter());
    			System.out.println("providerHypo.getYesPercent()"+providerHypo.getYesPercent());
    			System.out.println("providerHypo.getNoPercent()"+providerHypo.getNoPercent());
    		}*/
    		
    		assert(true);
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		assert(false);
    	}

    }
    
}
