package com.csresource.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Resource" database table.
 * 
 */
@Entity
@Table(name="\"resource\"")
@NamedQuery(name="Resource.findAll", query="SELECT r FROM Resource r")
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"name\"")
	private String name;

	@Column(name="\"description\"")
	private String description;

	@Column(name="\"numratings\"")
	private Integer numRatings;

	@Column(name="\"rating\"")
	private Float rating;

	@Column(name="\"url\"")
	private String url;

	//bi-directional many-to-one association to ResourceQuestion
	@OneToMany(mappedBy="resource")
	@OrderBy("date DESC")
	private List<ResourceQuestion> resourceQuestions;

	//bi-directional many-to-one association to ResourceReview
	@OneToMany(mappedBy="resource")
	@OrderBy("date DESC")
	private List<ResourceReview> resourceReviews;

	//bi-directional many-to-one association to ResourceTag
	@OneToMany(mappedBy="resource")
	@OrderBy("count DESC")
	private List<ResourceTag> resourceTags;

	public Resource() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNumRatings() {
		return this.numRatings;
	}

	public void setNumRatings(Integer numRatings) {
		this.numRatings = numRatings;
	}

	public Float getRating() {
		return this.rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<ResourceQuestion> getResourceQuestions() {
		return this.resourceQuestions;
	}

	public void setResourceQuestions(List<ResourceQuestion> resourceQuestions) {
		this.resourceQuestions = resourceQuestions;
	}

	public ResourceQuestion addResourceQuestion(ResourceQuestion resourceQuestion) {
		getResourceQuestions().add(resourceQuestion);
		resourceQuestion.setResource(this);

		return resourceQuestion;
	}

	public ResourceQuestion removeResourceQuestion(ResourceQuestion resourceQuestion) {
		getResourceQuestions().remove(resourceQuestion);
		resourceQuestion.setResource(null);

		return resourceQuestion;
	}

	public List<ResourceReview> getResourceReviews() {
		return this.resourceReviews;
	}

	public void setResourceReviews(List<ResourceReview> resourceReviews) {
		this.resourceReviews = resourceReviews;
	}

	public ResourceReview addResourceReviews(ResourceReview resourceReview) {
		getResourceReviews().add(resourceReview);
		resourceReview.setResource(this);

		return resourceReview;
	}

	public ResourceReview removeResourceReviews(ResourceReview resourceReview) {
		getResourceReviews().remove(resourceReview);
		resourceReview.setResource(null);

		return resourceReview;
	}

	public List<ResourceTag> getResourceTags() {
		return this.resourceTags;
	}

	public void setResourceTags(List<ResourceTag> resourceTags) {
		this.resourceTags = resourceTags;
	}

	public ResourceTag addResourceTag(ResourceTag resourceTag) {
		getResourceTags().add(resourceTag);
		resourceTag.setResource(this);

		return resourceTag;
	}

	public ResourceTag removeResourceTag(ResourceTag resourceTag) {
		getResourceTags().remove(resourceTag);
		resourceTag.setResource(null);

		return resourceTag;
	}

}