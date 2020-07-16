package com.resource.json;

public class ResourceSearchResultsJson implements Comparable<ResourceSearchResultsJson>{

	private String resource;
	private Float rating;
	private Integer numberOfRatings;
	private String description;
	
	//Only populated if tag is included in the search query
	private String tag;
	private int tagCount;

	public ResourceSearchResultsJson(String resource, Float rating, Integer numberOfRatings, String description) {

		this.resource = resource;
		this.rating = rating;
		this.numberOfRatings = numberOfRatings;
		this.description = description;
		
		if(this.description.length() > 100) {
			
			this.description = this.description.substring(0, 100)+"...";
		}
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
	
	

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getTagCount() {
		return tagCount;
	}

	public void setTagCount(int tagCount) {
		this.tagCount = tagCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
