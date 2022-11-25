package com.mrhc.api.safe.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mrhc.api.safe.domain.dto.Product;

@RestController
@RequestMapping("/products")

public class Products {
	@GetMapping()
	public ResponseEntity<String> getProductos() {
		List<Product> productList = new ArrayList();
		Product a = new Product(1, "OIL", 1000, 2);
		Product b = new Product(2, "FLOUR", 1200, 12);
		Product c = new Product(3, "PASTA", 800, 5);
		productList.add(a);
		productList.add(b);
		productList.add(c);
		
		//Convert object to JSON
		Gson gson = new Gson();
		String jsonList = gson.toJson(productList);
		
		return new ResponseEntity<String>(jsonList, HttpStatus.OK);
	}
}
