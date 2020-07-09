package com.csresource.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "ResourceReply" database table.
 * 
 */
@Entity
@Table(name="\"resourcereply\"")
@NamedQuery(name="ResourceReply.findAll", query="SELECT r FROM ResourceReply r")
public class ResourceReply implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"id\"")
	private String id;

	@Column(name="\"accepted\"")
	private Boolean accepted;

	@Column(name="\"comment\"")
	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="\"date\"")
	private Date date;

	@Column(name="\"likes\"")
	private Integer likes;

	/*
	 * @Column(name="\"RepliedToID\"") private String repliedToID;
	 */

	@ManyToOne
	@JoinColumn(name="\"resourcename\"")
	private Resource resource;

	@ManyToOne
	@JoinColumn(name="\"user\"")
	private User user;

	//bi-directional many-to-one association to ResourceQuestion
	@ManyToOne
	@JoinColumn(name="\"repliedtoid\"")
	private ResourceQuestion resourceQuestion;

	public ResourceReply() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getAccepted() {
		return this.accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
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
	 * public String getRepliedToID() { return this.repliedToID; }
	 * 
	 * public void setRepliedToID(String repliedToID) { this.repliedToID =
	 * repliedToID; }
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

	public ResourceQuestion getResourceQuestion() {
		return this.resourceQuestion;
	}

	public void setResourceQuestion(ResourceQuestion resourceQuestion) {
		this.resourceQuestion = resourceQuestion;
	}

}