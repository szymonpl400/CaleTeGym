$('#create_target_btn').on('click', function(){
	$('#content').addClass('low_opacity');
	$('#target_form').fadeIn(2000);	
});

$('.x').on('click',function(){
	
	$('#target_form').fadeOut(200);
	$('#content').removeClass('low_opacity');
});

$('#target_info_form').on('submit',function(e){
	e.preventDefault();
	$('.target').remove();
	$('.target_action').remove();
	var TargetContent = $('#target_content').val();
	if(TargetContent == ''){		
		$('#content_error').removeClass('hide');
	} else{
		$('#content_error').addClass('hide');
		var target = {
				content: TargetContent
 			}
		$.ajax({
	 		type: "POST",
	 		url: 'save_target',
	 		timeout: 500,
	 		contentType : 'application/json',
	 		data: JSON.stringify(target),
	        success: function () {
	        	var remainingTargetsNumber = parseInt($('#remainingTargetsNumber').text());
				$('#remainingTargetsNumber').text(remainingTargetsNumber + 1);
	        	$('#target_form').fadeOut(200);
	        	$('#content').removeClass('low_opacity');
	        	$('#no_targets_alert').addClass('hide');
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


$('#show_remaining_targets_btn').on('click', function(){
	$('#no_targets_alert').addClass('hide');
	$.ajax({
		type: "GET",
		url: "show_remaining_targets",
		timeout: 5000,
		contentType : 'application/json',
		dataType: 'json',
		crossDomain: true,
		success: function (data) {
			
			$('.target').remove();
			$('.target_action').remove();
			if(data.length != 0){
				for(var temp of data) {
					$('.targets').prepend('<div class="target" id="target-' + temp.id +'">' + temp.content + '</div><div class="target_action"><button type="button" class="btn btn-success complete_target_btn" data-targetId="' + temp.id + '">Complete</button></div>');
				}
			} else{
				$('#no_targets_alert').removeClass('hide');
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


$('body').on('click', '.complete_target_btn', function(e){
	$('#no_targets_alert').addClass('hide');	
	var $el = $(e.target);
	var targetId = $el.data('targetid');
	$.ajax({
		type: "GET",
		url: "complete_target?targetId=" + targetId,
		timeout: 500,
		contentType : 'application/json',
		crossDomain: true,
		success: function () {
			$('#target-' + targetId).remove();
			$el.parent().remove();
			var remainingTargetsNumber = parseInt($('#remainingTargetsNumber').text());
			var completedTargetsNumber = parseInt($('#completedTargetsNumber').text());
			$('#remainingTargetsNumber').text(remainingTargetsNumber - 1);
			$('#completedTargetsNumber').text(completedTargetsNumber + 1);
			
    	},
		fail: function(){
			console.log("fail");
		},
		error: function(e){
			console.log(e);
		}
	});
});

$('#show_completed_targets_btn').on('click', function(){
	$('#no_targets_alert').addClass('hide');
	console.log($('#remainingTargetsNumber').text());
	$.ajax({
		type: "GET",
		url: "show_completed_targets",
		timeout: 5000,
		contentType : 'application/json',
		dataType: 'json',
		crossDomain: true,
		success: function (data) {
			$('.target').remove();			
			if(data.length != 0){
				for(var temp of data) {
			    $('.targets').prepend('<div class="target" id="target-' + temp.id +'">' + temp.content + '</div>');
				}
			} else{
				$('#no_targets_alert').removeClass('hide');
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
