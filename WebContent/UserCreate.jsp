<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Home Page</title>
</head>
	<body>
	<style type="text/css">
		.form-control {
 		width:300px;
		}
		
		.btn{
		width:300px;
		}
		
		#signInBlock{
			top:50%;
			width:500px;
			margin:0 auto;
			background-collor:#66ffff;
		}
		.container{
		left:50%;
		}
		#error{
		color: rgb(201, 76, 76);
		}
	</style>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">	
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/Electronics/home">Home Page</a>
				</div>	
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li>
								<a href="/Electronics/Blog">Blogs</a>
						</li>					
						<li class="dropdown">
				          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Parts <span class="caret"></span></a>
				          <ul class="dropdown-menu">
				            <li><a href="/Electronics/findcpuprocessor">CPU</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="/Electronics/findgraphiccard">Graphic Cards</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="/Electronics/findrammemory">Ram Memory</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="/Electronics//findharddisk">Hard Disks</a></li>
				          </ul>
	       				 </li>
	       				<li>		
							<a href="/Electronics/Recommendation">Recommendation</a>
						</li>	       				 
						<li>		
							<a href="/Electronics/finddesktop">Desktop</a>
						</li>
						<li>
							<a href="/Electronics/findlaptop">Laptop</a>
						</li>
						<li>
							<a href="${Logstatehref}">${Logstate}</a>
						</li> 
					</ul>
				</div>
			</div>
		</nav>
		<br>
		<br>
		<br>
			<div id ="signInBlock"class="container">
			<form method="post" class="form-signin">
			<div class="form-label-group">
			<br/>
			<h2 class="form-signin-heading">Create a User</h2>
			</div>
			<div class="form-label-group">
			<label for="inputUsername" class="sr-only">Username</label>
			<input id="inputUsername" class="form-control" placeholder="Username" name="username" value="${fn:escapeXml(param.username)}" required autofocus>
			</div>
			<div class="form-label-group">
			<label for="inputPassword" class="sr-only">Password</label>
			<input id="inputPassword" class="form-control" placeholder="Password" name="password" value="${fn:escapeXml(param.password)}" required>
			</div>
			<div class="form-label-group">
			<label for="firstname" class="sr-only">First Name</label>
			<input id="firstname" class="form-control" placeholder="firstname" name="firstname" value="${fn:escapeXml(param.firstname)}" required>
			</div>
			<div class="form-label-group">
			<label for="lastname" class="sr-only">Last Name</label>
			<input id="lastname" class="form-control" placeholder="lastname" name="lastname" value="${fn:escapeXml(param.lastname)}" required>
			</div>
			<div class="form-label-group">
			<label for="email" class="sr-only">Email</label>
			<input id="email" class="form-control" placeholder="email" name="email" value="${fn:escapeXml(param.email)}" required>
			</div>
			<div class="form-label-group">
			<label for="phonenumber" class="sr-only">Phone Number</label>
			<input id="phonenumber" class="form-control" placeholder="phonenumber" name="phonenumber" value="${fn:escapeXml(param.phonenumber)}" required>
			</div>
			<p>
					<span id="error"><b>${messages.success}</b></span>
			</p>
				
				<p>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Create</button>
				</p>
			</form>
			<br/>
		</div>
			<br/><br/>
			<p>
				<span id="successMessage"><b>${messages.success}</b></span>
			</p>
	   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
   	   <script src="js/bootstrap.min.js"></script>
	</body>
</html>