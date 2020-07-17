var $pupilList = $('.pupil_list');
var $content = $('#content');
var $container = $('#container');
var $breakfast_button = $('#breakfast');
var userId;
var date;
var userPhoto;

var age;
var weight;
var growth;
var bmi;
var caloricDemand;
var name;
var lastname;

$pupilList.on('click', function(e){
	var $el = $(e.target);
	age = $el.data('age');
	weight = $el.data('weight');
	growth = $el.data('growth');
	bmi = $el.data('bmi');
	userId = $el.data('id');
	caloricDemand = $el.data('demand');
	name = $el.data('name');
	lastname = $el.data('lastname');
	userPhoto = $el.data('photo');
	$('#left_side_menu').removeClass('hide');
	if($('#right_side_menu')){
		$('#right_side_menu').remove();
	}	
	
	if($('#date').val()){
		$content.append('<div id="right_side_menu"><div id="user_photo"><img src="' + userPhoto + '" class="img-responsive center-block" id="postUserPhoto"/></div><div id="pupil_name"><span>' + name+ '</span><br><span>' + lastname+ '</span></div><div id="user_info"><span>Age: ' + age+ '</span><br><span>Weight: ' + weight+ '</span><br><span>Growth: ' + growth+ '</span><br><span>BMI: ' + bmi+ '</span><br><span>Caloric Demand: ' + caloricDemand+ '</span><br></div></div>');
		$('#meal_buttons').removeClass('hide');
		$('#date_text').addClass('hide');
		$('#choose_user').addClass('hide');
		$('#date').addClass('hide');
		$('.alert-danger').remove();
		date = $('#date').val();
	} else {
			$container.prepend('<div class="alert alert-danger" role="alert">Please choose date.</div>');
	}
});

$('.meal').on('click', function(e){
	$('#find_product').removeClass('hide');
	$('.meal').each(function() {
	  $(this).removeClass('btn-success');
	  $(this).addClass('btn-info');
	});
	$(e.target).removeClass('btn-info');
	$(e.target).addClass('btn-success');
	if($('.products')){
		$('.products').remove();
	}
	if($('.added_product')){
		$('.added_product').remove();
	}
		$('#save_meal').removeClass('hide');
});

$('#search').on('click', function(){
	var product = $('#product').val();
	$('#spinner').removeClass('hide');
	var data1;
	var counter = 0;
	$.ajax({
		type: "GET",
		url: "https://cors-anywhere.herokuapp.com/https://api.spoonacular.com/food/products/search?query=" + product + "&apiKey=ebbd34d035a44655a44d5f88d26a75b3",
		timeout: 50000,
		headers: {'Access-Control-Allow-Origin': 'https://api.spoonacular.com' },
		contentType : 'application/json',
		dataType: 'json',
		crossDomain: true,
		beforeSend: setHeader,
		success: function (data) {
			$('.products').remove();
			for(var i=0; i < data.products.length - 1; i++){
				$.ajax({
					type: "GET",
					url: "https://cors-anywhere.herokuapp.com/https://api.spoonacular.com/food/products/" + data.products[i].id + "?apiKey=ebbd34d035a44655a44d5f88d26a75b3",
					timeout: 50000,
					headers: {'Access-Control-Allow-Origin': 'https://api.spoonacular.com' },
					contentType : 'application/json',
					dataType: 'json',
					crossDomain: true,
					beforeSend: setHeader,
					success: function (productInformations) {
						$container.append('<div class="products"><div class="product_photo"><img src="' + data.products[counter].image + '" class="img-responsive center-block"/></div><div class="product_informations"><div class="left_side_product_informations"><span>Name: "' + data.products[counter].title + '"</span><br><span>Kcal: "' + productInformations.nutrition.calories + '"</span><br><span>Protein: "' + productInformations.nutrition.protein + '"</span><br><span>Fat: "' + productInformations.nutrition.fat+ '"</span><br><span>Carbohydrates: "' + productInformations.nutrition.carbs + '"</span><br></div></div><div class="right_side_of_product_informations"><button type="button" class="btn btn-success add_product_btn" data-name="' + data.products[counter].title + '" data-calories="' + productInformations.nutrition.calories + '" data-protein="' + productInformations.nutrition.protein + '" data-fat="' + productInformations.nutrition.fat+ '" data-carbohydrates="' + productInformations.nutrition.carbs +'" data-image="' + data.products[counter].image +'">Add Product</button></div></div>')
						counter++;
						$('#spinner').addClass('hide');
					},
					fail: function(){
						console.log("fail");
					},
					error: function(e){
						console.log(e);
					}
				});
			}
    	},
		fail: function(){
			console.log("fail");
		},
		error: function(e){
			console.log(e);
		}
	});
});

