package com.csresource.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csresource.jpa.Tag;
import com.csresource.repositories.TagRepository;

@RestController
public class TagController {

	@Autowired
	TagRepository tagRepo;

	@GetMapping("/getTags")
	public List<String> getTags() {

		LinkedList<String> tags = new LinkedList<String>();

		Iterable<Tag> tagsJpaList = tagRepo.findAll(Sort.by("name").ascending());

		//Add a blank option for a tag
		tags.add("");
		for (Tag tag : tagsJpaList) {
			tags.add(tag.getName());
		}

		return tags;

	}

}
