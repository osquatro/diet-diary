package com.zsoft.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.zsoft.dao.entities.Meal;
import com.zsoft.dao.entities.User;
import com.zsoft.dao.entities.Workout;
import com.zsoft.exception.ServiceException;
import com.zsoft.utils.DateUtils;

@Service("caloriesService")
public class CaloriesServiceImpl implements ICaloriesService {
	
	@Autowired
	private Dao<Meal, Integer> mealDao;
	
	@Autowired
	private Dao<Workout, Integer> workoutDao;
	
	@Override
	public Integer saveMeal(Meal meal) {
		try {
			return mealDao.create(meal);
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void editMeal(Meal meal) {
		try {
			mealDao.update(meal);
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public void deleteMeal(Meal meal) {
		try {
			mealDao.delete(meal);
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<Meal> getUserMealsByDate(User user, Date date) {
		try {
			Date startDate = DateUtils.prepareStartDate(date);
			Date endDate = DateUtils.prepareEndDate(date);
			
			QueryBuilder<Meal, Integer> builder = mealDao.queryBuilder();
			builder.where().eq(Meal.USER_ID, user.getId()).and().between(Meal.CREATED, startDate, endDate);
			
			return mealDao.query(builder.prepare());
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Meal> getTodayUserMeals(User user) {
		return getUserMealsByDate(user, new Date());
	}

	@Override
	public Integer saveWorkout(Workout workout) {
		try {
			return workoutDao.create(workout);
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void editWorkout(Workout workout) {
		try {
			workoutDao.update(workout);
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteWorkout(Workout workout) {
		try {
			workoutDao.delete(workout);
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<Workout> getWorkoutsByDate(User user, Date date) {
		try {
			Date startDate = DateUtils.prepareStartDate(date);
			Date endDate = DateUtils.prepareEndDate(date);
			
			QueryBuilder<Workout, Integer> builder = workoutDao.queryBuilder();
			builder.where().eq(Meal.USER_ID, user.getId()).and().between(Workout.CREATED, startDate, endDate);
			
			return workoutDao.query(builder.prepare());
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Workout> getTodayUserWorkouts(User user) {
		return getWorkoutsByDate(user, new Date());
	}

}
