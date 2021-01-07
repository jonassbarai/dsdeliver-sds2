package com.devsuperior.dsDeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsDeliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
