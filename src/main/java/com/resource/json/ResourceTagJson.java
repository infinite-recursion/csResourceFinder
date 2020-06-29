package com.resource.json;

public class ResourceTagJson {

	private String id;

	private Integer count;

	private String name;

	public ResourceTagJson() {

	}

	public ResourceTagJson(String id, Integer count, String name) {
		this.id = id;
		this.count = count;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
