package com.company.domain.library.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.company.domain.library.entity.IssuedBookEntity;

public interface BookIssueRecordRepo extends PagingAndSortingRepository<IssuedBookEntity, Integer> {
	
	public List<IssuedBookEntity> findByBook_Id(Integer bookId);

}
