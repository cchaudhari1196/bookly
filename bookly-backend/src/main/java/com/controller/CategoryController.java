package com.controller;

import com.entities.Category;
import com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryController
{

	@Autowired
	 private CategoryService categoryservice;
	 
	
	@PostMapping()
	public Category registerCategory(@RequestBody Category category)
	{
		return categoryservice.registerCategory(category);
	
	}//Ok
	
	@GetMapping()
	public List<Category> allCategory()
	{
		return categoryservice.allCategory();
	}//Ok
	
	 
}
