package com.resource.json;

import java.util.Date;

public class ActivityJson {

	private String type;
	private Date date;
	private String username;
	private String resourceName;

	// For review and rating types
	private Integer rating;

	// For review types;
	private String review;

	// For question and reply types
	private String question;

	// For reply types
	private String questionReply;

	public ActivityJson(String type, Date date) {
		this.type = type;
		this.date = date;

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestionReply() {
		return questionReply;
	}

	public void setQuestionReply(String questionReply) {
		this.questionReply = questionReply;
	}

}
