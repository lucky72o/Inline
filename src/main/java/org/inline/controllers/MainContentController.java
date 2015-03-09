package org.inline.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author slavahurmanau Date: 09/03/15 Time: 12:35
 */

@Controller
public class MainContentController {

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(final Model model) {
		return "/inline/content/mainPage";
	}
}
