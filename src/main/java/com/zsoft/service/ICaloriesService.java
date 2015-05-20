package com.zsoft.service;

import java.util.Date;
import java.util.List;

import com.zsoft.dao.entities.Meal;
import com.zsoft.dao.entities.User;
import com.zsoft.dao.entities.Workout;

public interface ICaloriesService {
	
	Integer saveMeal(Meal meal);
	
	void editMeal(Meal meal);
	
	void deleteMeal(Meal meal);
	
	List<Meal> getUserMealsByDate(User user, Date date);
	
	List<Meal> getTodayUserMeals(User user);
	
	Integer saveWorkout(Workout workout);
	
	void editWorkout(Workout workout);
	
	void deleteWorkout(Workout workout);
	
	List<Workout> getWorkoutsByDate(User user, Date date);
	
	List<Workout> getTodayUserWorkouts(User user);	
}
