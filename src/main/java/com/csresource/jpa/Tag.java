package com.csresource.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Tag" database table.
 * 
 */
@Entity
@Table(name="\"tag\"", schema="csresource")
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String name;

	//bi-directional many-to-one association to ResourceTag
	@OneToMany(mappedBy="tag")
	private List<ResourceTag> resourceTags;

	public Tag() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ResourceTag> getResourceTags() {
		return this.resourceTags;
	}

	public void setResourceTags(List<ResourceTag> resourceTags) {
		this.resourceTags = resourceTags;
	}

	public ResourceTag addResourceTag(ResourceTag resourceTag) {
		getResourceTags().add(resourceTag);
		resourceTag.setTag(this);

		return resourceTag;
	}

	public ResourceTag removeResourceTag(ResourceTag resourceTag) {
		getResourceTags().remove(resourceTag);
		resourceTag.setTag(null);

		return resourceTag;
	}

}