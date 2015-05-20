
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Diet Diary Application</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
<link href="css/bootstrap-datatable.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" media="screen"
	href="css/bootstrap-datetimepicker.css">

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Diet Diary</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar" id="menu-container">
				<ul class="nav nav-sidebar">
					<li><a href="#" id="meals-button">Meals</a></li>
					<li><a href="#" id="workouts-button">Workouts</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="btn-group">
					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						<span id="button-title">&nbsp;&nbsp;Meals &nbsp;&nbsp;</span><span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#" id="create-new">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;Create New</a>
						</li>
						<li><a href="#" id="edit-item">
							<span class="glyphicon glyphicon-pencil"></span>&nbsp;Edit Item</a>
						</li>
						<li><a href="#" id="remove-item">
							<span class="glyphicon glyphicon-minus"></span>&nbsp;Remove Item</a>
						</li>		
					</ul>
				</div>
				<br/><br/>
				<form class="form-inline">
				  <div class="form-group">
				    <input type="text" class="form-control" id="date-filter" >
				  </div>
  				<button type="button" class="btn btn-default" id="filter-button">Filter</button>
				</form>
				<hr />
				<div class="table-responsive">
					<table class="table table-striped table-bordered" id="datatable">
						<thead>
							<tr>
								<th>Name</th>
								<th>Calories</th>
								<th>Date</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="footer">
			<p class="text-center">© Company 2015.</p>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">

		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="modal-header-title"></h4>
				</div>
				<div class="modal-body">
				
				<div class="alert alert-danger alert-dismissible" role="alert" style="display:none;" id="error-alert">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <strong>Validation Errors:</strong><div id="errors-holder"></div>
				</div>
								
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">Name</label>
							<div class="col-sm-10">
								<input type="hidden" id="input-id" value="">
								<input type="text" class="form-control" placeholder="Name"
									id="input-name">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Calories</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" placeholder="Calories"
									id="input-calories">
							</div>
						</div>

						<div class="form-group">
							<label for="input-date" class="col-sm-2 control-label">Date</label>
							<div class="col-sm-10">
								<input type='text' class="form-control" id='datetimepicker' />
							</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="save-button">Save</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
        ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="jquery/jquery-1.11.1.js" type="text/javascript"></script>
	<script src="jquery/scroll-to.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/moment.js" type="text/javascript"></script>
	<script src="js/collapse.js" type="text/javascript"></script>
	<script src="js/jquery.dataTables.js" type="text/javascript"></script>
	<script src="js/dataTables.bootstrap.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="js/init.js"></script>
</body>
</html>
