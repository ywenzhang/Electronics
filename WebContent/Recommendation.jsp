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
		<br>
	<div class="container-fluid">
	<div class="row">
	<div class="col-sm-3 col-md-2 sidebar">
	<form class="pure-form" action="Recommendation" method="post">
		<br>
		<h2 class="sub-header">Laptop Budget</h2>
		<p>
			<input class="pure-input-rounded" id="min" name="lowLaptop" placeholder="Min$" value="${fn:escapeXml(param.lowLaptop)}">
		</p>
		<p>
			<input class="pure-input-rounded" id="max" name="highLaptop" placeholder="Max$" value="${fn:escapeXml(param.highLaptop)}">
		</p>
		<h2 class="sub-header">Desktop Budget</h2>
		<p>
			<input class="pure-input-rounded" id="min" name="lowDesktop" placeholder="Min$" value="${fn:escapeXml(param.lowDesktop)}">
		</p>
		<p>
			<input class="pure-input-rounded" id="max" name="highDesktop" placeholder="Max$" value="${fn:escapeXml(param.highDesktop)}">
		</p>
		<h2 class="sub-header">CPU Budget</h2>
		<p>
			<input class="pure-input-rounded" id="min" name="lowCPU" placeholder="Min$" value="${fn:escapeXml(param.lowCPU)}">
		</p>
		<p>
			<input class="pure-input-rounded" id="max" name="highCPU" placeholder="Max$" value="${fn:escapeXml(param.highCPU)}">
		</p>
		<h2 class="sub-header">Memory Budget</h2>
		<p>
			<input class="pure-input-rounded" id="min" name="lowMemory" placeholder="Min$" value="${fn:escapeXml(param.lowMemory)}">
		</p>
		<p>
			<input class="pure-input-rounded" id="max" name="highMemory" placeholder="Max$" value="${fn:escapeXml(param.highMemory)}">
		</p>
		<h2 class="sub-header">GPU Budget</h2>
		<p>
			<input class="pure-input-rounded" id="min" name="lowGPU" placeholder="Min$" value="${fn:escapeXml(param.lowGPU)}">
		</p>
		<p>
			<input class="pure-input-rounded" id="max" name="highGPU" placeholder="Max$" value="${fn:escapeXml(param.highGPU)}">
		</p>
		<h2 class="sub-header">Hard Disk Budget</h2>
		<p>
			<input class="pure-input-rounded" id="min" name="lowHardDisk" placeholder="Min$" value="${fn:escapeXml(param.lowHardDisk)}">
		</p>
		<p>
			<input class="pure-input-rounded" id="max" name="highHardDisk" placeholder="Max$" value="${fn:escapeXml(param.highHardDisk)}">
		</p>
		<p>
			<button type="submit" class="pure-button pure-button-primary">Submit</button>
		</p>
	</form>
	</div>
	<div class="col-sm-offset-3 col-md-offset-2 main">
	<h1 class="page-header">Tell us your budget and we will help you decide</h1>
	<h1 class="sub-header">TOP 10 Laptops</h1>
	<div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>Product Name</th>
                <th>Price</th>
                <th>Brand</th>
                <th>Rating</th>
                <th>Number of Reviews</th>
                <th>CPU series</th>
                <th>Memory Size</th>
                <th>Hard Disk Size</th>
            </tr>
            <c:forEach items="${laptops}" var="laptop" >
                <tr>
                    <td><a href="${laptop.getName_hef()}"><c:out value="${laptop.getProductName()}" /></a></td>
                    <td><c:out value="${laptop.getPrice()}" /></td>
                    <td><c:out value="${laptop.getProducer()}" /></td>
                    <td><c:out value="${laptop.getRating()}" /></td>
                    <td><c:out value="${laptop.getNumber_of_Reviews()}" /></td>
                    <td><c:out value="${laptop.getCPU_Model_Family()}" /></td>
                    <td><c:out value="${laptop.getMemory_Size()}" /></td>
                    <td><c:out value="${laptop.getHard_Disk_Size()}" /></td>		
                </tr>
            </c:forEach>
       </table>
    </div>
   	<h1 class="sub-header">TOP 10 Desktops</h1>
	<div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>Product Name</th>
                <th>Price</th>
                <th>Brand</th>
                <th>Rating</th>
                <th>Number of Reviews</th>
                <th>CPU series</th>
                <th>Memory Size</th>
                <th>Hard Disk Size</th>
            </tr>
            <c:forEach items="${desktops}" var="desktop" >
                <tr>
                    <td><a href ="${desktop.getName_hef()}"><c:out value="${desktop.getProductName()}" /></a></td>
                    <td><c:out value="${desktop.getName_hef()}" /></td>
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
    <h1 class="sub-header">TOP 10 CPUs</h1>
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
     <h1 class="sub-header">TOP 10 Ram Memory Cards</h1>
	<div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>Product Name</th>
                <th>Product Link</th>
                <th>Price</th>
                <th>Rating</th>
                <th>Number of Reviews</th>
                <th>Memory Size</th>
                <th>Memory Type</th>
            </tr>
            <c:forEach items="${rammemorys}" var="rammemory" >
                <tr>
                    <td><a href="${rammemory.getName_hef()}"><c:out value="${rammemory.getProductName()}" /></a></td>
                    <td><c:out value="${rammemory.getName_hef()}" /></td>
                    <td><c:out value="${rammemory.getPrice()}" /></td>
                    <td><c:out value="${rammemory.getRating()}" /></td>
                    <td><c:out value="${rammemory.getNumber_of_Reviews()}" /></td>
                    <td><c:out value="${rammemory.getMemory_Size()}" /></td>
                    <td><c:out value="${rammemory.getMemoryType()}" /></td>
                </tr>
            </c:forEach>
       </table>
       </div>
    </div>
    <h1 class="sub-header">TOP 10 GPUs</h1>
	<div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>Product Name</th>
                <th>Price</th>
                <th>Rating</th>
                <th>Number of Reviews</th>
                <th>Size</th>
                <th>Manufacturer</th>
                <th>DDR GDDR</th>
                <th>Capacity</th>
            </tr>
            <c:forEach items="${graphiccards}" var="graphiccard" >
                <tr>
                    <td><a href="${graphiccard.getName_hef()}"><c:out value="${graphiccard.getProductName()}" /></a></td>
                    <td><c:out value="${graphiccard.getPrice()}" /></td>
                    <td><c:out value="${graphiccard.getRating()}" /></td>
                    <td><c:out value="${graphiccard.getNumber_of_Reviews()}" /></td>
                    <td><c:out value="${graphiccard.getSize()}" /></td>
                    <td><c:out value="${graphiccard.getManufacturer()}" /></td>
                    <td><c:out value="${graphiccard.getDDR_GDDR()}" /></td>	
                    <td><c:out value="${hagraphiccardrddisk.getCapacity()}" /></td>		                    	
                </tr>
            </c:forEach>
       </table>
    </div>
    <h1 class="sub-header">TOP 10 Hard Disks</h1>
	<div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>Product Name</th>
                <th>Price</th>
                <th>Rating</th>
                <th>Number of Reviews</th>
                <th>Manufacturer</th>
                <th>Capacity</th>
            </tr>
            <c:forEach items="${harddisks}" var="harddisk" >
                <tr>
                    <td><a href="${harddisk.getName_hef()}"><c:out value="${harddisk.getProductName()}" /></a></td>
                    <td><c:out value="${harddisk.getPrice()}" /></td>
                    <td><c:out value="${harddisk.getRating()}" /></td>
                    <td><c:out value="${harddisk.getNumber_of_Reviews()}" /></td>
                    <td><c:out value="${harddisk.getManufacturer()}" /></td>
                    <td><c:out value="${harddisk.getCapacity()}" /></td>		                    	
                </tr>
            </c:forEach>
       </table>
       </div>
    </div>
    </div>
	   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
   	   <script src="js/bootstrap.min.js"></script>
	</body>
</html>