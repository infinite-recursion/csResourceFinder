package com.csresource.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Resource" database table.
 * 
 */
@Entity
@Table(name="\"Resource\"")
@NamedQuery(name="Resource.findAll", query="SELECT r FROM Resource r")
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"Name\"")
	private String name;

	@Column(name="\"Description\"")
	private String description;

	@Column(name="\"NumRatings\"")
	private Integer numRatings;

	@Column(name="\"Rating\"")
	private Integer rating;

	@Column(name="\"Url\"")
	private String url;

	//bi-directional many-to-one association to ResourceQuestion
	@OneToMany(mappedBy="resource")
	private List<ResourceQuestion> resourceQuestions;

	//bi-directional many-to-one association to ResourceReview
	@OneToMany(mappedBy="resource")
	private List<ResourceReview> resourceReviews;

	//bi-directional many-to-one association to ResourceTag
	@OneToMany(mappedBy="resource")
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

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
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