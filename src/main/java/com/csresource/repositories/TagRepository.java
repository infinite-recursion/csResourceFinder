package com.csresource.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.csresource.jpa.Tag;

public interface TagRepository extends PagingAndSortingRepository<Tag, String> {

}
