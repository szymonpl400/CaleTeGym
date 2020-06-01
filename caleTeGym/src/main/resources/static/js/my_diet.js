$('#date').on('change', function(){
	var date = ($('#date').val());
	
	$.ajax({
		url: 'show_diet?date=' + date,
		type: 'GET',
	    contentType : 'application/json',
	    dataType: 'json',
	    success: function(data){		 
		    console.log("Success my diet");
		    console.log(data);
		    $('.products').remove();
		    if (typeof data.breakfast !== 'undefined' && data.breakfast !== null) {		       
			    for(var breakfast of data.breakfast) {
			    	$('#breakfast').append('<div class="products"><div class="products_image"><img src="' + breakfast.image + '" class="img-responsive center-block"/></div><div class="products_details"><span>Name: "' + breakfast.name + '"</span><br><span>Kcal: "' + breakfast.calories + '"</span><br><span>Protein: "' + breakfast.protein + '"g</span><br><span>Fat: "' + breakfast.fat + '"g</span><br><span>Carbohydrates: "' + breakfast.carbohydrates + '"g</span></div></div>');    
			    }
		    }
		    if (typeof data.secondBreakfast !== 'undefined' && data.secondBreakfast !== null) {
			    for(var secondBreakfast of data.secondBreakfast) {
			    	$('#second_breakfast').append('<div class="products"><div class="products_image"><img src="' + secondBreakfast.image + '" class="img-responsive center-block"/></div><div class="products_details"><span>Name: "' + secondBreakfast.name + '"</span><br><span>Kcal: "' + secondBreakfast.calories + '"</span><br><span>Protein: "' + secondBreakfast.protein + '"g</span><br><span>Fat: "' + secondBreakfast.fat + '"g</span><br><span>Carbohydrates: "' + secondBreakfast.carbohydrates + '"g</span></div></div>');    
			    }
		    }
		    if (typeof data.dinner !== 'undefined' && data.dinner !== null) {
			    for(var dinner of data.dinner) {
			    	$('#dinner').append('<div class="products"><div class="products_image"><img src="' + dinner.image + '" class="img-responsive center-block"/></div><div class="products_details"><span>Name: "' + dinner.name + '"</span><br><span>Kcal: "' + dinner.calories + '"</span><br><span>Protein: "' + dinner.protein + '"g</span><br><span>Fat: "' + dinner.fat + '"g</span><br><span>Carbohydrates: "' + dinner.carbohydrates + '"g</span></div></div>');    
			    }
		    }
		    if (typeof data.tea !== 'undefined' && data.tea !== null) {
			    for(var tea of data.tea) {
			    	$('#tea').append('<div class="products"><div class="products_image"><img src="' + tea.image + '" class="img-responsive center-block"/></div><div class="products_details"><span>Name: "' + tea.name + '"</span><br><span>Kcal: "' + tea.calories + '"</span><br><span>Protein: "' + tea.protein + '"g</span><br><span>Fat: "' + tea.fat + '"g</span><br><span>Carbohydrates: "' + tea.carbohydrates + '"g</span></div></div>');    
			    }
		    }
		    if (typeof data.supper !== 'undefined' && data.supper !== null) {
			    for(var supper of data.supper) {
			    	$('#supper').append('<div class="products"><div class="products_image"><img src="' + supper.image + '" class="img-responsive center-block"/></div><div class="products_details"><span>Name: "' + supper.name + '"</span><br><span>Kcal: "' + supper.calories + '"</span><br><span>Protein: "' + supper.protein + '"g</span><br><span>Fat: "' + supper.fat + '"g</span><br><span>Carbohydrates: "' + supper.carbohydrates + '"g</span></div></div>');    
			    }
		    }
	    },
	    error: function(e){
		    console.log(e);
	    }
	});
})

//<div class="products">
//	<div class="products_image"></div>
//	<div class="products_details">
//	<span>Name: Pizza</span><br>
//	<span>Kcal: 200</span><br>
//	<span>Protein: 20g</span><br>
//	<span>Fat: 5g</span><br>
//	<span>Carbohydrates: 25;</span>
//	</div>
//</div>