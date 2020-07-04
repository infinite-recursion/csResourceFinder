package com.resource.json;

import java.util.Date;

public class ResourceReplyJson {

	private String id;

	private Boolean accepted;

	private String comment;

	private Date date;
	
	private String username;
	
	private Integer likes;
	
	private boolean requestInProgress;


	public ResourceReplyJson(String id, Boolean accepted, String comment, Date date, String username, Integer likes) {
		this.id = id;
		this.accepted = accepted;
		this.comment = comment;
		this.date = date;
		this.username = username;
		this.likes = likes;
		requestInProgress = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isRequestInProgress() {
		return requestInProgress;
	}

	public void setRequestInProgress(boolean requestInProgress) {
		this.requestInProgress = requestInProgress;
	}



}
