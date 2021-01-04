package com.company.controller;

import com.company.service.ProductService;
import com.company.service.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class HomeControllerTests {

    @InjectMocks
    CustomErrorController customErrorController;

    @InjectMocks
    HomeController homeController;

    @InjectMocks
    ProductServiceImpl productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Error when access denied")
    public void login() {
        assertEquals("/error", customErrorController.error().getViewName());
    }

    @Test
    @DisplayName("Home page")
    public void home() {
        MockitoAnnotations.initMocks(productService);
        MockitoAnnotations.initMocks(homeController);
        ModelAndView modelAndView = homeController.home(Optional.of(1));
        String viewName = modelAndView.getViewName();
        assertEquals("home", viewName);
    }


}
