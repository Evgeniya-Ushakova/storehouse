package com.evg.storehouse.repository;

import com.evg.storehouse.entity.Product;
import com.evg.storehouse.enums.ErrorMessageCode;
import com.evg.storehouse.exception.DataNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    default Product findByIdOrElseThrow(Long productId) {
        return findById(productId).orElseThrow(() -> new DataNotFoundException(ErrorMessageCode.DATA_NOT_FOUND.getCode(),
                String.format("Product with id = %s was not found", productId)));
    }

}
