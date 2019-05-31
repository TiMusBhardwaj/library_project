package com.company.domain.library.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name= "Name")
	private String name;
	
	@Column(name="Age")
	private Integer age;
	
	@Column(name="Book_Limit")
	private Integer bookLimit;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	private UserBookIssueRecordEntity userbirEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
	
	public UserBookIssueRecordEntity getUserbirEntity() {
		return userbirEntity;
	}

	public void setUserbirEntity(UserBookIssueRecordEntity userbirEntity) {
		this.userbirEntity = userbirEntity;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", bookLimit=" + bookLimit + "]";
	}
	
	

}
