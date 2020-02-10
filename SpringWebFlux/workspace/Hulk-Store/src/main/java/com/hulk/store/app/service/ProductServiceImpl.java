package com.hulk.store.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hulk.store.app.entity.ProductEntity;
import com.hulk.store.app.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {
	private boolean sold = false;
	private boolean bought = false;
	
	@Autowired
	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}


	@Override
	public Iterable<ProductEntity> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Optional<ProductEntity> getById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public ProductEntity save(ProductEntity productEntity) {
		return productRepository.save(productEntity);
	}

	@Override
	public HttpStatus delete(Long id) {
		Optional<ProductEntity> product =	productRepository.findById(id);
	 	if(!product.isEmpty()) {
	 		product.ifPresent(productResult->{
	 			productRepository.delete(productResult);
	 		});
	 		return HttpStatus.ACCEPTED;
	 		
	 	}
		return HttpStatus.NOT_FOUND;
	}

	@Override
	public HttpStatus update(Long id, ProductEntity productEntity) {
		Optional<ProductEntity> product =	productRepository.findById(id);
		if(!product.isEmpty()) {
			product.ifPresent(productResult->{
				productEntity.setId(id);
				productRepository.save(productEntity);
	 		});
	 		return HttpStatus.ACCEPTED;
	 		
	 	}
		return HttpStatus.NOT_FOUND;
	}

	@Override
	public boolean sale(Long id ,ProductEntity productEntity) {
		sold = false;
		Optional<ProductEntity> product =	productRepository.findById(id);
		if(!product.isEmpty()) {
			product.ifPresent(productResult->{
				int existingAmount = productResult.getQuantity();
				if(existingAmount >= productEntity.getQuantity()) {
					int totalQuantity = existingAmount-productEntity.getQuantity();
					productEntity.setQuantity(totalQuantity);
					productEntity.setId(id);
					productEntity.setName(productResult.getName());
					productEntity.setPrice(productResult.getPrice());
					productRepository.save(productEntity);
					sold = true;
				}
				
	 		});
			
	 	}
		return sold;
		
	}

	@Override
	public boolean buy(Long id, ProductEntity productEntity) {
		bought = false;
		Optional<ProductEntity> product =	productRepository.findById(id);
		if(!product.isEmpty()) {
			product.ifPresent(productResult->{
				int existingAmount = productResult.getQuantity();
					int totalQuantity = existingAmount+productEntity.getQuantity();
					productEntity.setQuantity(totalQuantity);
					productEntity.setId(id);
					productEntity.setName(productResult.getName());
					productEntity.setPrice(productResult.getPrice());
					productRepository.save(productEntity);
					bought = true;
				
	 		});
			
	 	}
		return bought;
	}

}
