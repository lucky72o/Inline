package org.inline.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author slavahurmanau
 *         Date: 09/03/15
 *         Time: 15:19
 */
@Controller
public class LoginController {

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String main(final Model model) {
		return "/inline/user/registrationPage";
	}
}
