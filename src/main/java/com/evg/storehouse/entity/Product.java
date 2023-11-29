package com.evg.storehouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(schema = "storehouse", name = "product")
@EqualsAndHashCode(callSuper = true)
public class Product extends EntityBase<Long> {

    @Column(name = "NAME", columnDefinition = "VARCHAR(50)")
    private String name;
    @Column(name = "DESCRIPTION", columnDefinition = "text")
    private String description;
    @Column(name = "COST", columnDefinition = "decimal")
    private BigDecimal cost;
    @Column(name = "COUNT", columnDefinition = "BIGINT")
    private Long count;

}
