
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
	<style type="text/css">
		.page-header {
 		font-style:italic;
 		font-size: 40px;
 		text-align:center;
		}
		.cover-container{
		text-align:center;
		top:50%;
		}
		.inner cover{
		top:50%
		}
		.site-wrapper-inner{
		top:50%
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
			<div class="site-wrapper-inner">
			<div class="cover-container">
			<div class="inner cover">
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<h1 class="cover-heading">Welcome to Trackers' PC Parts Picker Website</h1>
			<p class="lead"> Website for Desktops, Laptops, Books and PC assembly </p>
			</div>
			</div>
			</div>
			<span id="successMessage"><b>${messages.present}</b></span>
	   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
   	   <script src="js/bootstrap.min.js"></script>
	</body>
</html>