package com.hulk.store.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductEntity {

		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;
		
		private String name;
		
		private Double price;
		
		private int quantity;
		
		public ProductEntity() {
			
		}

		public ProductEntity(String name, Double price, int quantity) {
			this.name = name;
			this.price = price;
			this.quantity = quantity;
		}

	public ProductEntity(int quantity) {
			this.quantity = quantity;
	}

	public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		
	  
}
