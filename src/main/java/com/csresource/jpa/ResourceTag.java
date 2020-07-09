package com.csresource.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "ResourceTag" database table.
 * 
 */
@Entity
@Table(name="\"resourcetag\"", schema="csresource")
@NamedQuery(name="ResourceTag.findAll", query="SELECT r FROM ResourceTag r")
public class ResourceTag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"id\"")
	private String id;

	@Column(name="\"count\"")
	private Integer count;


	//bi-directional many-to-one association to Resource
	@ManyToOne
	@JoinColumn(name="\"resourcename\"")
	private Resource resource;

	//bi-directional many-to-one association to Tag
	@ManyToOne
	@JoinColumn(name="\"tagname\"")
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