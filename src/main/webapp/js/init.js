jQuery(window).ready(function() {
	var MEAL_PAGE = 'meal';
	var WORKOUT_PAGE = 'workout';
	
	var currentPage = MEAL_PAGE;

	$('#date-filter').val(moment().format("DD/MMM/YYYY"));
	
	var table = jQuery('#datatable').DataTable({
		sDom : 'CT<"clear">flprtip',
		"ajax" : {
			"url" : "meal/get?date=" + encodeURI($('#date-filter').val()),
			"dataSrc" : "",
			"method" : 'GET'
		},
		"bPaginate" : false,
		"bAutoWidth" : false,
		"bLengthChange" : false,
		"order" : [ [ 2, "asc" ] ],
		"columns" : [ {
			"data" : "name"
		}, {
			"data" : "calories"
		}, {
			"data" : "created"
		} ],

		"paging" : true,
		"displayLength" : 50,
		"sPaginationType" : "bootstrap",
		"columnDefs" : [ {
			"sClass" : "dt-body-right",
			"sType" : 'numeric',
			"targets" : 1
		}, 
		{ "type": "date-euro", targets: 2 },
		],
		
		"initComplete" : function(settings, json) {
			$('#datatable tbody').on('click', 'tr', function() {
				if ($(this).hasClass('active')) {
					$(this).removeClass('active');
				} else {
					table.$('tr.active').removeClass('active');
					$(this).addClass('active');
					selectedRow = table.rows(this).data()
				}
			});
		}
	});
	
	$('#filter-button').unbind('click').bind('click', function() {
		reloadTable();
	});
	
	$('#meals-button').unbind('click').bind('click', function() {
		if (currentPage != MEAL_PAGE) {
			currentPage = MEAL_PAGE;
			$('#button-title').html('&nbsp;&nbsp;Meals&nbsp;&nbsp;');
			reloadTable();
		}
	});
	
	$('#workouts-button').unbind('click').bind('click', function() {
		if (currentPage != WORKOUT_PAGE) {
			currentPage = WORKOUT_PAGE;
			$('#button-title').html('&nbsp;&nbsp;Workouts&nbsp;&nbsp;');
			reloadTable();
		}
	});
	
	$('#create-new').unbind('click').bind('click', function() {
		$('#modal-header-title').text('Create Item');
		$('#input-id').val('');
		$('#input-name').val('');
		$('#input-calories').val('');
		$('#datetimepicker').val('');
		$('#myModal').modal('show');
	});

	$('#edit-item').unbind('click').bind('click', function() {
		var row = table.row('.active').data();
		if (typeof row != 'undefined') {
			$('#modal-header-title').text('Edit Item');
			$('#input-id').val(row.id);
			$('#input-name').val(row.name);
			$('#input-calories').val(row.calories);
			$('#datetimepicker').val(row.created);
			$('#myModal').modal('show');
		}
	});
	
	$('#remove-item').unbind('click').bind('click', function() {
		var row = table.row('.active').data();
		if (typeof row != 'undefined') {
			$.ajax({
				type : "POST",
				url : currentPage + '/delete' + '/' + row.id,
				success : function(answer) {
					table.ajax.reload();
				}
			});
		}
	});
	
	$('#datetimepicker').datetimepicker({
		format : 'DD/MMM/YYYY HH:mm',
		sideBySide : true,
		showTodayButton : true,
		toolbarPlacement : 'default'
	});
	
	
	$('#date-filter').datetimepicker({
		format : 'DD/MMM/YYYY',
	});

	$('#save-button').unbind().bind('click', function() {
		var errors = [];//validateForm();
		if (errors.length > 0) {
			var errString = '';
			for (var error in errors) {
				errString += errors[error];
				errString += '<br/>';
			}
			$('#errors-holder').html(errString);
			$('#error-alert').css('display', 'block');
			return;
		} else {
			$('#errors-holder').html('');
			$('#error-alert').css('display', 'none');
			 
			var id = ($('#input-id').val() != "" ? id = parseInt( $('#input-id').val()) : null);
			var url = (id == null ?  currentPage + '/save' : currentPage+ '/edit');
	
			var obj = {
				id : id,
				name : $('#input-name').val(),
				calories : $('#input-calories').val(),
				created : $('#datetimepicker').val(),
				userId : null,
			};
	
			$.ajax({
				type : "POST",
				url : url,
				data : JSON.stringify(obj),
				success : function(answer) {			
					$('#input-id').val('');
					$('#input-name').val('');
					$('#input-calories').val('');
					$('#datetimepicker').val('');
					$('#myModal').modal('hide');
					table.ajax.reload();
				},
				error : function(xhrResult) {
					console.log(xhrResult);//responseText
					//if (xhrResult.status == 400)
				},
				dataType : 'json',
				contentType : 'application/json',
				mimeType : 'application/json'
			});
		}
	});
	
	function reloadTable() {
		table.ajax.url( currentPage+ '/get?date=' + encodeURI($('#date-filter').val())).load();
	}
	
	function validateForm() {
		var errors = [];
		if ($('#input-name').val() == "") {
			errors.push ("Name should not be empty.");
		}
		if ($('#input-calories').val() != "") {
			var calories = parseInt($('#input-calories').val());
			if (isNaN(calories)) {
				errors.push("Calories is not a valid number.")
			}
			if (calories <= 0) {
				errors.push("Calories should be positive number.");
			}
		} else {
			errors.push ("Calories should not be empty.");
		}
		if (!moment($('#datetimepicker').val(), ["DD/MMM/YYYY HH:mm"]).isValid()) {
			errors.push ("Date is not valid.");
		}
		
		return errors;
	}
});