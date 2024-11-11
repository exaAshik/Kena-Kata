package com.ashik.kenakata.Service.Implementation.Product;

import com.ashik.kenakata.Entity.Product;
import com.ashik.kenakata.Exception.ResourceNotFoundException;
import com.ashik.kenakata.Repository.ProductRepository;
import com.ashik.kenakata.Service.Product.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) {

        return productRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    @Override
    public List<Product> findAllProducts() {

        return productRepository.findAllByIsDeletedFalse();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStockQuantity(product.getStockQuantity());
        existingProduct.setTotalSale(product.getTotalSale());
        existingProduct.setDiscountPercentage(product.getDiscountPercentage());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setReviews(product.getReviews());
        existingProduct.setImages(product.getImages());
        existingProduct.setSeller(product.getSeller());

        return productRepository.save(existingProduct);    }

    @Override
    public void deleteProduct(Long id) {
        Product existingProduct = productRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        existingProduct.setDeleted(true);
        productRepository.save(existingProduct);
    }

    @Override
    public Page<Product> searchProducts(String name, Long categoryId,String sortDirection, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> productRoot = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(productRoot.get("name")), "%" + name.toLowerCase() + "%"));
        }

        if (categoryId != null) {
            predicates.add(cb.equal(productRoot.get("category").get("id"), categoryId));
        }

        predicates.add(cb.isFalse(productRoot.get("isDeleted")));

        cq.where(predicates.toArray(new Predicate[0]));

        if ("desc".equalsIgnoreCase(sortDirection)) {
            cq.orderBy(cb.desc(productRoot.get("price")));
        } else {
            cq.orderBy(cb.asc(productRoot.get("price")));
        }
        TypedQuery<Product> query = entityManager.createQuery(cq);
        long total = query.getResultList().size();

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Product> products = query.getResultList();

        return new PageImpl<>(products, pageable, total);
    }
}
