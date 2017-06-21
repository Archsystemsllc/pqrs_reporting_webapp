package com.archsystemsinc.pqrs;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.archsystemsinc.pqrs.model.ProviderHypothesis;
import com.archsystemsinc.pqrs.service.ProviderHypothesisService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@Transactional
@TransactionConfiguration
public class BarChartImplTest {

	@Autowired
	ProviderHypothesisService providerHypothesisService;

	String year = null;
	String reportingOption = null;
    @Before
    public void init() {
    	year = "2013";
    	reportingOption = "QCDR";
    }
	
    @Test
    public void testProviderHypothesis() {
    	
    	try {
    		
    		List<ProviderHypothesis> providerHypothesisList =  providerHypothesisService.findByYearLookupAndReportingOptionLookup(year, reportingOption);
    		
    		for (ProviderHypothesis providerHypo : providerHypothesisList) {
    			//System.out.println("providerHypo.getParameter()"+providerHypo.getParameter());
    			System.out.println("providerHypo.getYesPercent()"+providerHypo.getYesPercent());
    			System.out.println("providerHypo.getNoPercent()"+providerHypo.getNoPercent());
    		}
    		
    		assert(true);
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		assert(false);
    	}

    }
    

    
}
