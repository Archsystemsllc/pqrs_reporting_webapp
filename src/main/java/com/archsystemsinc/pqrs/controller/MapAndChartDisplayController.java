/**
 * 
 */
package com.archsystemsinc.pqrs.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This is the Spring Controller Class for Hypothesis Screen(Map and Chart) Functionality
 * 
 * @author Murugaraj Kandaswamy
 * @since 6/19/2017
 */
@Controller
public class MapAndChartDisplayController {

	@RequestMapping("/mapandchartdisplay/hypothesis/{hypothesis}")
	public String displayMapAndChartScreen(@PathVariable("hypothesis") String hypothesis, HttpServletRequest request, Principal currentUser, Model model ) {
		model.addAttribute("hypothesis", hypothesis);
		return "mapandchartdisplay";
		
	}
	
	
}
