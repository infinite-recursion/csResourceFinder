package com.csresource.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the userlikes database table.
 * 
 */
@Entity
@Table(name="userlikes")
@NamedQuery(name="Userlike.findAll", query="SELECT u FROM Userlike u")
public class Userlike implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String contentid;

	private String contenttype;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="username")
	private User user;

	public Userlike() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContentid() {
		return this.contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public String getContenttype() {
		return this.contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}