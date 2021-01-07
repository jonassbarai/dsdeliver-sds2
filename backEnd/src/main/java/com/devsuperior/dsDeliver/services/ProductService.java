package com.devsuperior.dsDeliver.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsDeliver.DTO.ProductDTO;
import com.devsuperior.dsDeliver.entities.Product;
import com.devsuperior.dsDeliver.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly =true)
	public List<ProductDTO> findall(){		
		List<Product> list = repository.findAllByOrderByName();
		return list.stream().map(x-> new ProductDTO(x)).collect(Collectors.toList());
	}

}
