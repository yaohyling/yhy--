package com.boot.yhy.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "boot_role")
public class Role {
	@Id
	@GeneratedValue
	private BigInteger id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	public Role() {
		super();
	}
	

	public Role(BigInteger id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Role(BigInteger id, String name, User user) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

}
