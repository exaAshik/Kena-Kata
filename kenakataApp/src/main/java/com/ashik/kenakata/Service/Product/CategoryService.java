package com.ashik.kenakata.Service.Product;

import com.ashik.kenakata.Entity.ProductCategory;

import java.util.List;

public interface CategoryService {

    ProductCategory createProductCategory(ProductCategory productCategory);

    ProductCategory getProductCategoryById(Long id);

    List<ProductCategory> getAllProductCategories();

    ProductCategory updateProductCategory(Long id, ProductCategory productCategory);

    void deleteProductCategory(Long id);
}
