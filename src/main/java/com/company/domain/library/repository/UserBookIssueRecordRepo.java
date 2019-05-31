package com.company.domain.library.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.company.domain.library.entity.UserBookIssueRecordEntity;

public interface UserBookIssueRecordRepo extends PagingAndSortingRepository<UserBookIssueRecordEntity, Integer> {

}
