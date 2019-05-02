<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>DeleteComment</title>
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
		.h1,h1{
    margin-top: 70px;
    margin-bottom: 1px;
    margin-left: 50px
    }
    		.h2,h2{
    margin-top: 20px;
    margin-bottom: 1px;
    margin-left: 50px
    }
    .btn {
    margin-top: 15px;
    margin-bottom: 30px;
    margin-left: 50px;}
    .row {
    margin-right: 15px;
    margin-left: 15px;
    margin-top: 10px;
    margin-bottom: 10px;
}
.panel-primary {
    border-color: #337ab7;
    margin-bottom: 20px;
    background-color: #fff;
    border: 1px solid transparent;
    border-radius: 4px;
    -webkit-box-shadow: 0 1px 1px rgba(0,0,0,.05);
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
}
.panel-heading {
    color: #fff;
    background-color: #337ab7;
    border-color: #337ab7;
    padding: 10px 15px;
    border-bottom: 1px solid transparent;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
}
.panel-body {
    padding: 15px;
    border-color: #337ab7;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
    
}
	</style>
<body>
<% String uname = (String) session.getAttribute("username");
    if (null == uname) {
    session.setAttribute("errorMessage", "Login Failed ");
    response.sendRedirect("Login");
    } %>
<nav class="navbar navbar-inverse navbar-fixed-top">
				<div class="navbar-header">	
					<a class="navbar-brand" href="/Electronics/Blog">Home Page</a>
				</div>	
  <div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li>		
							<a href="/Electronics/findyourblogs">YourBlogs</a>
						</li>
						<li>
							<a href="/Electronics/findreviews">FindReviews</a>
						</li>
						<li>
							<a href="${Logstatehref}">${Logstate}</a>
						</li> 
					</ul>
				</div>
		</nav>
<div class="page-header">
<h1>Are you sure you want to delete this Blog?</h1>
<p>
		<span id="successMessage" class="bg-success"><b>${messages.success}</b></span>
</p>
</div>
	<form action="deletecomment" method="post">
	    <p>
        <input type="hidden" name="blogId" value="${blog.getBlogId()}">
		</p>
	    <p>
        <input type="hidden" name="commentId" value="${comment.getCommentId()}">
		</p>
		<p>
        <input type="hidden" name="username" value="${username}">
		</p>
		<p>
			<div 
			<c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="decision">yes or no</label>
				<input id="decision" name="decision" value="${fn:escapeXml(param.id)}">
			</div>
		</p>
		<div class="text-center"> 
        <button type="submit" class="pure-button pure-button-primary text-center">Submit</button>
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</div>
	</form>
	<br/><br/>
</body>
</html>
