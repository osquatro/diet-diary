package com.zsoft.dao.entities;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1826222756482475611L;
	
	public static final String USERNAME = "username";
	
	@DatabaseField(columnName = "username", canBeNull = false)
	private String username;
	@DatabaseField(columnName = "password", canBeNull = false)
	private String password;
	@DatabaseField(columnName = "created", canBeNull = false)
	private Date created;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
