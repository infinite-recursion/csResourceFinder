package com.csresource.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.csresource.AppConstants;
import com.csresource.jpa.Resource;
import com.csresource.jpa.ResourceTag;
import com.csresource.jpa.Tag;
import com.csresource.repositories.ResourceRepository;
import com.csresource.repositories.ResourceTagRepository;
import com.csresource.repositories.TagRepository;
import com.resource.json.ResourceSearchResultsJson;
import com.resource.json.SearchJson;

@RestController
public class SearchController {

	@Autowired
	ResourceRepository resourceRepo;

	@Autowired
	TagRepository tagRepo;

	@Autowired
	ResourceTagRepository resourceTagRepo;

	@GetMapping("/search")
	public List<ResourceSearchResultsJson> search(@RequestBody SearchJson searchJson) {

		LinkedList<ResourceSearchResultsJson> searchResults = new LinkedList<ResourceSearchResultsJson>();

		// If are searching by tag alone
		if (searchJson.getKeyword() == null && searchJson.getTag() != null) {

			Tag tag = tagRepo.findById(searchJson.getTag()).get();

			// If searching by highest tag frequency of the tag selected
			if (searchJson.getSearchPriority().equals(AppConstants.SEARCH_HIGHEST_TAG_FREQ)) {
				List<ResourceTag> resourceTags = resourceTagRepo.findByTag(tag, Sort.by("count").descending());

				if (resourceTags != null) {

					for (ResourceTag resourceTag : resourceTags) {

						Resource resource = resourceTag.getResource();

						ResourceSearchResultsJson resultsJson = new ResourceSearchResultsJson(resource.getName(),
								resource.getRating(), resource.getNumRatings());
						searchResults.add(resultsJson);
					}
				}
			}
			// TODO: If searching by tag and highest rating
			else {

			}

		}
		// TODO: Searching by keyword alone (automatically search by highest rating
		else if (searchJson.getKeyword() != null && searchJson.getTag() == null) {

		}
		// TODO: Searching by tag and keyword
		else if (searchJson.getKeyword() != null && searchJson.getTag() != null) {

		}
		// Just return all results sorted by highest rating
		else {

			Iterable<Resource> resources = resourceRepo.findAll(Sort.by("rating").descending());

			for (Resource resource : resources) {

				ResourceSearchResultsJson resultsJson = new ResourceSearchResultsJson(resource.getName(),
						resource.getRating(), resource.getNumRatings());
				searchResults.add(resultsJson);

			}
		}

		return searchResults;

	}

}
