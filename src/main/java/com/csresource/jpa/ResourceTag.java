package com.csresource.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "ResourceTag" database table.
 * 
 */
@Entity
@Table(name="\"ResourceTag\"")
@NamedQuery(name="ResourceTag.findAll", query="SELECT r FROM ResourceTag r")
public class ResourceTag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"ID\"")
	private String id;

	@Column(name="\"Count\"")
	private Integer count;


	//bi-directional many-to-one association to Resource
	@ManyToOne
	@JoinColumn(name="\"ResourceName\"")
	private Resource resource;

	//bi-directional many-to-one association to Tag
	@ManyToOne
	@JoinColumn(name="\"TagName\"")
	private Tag tag;

	public ResourceTag() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}


	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Tag getTag() {
		return this.tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

}