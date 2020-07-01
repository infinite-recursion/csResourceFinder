package com.csresource.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.csresource.jpa.Resource;
import com.csresource.jpa.ResourceTag;
import com.csresource.jpa.Tag;

public interface ResourceTagRepository extends PagingAndSortingRepository<ResourceTag, String> {

	ResourceTag findByTagAndResource(Tag tag, Resource resource);
}
