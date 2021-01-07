package com.devsuperior.dsDeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsDeliver.DTO.OrderDTO;
import com.devsuperior.dsDeliver.DTO.ProductDTO;
import com.devsuperior.dsDeliver.entities.Order;
import com.devsuperior.dsDeliver.entities.OrderStatus;
import com.devsuperior.dsDeliver.entities.Product;
import com.devsuperior.dsDeliver.repositories.OrderRepository;
import com.devsuperior.dsDeliver.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public List<OrderDTO> findall() {
		List<Order> list = repository.findOrderWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}

	@Transactional
	public OrderDTO insert(OrderDTO dto){
		
		Order order = new Order(null, dto.getAddress(),dto.getLatitude(), dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
		for(ProductDTO p :dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		
		order =repository.save(order);
		return new OrderDTO(order);
		
		}

}
