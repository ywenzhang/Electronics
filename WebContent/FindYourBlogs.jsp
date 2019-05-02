<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
  <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
<title>FindYourBlogs</title>
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
				.h1, .h2, .h3, h1, h2, h3 {
    margin-top: 70px;
    margin-bottom: 1px;
    margin-left: 50px
    }
    .btn,.a {
    margin-top: 15px;
    margin-bottom: 30px;
    margin-left: 50px;}
    .row {
    margin-right: 15px;
    margin-left: 15px;
    margin-top: 10px;
    margin-bottom: 10px;
}
	</style>
  <body>
  <% String uname = (String) session.getAttribute("username");
    if (null == uname) {
    session.setAttribute("errorMessage", "Login Failed ");
    response.sendRedirect("Login");
    } %>
    <nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">	
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/Electronics/Blog">Home Page</a>
				</div>	
  <div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li>		
							<a href="/Electronics/findblogs">OtherBlogs</a>
						</li>
						<li>
							<a href="/Electronics/findreviews">FindReviews</a>
						</li>
						<li>
							<a href="${Logstatehref}">${Logstate}</a>
						</li> 
					</ul>
				</div>
			</div>
		</nav>
<div class="page-header">
<h1>Hello <c:out value="${username}" escapeXml="false" /> ! Here are your blogs!</h1>
<p>
		<span id="successMessage" class="bg-success"><b>${messages.success}</b></span>
</p>
</div> 
<div class="row">   
	<a id="createblog" class="pure-button pure-button-primary" href="createblog?username=<c:out value="${user.getUsername()}"/>" role="button">Create a new blog here!	</a>
</div>
<div class="table-responsize">
        <table class="table table-striped">            <tr>
                <th>BlogId</th>
                <th>Time_of_Creation</th>
                <th>DetailPage</th>
                <th>Update</th>                
                <th>Delete</th>                          
            </tr>
            <c:forEach items="${blogs}" var="blog" >
                <tr>
                    <td><c:out value="${blog.getBlogId()}" /></td>
                    <td><c:out value="${blog.getCreatedTime()}" /></td>
                    <td><a href="blogdetail?blogId=<c:out value="${blog.getBlogId()}"/>">View Blog Detail</a></td>
                    <td><a href="updateblogs?blogId=<c:out value="${blog.getBlogId()}"/>&username=<c:out value="${username}"/>">Update Blog</a></td>                    
                    <td><a href="deleteblog?blogId=<c:out value="${blog.getBlogId()}"/>&username=<c:out value="${username}"/>">Delete</a></td>                    
                
                </tr>
            </c:forEach>
       </table>
       </div>       

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>