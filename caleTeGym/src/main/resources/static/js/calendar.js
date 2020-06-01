$('#create_event_btn').on('click', function(){
	$('#content').addClass('low_opacity');
	$('#event_form').fadeIn(2000);	
});

$('.x').on('click',function(){
	
	$('#event_form').fadeOut(200);
	$('#content').removeClass('low_opacity');
});

$('#info_form').on('submit',function(e){
	e.preventDefault();
	var isCorrect = true;
	var date = $('#date').val();
	var content = $('#event_content').val();
	if(date == ''){		
		$('#date_error').removeClass('hide');
		isCorrect = false;
	} else{
		$('#date_error').addClass('hide');
	}
	if(content == ''){		
		$('#content_error').removeClass('hide');
		isCorrect = false;		
	}else{
		$('#content_error').addClass('hide');
	}
	
	if(isCorrect){
		var event = {
 				date: date,
				content:content
 			}
		$.ajax({
	 		type: "POST",
	 		url: 'save_event',
	 		timeout: 500,
	 		contentType : 'application/json',
	 		data: JSON.stringify(event),
	        success: function () {
	        	$('#event_form').fadeOut(200);
	        	$('#content').removeClass('low_opacity');
	        	
	        },
	 		fail: function(){
	 			console.log("fail");
	 		},
	 		error: function(e){
	 			console.log(e);
	 		}
	 	});
	}
});


$('#show_event_for_today').on('click', function(){
	$.ajax({
		type: "GET",
		url: "show_events",
		timeout: 5000,
		contentType : 'application/json',
		dataType: 'json',
		crossDomain: true,
		success: function (data) {
			$('.event').remove();
			$('#no_events_alert').addClass('hide');
			if(data.length != 0){
				for(var temp of data) {
				  console.log(temp);
				  $('.events').append('<div class="event"><div class="event_date">' + temp.date + '</div>' + temp.content + '</div>');
				}
			} else{
				$('#no_events_alert').removeClass('hide');
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





