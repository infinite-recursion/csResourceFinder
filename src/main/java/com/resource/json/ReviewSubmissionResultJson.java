package com.resource.json;

public class ReviewSubmissionResultJson {
	
	private ResourceReviewJson review;
	
	private Integer numRatings;
	
	private Float rating;
	
	public ReviewSubmissionResultJson(ResourceReviewJson review, Integer numRatings, Float rating) {
		
		this.review = review;
		this.numRatings = numRatings;
		this.rating = rating;
	}

	public ResourceReviewJson getReview() {
		return review;
	}

	public void setReview(ResourceReviewJson review) {
		this.review = review;
	}

	public Integer getNumRatings() {
		return numRatings;
	}

	public void setNumRatings(Integer numRatings) {
		this.numRatings = numRatings;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}
	
	

}
