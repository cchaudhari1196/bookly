package com.service;

import com.entities.Author;
import com.entities.Category;
import com.entities.Product;
import com.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//controller -> service -> repos -> db

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	VendorService vendorService;
	@Autowired
	RegistrationService registrationService;

	public List<Product> getAllProducts() {
		return productRepository.findAll().stream().filter(e -> e.getPqty()>0).collect(Collectors.toList());
	}
	public List<Product> getproducts(int p_id)
	{
		return productRepository.getproducts(p_id);
	}

	public Product save(Product p) {
		return productRepository.save(p);
	}

	public List<Product> searchbykeyword(String pname, String pdesc) {
		// TODO Auto-generated method stub
		return productRepository.searchbykeyword(pname, pdesc);
		
	}
	public List<Product> getProductsByCategory(String category) {
		return productRepository.getByCategoryName(category);
	}

	public List<Product> getProductsByCategoryId(Integer categoryId) {
		return productRepository.getByCategoryId(categoryId);
	}

	public List<Product> getByVid(int v_id) {
		return productRepository.getByVid(v_id);
	}

	public int addproduct(com.models.Product product) throws Exception {
		try{
			Product productEntity = new Product();
			productEntity.setPname(product.getPname());
			productEntity.setPqty(product.getPqty());
			productEntity.setImageUrl(product.getImageUrl());
			productEntity.setPdesc(product.getPdesc());
			productEntity.setPprice(product.getPprice());
			productEntity.setNoOfPages(product.getNoOfPages());
			Set<Author> authors = product.getAuthorIds()
							.stream().map(e->registrationService.getAuthor(e)).collect(Collectors.toSet());
			productEntity.setAuthors(authors);
			Set<Category> categories = product.getCategoryIds()
							.stream().map(e->registrationService.getCategory(e)).collect(Collectors.toSet());
			productEntity.setCategories(categories);
			productEntity.setPublisher(registrationService.getPublisher(product.getPublisherId()));
			productEntity.setLanguage(registrationService.getLangauage(product.getLanguage()));
			productEntity.setVdr(vendorService.getVendor(product.getvId()));
			productRepository.save(productEntity);
			return productEntity.getP_id();
		}
		catch (Exception ex){
			ex.printStackTrace();
			throw new Exception("Adding product failed"+ ex.getMessage());
		}
	}

	public List<Product> viewOutOfStock(int v_id) {
		// TODO Auto-generated method stub
		return productRepository.viewOutOfStock(v_id);
	}
}
