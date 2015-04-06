package com.jamfsoftware.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jamfsoftware.event.FindEnvironment;

@Controller
public class CaspersController {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(CaspersController.class);
	
	@RequestMapping(value = "/caspers", method = RequestMethod.GET)
	public String caspers(ModelMap model) {
		model.addAttribute("tomcats", FindEnvironment.populateEnvironment(FindEnvironment.tomcatInstances));
		logger.debug("Display Caspers");
		return "caspers";
	}
	
	@RequestMapping(value = "/caspers", method = RequestMethod.POST)
	public String caspersConfig(ModelMap model) {
		
		
		return "caspers";
	}
}
