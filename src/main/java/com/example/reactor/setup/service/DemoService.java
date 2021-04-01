package com.example.reactor.setup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reactor.setup.domain.Product;
import com.example.reactor.setup.repository.DemoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DemoService {

	@Autowired
	private DemoRepository repository;

	public Flux<Product> getAll() {
		return repository.findAll();
	}

	public Mono<Product> getById(int id) {
		return repository.findById(id);
	}

	public Mono<Product> create(final Product id) {
		return repository.save(id);
	}

	public Mono<Product> update(int id, final Mono<Product> product) {
		return repository.findById(id).flatMap(p -> product.map(u -> {
			///p.setName(u.getName());
			return p;
		})).flatMap(p -> repository.save(p));
	}

	public Mono<Void> delete(final int id) {
		return repository.deleteById(id);
	}

}
