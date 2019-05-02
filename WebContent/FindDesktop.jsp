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
<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
<title>Home Page</title>
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
	<form class="pure-form" action="finddesktop" method="post">
		<br/>
		<br/>
		<h2 class="sub-header">Filters</h2>
		<p>
			<input class="pure-input-rounded" id="operating_system" name="operating_system" placeholder="Operating System" value="${fn:escapeXml(param.operating_system)}">
		</p>
		<p>
			<input class="pure-input-rounded" id="CPU_Model_family" name="CPU_Model_family" placeholder="CPU Model Family" value="${fn:escapeXml(param.CPU_Model_family)}">
		</p>
		<p>
			<input class="pure-input-rounded" id="memory_size" name="memory_size" placeholder="Memory Size" value="${fn:escapeXml(param.memory_size)}">
		</p>
		<p>
			<input class="pure-input-rounded" id="hard_disk_size" name="hard_disk_size" placeholder="Hard Disk Size" value="${fn:escapeXml(param.hard_disk_size)}">
		</p>
		<p>
			<button type="submit" class="pure-button pure-button-primary">Submit</button>
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	</div>
	<div  class="col-sm-offset-3 col-md-offset-2 main">
	<h1 class="page-header">Matching Desktops</h1>
	<div class="table-responsize">
        <table class="table table-striped">
            <tr>
                <th>Product Name</th>
                <th>Price</th>
                <th>Rating</th>
                <th>Number of Reviews</th>
                <th>CPU series</th>
                <th>Memory Size</th>
                <th>Hard Disk Size</th>
                <th>Other</th>
            </tr>
            <c:forEach items="${desktops}" var="desktop" >
                <tr>
                    <td><a href = "${desktop.getName_hef()}"><c:out value="${desktop.getProductName()}" /></a></td>
                    <td><c:out value="${desktop.getPrice()}" /></td>
                    <td><c:out value="${desktop.getRating()}" /></td>
                    <td><c:out value="${desktop.getNumber_of_Reviews()}" /></td>
                    <td><c:out value="${desktop.getCPU_Model_Family()}" /></td>
                    <td><c:out value="${desktop.getMemory_Size()}" /></td>
                    <td><c:out value="${desktop.getHard_Disk_Size()}" /></td>	
                    <td><c:out value="${desktop.getOther()}" /></td>		
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