package com.company.domain.library.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.company.domain.library.entity.UserEntity;

public interface UserRepo extends PagingAndSortingRepository<UserEntity, Integer> {

}
