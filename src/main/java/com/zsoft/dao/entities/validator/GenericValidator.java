package com.zsoft.dao.entities.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.zsoft.dao.entities.Meal;
import com.zsoft.dao.entities.Workout;

public class GenericValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Meal.class.isAssignableFrom(clazz) || Workout.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "calories", "field.required");

		if (object instanceof Meal) {
			Meal meal = (Meal) object;
			if (meal.getCalories() != null && meal.getCalories() <= 0) {
				errors.rejectValue("calories", "field.lessOrZero");
			}
		} else if (object instanceof Workout) {
			Workout workout = (Workout) object;
			if (workout != null && workout.getCalories() <= 0) {
				errors.rejectValue("calories", "field.lessOrZero");
			}
		}
	}

}
