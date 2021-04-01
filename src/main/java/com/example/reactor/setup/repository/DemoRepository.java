package com.example.reactor.setup.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.reactor.setup.domain.Product;

public interface DemoRepository extends ReactiveCrudRepository<Product, Integer> {

}
