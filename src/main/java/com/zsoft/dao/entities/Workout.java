package com.zsoft.dao.entities;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zsoft.jackson.CustomDateDeserializer;
import com.zsoft.jackson.CustomDateSerializer;

@DatabaseTable(tableName = "user_workout")
public class Workout extends BaseEntity {

	private static final long serialVersionUID = -6522429756904174736L;

	@DatabaseField(columnName = "user_id", canBeNull = false)
	private Integer userId;
	@DatabaseField(columnName = "name", canBeNull = false)
	private String name;
	@DatabaseField(columnName = "calories", canBeNull = false)
	private Integer calories;
	@DatabaseField(columnName = "created", canBeNull = false)
	private Date created;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	@JsonSerialize(using = CustomDateSerializer.class, as = Date.class)
	public Date getCreated() {
		return created;
	}
	
	@JsonDeserialize(using = CustomDateDeserializer.class, as = Date.class)
	public void setCreated(Date created) {
		this.created = created;
	}
}
