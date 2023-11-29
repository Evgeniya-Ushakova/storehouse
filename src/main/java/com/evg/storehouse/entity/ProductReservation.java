package com.evg.storehouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "storehouse", name = "product_reservation")
@EqualsAndHashCode(callSuper = true)
public class ProductReservation extends EntityBase<Long> {

    @ManyToOne
    @Column(name = "PRODUCT_ID", columnDefinition = "BIGINT")
    private Product product;

    @Column(name = "ORDER_ID", columnDefinition = "COLUMN_DEFINITION")
    private Long orderId;

    @Column(name = "COUNT", columnDefinition = "NUMERIC")
    private Long count;

    @Enumerated
    @Column(name = "STATUS", columnDefinition = "VARCHAR")
    private ReservationStatus status;

}
