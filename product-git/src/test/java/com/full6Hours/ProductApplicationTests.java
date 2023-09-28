package com.full6Hours;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.full6Hours.dto.ProductRequest;
import com.full6Hours.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductApplicationTests {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;
	@Test
	void testCreateProduct() throws Exception {

		String productRequest = getProductRequest();
		mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequest)).andExpect(status().isCreated());

		//verify product is added or not
        Assertions.assertEquals(1, productRepository.findAll().size());

	}

	private String getProductRequest() throws JsonProcessingException {

		ProductRequest productRequest = ProductRequest.builder()
				.name("HBDUAHD")
				.description("hjiieciwoj")
				.price(BigDecimal.valueOf(13000))
				.build();
		return objectMapper.writeValueAsString(productRequest);
	}

}
