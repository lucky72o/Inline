package org.inline.facades;

import org.inline.forms.RegistrationForm;
import org.inline.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component("userFacade")
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private Properties myProperties;

    @Autowired
    private RegistrationService registrationService;

    @Override
    public void createNewUser(RegistrationForm registrationForm) {
        String[] userRoles = myProperties.getProperty("default.create.user.roles").split(",");
        List<String> roles = Arrays.asList(userRoles);

        registrationService.createNewUser(registrationForm, roles);
    }
}
