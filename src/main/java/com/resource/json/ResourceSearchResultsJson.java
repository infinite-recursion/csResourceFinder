package com.resource.json;

public class ResourceSearchResultsJson implements Comparable<ResourceSearchResultsJson>{

	private String resource;
	private Float rating;
	private Integer numberOfRatings;

	public ResourceSearchResultsJson(String resource, Float rating, Integer numberOfRatings) {

		this.resource = resource;
		this.rating = rating;
		this.numberOfRatings = numberOfRatings;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Integer getNumberOfRatings() {
		return numberOfRatings;
	}

	public void setNumberOfRatings(Integer numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}

	@Override
	public int compareTo(ResourceSearchResultsJson o) {
		// TODO Auto-generated method stub
		if(this.rating > o.rating)
			return -1;
		else if(this.rating < o.rating)
			return 1;
		else
			return 0;
	}

}
