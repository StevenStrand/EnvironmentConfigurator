package com.jamfsoftware.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {
 
	private static final String VIEW_INDEX = "index";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);
 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		model.addAttribute("message", "Welcome");
		logger.debug("[welcome] counter : {}");
		return VIEW_INDEX;
 
	}
 
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {
 
		model.addAttribute("message", "Welcome " + name);
		return VIEW_INDEX;
 
	}
 
	@RequestMapping(value = "/CA/JAMFCRLServlet", method = RequestMethod.GET)
	public ModelAndView forwardCRL() {
		return new ModelAndView("redirect:" + "/963/CA/JAMFCRLServlet");
	}
}
