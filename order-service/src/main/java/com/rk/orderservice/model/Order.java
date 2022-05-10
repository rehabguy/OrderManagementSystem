package com.rk.orderservice.model;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

//always use only "import javax.persistence.Id; for @Id", never use anything else, shows error otherwise


@Entity
@Table(name = "t_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemsList;

}
