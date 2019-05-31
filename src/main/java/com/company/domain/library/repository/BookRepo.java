package com.company.domain.library.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.company.domain.library.entity.BookEntity;

public interface BookRepo extends PagingAndSortingRepository<BookEntity, Integer> {
	
	public List<BookEntity> findByAuthor(String author);
	
	public List<BookEntity> findByName(String name);

}
