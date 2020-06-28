package com.csresource.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "ResourceReply" database table.
 * 
 */
@Entity
@Table(name="\"ResourceReply\"")
@NamedQuery(name="ResourceReply.findAll", query="SELECT r FROM ResourceReply r")
public class ResourceReply implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"ID\"")
	private String id;

	@Column(name="\"Accepted\"")
	private Boolean accepted;

	@Column(name="\"Comment\"")
	private String comment;

	@Temporal(TemporalType.DATE)
	@Column(name="\"Date\"")
	private Date date;

	@Column(name="\"Likes\"")
	private Integer likes;

	/*
	 * @Column(name="\"RepliedToID\"") private String repliedToID;
	 */

	@Column(name="\"ResourceName\"")
	private String resourceName;

	@Column(name="\"User\"")
	private String user;

	//bi-directional many-to-one association to ResourceQuestion
	@ManyToOne
	@JoinColumn(name="\"RepliedToID\"")
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

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public ResourceQuestion getResourceQuestion() {
		return this.resourceQuestion;
	}

	public void setResourceQuestion(ResourceQuestion resourceQuestion) {
		this.resourceQuestion = resourceQuestion;
	}

}