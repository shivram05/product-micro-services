package com.order.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "t_order_line_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItems {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "sku_code")
    private String skuCode;

    private BigDecimal price;

    private Integer  quantity;
}
