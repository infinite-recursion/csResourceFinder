package com.csresource.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.csresource.jpa.Acitivity;

public interface ActivityRepository extends PagingAndSortingRepository<Acitivity, String>  {

}
