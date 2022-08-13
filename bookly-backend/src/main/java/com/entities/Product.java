package com.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int p_id;
	
	private String pname;

	private String pdesc;
	
	private int prating;
	
	private int pqty;

	private float pprice;

	private String imageUrl;
	private Integer noOfPages;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "v_id")
//	@JsonProperty(access = Access.WRITE_ONLY)
	private Vendor vdr;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name="products_authors",
			joinColumns = {
					@JoinColumn(name = "p_id")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "a_id")
			}
	)
	@ReadOnlyProperty
	private Set<Author> authors = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name="products_categories",
			joinColumns = {
					@JoinColumn(name = "p_id")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "c_id")
			}
	)
	@ReadOnlyProperty
	private Set<Category> categories = new HashSet<>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "publisher_id")
	private Publisher publisher;

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "language_id")
	private Language language;

	@OneToMany(mappedBy = "product")
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<MyOrderProductMapping> orderAssoc;

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public int getPrating() {
		return prating;
	}

	public void setPrating(int prating) {
		this.prating = prating;
	}

	public int getPqty() {
		return pqty;
	}

	public void setPqty(int pqty) {
		this.pqty = pqty;
	}

	public float getPprice() {
		return pprice;
	}

	public void setPprice(float pprice) {
		this.pprice = pprice;
	}

	public Vendor getVdr() {
		return vdr;
	}

	public void setVdr(Vendor vdr) {
		this.vdr = vdr;
	}

	public List<MyOrderProductMapping> getOrderAssoc() {
		return orderAssoc;
	}

	public void setOrderAssoc(List<MyOrderProductMapping> orderAssoc) {
		this.orderAssoc = orderAssoc;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public void addAuthors(Author author) {
		this.authors.add(author);
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public void addCategories(Category category) {
		this.categories.add(category);
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Integer getNoOfPages() {
		return noOfPages;
	}

	public void setNoOfPages(Integer noOfPages) {
		this.noOfPages = noOfPages;
	}
}
