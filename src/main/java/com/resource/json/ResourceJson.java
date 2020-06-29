package com.resource.json;

import java.util.ArrayList;
import java.util.List;

public class ResourceJson {

	private String name;

	private String description;

	private Integer numRatings;

	private Float rating;

	private String url;

	private List<ResourceQuestionJson> questions;

	private List<ResourceReviewJson> reviews;

	private List<ResourceTagJson> tags;

	public ResourceJson() {
		questions = new ArrayList<ResourceQuestionJson>();
		reviews = new ArrayList<ResourceReviewJson>();
		tags = new ArrayList<ResourceTagJson>();
	}

	public ResourceJson(String name, String description, Integer numRatings, Float rating, String url) {
		this.name = name;
		this.description = description;
		this.numRatings = numRatings;
		this.rating = rating;
		this.url = url;

		questions = new ArrayList<ResourceQuestionJson>();
		reviews = new ArrayList<ResourceReviewJson>();
		tags = new ArrayList<ResourceTagJson>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNumRatings() {
		return numRatings;
	}

	public void setNumRatings(Integer numRatings) {
		this.numRatings = numRatings;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<ResourceQuestionJson> getQuestions() {
		return questions;
	}

	public void setQuestions(List<ResourceQuestionJson> questions) {
		this.questions = questions;
	}

	public List<ResourceReviewJson> getReviews() {
		return reviews;
	}

	public void setReviews(List<ResourceReviewJson> reviews) {
		this.reviews = reviews;
	}

	public List<ResourceTagJson> getTags() {
		return tags;
	}

	public void setTags(List<ResourceTagJson> tags) {
		this.tags = tags;
	}

}
