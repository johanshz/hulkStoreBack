package com.hulk.store.app.service;

import com.hulk.store.app.entity.ProductEntity;
import com.hulk.store.app.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ProductServiceTest {

    private ProductService productService;
    ProductEntity product;
    ProductEntity product1;
    ProductEntity product2;
    ProductEntity product3;
    ProductEntity product3Updated;

    @Mock
    private ProductRepository productRepository;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);

        product = new ProductEntity(1);
        product1 = new ProductEntity(
                "Producto 1",
                1.5,
                50
        );

        product2 = new ProductEntity(

                "Producto 2",
                1.6,
                100

        );

        product3 = new ProductEntity(

                "Producto 3",
               1.3,
                10
        );
        product3Updated = new ProductEntity(
                "Producto 3",
                1.7,
                10
        );

    }
    @Test
    void methodGetAllShouldReturnAllProductos() {
        List<ProductEntity> productList = Arrays.asList(product1, product2, product3);
        when(productRepository.findAll()).thenReturn(productList);

        Iterable<ProductEntity> allProduct = productService.getAll();

        assert allProduct.iterator().hasNext();
        verify(productRepository).findAll();

    }
    @Test
    void methodGetByIdShouldReturnProduct2() {

        when(productRepository.findById(2L)).thenReturn(Optional.of(product2));

        Optional<ProductEntity> productById = productService.getById(2L);

        Assert.notNull(productById.orElseThrow(), "El objeto no puede ser null");
        verify(productRepository).findById(2L);
    }
    @Test
    void methodSaveShouldReturnASavedProduct() {
        ProductEntity testProduct = product1;
        when(productRepository.save(testProduct)).thenReturn(testProduct);

        ProductEntity savedItem = productService.save(testProduct);

        Assert.notNull(savedItem, "El objeto no puede ser null");
        verify(productRepository).save(testProduct);
    }
    @Test
    void methodUpdateShouldReturnAnUpdatedProduct() {

        when(productRepository.save(product1)).thenReturn(product1);

        HttpStatus updatedStatus = productService.update(1L, product1);

        assertEquals(HttpStatus.NOT_FOUND, updatedStatus);
        verify(productRepository).findById(1L);
    }
    @Test
    void methodDeleteShouldDeleteProduct() {
        HttpStatus deletedStatus = productService.delete(0L);

        assertEquals(HttpStatus.NOT_FOUND, deletedStatus);
        verify(productRepository).findById(0L);
    }
    @Test
    void methodSaleShouldRemoveQuantityFromStock() {
        when(productRepository.save(product1)).thenReturn(product1);

        boolean reply = productService.sale(1L, product);

        assertEquals(false, reply);
        verify(productRepository).findById(1L);
    }

}
