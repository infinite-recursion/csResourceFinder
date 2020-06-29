package com.resource.json;

import java.util.Date;

public class ResourceReviewJson {

	private String id;

	private String comment;

	private Date date;

	private Integer likes;

	private Integer rating;

	public ResourceReviewJson() {

	}

	public ResourceReviewJson(String id, String comment, Date date, Integer likes, Integer rating) {

		this.id = id;
		this.comment = comment;
		this.date = date;
		this.likes = likes;
		this.rating = rating;
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

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
