package com.boot.yhy.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "person")
@Component(value="person")
public class personProperties {
//	@Value("${person.height}")
	private String height;
//	@Value("${person.age}")
	private Integer age;

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "person [height=" + height + ", age=" + age + "]";
	}

}
