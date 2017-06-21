package com.archsystemsinc.pqrs.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.archsystemsinc.pqrs.model.ProviderHypothesis;
import com.archsystemsinc.pqrs.service.ProviderHypothesisService;

/**
 * This is the Spring Controller Class for Bar and Line Chart Implementation.
 * 
 * @author Murugaraj Kandaswamy
 * @since 6/19/2017
 */
@Controller
public class ProviderHypothesisController {
	
	@Autowired
	private ProviderHypothesisService providerHypothesisService;

	@RequestMapping("/barChart/year/{year}/reportingOption/{reportingOption}")
    public String zipcodes(@PathVariable("year") String year, @PathVariable("reportingOption") String reportingOption, HttpServletRequest request, Principal currentUser, Model model) {
		//model.addAttribute("filter", filter);
		model.addAttribute("year", year);
		model.addAttribute("reportingOption", reportingOption);
		
		final List<ProviderHypothesis> providerHypothesisList = providerHypothesisService.findByYearLookupAndReportingOptionLookup(year, reportingOption);
		
		// Preparing Parameter String Array
		List<String> parameters = new ArrayList<String>();
		List<Double> yesPercents = new ArrayList<Double>();
		List<Double> noPercents = new ArrayList<Double>();
		
		for (ProviderHypothesis providerHypothesis : providerHypothesisList){
			parameters.add("\""+providerHypothesis.getParameterLookup().getParameterName()+"\"");
			yesPercents.add(providerHypothesis.getYesPercent());
			noPercents.add(providerHypothesis.getNoPercent());
		}
		
		model.addAttribute("parameters", parameters);
		model.addAttribute("yesPercents", yesPercents);
		model.addAttribute("noPercents", noPercents);
        return "pqrsparticipatedbarchart";
    }
	
	
	@RequestMapping("/lineChart/parameter/{parameter}")
    public String lineChart(@PathVariable("parameter") String parameterName, HttpServletRequest request, Principal currentUser, Model model) {

		model.addAttribute("parameterName", parameterName);
		
		final List<ProviderHypothesis> providerHypothesisList = providerHypothesisService.findByParameterLookup(parameterName);
		
		List<String> uniqueYears = providerHypothesisService.getUniqueYearsForLineChart();
		List<Double> claimsPercents = new ArrayList<Double>();
		List<Double> ehrPercents = new ArrayList<Double>();
		List<Double> registryPercents = new ArrayList<Double>();
		List<Double> gprowiPercents = new ArrayList<Double>();
		List<Double> qcdrPercents = new ArrayList<Double>();
		
		providerHypothesisService.setRPPercentValue(providerHypothesisList, claimsPercents, ehrPercents, registryPercents, gprowiPercents, qcdrPercents);
		
		model.addAttribute("uniqueYears", uniqueYears);
		model.addAttribute("claimsPercents", claimsPercents);
		model.addAttribute("ehrPercents", ehrPercents);
		model.addAttribute("registryPercents", registryPercents);
		model.addAttribute("gprowiPercents", gprowiPercents);
		model.addAttribute("qcdrPercents", qcdrPercents);
		
        return "pqrsparticipatedlinechart";
    }
	

}