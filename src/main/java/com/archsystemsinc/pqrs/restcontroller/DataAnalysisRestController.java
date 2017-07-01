package com.archsystemsinc.pqrs.restcontroller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.archsystemsinc.pqrs.model.DataAnalysis;
import com.archsystemsinc.pqrs.service.DataAnalysisService;
import com.archsystemsinc.pqrs.service.SubDataAnalysisService;

/**
 * This is the Spring Rest Controller Class for the Hypothesis Screen.
 * 
 * @author Grmahun Redda
 * @since 6/26/2017
 */
@RestController
@RequestMapping("/api")
public class DataAnalysisRestController {
	
	@Autowired
	private DataAnalysisService dataAnalysisService;
	
	@Autowired
	private SubDataAnalysisService subDataAnalysisService;

	@RequestMapping(value = "/dataanalysis", method = RequestMethod.GET)
    public List<DataAnalysis> barChartDisplay(HttpServletRequest request, Principal currentUser) {		
		
		final List<DataAnalysis> dataAnalysisList = dataAnalysisService.findAll();
        return dataAnalysisList;
    }

}