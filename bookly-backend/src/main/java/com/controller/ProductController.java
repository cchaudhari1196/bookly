package com.controller;

import com.entities.Product;
import com.service.FilesStorageService;
import com.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RequestMapping("/product")
@RestController
public class ProductController {
	@Autowired
	ProductService pservice;

	@Autowired
	FilesStorageService storageService;
	
	@GetMapping("/getallproducts")
	public List<Product> getAllProducts() {
		return pservice.getAllProducts();
	}

	@GetMapping("/get")
	public ResponseEntity get() {
		return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/getbycid")
	public List<Product> getByCategoryId(@RequestParam("c_id") int c_id) {
		return pservice.getByCategoryId(c_id);
	}

	@PostMapping("/searchbykeyword")
	public List<Product> searchbykeyword(@RequestBody Product p) {
		return pservice.searchbykeyword(p.getPname(), p.getPdesc());
	}
	@GetMapping("/raw")
	public List<Product> getAllRaw() {
		return pservice.getAllRaw().stream().filter(e -> e.getPqty()>0).collect(Collectors.toList());
	}


	@GetMapping("/search/{data}")
	public List<Product> searchRaw(@PathVariable("data") String data) {
		return pservice.getAllProducts().stream().filter((e) -> {
			return StringUtils.containsIgnoreCase(e.getPname(),data);
		}).collect(Collectors.toList());
	}

	@GetMapping("/stitched")
	public List<Product> getAllStitched() {
		return pservice.getAllStitched().stream().filter(e -> e.getPqty()>0).collect(Collectors.toList());
	}

	@GetMapping("/viewbyvid")
	public List<Product> getByVid(@RequestParam("v_id")int v_id){
		return pservice.getByVid(v_id);
	}


	@PostMapping
	public Integer addproduct(@RequestBody com.models.Product product){
		try {
			return pservice.addproduct(product);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GetMapping("/viewoutofstock")
	public List<Product> viewOutOfStock(@RequestParam("v_id") int v_id)
	{
		return pservice.viewOutOfStock(v_id);
	}
}
