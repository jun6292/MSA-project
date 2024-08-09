package com.sparta.msa_exam.order.entity;

import com.sparta.msa_exam.order.dto.OrderUpdateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderProduct> productIds;

    @Builder
    public Order(String name, List<OrderProduct> productIds) {
        this.name = name;
        this.productIds = productIds;
    }

    public void updateOrder(Long productId) {
        this.productIds.add(OrderProduct.builder()
                .productId(productId)
                .build());
    }
}
