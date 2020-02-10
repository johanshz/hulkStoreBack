package com.hulk.store.app.controller;

import com.hulk.store.app.entity.ProductEntity;
import com.hulk.store.app.repository.ProductRepository;
import com.hulk.store.app.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.*;

public class ProductControllerTest {
	
	 @MockBean
	    private String ResourceUrl = "http://localhost:8080/hulkstore/product";
	    private TestRestTemplate testRestTemplate;
	    private ObjectMapper mapper;

	    @BeforeEach
	    void setUp() {
	        testRestTemplate = new TestRestTemplate();
	        mapper = new ObjectMapper();

	    }
	    
	    @Test
	    void methodShouldGetAllProducts() throws JsonProcessingException {
	        ResponseEntity<String> response = testRestTemplate.getForEntity(ResourceUrl, String.class);
	        JsonNode root = mapper.readTree(response.getBody());
	        JsonNode name = root.get(0).path("name");

	        assertNotNull(name.asText());
	        assertEquals(HttpStatus.OK,response.getStatusCode());
	    }
	@Test
	void methodShouldFindById() {
		ProductEntity response = testRestTemplate.getForObject(ResourceUrl + "/2", ProductEntity.class);

		assertNotNull(response.getName());
		assertEquals(response.getName(), "shirt Avenger");
	}
	@Test
	void methodSaveShouldSaveANewProduct() {
		HttpEntity<ProductEntity> request = new HttpEntity<>(new ProductEntity(
				"Avenger",
				1.5,
				20
		));

		ProductEntity response = testRestTemplate.postForObject(ResourceUrl, request, ProductEntity.class);

		assertNotNull(response);
		assertEquals("Avenger", response.getName());
		assertEquals(1.5, response.getPrice());
	}

	@Test
	void methodDeleteShould() {
		testRestTemplate.delete(ResourceUrl + "10");
		ProductEntity response = testRestTemplate.getForObject(ResourceUrl + "10", ProductEntity.class);
		assertNull(response.getName());
		assertNull(response.getPrice());

	}
	@Test
	void methodEditProductShouldUpdateProducto() {
		HttpEntity<ProductEntity> request = new HttpEntity<>(new ProductEntity(
				"pencil DC",
				2.4,
				20
		));

		testRestTemplate.put(ResourceUrl+"/11", request);

		ProductEntity response = testRestTemplate.getForObject(ResourceUrl + "/11", ProductEntity.class);

		assertNotNull(response);
		assertEquals("pencil DC", response.getName());
		assertEquals(2.4, response.getPrice());
	}
	@Test
	void methodSaleShouldUpdateStock() {
		HttpEntity<ProductEntity> request = new HttpEntity<>(new ProductEntity(
				10
		));


		boolean response = testRestTemplate.postForObject(ResourceUrl+"/sale/11", request, boolean.class);

		assertEquals(true,response);
	}
	@Test
	void methodSaleShouldUpdateStockReturnFalse() {
		HttpEntity<ProductEntity> request = new HttpEntity<>(new ProductEntity(
				500
		));
		boolean response = testRestTemplate.postForObject(ResourceUrl+"/sale/1", request, boolean.class);
		assertEquals(false,response);
	}
	@Test
	void methodBuyShouldUpdateStock() {
		HttpEntity<ProductEntity> request = new HttpEntity<>(new ProductEntity(
				10
		));
		boolean response = testRestTemplate.postForObject(ResourceUrl+"/buy/12", request, boolean.class);
		assertEquals(true,response);
	}


}
