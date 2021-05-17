/**
 * 
 */
package com.chochae.afes.control;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author Isco
 *
 */
@Controller
public class EngineController {
	private static final Logger logger = LoggerFactory.getLogger(EngineController.class);
	
	@RequestMapping(value = "/controller", method = RequestMethod.GET)
	public String sayHello(Locale locale, Model model) {
		
		return "/admin/controller";
	}	
}
