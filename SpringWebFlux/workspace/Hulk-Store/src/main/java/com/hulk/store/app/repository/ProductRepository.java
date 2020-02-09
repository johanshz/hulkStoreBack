package com.hulk.store.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hulk.store.app.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

}
