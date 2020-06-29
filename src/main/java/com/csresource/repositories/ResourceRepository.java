package com.csresource.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.csresource.jpa.Resource;

public interface ResourceRepository extends PagingAndSortingRepository<Resource, String> {

}
