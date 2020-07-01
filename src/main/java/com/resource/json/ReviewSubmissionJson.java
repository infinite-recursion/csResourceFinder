package com.resource.json;

/**
 * Represents a review and rating submitted by a user when they are creating a
 * review
 * 
 * @author jbree
 *
 */
public class ReviewSubmissionJson {

	private String username;
	private String resource;
	private String comment;
	private Integer rating;
	private String tag;

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
