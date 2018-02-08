package com.boot.yhy.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.boot.yhy.util.BaseBean;

@Entity
@Table(name = "boot_user")
public class User extends BaseBean {

	private String name;
	@Id
	@GeneratedValue
	@Column
	private Integer uid;

	private Integer age;
	@OneToMany(mappedBy = "user")
	private Set<Role> role = new HashSet<Role>(0);

	public User() {
		super();
	}

	public User(String name, Integer uid, Integer age) {
		super();
		this.name = name;
		this.uid = uid;
		this.age = age;
	}

	public User(String name, Integer uid, Integer age, Set<Role> role) {
		super();
		this.name = name;
		this.uid = uid;
		this.age = age;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

}
