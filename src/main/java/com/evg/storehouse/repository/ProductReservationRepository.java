package com.evg.storehouse.repository;

import com.evg.storehouse.entity.ProductReservation;
import com.evg.storehouse.enums.ErrorMessageCode;
import com.evg.storehouse.exception.DataNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductReservationRepository extends JpaRepository<ProductReservation, Long> {

    boolean existsByProductIdAndOrderId(Long productId, Long orderId);

    Optional<ProductReservation> findByProductIdAndOrderId(Long productId, Long orderId);

    default ProductReservation findByProductIdAndOrderIdOrElseThrow(Long productId, Long orderId) {
        return findByProductIdAndOrderId(productId, orderId)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessageCode.BAD_REQUEST.getCode(),
                        String.format("Reservation with productId = %s and orderId = %s does not exists",
                                productId,
                                orderId)));
    }

}
