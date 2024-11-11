package com.ashik.kenakata.Controller;


import com.ashik.kenakata.Dto.Product.ProductCategoryDto;
import com.ashik.kenakata.Entity.ProductCategory;
import com.ashik.kenakata.Mapper.ProductMapper.ProductCategoryMapper;
import com.ashik.kenakata.Service.Product.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-categories")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createProductCategory(@RequestBody ProductCategoryDto productCategory) {

        ProductCategoryDto productCategoryDto = ProductCategoryMapper.INSTANCE.productCategoryToProductCategoryDto(
                categoryService.createProductCategory(
                        ProductCategoryMapper.INSTANCE.productCategoryDtoToProductCategory(productCategory)
                )
        );

        return new ResponseEntity<>(productCategoryDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductCategoryById(@PathVariable Long id) {

        ProductCategoryDto productCategoryDto = ProductCategoryMapper.INSTANCE.productCategoryToProductCategoryDto(
                categoryService.getProductCategoryById(id)
        );

        return new ResponseEntity<>(productCategoryDto, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductCategory(@PathVariable Long id, @RequestBody ProductCategory productCategory) {

        ProductCategoryDto productCategoryDto = ProductCategoryMapper.INSTANCE.productCategoryToProductCategoryDto(
                categoryService.updateProductCategory(id, productCategory)
        );

        return new ResponseEntity<>(productCategoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable Long id) {

        categoryService.deleteProductCategory(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
