package com.csresource.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the "Acitivity" database table.
 * 
 */
@Embeddable
public class AcitivityPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="\"Type\"")
	private String type;

	@Column(name="\"TypeID\"")
	private String typeID;

	public AcitivityPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AcitivityPK)) {
			return false;
		}
		AcitivityPK castOther = (AcitivityPK)other;
		return 
			this.type.equals(castOther.type)
			&& this.typeID.equals(castOther.typeID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.type.hashCode();
		hash = hash * prime + this.typeID.hashCode();
		
		return hash;
	}
}