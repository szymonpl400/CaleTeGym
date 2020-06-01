
$('.pupil_list').on('click', function(e){
	var $el = $(e.target);
	
	if($('#date').val()){
		$('#date_text').remove();
		$('#choose_user').remove();
		$('#date').addClass('hide');
		$('.alert-danger').remove();
		date = $('#date').val();
		$('#container').append('<ul class="list-group body_list"><li class="list-group-item">Chest</li><li class="list-group-item">Forearms</li><li class="list-group-item">Lats</li><li class="list-group-item">Middle Back</li><li class="list-group-item">Lower Back</li><li class="list-group-item">Neck</li><li class="list-group-item">Quadriceps</li><li class="list-group-item">Hamstrings</li><li class="list-group-item">Calves</li><li class="list-group-item">Triceps</li><li class="list-group-item">Traps</li><li class="list-group-item">Shoulders</li><li class="list-group-item">Abdominals</li><li class="list-group-item">Glutes</li><li class="list-group-item">Biceps</li><li class="list-group-item">Adductors</li><li class="list-group-item">Abductors</li></ul>');
		
	} else {
			$('#container').prepend('<div class="alert alert-danger" role="alert">Please choose date.</div>');
	}
});


$('body').on('click', '.body_list', function(e){
		var $el = $(e.target);
		switch ($el.text()){
		
		case 'Chest':
		    $('#excercise_list').empty().append('<option>Dumbbell Bench Press</option><option>Pushups</option><option>Close-Grip Barbell Bench Press</option><option>Dumbbell Flyes</option><option>Incline Dumbbell Press</option><option>Low Cable Crossover</option><option>Barbell Bench Press - Medium Grip</option><option>Dips - Chest Version</option><option>Decline Dumbbell Flyes</option><option>Bodyweight Flyes</option><option>Incline Cable Flye</option><option>Decline Barbell Bench Press</option><option>Wide-Grip Barbell Bench Press</option><option>Wide-Grip Decline Barbell Bench Press</option><option>Incline Dumbbell Press Reverse-Grip</option>')
		    break;
		    
		case 'Forearms':
			$('#excercise_list').empty().append('<option>Rickshaw Carry</option><option>Palms-Down Wrist Curl Over A Bench</option><option>Wrist Rotations with Straight Bar</option><option>Farmers Walk</option><option>Palms-Up Barbell Wrist Curl Over A Bench</option><option>Standing Palms-Up Barbell Behind The Back Wrist Curl</option><option>Finger Curls</option><option>Seated Two-Arm Palms-Up Low-Pulley Wrist Curl</option><option>Wrist Roller</option><option>Seated One-Arm Dumbbell Palms-Up Wrist Curl</option><option>Seated Palms-Down Barbell Wrist Curl</option><option>Dumbbell Lying Supination</option><option>Dumbbell Lying Pronation</option><option>Seated Palm-Up Barbell Wrist Curl</option><option>Palms-Up Dumbbell Wrist Curl Over A Bench</option>')
		    break;  

		case 'Lats':
			$('#excercise_list').empty().append('<option>Weighted Pull Ups</option><option>Pullups</option><option>Chin-Up</option><option>Rocky Pull-Ups/Pulldowns</option><option>V-Bar Pulldown</option><option>Wide-Grip Pull-Up</option><option>Muscle Up</option><option>Shotgun Row</option><option>Close-Grip Front Lat Pulldown</option><option>V-Bar Pullup</option><option>Rope Climb</option><option>Wide-Grip Rear Pull-Up</option><option>Rope Straight-Arm Pulldown</option><option>Wide-Grip Lat Pulldown</option><option>Underhand Cable Pulldowns</option>')
		    break;
			
		case 'Middle Back':
			$('#excercise_list').empty().append('<option>T-Bar Row with Handle</option><option>Reverse Grip Bent-Over Rows</option><option>One-Arm Dumbbell Row</option><option>One-Arm Long Bar Row/Pulldowns</option><option>T-Bar Row</option><option>Bent Over Two-Arm Long Bar Row</option><option>Bent Over One-Arm Long Bar Row</option><option>Rowing, Stationary</option><option>Seated Cable Rows</option><option>Dumbbell Incline Row</option><option>Bent Over Two-Dumbbell Row With Palms In</option><option>Bodyweight Mid Row</option><option>Bent Over Barbell Row</option><option>Seated One-arm Cable Pulley Rows</option><option>Alternating Renegade Row</option>')
		    break;
			
		case 'Lower Back':
			$('#excercise_list').empty().append('<option>Atlas Stones</option><option>Deficit Deadlift</option><option>Hyperextensions (Back Extensions)</option><option>Axle Deadlift</option><option>Hyperextensions With No Hyperextension Bench</option><option>Deadlift with Bands</option><option>Deadlift with Chains</option><option>Rack Pull with Bands</option><option>Superman</option><option>Weighted Ball Hyperextension</option><option>Rack Pulls</option><option>Seated Back Extension</option><option>Seated Good Mornings</option><option>Stiff Leg Barbell Good Morning</option><option>Atlas Stone Trainer</option>')
		    break;
			
		case 'Neck':
			$('#excercise_list').empty().append('<option>Lying Face Down Plate Neck Resistance</option><option>Lying Face Up Plate Neck Resistance</option><option>Seated Head Harness Neck Resistance</option><option>Isometric Neck Exercise - Sides</option><option>Isometric Neck Exercise - Front And Back</option><option>Side Neck Stretch</option>')
		    break;

		case 'Quadriceps':
			$('#excercise_list').empty().append('<option>Single-Leg Press</option><option>Clean from Blocks</option><option>Barbell Full Squat</option><option>Tire flip</option><option>Barbell back squat to box</option><option>Push-press</option><option>Snatch</option><option>Hang Clean</option><option>Reverse Band Box Squat</option><option>Jumping rope</option><option>Barbell walking lunge</option><option>Front Squats With Two Kettlebells</option><option>Single Leg Push-off</option><option>Olympic Squat</option><option>Stair climber</option>')
		    break;
			
		case 'Hamstrings':
			$('#excercise_list').empty().append('<option>Barbell Deadlift</option><option>Romanian Deadlift With Dumbbells</option><option>Clean Deadlift</option><option>Sumo deadlift</option><option>Romanian Deadlift from Deficit</option><option>Power Snatch</option><option>Power Clean from Blocks</option><option>Natural Glute Ham Raise</option><option>Floor Glute-Ham Raise</option><option>Snatch Deadlift</option><option>Lying Leg Curls</option><option>Stiff-Legged Dumbbell Deadlift</option><option>Exercise ball leg curl</option><option>Kettlebell pass-through lunge</option><option>Power clean</option>')
		    break;
			
		case 'Calves':
			$('#excercise_list').empty().append('<option>Smith Machine Calf Raise</option><option>Standing Calf Raises</option><option>Lateral lunge</option><option>Seated Calf Raise</option><option>Calf Press On The Leg Press Machine</option><option>Rocking Standing Calf Raise</option><option>Calf Press</option><option>Lateral ape</option><option>Barbell Seated Calf Raise</option><option>Balance Board</option><option>Weighted donkey calf raise</option><option>Smith Machine Reverse Calf Raises</option><option>Dumbbell Seated One-Leg Calf Raise</option><option>Ankle Circles</option><option>Peroneals-SMR</option>')
		    break;
			
		case 'Triceps':
			$('#excercise_list').empty().append('<option>Triceps dip</option><option>Decline EZ-bar skullcrusher</option><option>Dumbbell floor press</option><option>Cable V-bar push-down</option><option>Weighted bench dip</option><option>EZ-Bar Skullcrusher</option><option>Reverse Grip Triceps Pushdown</option><option>Medicine ball cowboy squat</option><option>Push-Ups - Close Triceps Position</option><option>Kneeling cable triceps extension</option><option>Single-arm cable triceps extension</option><option>Triceps Pushdown - Rope Attachment</option><option>Seated triceps press</option><option>Incline EZ-bar skullcrusher</option><option>Decline Close-Grip Bench To Skull Crusher</option>')
		    break;
			
		case 'Traps':
			$('#excercise_list').empty().append('<option>Smith machine shrug</option><option>Leverage Shrug</option><option>Standing dumbbell shrug</option><option>Single-arm landmine pull and press</option><option>Kettlebell Sumo High Pull</option><option>Calf-Machine Shoulder Shrug</option><option>Barbell shrug</option><option>Barbell behind-the-back shrug</option><option>Cable straight-bar upright row</option><option>Cable shrug</option><option>Smith machine behind-the-back shrug</option><option>Upright Row - With Bands</option><option>Smith machine upright row</option><option>Clean Shrug</option><option>Scapular Pull-Up</option>')
		    break;
			
		case 'Shoulders':
			$('#excercise_list').empty().append('<option>Hollow-body knee tuck</option><option>Dumbbell front raise to lateral raise</option><option>Clean and press</option><option>Single-arm palm-in dumbbell shoulder press</option><option>Clean and jerk</option><option>Single-arm kettlebell push-press</option><option>Military press</option><option>Standing palms-in shoulder press</option><option>Seated barbell shoulder press</option><option>Seated Dumbbell Press</option><option>Standing dumbbell shoulder press</option><option>Single-arm lateral raise</option><option>Power Partials</option><option>Incline dumbbell reverse fly</option><option>Overhead dumbbell front raise</option>')
		    break;
			
		case 'Abdominals':
			$('#excercise_list').empty().append('<option>Bear crawl hold</option><option>Lunge high-knee clap</option><option>Elbow plank</option><option>Bottoms Up</option><option>Suspended ab fall-out</option><option>Dumbbell V-Sit Cross Jab</option><option>Standing Cable Lift</option><option>Dumbbell spell caster</option><option>Decline reverse crunch</option><option>Spider crawl</option><option>Cocoons</option><option>Cross-Body Crunch</option><option>Single-arm high-cable side bend</option><option>Elbow-to-knee crunch</option><option>Decline Crunch</option>')
		    break;
			
		case 'Glutes':
			$('#excercise_list').empty().append('<option>Barbell glute bridge</option><option>Barbell Hip Thrust</option><option>Single-leg cable hip extension</option><option>Glute bridge</option><option>Single-leg glute bridge</option><option>Step-up with knee raise</option><option>Kettlebell thruster</option><option>Kneeling Squat</option><option>Flutter Kicks</option><option>Glute Kickback</option><option>Exercise ball hip thrust</option><option>Kneeling Jump Squat</option><option>Pull Through</option><option>Hip Extension with Bands</option><option>Piriformis SMR</option>')
		    break;
			
		case 'Biceps':
			$('#excercise_list').empty().append('<option>Incline Hammer Curls</option><option>Wide-grip barbell curl</option><option>EZ-bar spider curl</option><option>Hammer Curls</option><option>EZ-Bar Curl</option><option>Zottman Curl</option><option>Biceps curl to shoulder press</option><option>Barbell Curl</option><option>Concentration curl</option><option>Flexor Incline Dumbbell Curls</option><option>Machine Bicep Curl</option><option>Overhead Cable Curl</option><option>Dumbbell Bicep Curl</option><option>Close-grip EZ-bar curl</option><option>Cross-body hammer curl</option>')
		    break;
			
		case 'Adductors':
			$('#excercise_list').empty().append('<option>Thigh adductor</option><option>Groiners</option><option>Band Hip Adductions</option><option>Side Leg Raises</option><option>Lateral hop</option><option>Groin and Back Stretch</option><option>Adductor SMR</option><option>Side Lying Groin Stretch</option><option>Adductor/Groin</option><option>Lying Bent Leg Groin</option><option>Carioca quick step</option><option>Lateral cone hop</option><option>Lateral box jump</option><option>Side-To-Side Adductor Stretch</option><option>HM Left Leg Swing</option>')
		    break;
			
		case 'Abductors':
			$('#excercise_list').empty().append('<option>Hip Circles (prone)</option><option>Standing Hip Circles</option><option>Clam</option><option>Smith machine end-grip shoulder press</option><option>Thigh abductor</option><option>Fire Hydrant</option><option>Windmills</option><option>Monster Walk</option><option>IT Band and Glute Stretch</option><option>Single-leg lying cross-over stretch</option><option>Standing hip circle</option><option>Lateral Band Walk</option><option>Dynamic Pigeon</option><option>Cross-over jack</option><option>Traveling thigh killa</option>')
		    break;
		}
		$('#excercise_list').removeClass('hide');
});

$('body').on('change', '#excercise_list', function(e){
	var $el = $(e.target);
	console.log($el);
	$('#series_text').removeClass('hide');
	$('#series_list').removeClass('hide');
});

$('body').on('change', '#series_list', function(e){
	var $el = $(e.target);
	console.log($el.val());
	$('#series_details').removeClass('hide');
})
