package com.boot.yhy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(name = "productcode")
	private String productCode;
	
	@Column(name = "productname")
	private String productName;
	
	@Column
	private Long leader;
	
	@Column
	private Long status;
	
	@Column
	private String description;
	
	@Column
	private Date date;

	public Product() {
		super();
	}

	public Product(Long id, String productCode, String productName,
			Long leader, Long status, String description) {
		super();
		this.id = id;
		this.productCode = productCode;
		this.productName = productName;
		this.leader = leader;
		this.status = status;
		this.description = description;
	}

	public Product(Long id, String productCode, String productName,
			Long leader, Long status, String description, Date date) {
		super();
		this.id = id;
		this.productCode = productCode;
		this.productName = productName;
		this.leader = leader;
		this.status = status;
		this.description = description;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getLeader() {
		return leader;
	}

	public void setLeader(Long leader) {
		this.leader = leader;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productCode=" + productCode
				+ ", productName=" + productName + ", leader=" + leader
				+ ", status=" + status + ", description=" + description
				+ ", date=" + date + "]";
	}
	
	
}
