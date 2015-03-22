package org.inline.controllers;

import org.inline.exceptions.DuplicateUserException;
import org.inline.facades.UserFacade;
import org.inline.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @author slavahurmanau
 *         Date: 09/03/15
 *         Time: 15:19
 */
@Controller
public class LoginController {

    @Autowired
    private UserFacade userFacade;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String main(final Model model) {
		return "/inline/user/registrationPage";
	}

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(Model model, RegistrationForm form, ServletRequest request) {

//        String challenge = request.getParameter("recaptcha_challenge_field");
//        String response = request.getParameter("recaptcha_response_field");
//        String remoteAddr = request.getRemoteAddr();
//
//        ReCaptchaResponse reCaptchaResponse = reCaptchaService.checkAnswer(remoteAddr, challenge, response);
//
//        if (!reCaptchaResponse.isValid()) {
//            model.addAttribute("registration_message", "captcha_error");
//            model.addAttribute("registrationForm", form);
//            return "pc/content/registration";
//        }

        try {
            userFacade.createNewUser(form);
        } catch (DuplicateUserException e) {                                                                            //TODO fix hardcoded error
            model.addAttribute("registration_message", "error_user_already_exist");
            model.addAttribute("registrationForm", form);
            return "inline/user/registrationPage";
        }

        model.addAttribute("login_message", "msd_successful_registration");
        return "pc/content/login";
    }
}
