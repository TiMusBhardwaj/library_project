package com.company.domain.library.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class Book {

	@NotNull
	@NotBlank
	@Size(max = 100)
	private String name;
	
	@NotNull
	@NotBlank
	@Size(max = 100)
	private String author;
	
	
	@NotNull
	private Category category;
	
	@NotNull
	@Digits(integer=6, fraction=2)
	private Double price;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + ", category=" + category + ", price="
				+ price + "]";
	}
	

}

