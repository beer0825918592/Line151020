package com.iphayao.linebot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	@GetMapping("/{id}")
	public ResponseEntity<Product> findProductById(@PathVariable Long id) {
		
		// TODO get product by id from DB 
		
		return ResponseEntity.ok().body(new Product(1, "Jackfruit", "Fruit"));
	}
	
	@PostMapping
	public ResponseEntity<ProductResponse> saveProduct(@RequestBody Product product) {
		
		// TODO save product to DB
		
		return ResponseEntity.ok().body(new ProductResponse("00", "Success"));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		
		// TODO update product to DB
		
		return ResponseEntity.ok().body(new ProductResponse("00", "Success"));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long id) {
		
		// TODO delete product by id from DB
		
		return ResponseEntity.ok().body(new ProductResponse("00", "Success")); 
	}
}
