package com.csresource.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.csresource.jpa.Resource;

public interface ResourceRepository extends PagingAndSortingRepository<Resource, String> {

	List<Resource> findByNameLikeIgnoreCase(String name, Sort sort);
	
}
