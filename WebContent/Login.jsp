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
<link rel="stylesheet" href="css/bootstrap.css">
<link href="signin.css" rel="stylesheet">
<title>Log in</title>
</head>
	<style type="text/css">
		.form-control {
 		width:300px;
		}
		
		.btn{
		width:300px;
		}
		
		#signInBlock{
			top:50%;
			width:400px;
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
	<body>
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
				            <li><a href="#">CPU</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="#">Graphic Cards</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="#">Ram Memory</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="#">Hard Disks</a></li>
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
			<form class="form-signin">
			<div class="form-label-group">
			<h2 class="form-signin-heading">Please sign in</h2>
			<br/>
			<label for="inputUsername" class="sr-only">Username</label>
			<input type="username" id="inputUsername" class="form-control" placeholder="Username" name="uname" value="${fn:escapeXml(param.uname)}" required autofocus>
			</div>
			<div class="form-label-group">
			<label for="inputPassword" class="sr-only">Password</label>
			<input type="password" id="inputPassword" class="form-control" placeholder="Password" name="pass" value="${fn:escapeXml(param.pass)}" required>
			</div>
			<br/>
			<p id="error"><c:out value="${messages.error}" /></p>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			</form>
			<p>
			<a href="/Electronics/usercreate">new user?</a>
			</p>
			<p>
			<a href="/Electronics/userupdate">update your password?</a>
			</p>
		</div>
	   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
   	   <script src="js/bootstrap.min.js"></script>
	</body>
</html>