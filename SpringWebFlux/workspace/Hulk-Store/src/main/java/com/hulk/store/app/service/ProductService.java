package com.hulk.store.app.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;

import com.hulk.store.app.entity.ProductEntity;



public interface ProductService {
	Iterable<ProductEntity> getAll();
	
	Optional<ProductEntity> getById(Long id);
	
	ProductEntity save(ProductEntity producEntity);
	
	HttpStatus delete(Long id);
	
	HttpStatus update(Long id, ProductEntity producEntity);
	
	boolean sale(Long id ,ProductEntity productEntity);
	
	boolean buy(Long id ,ProductEntity productEntity);

}
