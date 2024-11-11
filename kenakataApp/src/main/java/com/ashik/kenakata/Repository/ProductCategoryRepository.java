package com.ashik.kenakata.Repository;

import com.ashik.kenakata.Entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {

    Optional<ProductCategory> findByIdAndIsDeletedFalse(Long id);

    List<ProductCategory> findAllByIsDeletedFalse();

}
