package com.company.repository;

import com.company.model.Product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class ProductTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void getProductCodeTest() {
        Product product = productRepository.findById(1L).get();
        assertEquals("Pears baby soap for Kids", product.getDescription());
    }

    @Test
    public void getProductPriceTest() {
        Product product = productRepository.findById(1L).get();
        assertEquals(BigDecimal.valueOf(35.75), product.getPrice());
    }

}