function setHeader(xhr) {
	xhr.setRequestHeader('Authorization', 'ebbd34d035a44655a44d5f88d26a75b3');
}

$('body').on('click', '.add_product_btn', function(e){
	var $el = $(e.target);
	var name = $el.data('name') ;
	var calories = $el.data('calories') ;
	var protein = $el.data('protein') ;
	var fat = $el.data('fat') ;
	var carbs = $el.data('carbohydrates') ;
	var img = $el.data('image');
	console.log('name: ' + name + ' ' + 'calories: ' + calories + ' ' + 'protein: ' + protein + ' ' + 'fat: ' + fat + ' ' + 'carbs: ' + carbs);
	$('#left_side_menu').prepend('<div class="added_product" data-name="' + name + '" data-calories="' + calories + '" data-protein="' + protein + '" data-fat="' + fat + '" data-carbs="' + carbs + '" data-image="' + img + '"><div class="product_photo"><img src="' + img + '" class="img-responsive center-block"/></div><div class="product_informations"><div class="left_menu_left_side_product_informations"><span>Name: "' + name + '"</span><br><span>Kcal: "' + calories + '"</span><br><span>Protein: "' + protein + '"</span><br><span>Fat: "' + fat+ '"</span><br><span>Carbohydrates: "' + carbs + '"</span><br></div></div><div class="right_side_of_product_informations"><button type="button" class="btn btn-danger remove_product_btn">Remove Product</button></div></div>')
	
	

});

$('body').on('click', '.remove_product_btn', function(e){
    var $el = $(e.target);
	$el.parent().parent().remove();
});

