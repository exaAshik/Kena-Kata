package com.ashik.kenakata.Service.Product;

import com.ashik.kenakata.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    Product findProductById(Long id);

    List<Product> findAllProducts();

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    Page<Product> searchProducts(String name, Long categoryId,String sortDirection, Pageable pageable);

}
