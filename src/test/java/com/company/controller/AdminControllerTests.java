package com.company.controller;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.View;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class AdminControllerTests {

    @Mock
    private View view;

    @Mock
    private Model model;

    @InjectMocks
    LoginController loginController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getLoginPage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("/login"))
                .andExpect(content().string(containsString("Login")));
    }

    @Test
    public void successUserLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost/login")
                .param("username", "user")
                .param("password", "password")).andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    public void successAdminLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost/login")
                .param("username", "admin")
                .param("password", "admin")).andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    public void wrongCredentials() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost/login")
                .param("username", "user1")
                .param("password", "user2")).andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }
}
