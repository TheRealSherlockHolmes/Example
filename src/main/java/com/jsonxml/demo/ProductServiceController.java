package com.jsonxml.demo;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jsonxml.demo.Product;

@RestController
public class ProductServiceController {
	private static Map<Integer, Product> productRepo = new HashMap<>();
	static {
		Product honey = new Product();
		honey.setId(1);
		honey.setName("Honey");
		productRepo.put(honey.getId(), honey);

		Product almond = new Product();
		almond.setId(2);
		almond.setName("Almond");
		productRepo.put(almond.getId(), almond);
	}


	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) { 
		productRepo.remove(id);
		return new ResponseEntity<>("Product is deleted successsfully-json", HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) { 
		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
		return new ResponseEntity<>("Product is updated successsfully-json", HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<Object> getProductJson(@PathVariable("id") Integer id) { 
		return new ResponseEntity<>(productRepo.get(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		productRepo.put(product.getId(), product);
		return new ResponseEntity<>("Product is created successfully-json", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/products", produces="application/json")
	public ResponseEntity<Object> getProduct() {
		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}

	//for xml format

	@RequestMapping(value = "/productsxml/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletexml(@PathVariable("id") Integer id) { 
		productRepo.remove(id);
		return new ResponseEntity<>("Product is deleted successsfully-xml", HttpStatus.OK);
	}

	@RequestMapping(value = "/productsxml/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProductxml(@PathVariable("id") Integer id, @RequestBody Product product) { 
		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
		return new ResponseEntity<>("Product is updated successsfully-xml", HttpStatus.OK);
	}

	@RequestMapping(value = "/productsxml", method = RequestMethod.POST,consumes = "application/xml")
	public ResponseEntity<Object> createProductxml(@RequestBody Product product) {
		productRepo.put(product.getId(), product);
		return new ResponseEntity<>("Product is created successfully-xml", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/productsxml", produces="application/xml")
	public ResponseEntity<Object> getProductxml() {
		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}
	@RequestMapping(value = "/productsxml/{id}", method = RequestMethod.GET, produces="application/xml")
	public ResponseEntity<Object> getProductXml(@PathVariable("id") Integer id) { 

		return new ResponseEntity<>(productRepo.get(id), HttpStatus.OK);
	}
}
