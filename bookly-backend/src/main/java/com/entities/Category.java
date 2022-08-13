package com.entities;

import javax.persistence.*;
import java.util.Set;

@Table
@Entity
public class Category {

	@Id
	@GeneratedValue
	private int c_id;

	private String c_name;

	@ManyToMany(mappedBy = "categories", cascade = { CascadeType.ALL })
	private Set<Product> products;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String c_name) {
		super();
		this.c_name = c_name;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	@Override
	public String toString() {
		return "Category [c_id=" + c_id  + "]";
	}
	
	 
}

