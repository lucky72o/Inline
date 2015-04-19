package org.inline.test.controller;

import junit.framework.TestCase;
import org.inline.controllers.MainContentController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class MainContentControllerTest extends TestCase {

    @Test
    public void testMain() throws Exception {
        MainContentController controller = new MainContentController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/main")).andExpect(view().name("/inline/content/mainPage"));

    }
}