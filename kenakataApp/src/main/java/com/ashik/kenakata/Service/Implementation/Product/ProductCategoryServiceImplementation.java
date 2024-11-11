package com.ashik.kenakata.Service.Implementation.Product;

import com.ashik.kenakata.Entity.ProductCategory;
import com.ashik.kenakata.Exception.ResourceNotFoundException;
import com.ashik.kenakata.Repository.ProductCategoryRepository;
import com.ashik.kenakata.Repository.ProductRepository;
import com.ashik.kenakata.Service.Product.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImplementation implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory createProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory getProductCategoryById(Long id) {
        return productCategoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Category not found with id " + id + " or is deleted."));
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAllByIsDeletedFalse();
    }

    @Override
    public ProductCategory updateProductCategory(Long id, ProductCategory productCategory) {
        ProductCategory existingCategory = productCategoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Category not found with id " + id + " or is deleted."));

        existingCategory.setName(productCategory.getName());
        existingCategory.setDescription(productCategory.getDescription());

        return productCategoryRepository.save(existingCategory);    }

    @Override
    public void deleteProductCategory(Long id) {
        ProductCategory existingCategory = productCategoryRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Category not found with id " + id + " or is already deleted."));

        existingCategory.setDeleted(true);

        productCategoryRepository.save(existingCategory);
    }
}
