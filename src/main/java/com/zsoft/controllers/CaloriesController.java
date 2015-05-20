package com.zsoft.controllers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zsoft.constants.Constants;
import com.zsoft.dao.entities.Meal;
import com.zsoft.dao.entities.User;
import com.zsoft.dao.entities.Workout;
import com.zsoft.dao.entities.validator.GenericValidator;
import com.zsoft.dto.StatusDTO;
import com.zsoft.service.ICaloriesService;
import com.zsoft.utils.DateParam;

@Controller
public class CaloriesController {
	
	Logger log = Logger.getLogger(CaloriesController.class.getName());
	
	@Autowired
	private ICaloriesService caloriesService;
	
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new GenericValidator());
    }

	@RequestMapping("/calories-page")
	public ModelAndView getPage() {
		return new ModelAndView("calories");
	}

	/* Workout handlers */
	@RequestMapping(value="/workout/today", produces = "application/json", method = RequestMethod.GET)
	public  @ResponseBody List<Workout> todayWorkouts(HttpSession session) {
		User user = (User) session.getAttribute(Constants.USER_SESSION_KEY);
		return caloriesService.getTodayUserWorkouts(user);
	}
	
	@RequestMapping(value="/workout/get", produces = "application/json", method = RequestMethod.GET)
	public  @ResponseBody List<Workout> workouts(
			@RequestParam(value = "date") DateParam date,
			HttpSession session) {
		
		User user = (User) session.getAttribute(Constants.USER_SESSION_KEY);
		return caloriesService.getWorkoutsByDate(user, date.getDate());
	}
	
	@RequestMapping(value="/workout/save", consumes ="application/json", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> saveWorkout(
			@RequestBody @Validated Workout workout, BindingResult errors, HttpSession session) {
		
		if (errors.hasErrors()) {
			StatusDTO statusDto = createValidationErrorStatusDTO(); 
			putErrors(statusDto, errors);
			return new ResponseEntity<StatusDTO>(statusDto, HttpStatus.BAD_REQUEST);
		}

		User user = (User) session.getAttribute(Constants.USER_SESSION_KEY);
		workout.setUserId(user.getId());
		return new ResponseEntity<Integer>(caloriesService.saveWorkout(workout), HttpStatus.CREATED);
	}
	

	@RequestMapping(value="/workout/edit", consumes ="application/json",  produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> editWorkout(
			@RequestBody @Validated Workout workout, BindingResult errors, HttpSession session) {
		if (errors.hasErrors() || workout.getId() == null) {
			StatusDTO statusDto = createValidationErrorStatusDTO(); 
			
			if (errors.hasErrors()) {
				putErrors(statusDto, errors);
			}
			
			if (workout.getId() == null) {
				statusDto.getErrors().add(createErrorString(Constants.WORKOUT_OBJECT_KEY, Workout.ID, "field.required"));
			}
			
			return new ResponseEntity<StatusDTO>(statusDto, HttpStatus.BAD_REQUEST); }
		else {
			User user = (User) session.getAttribute(Constants.USER_SESSION_KEY);
			workout.setUserId(user.getId());
			caloriesService.editWorkout(workout);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/workout/delete/{id}", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody Boolean deleteWorkout(@PathVariable Integer id, HttpSession session) {
		User user = (User) session.getAttribute(Constants.USER_SESSION_KEY);
		
		Workout workout = new Workout();
		workout.setUserId(user.getId());
		workout.setId(id);
		
		caloriesService.deleteWorkout(workout);
		return true;
	}
	
	/* Meal handlers */
	@RequestMapping(value="/meal/save", consumes ="application/json", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> saveMeal(
			@RequestBody @Validated Meal meal, BindingResult errors, HttpSession session) {
		
		if (errors.hasErrors()) {
			StatusDTO statusDto = createValidationErrorStatusDTO(); 
			putErrors(statusDto, errors);

			return new ResponseEntity<StatusDTO>(statusDto, HttpStatus.BAD_REQUEST);
		} else {
			User user = (User) session.getAttribute(Constants.USER_SESSION_KEY);
			meal.setUserId(user.getId());
		
			return new ResponseEntity<Integer>(caloriesService.saveMeal(meal), HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value="/meal/edit", consumes ="application/json",  produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> editMeal(
			@RequestBody @Validated Meal meal, BindingResult errors, HttpSession session) {
		
		
		if (errors.hasErrors() || meal.getId() == null) {
			StatusDTO statusDto = createValidationErrorStatusDTO(); 
			
			if (errors.hasErrors()) {
				putErrors(statusDto, errors);
			}
			
			if (meal.getId() == null) {
				statusDto.getErrors().add(createErrorString(Constants.MEAL_OBJECT_KEY, Meal.ID, "field.required"));
			}
			
			return new ResponseEntity<StatusDTO>(statusDto, HttpStatus.BAD_REQUEST);
		} else {
			User user = (User) session.getAttribute(Constants.USER_SESSION_KEY);
			meal.setUserId(user.getId());
			caloriesService.editMeal(meal);
			
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/meal/delete/{id}", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> deleteMeal(@PathVariable Integer id, HttpSession session) {
		User user = (User) session.getAttribute(Constants.USER_SESSION_KEY);
		
		if (id == null) {
			StatusDTO dto = createValidationErrorStatusDTO(); 
			dto.getErrors().add(createErrorString(Constants.MEAL_OBJECT_KEY, Meal.ID, "field.required"));
			return new ResponseEntity<StatusDTO>(dto, HttpStatus.BAD_REQUEST);
		}
		
		Meal meal = new Meal();
		meal.setUserId(user.getId());
		meal.setId(id);
		
		caloriesService.deleteMeal(meal);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@RequestMapping(value="/meal/today", produces = "application/json", method = RequestMethod.GET)
	public  @ResponseBody List<Meal> todayMeals(HttpSession session) {
		User user = (User) session.getAttribute(Constants.USER_SESSION_KEY);
		return caloriesService.getTodayUserMeals(user);
	}

	@RequestMapping(value="/meal/get", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody List<Meal> getMeals(
	        @RequestParam(value = "date") DateParam date,
	        HttpSession session) {
		User user = (User) session.getAttribute(Constants.USER_SESSION_KEY);
		return caloriesService.getUserMealsByDate(user, date.getDate());
	}

	
	@ExceptionHandler(value = Exception.class)
	public @ResponseBody ResponseEntity<StatusDTO> processExceptiom(Exception e) {
		log.log(Level.SEVERE, "Processing error", e);
		
		StatusDTO dto = new StatusDTO();
		List<String> errors = new ArrayList<>();

		if (e.getClass().isAssignableFrom(HttpMessageNotReadableException.class)) {
			dto.setValidationError(Boolean.TRUE);
			errors.add("Input message is unparseable.");
		} else {
			dto.setSystemError(Boolean.TRUE);
			errors.add("System exception, report error.");
		}
		
		dto.setErrors(errors);
		
	    return new ResponseEntity<StatusDTO>(dto, HttpStatus.BAD_REQUEST);
	}
	
	private StatusDTO createValidationErrorStatusDTO() {
		StatusDTO dto = new StatusDTO();
		dto.setValidationError(Boolean.TRUE);
		List<String> errorList = new ArrayList<>();
		dto.setErrors(errorList);
		
		return dto;
	}
	
	
	private void putErrors(StatusDTO dto, BindingResult errors) {
		Iterator<FieldError> it = errors.getFieldErrors().iterator();
		while (it.hasNext()) {
			FieldError next = it.next();
			dto.getErrors().add(createErrorString(next.getObjectName(), next.getField(), next.getCode()));
		}
	}
	
	private String createErrorString(String object, String field, String code) {
		return MessageFormat.format("{0}.{1}.{2}", new Object [] { object, field, code });
	}
}
