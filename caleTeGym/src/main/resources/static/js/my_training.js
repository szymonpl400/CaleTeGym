var trainings;

$('#date').on('change', function(){
	var date = ($('#date').val());
	
	$.ajax({
		url: 'show_training?date=' + date,
		type: 'GET',
	    contentType : 'application/json',
	    dataType: 'json',
	    success: function(data){
	    	$('#training_list').empty();
	    	trainings = data;
	    	for(var i=0; i<data.length; i++){
		    	
		    	$('#training_list').append('<option data-id=' + data[i].id + '>' + data[i].trainerName + '</option>')
		    }
	    	
	    	$('#training_list').removeClass('hide');
	    },
	    error: function(e){
		    console.log(e);
	    }
	});
})

$('body').on('click', '#training_list', function(e){
	$('#training_container').empty();
	var $el = $(e.target);
	var currentOption = $el.children("option:selected");
	console.log(trainings);
	for(var i=0; i<trainings.length; i++){
		if(currentOption.data('id') == trainings[i].id){
			for(var y = 0; y < trainings[i].exercises.length; y++){
				$('#training_container').append('<div class="exercise"><div class="exerciseName">' + trainings[i].exercises[y].name + '</div><div class="exerciseNote" id="exerciseNote-' + y + '" white-space="pre-line"><span></span></div><div class="seriesContainer"></div></div>');				
				$('#exerciseNote-' + y).html(trainings[i].exercises[y].note.split('/n').join('\n'));
				for(var z = 0; z < trainings[i].exercises[y].series.length; z++){
					$('.seriesContainer').append('<div class="series">Res: ' + trainings[i].exercises[y].series[z].reps +' Weight: ' + trainings[i].exercises[y].series[z].weight + ' Time: ' + trainings[i].exercises[y].series[z].time +' </div>')
				}
			}
		}
		console.log(trainings[i]);
	}
})