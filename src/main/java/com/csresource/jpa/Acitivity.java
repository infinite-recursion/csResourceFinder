package com.csresource.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "Acitivity" database table.
 * 
 */
@Entity
@Table(name="\"Acitivity\"")
@NamedQuery(name="Acitivity.findAll", query="SELECT a FROM Acitivity a")
public class Acitivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AcitivityPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="\"Date\"")
	private Date date;

	public Acitivity() {
	}

	public AcitivityPK getId() {
		return this.id;
	}

	public void setId(AcitivityPK id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}