package com.csresource.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "User" database table.
 * 
 */
@Entity
@Table(name="\"user\"")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"username\"")
	private String username;

	@Column(name="\"password\"")
	private String password;

	//bi-directional many-to-one association to ResourceQuestion
	@OneToMany(mappedBy="user")
	private List<ResourceQuestion> resourceQuestions;

	//bi-directional many-to-one association to ResourceReview
	@OneToMany(mappedBy="user")
	private List<ResourceReview> resourceReviews;

	public User() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ResourceQuestion> getResourceQuestions() {
		return this.resourceQuestions;
	}

	public void setResourceQuestions(List<ResourceQuestion> resourceQuestions) {
		this.resourceQuestions = resourceQuestions;
	}

	public ResourceQuestion addResourceQuestion(ResourceQuestion resourceQuestion) {
		getResourceQuestions().add(resourceQuestion);
		resourceQuestion.setUser(this);

		return resourceQuestion;
	}

	public ResourceQuestion removeResourceQuestion(ResourceQuestion resourceQuestion) {
		getResourceQuestions().remove(resourceQuestion);
		resourceQuestion.setUser(null);

		return resourceQuestion;
	}

	public List<ResourceReview> getResourceReviews() {
		return this.resourceReviews;
	}

	public void setResourceReviews(List<ResourceReview> resourceReviews) {
		this.resourceReviews = resourceReviews;
	}

	public ResourceReview addResourceReview(ResourceReview resourceReview) {
		getResourceReviews().add(resourceReview);
		resourceReview.setUser(this);

		return resourceReview;
	}

	public ResourceReview removeResourceReview(ResourceReview resourceReview) {
		getResourceReviews().remove(resourceReview);
		resourceReview.setUser(null);

		return resourceReview;
	}

}