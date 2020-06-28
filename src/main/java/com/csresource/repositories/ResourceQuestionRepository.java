package com.csresource.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.csresource.jpa.ResourceQuestion;

public interface ResourceQuestionRepository extends PagingAndSortingRepository<ResourceQuestion, String> {

}
