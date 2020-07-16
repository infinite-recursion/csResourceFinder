package com.resource.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResourceQuestionJson {

	private String id;

	private String comment;

	private Date date;

	private Integer likes;

	private String username;
	
	private boolean requestInProgress;
	
	private boolean editRequestInProgress;

	private List<ResourceReplyJson> replies;

	public ResourceQuestionJson() {

		requestInProgress = false;
		editRequestInProgress = false;
	}

	public ResourceQuestionJson(String id, String comment, Date date, Integer likes, String username) {

		this.id = id;
		this.comment = comment;
		this.date = date;
		this.likes = likes;
		this.username = username;
		requestInProgress = false;
		editRequestInProgress = false;

		this.replies = new ArrayList<ResourceReplyJson>();

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<ResourceReplyJson> getReplies() {
		return replies;
	}

	public void setReplies(List<ResourceReplyJson> replies) {
		this.replies = replies;
	}

	public boolean isRequestInProgress() {
		return requestInProgress;
	}

	public void setRequestInProgress(boolean requestInProgress) {
		this.requestInProgress = requestInProgress;
	}

	public boolean isEditRequestInProgress() {
		return editRequestInProgress;
	}

	public void setEditRequestInProgress(boolean editRequestInProgress) {
		this.editRequestInProgress = editRequestInProgress;
	}

}
