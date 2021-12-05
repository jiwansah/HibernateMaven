package com.jcg.hibernate.maven.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
 
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "PRODUCT_DETAIL")
public class ProductDetail {

	@Id
    @GeneratedValue(generator = "foreigngen")
    @GenericGenerator(strategy = "foreign", name="foreigngen",
            parameters = @Parameter(name = "property", value="product"))
    @Column(name = "PRODUCT_ID")
	private long productId;
	
	@Column(name = "PART_NUMBER")
    private String partNumber;
    private String dimension;
    private float weight;
    private String manufacturer;
    private String origin;
    
    @OneToOne(mappedBy = "productDetail")
    private Product product;
 
    
    
    
    public ProductDetail() {
    }
 
    public long getProductId() {
        return productId;
    }
 
    public String getPartNumber() {
        return partNumber;
    }
 
    public Product getProduct() {
        return product;
    }

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ProductDetail [productId=" + productId + ", partNumber=" + partNumber + ", dimension=" + dimension
				+ ", weight=" + weight + "]";
	}
    
}
