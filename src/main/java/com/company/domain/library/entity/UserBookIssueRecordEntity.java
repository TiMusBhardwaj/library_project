package com.company.domain.library.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "user_book_issue_record")
public class UserBookIssueRecordEntity {
	
	@Id
	private Integer id;
	
	@MapsId
	@OneToOne()
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	
	
	@OneToMany
	@JoinColumn(name="owner_id")
	private List<IssuedBookEntity> issuedBooks = new ArrayList<IssuedBookEntity>();



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public UserEntity getUser() {
		return user;
	}



	public void setUser(UserEntity user) {
		this.user = user;
	}



	public List<IssuedBookEntity> getIssuedBooks() {
		return issuedBooks;
	}



	public void setIssuedBooks(List<IssuedBookEntity> issuedBooks) {
		this.issuedBooks = issuedBooks;
	}
	
	
	
	

}
