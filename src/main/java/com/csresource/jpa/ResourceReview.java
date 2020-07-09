package com.csresource.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "ResourceReview" database table.
 * 
 */
@Entity
@Table(name="\"resourcereview\"")
@NamedQuery(name="ResourceReview.findAll", query="SELECT r FROM ResourceReview r")
public class ResourceReview implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"id\"")
	private String id;

	@Column(name="\"comment\"")
	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="\"date\"")
	private Date date;

	@Column(name="\"likes\"")
	private Integer likes;

	@Column(name="\"rating\"")
	private Integer rating;

	/*
	 * @Column(name="\"ResourceName\"") private String resourceName;
	 */

	/*
	 * @Column(name="\"User\"") private String user;
	 */

	//bi-directional many-to-one association to Resource
	@ManyToOne
	@JoinColumn(name="\"resourcename\"")
	private Resource resource;


	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="\"user\"")
	private User user;

	public ResourceReview() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getLikes() {
		return this.likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	/*
	 * public String getResourceName() { return this.resourceName; }
	 * 
	 * public void setResourceName(String resourceName) { this.resourceName =
	 * resourceName; }
	 */
	/*
	 * public String getUser() { return this.user; }
	 * 
	 * public void setUser(String user) { this.user = user; }
	 */

	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}