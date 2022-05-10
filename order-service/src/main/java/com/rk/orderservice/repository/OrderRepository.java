package com.rk.orderservice.repository;

import com.rk.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface OrderRepository extends JpaRepository<Order,Long> {
}
