package com.hulk.store.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hulk.store.app.entity.ProductEntity;
import com.hulk.store.app.service.ProductService;


@RestController
@RequestMapping("/hulkstore/product")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public Iterable<ProductEntity> getAllProduct(){
		return productService.getAll();
	}
	@GetMapping("/{id}")
	public Optional<ProductEntity> getByIdProduct(@PathVariable Long id){
		return productService.getById(id);
	}
	@PostMapping
	public ProductEntity saveProduct (@RequestBody ProductEntity productEntity) {
		return productService.save(productEntity);
	}
	@DeleteMapping("/{id}")
	public HttpStatus deleteProduct(@PathVariable Long id) {
		return productService.delete(id);
	}
	@PutMapping("/{id}")
	public HttpStatus editProduct(@PathVariable Long id ,@RequestBody ProductEntity productEntity) {
		return productService.update(id, productEntity);
	}
	@PostMapping("sale/{id}")
	public boolean saleProducts(@PathVariable Long id ,@RequestBody ProductEntity productEntity) {
		return productService.sale(id, productEntity);
	}
	@PostMapping("buy/{id}")
	public boolean buyProducts(@PathVariable Long id ,@RequestBody ProductEntity productEntity) {
		return productService.buy(id, productEntity);
	}
	

}
