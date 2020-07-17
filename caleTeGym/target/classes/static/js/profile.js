elements = {
	$updateInformationsBtn: $('.btn-update-info'),
	$userInfoForm: $('#user_info_form'),
	$content: $('#content'),
	$close: $('.x'),
	$infoForm: $('#info_form')
}

elements.$updateInformationsBtn.on('click', function(){
	elements.$content.addClass('low_opacity');
	elements.$userInfoForm.fadeIn(2000);	
});

elements.$close.on('click',function(){
	
	elements.$userInfoForm.fadeOut(200);
	elements.$content.removeClass('low_opacity');
});

elements.$infoForm.on('submit', function(e){
	e.preventDefault()
	var age = elements.$infoForm.find('#age').val();
	var growth = elements.$infoForm.find('#growth').val();
	var weight = elements.$infoForm.find('#weight').val();
	var sex = $('input[name="Radios"]:checked').val();
	var caloricForMale = 66.5 + (13.7 * weight) + (5 * growth) - (6.8 * age);
	var caloricForFemale = 655 + (9.6 * weight) + (1.85 * growth) - (4.7 * age);
	var bmi = (weight/Math.pow((growth/100), 2)).toFixed(4);
	userInformations = {
			age: age,
			growth: growth,
			weight: weight,
			sex: sex,
	}
	if(sex === 'male'){
		userInformations.caloricDemand = caloricForMale.toFixed(0);
	} else{
		userInformations.caloricDemand = caloricForFemale.toFixed(0);
	}
	if(weight && growth){
		userInformations.bmi = bmi;
	}
	
	$.ajax({
 		type: "POST",
 		url: 'setUserInfo',
 		timeout: 500,
 		contentType : 'application/json',
 		data: JSON.stringify(userInformations),
        success: function () {
        	elements.$content.removeClass('low_opacity');
        	elements.$userInfoForm.fadeOut(200);
        	$('#age_info').text('Age: ' + age);
        	$('#weight_info').text('Weight: ' + weight);
        	$('#growth_info').text('Growth: ' + growth);
        	if(weight && growth){
        		$('#bmi_info').text('BMI: ' + bmi);
        	}
        	if(weight && growth && age){
	        	if(sex === 'male'){		
	        		$('#caloricDemand_info').text('Calorid Demand: ' +  caloricForMale.toFixed(0));
	        	} else{	
	        		$('#caloricDemand_info').text('Calorid Demand: ' + caloricForFemale.toFixed(0));
	        	}
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

//$('').on('submit', function(e){
//	console.log('aaa');
//	e.preventDefault();
//});

$('body').on("submit", "#imageForm", function(e) {
    window.location.href = window.location.href;
    console.log('aaa');
});

