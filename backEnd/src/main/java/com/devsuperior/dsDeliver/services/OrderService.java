package com.devsuperior.dsDeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsDeliver.DTO.OrderDTO;
import com.devsuperior.dsDeliver.DTO.ProductDTO;
import com.devsuperior.dsDeliver.entities.Order;
import com.devsuperior.dsDeliver.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly =true)
	public List<OrderDTO> findall(){		
		List<Order> list = repository.findOrderWithProducts();
		return list.stream().map(x-> new OrderDTO(x)).collect(Collectors.toList());
	}

}
