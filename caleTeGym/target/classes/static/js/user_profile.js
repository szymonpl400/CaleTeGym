var $addOrRemoveFriendbtn = $('#addOrRemoveFriend');
var userId = $addOrRemoveFriendbtn.data('user');
console.log(userId);
$addOrRemoveFriendbtn.on('click', function(){
	var className = $addOrRemoveFriendbtn.attr('class');
	if(className === 'btn btn-success'){
		$.ajax({
		  url: '/add_friend',
		  data: 'userId=' + userId,
		  success: function(){
			 
			 $addOrRemoveFriendbtn.removeClass('btn-success');
			 $addOrRemoveFriendbtn.text('Invitation Sent');
			 $addOrRemoveFriendbtn.addClass('btn-secondary');
		  },
		});
	} else if(className === 'btn btn-danger'){
		$.ajax({
		  url: '/removeFriend',
		  type: 'GET',
		  data: 'userId=' + userId,
		  contentType : 'application/json',
		  dataType: 'text',
		  success: function(data){		 
			 $addOrRemoveFriendbtn.removeClass('btn-danger');
			 $addOrRemoveFriendbtn.text('Add Friend');
			 $addOrRemoveFriendbtn.addClass('btn-success');
		  },
		  error: function(e){
	 			console.log(e);
	 	  }
		});	
	} else {
		
	}
	
});

$('#train').on('click', function(e){
	var $el = $(e.target);
	var $trainButton;
	var trainer = $el.data('trainer');
	var pupil = $el.data('pupil');
	
	
	if($el.attr('class') == 'btn btn-warning' || $el.attr('class') == 'btn btn-danger' || $el.attr('class') == 'btn btn-secondary'){
		$trainButton = $el;
	} else {
		$trainButton = $el.parent();
	};

	trainer = $trainButton.data('trainer');
	pupil = $trainButton.data('pupil');
	
	var Users = {
		pupil: pupil,
		trainer: trainer
	}
		
		if($el.attr('class') == 'btn btn-warning'){
			$.ajax({
		 		type: "POST",
		 		url: 'createTrainerRequest',
		 		timeout: 500,
		 		contentType : 'application/json',
		 		data: JSON.stringify(Users),
		        success: function () {
		        	$trainButton.removeClass('btn-warning');
		        	$trainButton.addClass('btn-secondary');
		        	$trainButton.html('Invitation Sent  <img src="https://img.icons8.com/material-outlined/30/000000/dumbbell.png"/>');
		        },
		 		fail: function(){
		 			console.log("fail");
		 		},
		 		error: function(e){
		 			console.log(e);
		 		}
		 	});
			
		} else if($el.attr('class') == 'btn btn-danger') {
			$.ajax({
		 		type: "POST",
		 		url: 'stopTraining',
		 		timeout: 500,
		 		contentType : 'application/json',
		 		data: JSON.stringify(Users),
		        success: function () {
		        	$trainButton.removeClass('btn-danger');
		        	$trainButton.addClass('btn-warning');
		        	$trainButton.html('Train  <img src="https://img.icons8.com/material-outlined/30/000000/dumbbell.png"/>');
		        },
		 		fail: function(){
		 			console.log("fail");
		 		},
		 		error: function(e){
		 			console.log(e);
		 		}
		 	});

		} else {
			
		}
	});

