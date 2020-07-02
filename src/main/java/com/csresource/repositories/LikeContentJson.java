package com.csresource.repositories;

public class LikeContentJson {
	
	private String contentType;
	
	private String contentId;
	
	//Will be set in the act of unliking something
	private String userLikeId;
	
	private boolean liked;
	
	private String username;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	public String getUserLikeId() {
		return userLikeId;
	}

	public void setUserLikeId(String userLikeId) {
		this.userLikeId = userLikeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
