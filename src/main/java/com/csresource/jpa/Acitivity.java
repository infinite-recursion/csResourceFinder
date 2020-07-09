package com.csresource.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "Acitivity" database table.
 * 
 */
@Entity
@Table(name="\"acitivity\"", schema="csresource")
@NamedQuery(name="Acitivity.findAll", query="SELECT a FROM Acitivity a")
public class Acitivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"id\"")
	private String id;
	
	@Column(name="\"type\"")
	private String type;

	@Column(name="\"typeid\"")
	private String typeID;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="\"date\"")
	private Date date;

	public Acitivity() {
	}

	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeID() {
		return this.typeID;
	}
	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}