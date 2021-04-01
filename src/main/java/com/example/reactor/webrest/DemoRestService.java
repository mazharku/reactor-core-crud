package com.example.reactor.webrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reactor.setup.domain.Product;
import com.example.reactor.setup.service.DemoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RestController
@RequestMapping(path = "/api/demo")
public class DemoRestService {
 
	@Autowired
	private DemoService service;
	
	@GetMapping()
	public Flux<Product> getAll(){
		return service.getAll();
	}
	
	@PostMapping
    public Mono<Product> save(@RequestBody Mono<Product> product){
        return product.flatMap(service::create);
    }
	
	@GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> getById(@PathVariable int id){
        return service.getById(id)
                                .map(ResponseEntity::ok)
                                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
	@DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable int id){
        return service.delete(id);
    }
	
	
}
