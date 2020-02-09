package com.hulk.store.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hulk.store.app.entity.ProductEntity;
import com.hulk.store.app.service.ProductService;

@SpringBootApplication
public class HulkStoreApplication implements CommandLineRunner {
	
	@Autowired
	private ProductService productService;
	

	public static void main(String[] args) {
		SpringApplication.run(HulkStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<ProductEntity> products = new ArrayList<ProductEntity>();
		
		products.add(new ProductEntity("Glass Hulk", 2.0, 100));
		products.add(new ProductEntity("shirt Avenger", 3.0, 500));
		products.add(new ProductEntity("pencil DC", 5.0, 300));
		products.add(new ProductEntity("Toys Avenger", 6.0, 10));
		products.add(new ProductEntity("Toys DC", 3.0, 400));
		products.add(new ProductEntity("Toys Marvel", 4.0, 500));
		products.add(new ProductEntity("Shorts DC", 100.0, 100));
		products.add(new ProductEntity("Shorts Marvel", 7.0, 230));
		products.add(new ProductEntity("NoteBooks DC", 2.0, 340));
		products.add(new ProductEntity("NoteBooks Marvel", 3.0, 530));
		products.add(new ProductEntity("Sticker Marvel ", 4.0, 169));
		products.add(new ProductEntity("Sticker DC", 40.0, 78));
		products.add(new ProductEntity("Sticker Avenger", 60.0, 81));
		products.add(new ProductEntity("Sticker League of Justice", 79.0, 111));
		products.add(new ProductEntity("NoteBooks League of Justice", 89.0, 115));
		products.add(new ProductEntity("Shorts League of Justice", 98.0, 120));
		products.add(new ProductEntity("Toys League of Justice", 23.0, 121));
	
		for(ProductEntity product : products ) {
			productService.save(product);
		}
		
	}

}
