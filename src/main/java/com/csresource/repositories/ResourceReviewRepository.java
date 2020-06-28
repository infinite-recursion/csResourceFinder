package com.csresource.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.csresource.jpa.ResourceReview;

public interface ResourceReviewRepository extends PagingAndSortingRepository<ResourceReview, String>   {

}
