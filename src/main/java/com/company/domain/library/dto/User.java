package com.company.domain.library.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	
	@NotNull
	@NotBlank
	@Size(max = 100)
	private String name;
	
	
	@Min(value= 6, message="You are under age")
	private Integer age;
	
	@Min(1)
	@Max(20)
	private Integer bookLimit;

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getBookLimit() {
		return bookLimit;
	}

	public void setBookLimit(Integer bookLimit) {
		this.bookLimit = bookLimit;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", bookLimit=" + bookLimit + "]";
	}
	
	

}
