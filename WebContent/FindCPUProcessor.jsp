<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
<title>Find CPU by Parameters</title>
</head>
	<style type="text/css">
		.page-header {
 		font-style:italic;
 		font-size: 20px;
		}
		.sub-header {
		font-variant: small-caps;
 		font-size: 20px;
 		color:#0040ff
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
		.pure-input-rounded{
		width:150px
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
	<div class="container-fluid">
	<div class="row">
	<div class="col-sm-3 col-md-2 sidebar">
	<form class="pure-form" action="findcpuprocessor" method="post">
		<br>
	<h2 class="sub-header">Filters</h2>
		<p>
			<input class="pure-input-rounded" id="series" name="series" placeholder="Series" value="${fn:escapeXml(param.series)}">
		</p>
		<p>
			<input class="pure-input-rounded" id="cache" name="cache" placeholder="Cache" value="${fn:escapeXml(param.cache)}">
		</p>
		<p>
			<input class="pure-input-rounded" id="cacheSize" name="cacheSize" placeholder="Cache Size" value="${fn:escapeXml(param.cacheSize)}">
		</p>
		<p>
			<button type="submit" class="pure-button pure-button-primary">Submit</button>
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	</div>
	<div class="col-sm-offset-3 col-md-offset-2 main">
	<h1 class="page-header">Matching CPU</h1>
	<div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>Product Name</th>
                <th>Price</th>
                <th>Rating</th>
                <th>Number of Reviews</th>
                <th>Series</th>
                <th>Cache</th>
                <th>CacheSize</th>
            </tr>
            <c:forEach items="${cpuprocessors}" var="cpuprocessor" >
                <tr>
                    <td><a href="${cpuprocessor.getName_hef()}"><c:out value="${cpuprocessor.getProductName()}" /></a></td>
                    <td><c:out value="${cpuprocessor.getPrice()}" /></td>
                    <td><c:out value="${cpuprocessor.getRating()}" /></td>
                    <td><c:out value="${cpuprocessor.getNumber_of_Reviews()}" /></td>
                    <td><c:out value="${cpuprocessor.getSeries()}" /></td>
                    <td><c:out value="${cpuprocessor.getCache()}" /></td>
                    <td><c:out value="${cpuprocessor.getCacheSize()}" /></td>	
                </tr>
            </c:forEach>
       </table>
       </div>
    </div>
    </div>
    </div>
	   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
   	   <script src="js/bootstrap.min.js"></script>
	</body>
</html>