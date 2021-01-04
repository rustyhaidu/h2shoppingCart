package com.company.repository;

import com.company.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class AccountTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void getUserName() {
        User user = userRepository.findByUsername("johndoe").get();
        assertEquals("johndoe", user.getUsername());
    }

    @Test
    public void getUserLastName() {
        User user = userRepository.findByUsername("johndoe").get();
        assertEquals("Doe", user.getLastName());
    }

    @Test
    public void getManagerUserRole() {
        User user = userRepository.findByUsername("user").get();
        assertEquals("ROLE_ADMIN", (user.getRoles().iterator().next().getRole()));
    }

    @Test
    public void getEmployeeUserRole() {
        User user = userRepository.findByUsername("johndoe").get();
        assertEquals("ROLE_USER", (user.getRoles().iterator().next().getRole()));
    }
}
