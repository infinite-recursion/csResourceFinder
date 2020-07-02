package com.csresource.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.csresource.jpa.Resource;
import com.csresource.jpa.ResourceTag;
import com.csresource.jpa.Tag;

public interface ResourceTagRepository extends PagingAndSortingRepository<ResourceTag, String> {

	ResourceTag findByTagAndResource(Tag tag, Resource resource);
	
	List<ResourceTag> findByTag(Tag tag, Sort sort);
	
	List<ResourceTag> findByTag(Tag tag);

}
