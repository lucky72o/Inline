package org.inline.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping(value = "/account")
public class AccountPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String getAccountPage(Model model, Principal principal) {
        model.addAttribute("currentUser", principal.getName());
        return "/inline/user/accountPage";
    }
}
