package com.spring.project.caleTeGym.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.spring.project.caleTeGym.entity.FriendRequest;
import com.spring.project.caleTeGym.entity.Group;
import com.spring.project.caleTeGym.entity.GroupRequest;
import com.spring.project.caleTeGym.entity.InComingMail;
import com.spring.project.caleTeGym.entity.Meal;
import com.spring.project.caleTeGym.entity.OutComingMail;
import com.spring.project.caleTeGym.entity.Product;
import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.service.MealService;
import com.spring.project.caleTeGym.service.ProductService;
import com.spring.project.caleTeGym.service.UserService;

@Controller 
public class DietController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MealService mealService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value={"/writeDiet"}, method = RequestMethod.GET)
	public ModelAndView writeDiet() 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("currentUser", user);
		System.out.println(user.getPupils().size());
		modelAndView.setViewName("/writeDiet");
		
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value={"/create_breakfast"}, method = RequestMethod.POST)
	public void create_breakfast(@RequestBody String data, HttpServletRequest request) throws ParseException 
	{
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		try {
			jsonObject = new JSONObject(data);
			jsonArray = (JSONArray) jsonObject.get("breakfast");
			
			String[] arrOfStr = jsonObject.get("date").toString().split("-");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(arrOfStr[0]));
			cal.set(Calendar.MONTH, Integer.parseInt(arrOfStr[1]) -1);
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arrOfStr[2]));
			Date date = cal.getTime();
			
			int pupilId = (int) jsonObject.get("userId");
			List<Product> products = new ArrayList<Product>();;
			User pupil = userService.getOne(pupilId);
			for(Object jsonProduct: jsonArray){
				
				JSONObject	tempProduct = (JSONObject) jsonProduct;
				Product product = new Product();
				String name = (String) tempProduct.get("name");
				int calories = (int) tempProduct.get("calories");
				String protein = tempProduct.get("protein").toString().replace("g", "");
				String fat = tempProduct.get("fat").toString().replace("g", "");
				System.out.println("AAAAAAAAAAAAA" + tempProduct.get("carbohydrates"));
				String carbohydrates = tempProduct.get("carbohydrates").toString().replace("g", "");
				String image = (String) tempProduct.get("image");
				
				product.setCalories(calories);
				product.setName(name);
				product.setProtein((int) Double.parseDouble(protein));
				product.setFat((int) Double.parseDouble(fat));
				product.setCarbohydrates((int) Double.parseDouble(carbohydrates));
				product.setImage(image);
				
				productService.save(product);
				products.add(product);
			}
			 	Meal meal = new Meal();
			 	meal.setDate(date);
			 	meal.setProducts(products);
			 	meal.setTrainer(user);
			 	mealService.save(meal);
			 	
			 	pupil.getBreakfast().add(meal);
			 	userService.saveOnlyUser(pupil);
		}catch (JSONException err){
		    System.out.println(err);
		}
	}
	
	@ResponseBody
	@RequestMapping(value={"/create_second_breakfast"}, method = RequestMethod.POST)
	public void create_second_breakfast(@RequestBody String data, HttpServletRequest request) throws ParseException 
	{
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		try {
			jsonObject = new JSONObject(data);
			jsonArray = (JSONArray) jsonObject.get("secondBreakfast");
			
			String[] arrOfStr = jsonObject.get("date").toString().split("-");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(arrOfStr[0]));
			cal.set(Calendar.MONTH, Integer.parseInt(arrOfStr[1]) -1);
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arrOfStr[2]));
			Date date = cal.getTime();

			int pupilId = (int) jsonObject.get("userId");
			List<Product> products = new ArrayList<Product>();;
			User pupil = userService.getOne(pupilId);
			for(Object jsonProduct: jsonArray){
				
				JSONObject	tempProduct = (JSONObject) jsonProduct;
				Product product = new Product();
				String name = (String) tempProduct.get("name");
				int calories = (int) tempProduct.get("calories");
				String protein = tempProduct.get("protein").toString().replace("g", "");
				String fat = tempProduct.get("fat").toString().replace("g", "");
				System.out.println("AAAAAAAAAAAAA" + tempProduct.get("carbohydrates"));
				String carbohydrates = tempProduct.get("carbohydrates").toString().replace("g", "");
				String image = (String) tempProduct.get("image");
				
				product.setCalories(calories);
				product.setName(name);
				product.setProtein((int) Double.parseDouble(protein));
				product.setFat((int) Double.parseDouble(fat));
				product.setCarbohydrates((int) Double.parseDouble(carbohydrates));
				product.setImage(image);
				
				productService.save(product);
				products.add(product);
			}
			 	Meal meal = new Meal();
			 	meal.setDate(date);
			 	meal.setProducts(products);
			 	meal.setTrainer(user);
			 	mealService.save(meal);
			 	
			 	pupil.getSecondBreakfast().add(meal);
			 	userService.saveOnlyUser(pupil);
		}catch (JSONException err){
		    System.out.println(err);
		}
	}

	
	@ResponseBody
	@RequestMapping(value={"/create_dinner"}, method = RequestMethod.POST)
	public void create_dinner(@RequestBody String data, HttpServletRequest request) throws ParseException 
	{
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		try {
			jsonObject = new JSONObject(data);
			jsonArray = (JSONArray) jsonObject.get("dinner");
			String[] arrOfStr = jsonObject.get("date").toString().split("-");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(arrOfStr[0]));
			cal.set(Calendar.MONTH, Integer.parseInt(arrOfStr[1]) -1);
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arrOfStr[2]));
			Date date = cal.getTime();

			int pupilId = (int) jsonObject.get("userId");
			List<Product> products = new ArrayList<Product>();;
			User pupil = userService.getOne(pupilId);
			for(Object jsonProduct: jsonArray){
				
				JSONObject	tempProduct = (JSONObject) jsonProduct;
				Product product = new Product();
				String name = (String) tempProduct.get("name");
				int calories = (int) tempProduct.get("calories");
				String protein = tempProduct.get("protein").toString().replace("g", "");
				String fat = tempProduct.get("fat").toString().replace("g", "");
				System.out.println("AAAAAAAAAAAAA" + tempProduct.get("carbohydrates"));
				String carbohydrates = tempProduct.get("carbohydrates").toString().replace("g", "");
				String image = (String) tempProduct.get("image");
				
				product.setCalories(calories);
				product.setName(name);
				product.setProtein((int) Double.parseDouble(protein));
				product.setFat((int) Double.parseDouble(fat));
				product.setCarbohydrates((int) Double.parseDouble(carbohydrates));
				product.setImage(image);
				
				productService.save(product);
				products.add(product);
			}
			 	Meal meal = new Meal();
			 	meal.setDate(date);
			 	meal.setProducts(products);
			 	meal.setTrainer(user);
			 	mealService.save(meal);
			 	
			 	pupil.getDinner().add(meal);
			 	userService.saveOnlyUser(pupil);
		}catch (JSONException err){
		    System.out.println(err);
		}
	}
	
	@ResponseBody
	@RequestMapping(value={"/create_tea"}, method = RequestMethod.POST)
	public void create_tea(@RequestBody String data, HttpServletRequest request) throws ParseException 
	{
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		try {
			jsonObject = new JSONObject(data);
			jsonArray = (JSONArray) jsonObject.get("tea");
			
			String[] arrOfStr = jsonObject.get("date").toString().split("-");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(arrOfStr[0]));
			cal.set(Calendar.MONTH, Integer.parseInt(arrOfStr[1]) -1);
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arrOfStr[2]));
			Date date = cal.getTime();
			
			int pupilId = (int) jsonObject.get("userId");
			List<Product> products = new ArrayList<Product>();;
			User pupil = userService.getOne(pupilId);
			for(Object jsonProduct: jsonArray){
				
				JSONObject	tempProduct = (JSONObject) jsonProduct;
				Product product = new Product();
				String name = (String) tempProduct.get("name");
				int calories = (int) tempProduct.get("calories");
				String protein = tempProduct.get("protein").toString().replace("g", "");
				String fat = tempProduct.get("fat").toString().replace("g", "");
				System.out.println("AAAAAAAAAAAAA" + tempProduct.get("carbohydrates"));
				String carbohydrates = tempProduct.get("carbohydrates").toString().replace("g", "");
				String image = (String) tempProduct.get("image");
				
				product.setCalories(calories);
				product.setName(name);
				product.setProtein((int) Double.parseDouble(protein));
				product.setFat((int) Double.parseDouble(fat));
				product.setCarbohydrates((int) Double.parseDouble(carbohydrates));
				product.setImage(image);
				
				productService.save(product);
				products.add(product);
			}
			 	Meal meal = new Meal();
			 	meal.setDate(date);
			 	meal.setProducts(products);
			 	meal.setTrainer(user);
			 	mealService.save(meal);
			 	
			 	pupil.getTea().add(meal);
			 	userService.saveOnlyUser(pupil);
		}catch (JSONException err){
		    System.out.println(err);
		}
	}
	
	@ResponseBody
	@RequestMapping(value={"/create_supper"}, method = RequestMethod.POST)
	public void create_supper(@RequestBody String data, HttpServletRequest request) throws ParseException 
	{
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		try {
			jsonObject = new JSONObject(data);
			jsonArray = (JSONArray) jsonObject.get("supper");
			
			String[] arrOfStr = jsonObject.get("date").toString().split("-");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(arrOfStr[0]));
			cal.set(Calendar.MONTH, Integer.parseInt(arrOfStr[1]) -1);
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arrOfStr[2]));
			Date date = cal.getTime();

			int pupilId = (int) jsonObject.get("userId");
			List<Product> products = new ArrayList<Product>();;
			User pupil = userService.getOne(pupilId);
			for(Object jsonProduct: jsonArray){
				
				JSONObject	tempProduct = (JSONObject) jsonProduct;
				Product product = new Product();
				String name = (String) tempProduct.get("name");
				int calories = (int) tempProduct.get("calories");
				String protein = tempProduct.get("protein").toString().replace("g", "");
				String fat = tempProduct.get("fat").toString().replace("g", "");
				System.out.println("AAAAAAAAAAAAA" + tempProduct.get("carbohydrates"));
				String carbohydrates = tempProduct.get("carbohydrates").toString().replace("g", "");
				String image = (String) tempProduct.get("image");
				
				product.setCalories(calories);
				product.setName(name);
				product.setProtein((int) Double.parseDouble(protein));
				product.setFat((int) Double.parseDouble(fat));
				product.setCarbohydrates((int) Double.parseDouble(carbohydrates));
				product.setImage(image);
				
				productService.save(product);
				products.add(product);
			}
			 	Meal meal = new Meal();
			 	meal.setDate(date);
			 	meal.setProducts(products);
			 	meal.setTrainer(user);
			 	mealService.save(meal);
			 	
			 	pupil.getSupper().add(meal);
			 	userService.saveOnlyUser(pupil);
		}catch (JSONException err){
		    System.out.println(err);
		}
	}
	
	@RequestMapping(value={"/show_my_diet"}, method = RequestMethod.GET)
	public ModelAndView show_my_diet() 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("my_diet");
		
		return modelAndView;
	}
	
	 //It is calling when user choose date
	 @ResponseBody
	 @RequestMapping(value={"/show_diet"}, method = RequestMethod.GET)
	 public String show_diet(@RequestParam("date") String date) throws JsonProcessingException
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
		Diet diet = new Diet();
		String dietJson = "";
			for(Meal meal : currentUser.getBreakfast()) {
				if(isTheSameDate(meal.getDate(), choosenDate)) {					
					diet.setBreakfast(meal.getProducts());
				}				
			}
			for(Meal meal : currentUser.getDinner()) {
				if(isTheSameDate(meal.getDate(), choosenDate)) {					
					diet.setDinner(meal.getProducts());
				}				
			}
			for(Meal meal : currentUser.getSecondBreakfast()) {
				if(isTheSameDate(meal.getDate(), choosenDate)) {					
					diet.setSecondBreakfast(meal.getProducts());
				}				
			}
			for(Meal meal : currentUser.getTea()) {
				if(isTheSameDate(meal.getDate(), choosenDate)) {					
					diet.setTea(meal.getProducts());
				}				
			}
			for(Meal meal : currentUser.getSupper()) {
				if(isTheSameDate(meal.getDate(), choosenDate)) {					
					diet.setSupper(meal.getProducts());
				}				
			}
			ObjectMapper mapper = new ObjectMapper();
		    
			//Converting the Object to JSONString
			dietJson = mapper.writeValueAsString(diet);
		    System.out.println(dietJson);
		
		    return dietJson;
	}
	 
	 @ResponseBody
	 @RequestMapping(value={"/getUserPhoto"}, method = RequestMethod.GET)
	 public String nameAndLastname(@RequestParam("userId") int userId)
	 {
	 	User user = userService.getOne(userId);
	 	System.out.println(user.getPhoto());
	       
	 	return user.getPhoto();
	 }
	 
	 public boolean isToday(Date date) {
		 
		 Date todaysDate = new Date();
		 
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(todaysDate);
		 int todayYear = calendar.get(Calendar.YEAR);
		 int todayDay = calendar.get(Calendar.DAY_OF_MONTH);
		 int todayMonth = calendar.get(Calendar.MONTH);
		 
		 calendar.setTime(date);
		 
		 int choosenDateYear = calendar.get(Calendar.YEAR);
		 int choosenDateDay = calendar.get(Calendar.DAY_OF_MONTH);
		 int choosenDateMonth = calendar.get(Calendar.MONTH);
		 
		 return todayYear == choosenDateYear && todayDay == choosenDateDay && todayMonth == choosenDateMonth;				 
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