$('#save_meal').on('click', function(){
	var $mealType = $('.meal.btn-success');
	switch ($mealType.attr('id')) {
		case 'breakfast':
			var breakfast = [];
			$('.added_product').each(function(){
				var name = $(this).data('name') ;
				var calories = $(this).data('calories') ;
				var protein = $(this).data('protein') ;
				var fat = $(this).data('fat') ;
				var carbs = $(this).data('carbs') ;
				console.log(carbs);
				var image= $(this).data('image');
				var meal = {
						name: name,
						calories: calories,
						protein: protein,
						fat: fat,
						carbohydrates: carbs,
						image: image
				};
				breakfast.push(meal);			
			});			
			var object = {
					breakfast: breakfast,
					date: date,
					userId: userId
			};
			$.ajax({
		 		type: "POST",
		 		url: 'create_breakfast',
		 		timeout: 5000,
		 		contentType : 'application/json',
		 		data: JSON.stringify(object),
		        success: function () {
		        	if($('.products')){
		        		$('.products').remove();
		        	}
		        	if($('.added_product')){
		        		$('.added_product').remove();
		        	}
		        },
		 		fail: function(){
		 			console.log("fail");
		 		},
		 		error: function(e){
		 			console.log(e);
		 		}
		 	});
			
		  break;
		case 'second_breakfast':
			var secondBreakfast = [];
			$('.added_product').each(function(){
				var name = $(this).data('name') ;
				var calories = $(this).data('calories') ;
				var protein = $(this).data('protein') ;
				var fat = $(this).data('fat') ;
				var carbs = $(this).data('carbs') ;
				console.log(carbs);
				var image= $(this).data('image');
				var meal = {
						name: name,
						calories: calories,
						protein: protein,
						fat: fat,
						carbohydrates: carbs,
						image: image
				};
				secondBreakfast.push(meal);			
			});			
			var object = {
					secondBreakfast: secondBreakfast,
					date: date,
					userId: userId
			};
			$.ajax({
		 		type: "POST",
		 		url: 'create_second_breakfast',
		 		timeout: 5000,
		 		contentType : 'application/json',
		 		data: JSON.stringify(object),
		        success: function () {
		        	if($('.products')){
		        		$('.products').remove();
		        	}
		        	if($('.added_product')){
		        		$('.added_product').remove();
		        	}
		        },
		 		fail: function(){
		 			console.log("fail");
		 		},
		 		error: function(e){
		 			console.log(e);
		 		}
		 	});
		  break;
		case 'dinner':
			var dinner = [];
			$('.added_product').each(function(){
				var name = $(this).data('name') ;
				var calories = $(this).data('calories') ;
				var protein = $(this).data('protein') ;
				var fat = $(this).data('fat') ;
				var carbs = $(this).data('carbs') ;
				console.log(carbs);
				var image= $(this).data('image');
				var meal = {
						name: name,
						calories: calories,
						protein: protein,
						fat: fat,
						carbohydrates: carbs,
						image: image
				};
				dinner.push(meal);			
			});			
			var object = {
					dinner: dinner,
					date: date,
					userId: userId
			};
			$.ajax({
		 		type: "POST",
		 		url: 'create_dinner',
		 		timeout: 5000,
		 		contentType : 'application/json',
		 		data: JSON.stringify(object),
		        success: function () {
		        	if($('.products')){
		        		$('.products').remove();
		        	}
		        	if($('.added_product')){
		        		$('.added_product').remove();
		        	}
		        },
		 		fail: function(){
		 			console.log("fail");
		 		},
		 		error: function(e){
		 			console.log(e);
		 		}
		 	});
		  break;
		case 'tea':
			var tea = [];
			$('.added_product').each(function(){
				var name = $(this).data('name') ;
				var calories = $(this).data('calories') ;
				var protein = $(this).data('protein') ;
				var fat = $(this).data('fat') ;
				var carbs = $(this).data('carbs') ;
				var image= $(this).data('image');
				var meal = {
						name: name,
						calories: calories,
						protein: protein,
						fat: fat,
						carbohydrates: carbs,
						image: image
				};
				tea.push(meal);			
			});			
			var object = {
					tea: tea,
					date: date,
					userId: userId
			};
			$.ajax({
		 		type: "POST",
		 		url: 'create_tea',
		 		timeout: 5000,
		 		contentType : 'application/json',
		 		data: JSON.stringify(object),
		        success: function () {
		        	if($('.products')){
		        		$('.products').remove();
		        	}
		        	if($('.added_product')){
		        		$('.added_product').remove();
		        	}
		        },
		 		fail: function(){
		 			console.log("fail");
		 		},
		 		error: function(e){
		 			console.log(e);
		 		}
		 	});
		  break;
		case 'supper':
			var supper = [];
			$('.added_product').each(function(){
				var name = $(this).data('name') ;
				var calories = $(this).data('calories') ;
				var protein = $(this).data('protein') ;
				var fat = $(this).data('fat') ;
				var carbs = $(this).data('carbs') ;
				console.log(carbs);
				var image= $(this).data('image');
				var meal = {
						name: name,
						calories: calories,
						protein: protein,
						fat: fat,
						carbohydrates: carbs,
						image: image
				};
				supper.push(meal);			
			});			
			var object = {
					supper: supper,
					date: date,
					userId: userId
			};
			$.ajax({
		 		type: "POST",
		 		url: 'create_supper',
		 		timeout: 5000,
		 		contentType : 'application/json',
		 		data: JSON.stringify(object),
		        success: function () {
		        	if($('.products')){
		        		$('.products').remove();
		        	}
		        	if($('.added_product')){
		        		$('.added_product').remove();
		        	}
		        },
		 		fail: function(){
		 			console.log("fail");
		 		},
		 		error: function(e){
		 			console.log(e);
		 		}
		 	});
		  break;
	} 
});




