(function() {
	
	var elements = {
		$postList: $("#postList"),
		$postElements: $(".postElements"),
		$groupList: $('#group_list'),
		$new_comment_button: $('.new_comment_button')
	};
	var userId = $('#heart_href').data('user');
	var nameAndLastname = '';
	$.get( "nameAndLastname?userId=" + userId, function( data ) {
		  nameAndLastname = data.split(':');

		});
	var userPhoto;

	$.get( "getUserPhoto?userId=" + userId, function( data ) {
	userPhoto = data;
	});

	elements.$postList.on('click',function(e){
		
		var $el = $(e.target);
		if($el.attr('id') === 'heart'){
			var postId = $el.parent().data('post');
			var groupId = $el.parent().data('group');
			var userId = $el.parent().data('user');
			var like = {
					postId: postId,
					groupId:  groupId,
					userId: userId
			}
		 	
			$.ajax({
		 		type: "POST",
		 		url: 'like',
		 		timeout: 500,
		 		contentType : 'application/json',
		 		data: JSON.stringify(like),
		        success: function () {// success callback function
		        	console.log($el.parent().parent().find('#number_of_likes').get(0));
		        	$el.toggleClass('heart_white heart');
		        	
		        	if($el.attr('class') === 'heart'){
		        		 var valText = $el.parent().parent().find('#number_of_likes').text();
		        		 var likes = parseInt(valText) + 1;
		        		 $el.parent().parent().find('#number_of_likes').text(likes).get(0)
		        		 console.log(likes);
		        		 
		        	}
		        	else{
		        		var valText = $el.parent().parent().find('#number_of_likes').text();
		        		var likes = parseInt(valText) - 1;
		        		$el.parent().parent().find('#number_of_likes').text(likes).get(0)
		        		console.log(likes);
		        	}
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
	
	elements.$new_comment_button.on('click',function(e){
		var $el = $(e.target);
		var postId = $el.data('post');
		var $comment = $('#new_comment-' + postId);
		var comment = {
				postId: postId,
				comment: $comment.val()
		}
		if($comment.val()){
			$.ajax({
		 		type: "POST",
		 		url: 'comment',
		 		timeout: 500,
		 		contentType : 'application/json',
		 		data: JSON.stringify(comment),
		        success: function (data) {
		        	var $commentList = $el.next().next();
		        	var date = new Date();
		        	var dateText = date.getFullYear() + '-' + date.getMonth() + '-' + date.getDay();
		        	var html = '<li class="commentElements">'+
								    			'<div class="comment">'+
								    				'<div class="post_user_info">'+
								    				'<div class="comment_owner_photo">'+ '<img src="' + userPhoto + '" class="img-responsive center-block" id="postUserPhoto"/>' + '</div>'+
														'<div class="comment_owner_name_lastname">'+
															'<a class="name_lastname_span" th:href=@{/friend(userId=${comment.getOwner().id})}>' + nameAndLastname[0] + " " + nameAndLastname[1] + '</a><br>' +
															'<span class="postDate">'+ formatDate(date) +'</span>' +
														'</div>' +
													'</div>' +
													'<div class="comment_frame">' +
														'<span class="comment_content">' + comment.comment + '</span>'+
													'</div>' +
								    			'</div>' +
								    		'</li>';
		        	$commentList.prepend(html);
		        },
		 		fail: function(){
		 			console.log("fail");
		 		},
		 		error: function(e){
		 			console.log(e);
		 		}
		 	});
		} else{
			
		}
		
	})
	function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('-');
}
	
}());
