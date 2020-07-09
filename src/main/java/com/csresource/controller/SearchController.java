package com.csresource.controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("/search")
	public List<List<ResourceSearchResultsJson>> search(@RequestBody SearchJson searchJson) {

		if (searchJson.getTag() != null && searchJson.getTag().equals("")) {
			searchJson.setTag(null);
		}

		LinkedList<List<ResourceSearchResultsJson>> allSearchResults = new LinkedList<List<ResourceSearchResultsJson>>();

		LinkedList<ResourceSearchResultsJson> searchResults = new LinkedList<ResourceSearchResultsJson>();

		// If are searching by tag alone
		if (searchJson.getKeyword() == null && searchJson.getTag() != null) {

			Tag tag = tagRepo.findById(searchJson.getTag()).get();

			// If searching by highest tag frequency of the tag selected
			if (searchJson.getSearchPriority() != null
					&& searchJson.getSearchPriority().equals(AppConstants.SEARCH_HIGHEST_TAG_FREQ)) {
				List<ResourceTag> resourceTags = resourceTagRepo.findByTag(tag, Sort.by("count").descending());

				if (resourceTags != null) {

					int numResults = 1;

					for (ResourceTag resourceTag : resourceTags) {

						Resource resource = resourceTag.getResource();

						ResourceSearchResultsJson resultsJson = new ResourceSearchResultsJson(resource.getName(),
								resource.getRating(), resource.getNumRatings());
						resultsJson.setTag(tag.getName());
						resultsJson.setTagCount(resourceTag.getCount());

						searchResults.add(resultsJson);

						if (numResults % 4 == 0) {
							allSearchResults.add(searchResults);
							searchResults = new LinkedList<ResourceSearchResultsJson>();
						}

						numResults++;
					}

					// Add any remaining
					if (!searchResults.isEmpty()) {
						allSearchResults.add(searchResults);
					}
				}
			}
			// If searching by tag and highest rating
			else {

				// Think I need to get all the resource tags that match the tag, and then
				// sort the resources retrieved from those resource tags by rating
				List<ResourceTag> resourceTags = resourceTagRepo.findByTag(tag);

				if (resourceTags != null) {

					for (ResourceTag resourceTag : resourceTags) {

						Resource resource = resourceTag.getResource();

						ResourceSearchResultsJson resultsJson = new ResourceSearchResultsJson(resource.getName(),
								resource.getRating(), resource.getNumRatings());

						resultsJson.setTag(tag.getName());
						resultsJson.setTagCount(resourceTag.getCount());

						searchResults.add(resultsJson);

					}

					// Sort them by rating
					Collections.sort(searchResults);

					// Now combine them into the list of list
					int numResults = 1;

					List<ResourceSearchResultsJson> searchResultsJson = new LinkedList<ResourceSearchResultsJson>();

					for (ResourceSearchResultsJson searchResultJson : searchResults) {

						searchResultsJson.add(searchResultJson);

						if (numResults % 4 == 0) {
							allSearchResults.add(searchResultsJson);
							searchResultsJson = new LinkedList<ResourceSearchResultsJson>();
						}

						numResults++;
					}

					// Add any remaining
					if (!searchResultsJson.isEmpty()) {
						allSearchResults.add(searchResultsJson);
					}

				}
			}

		}
		// Searching by keyword alone (automatically search by highest rating since no
		// tag specified)
		else if (searchJson.getKeyword() != null && searchJson.getTag() == null) {

			String keyword = "%" + searchJson.getKeyword() + "%";
			List<Resource> resources = resourceRepo.findByNameLikeIgnoreCase(keyword, Sort.by("rating").descending());

			int numResults = 1;
			for (Resource resource : resources) {

				ResourceSearchResultsJson resultsJson = new ResourceSearchResultsJson(resource.getName(),
						resource.getRating(), resource.getNumRatings());
				searchResults.add(resultsJson);

				if (numResults % 4 == 0) {
					allSearchResults.add(searchResults);
					searchResults = new LinkedList<ResourceSearchResultsJson>();
				}

				numResults++;

			}

			// Add any remaining
			if (!searchResults.isEmpty()) {
				allSearchResults.add(searchResults);
			}
		}
		//Searching by tag and keyword
		else if (searchJson.getKeyword() != null && searchJson.getTag() != null) {

			//search by either highest rating or highest frequency

			// Searching by highest rating
			if (searchJson.getSearchPriority().equals(AppConstants.SEARCH_HIGHEST_RATING)) {

				String keyword = searchJson.getKeyword().toLowerCase();

				Tag tag = tagRepo.findById(searchJson.getTag()).get();

				// First search by tag
				List<ResourceTag> resourceTags = resourceTagRepo.findByTag(tag);

				// Then filter out based on the keyword: will only include results that contain
				// keyword
				for (ResourceTag resourceTag : resourceTags) {

					Resource resource = resourceTag.getResource();

					String resourceNameLowerCase = resource.getName().toLowerCase();

					if (resourceNameLowerCase.contains(keyword)) {

						ResourceSearchResultsJson resultsJson = new ResourceSearchResultsJson(resource.getName(),
								resource.getRating(), resource.getNumRatings());

						resultsJson.setTag(tag.getName());
						resultsJson.setTagCount(resourceTag.getCount());
						searchResults.add(resultsJson);
					}

				}

				// Now sort by rating
				Collections.sort(searchResults);

				// Now combine them into the list of list
				int numResults = 1;

				List<ResourceSearchResultsJson> searchResultsJson = new LinkedList<ResourceSearchResultsJson>();

				for (ResourceSearchResultsJson searchResultJson : searchResults) {

					searchResultsJson.add(searchResultJson);

					if (numResults % 4 == 0) {
						allSearchResults.add(searchResultsJson);
						searchResultsJson = new LinkedList<ResourceSearchResultsJson>();
					}

					numResults++;
				}

				// Add any remaining
				if (!searchResultsJson.isEmpty()) {
					allSearchResults.add(searchResultsJson);
				}

			}
			// Search by highest tag
			else if (searchJson.getSearchPriority().equals(AppConstants.SEARCH_HIGHEST_TAG_FREQ)) {

				Tag tag = tagRepo.findById(searchJson.getTag()).get();

				String keyword = searchJson.getKeyword().toLowerCase();

				// Retrieve by highest tag
				List<ResourceTag> resourceTags = resourceTagRepo.findByTag(tag, Sort.by("count").descending());

				if (resourceTags != null) {

					int numResults = 1;

					for (ResourceTag resourceTag : resourceTags) {

						// Only include the result if it contains the keyword

						Resource resource = resourceTag.getResource();

						String resourceNameLowerCase = resource.getName().toLowerCase();

						if (resourceNameLowerCase.contains(keyword)) {

							ResourceSearchResultsJson resultsJson = new ResourceSearchResultsJson(resource.getName(),
									resource.getRating(), resource.getNumRatings());
							resultsJson.setTag(tag.getName());
							resultsJson.setTagCount(resourceTag.getCount());

							searchResults.add(resultsJson);

							if (numResults % 4 == 0) {
								allSearchResults.add(searchResults);
								searchResults = new LinkedList<ResourceSearchResultsJson>();
							}

							numResults++;
						}
					}

					// Add any remaining
					if (!searchResults.isEmpty()) {
						allSearchResults.add(searchResults);
					}
				}
			}
		}
		// Just return all results sorted by highest rating if no keyword or tag
		// specified
		else {

			Iterable<Resource> resources = resourceRepo.findAll(Sort.by("rating").descending());

			int numResults = 1;

			for (Resource resource : resources) {

				ResourceSearchResultsJson resultsJson = new ResourceSearchResultsJson(resource.getName(),
						resource.getRating(), resource.getNumRatings());
				searchResults.add(resultsJson);

				if (numResults % 4 == 0) {
					allSearchResults.add(searchResults);
					searchResults = new LinkedList<ResourceSearchResultsJson>();
				}

				numResults++;

			}

			// Add any remaining
			if (!searchResults.isEmpty()) {
				allSearchResults.add(searchResults);
			}
		}

		return allSearchResults;

	}

}
