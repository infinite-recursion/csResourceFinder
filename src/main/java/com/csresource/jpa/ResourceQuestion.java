package com.csresource.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "ResourceQuestion" database table.
 * 
 */
@Entity
@Table(name="\"resourcequestion\"")
@NamedQuery(name="ResourceQuestion.findAll", query="SELECT r FROM ResourceQuestion r")
public class ResourceQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"id\"")
	private String id;

	@Column(name="\"comment\"")
	private String comment;

	@Temporal(TemporalType.DATE)
	@Column(name="\"date\"")
	private Date date;

	@Column(name="\"likes\"")
	private Integer likes;

	/*
	 * @Column(name="\"ResourceName\"") private String resourceName;
	 */

	//This is the suername
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

	//bi-directional many-to-one association to ResourceReply
	@OneToMany(mappedBy="resourceQuestion")
	@OrderBy("date DESC")
	private List<ResourceReply> resourceReplies;

	public ResourceQuestion() {
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

	public List<ResourceReply> getResourceReplies() {
		return this.resourceReplies;
	}

	public void setResourceReplies(List<ResourceReply> resourceReplies) {
		this.resourceReplies = resourceReplies;
	}

	public ResourceReply addResourceReply(ResourceReply resourceReply) {
		getResourceReplies().add(resourceReply);
		resourceReply.setResourceQuestion(this);

		return resourceReply;
	}

	public ResourceReply removeResourceReply(ResourceReply resourceReply) {
		getResourceReplies().remove(resourceReply);
		resourceReply.setResourceQuestion(null);

		return resourceReply;
	}

}