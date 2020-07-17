package com.spring.project.caleTeGym.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project.caleTeGym.entity.Diet;
import com.spring.project.caleTeGym.entity.Exercise;
import com.spring.project.caleTeGym.entity.FriendRequest;
import com.spring.project.caleTeGym.entity.Meal;
import com.spring.project.caleTeGym.entity.Product;
import com.spring.project.caleTeGym.entity.Series;
import com.spring.project.caleTeGym.entity.Training;
import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.repository.TrainingRepository;
import com.spring.project.caleTeGym.service.ExerciseService;
import com.spring.project.caleTeGym.service.MealService;
import com.spring.project.caleTeGym.service.ProductService;
import com.spring.project.caleTeGym.service.SeriesService;
import com.spring.project.caleTeGym.service.TrainingService;
import com.spring.project.caleTeGym.service.UserService;

@Controller 
public class TrainingController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MealService mealService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	TrainingService trainingService;
	
	@Autowired
	ExerciseService exerciseService;
	
	@Autowired
	SeriesService seriesService;
	
	@RequestMapping(value={"/writeTraining"}, method = RequestMethod.GET)
	public ModelAndView writeDiet() 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("currentUser", user);
		modelAndView.setViewName("/writeTraining");
		
		return modelAndView;
	}
	
	@RequestMapping(value={"/show_my_training"}, method = RequestMethod.GET)
	public ModelAndView show_my_diet() 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("my_training");
		
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value={"/create_training"}, method = RequestMethod.POST)
	public void create_breakfast(@RequestBody String data, HttpServletRequest request) throws ParseException 
	{
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User trainer = userService.findUserByEmail(auth.getName());
		
		try {
				jsonObject = new JSONObject(data);
				System.out.println(jsonObject);
				JSONArray exercises = jsonObject.getJSONArray("exercises");
				
				System.out.println(exercises);
				Training training = new Training();
				List<Exercise> exercisesList  = new ArrayList();
				for (int i = 0; i < exercises.length(); i++) {
					
				    JSONObject rec = exercises.getJSONObject(i);
				    JSONArray sets = rec.getJSONArray("sets");
				    
				    String name = (String) rec.get("name");
				    System.out.println("Exercise name: " + name);
				    
				    String note = (String) rec.get("note");
				    System.out.println("Exercise note: " + note);
			    	
				    Exercise exercise = new Exercise();
				    List<Series> series = new ArrayList<>();
				    for (int x = 0; x < sets.length(); x++) {
				    	Series tempSeries = new Series();
				    	JSONObject a = (JSONObject) sets.get(x);
				    	tempSeries.setReps(Integer.parseInt((String) a.get("reps")));
				    	tempSeries.setWeight(Float.parseFloat((String) a.get("weight")));
				    	tempSeries.setTime(Integer.parseInt((String) a.get("time")));
				    	
				    	System.out.println("Reps: " + a.get("reps"));
				    	System.out.println("Weight: " + a.get("weight"));
				    	System.out.println("Time: " + a.get("time"));
				    	series.add(tempSeries);
				    	seriesService.saveOneSeries(tempSeries);
				    	System.out.println("Series nr. "+ x + " " + sets.get(i));
				    }
				    exercise.setName(name);
				    exercise.setNote(note);
				    exercise.setSeries(series);
					exerciseService.saveExercise(exercise);
				    exercisesList.add(exercise);
					
				}
				
				//parsing to format YYYY-MM-DD
				String[] arrOfStr = jsonObject.get("date").toString().split("-");
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, Integer.parseInt(arrOfStr[0]));
				cal.set(Calendar.MONTH, Integer.parseInt(arrOfStr[1]) -1);
				cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arrOfStr[2]));
				Date date = cal.getTime();
				
				User pupil = userService.getOne( (int) jsonObject.get("pupil"));
				
				training.setDate(date);
				training.setExercises(exercisesList);
				training.setPupil(pupil);
				training.setTrainer(trainer);
				training.setTrainerName(trainer.getName() + " " + trainer.getLastName());
				
				if(pupil.getTrainings() == null) {
					List<Training> trainings = new ArrayList();
					trainings.add(training);
					pupil.setTrainings(trainings);
					

					trainingService.saveTraining(training);
					userService.saveOnlyUser(pupil);
					
				}
				else {
					pupil.getTrainings().add(training);
					trainingService.saveTraining(training);
					userService.saveOnlyUser(pupil);
				}
				
		}catch (JSONException err){
		    System.out.println(err);
		}
	}
	
	 @ResponseBody
	 @RequestMapping(value={"/show_training"}, method = RequestMethod.GET)
	 public String show_training(@RequestParam("date") String date) throws JsonProcessingException
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userService.findUserByEmail(auth.getName());
		System.out.println(date);
		
		String[] arrOfStr = date.split("-");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(arrOfStr[0]));
		cal.set(Calendar.MONTH, Integer.parseInt(arrOfStr[1]) -1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arrOfStr[2]));
		Date choosenDate = cal.getTime();
		String trainingJson = "";
		List<Training> trainingsToShow = new ArrayList();
		List<Training> allTrainings = currentUser.getTrainings();
		
		ObjectMapper mapper = new ObjectMapper();
	    for(Training training : allTrainings) {
	    	if(isTheSameDate(training.getDate(), choosenDate)){
	    		trainingsToShow.add(training);
	    	}
	    }
	  
		//Converting the Object to JSONString
		trainingJson = mapper.writeValueAsString(trainingsToShow);
	    System.out.println(trainingJson);
	
	    return trainingJson;
	}
	 
	 public boolean isTheSameDate(Date date, Date date2) {
		  
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 int dateYear = calendar.get(Calendar.YEAR);
		 int dateDay = calendar.get(Calendar.DAY_OF_MONTH);
		 int dateMonth = calendar.get(Calendar.MONTH);
		 
		 calendar.setTime(date2);
		 
		 int date2Year = calendar.get(Calendar.YEAR);
		 int date2Day = calendar.get(Calendar.DAY_OF_MONTH);
		 int date2Month = calendar.get(Calendar.MONTH);
		 
		 return dateYear == date2Year && dateDay == date2Day && dateMonth == date2Month;				 
	 }
}
