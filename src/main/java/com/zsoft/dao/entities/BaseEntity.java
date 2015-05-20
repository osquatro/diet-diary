package com.zsoft.dao.entities;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;

public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 5650684113698227336L;
	
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String USER_ID = "user_id";
	public static final String CREATED = "created";
	
	@DatabaseField(generatedId = true)
	protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
