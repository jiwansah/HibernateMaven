package com.jcg.hibernate.maven.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue
	private long id;
    private String name;
    private String description;
    private float price;
 
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
 
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ProductDetail productDetail;
    
    
    public Product() {
    }
 
    public Product(String name, String description, float price,
            Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
 
    public long getId() {
        return id;
    }
 
    public Category getCategory() {
        return category;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", category=" + category + ", productDetail=" + productDetail + "]";
	}

	
    
}
