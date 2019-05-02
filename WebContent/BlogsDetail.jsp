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
<title>BlogsDetail</title>
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
<h1>Blog ID <c:out value="${blog.getBlogId()}" /></h1>
<p>
		<span id="successMessage" class="bg-success"><b>${messages.success}</b></span>
</p>
</div>
<div class="row">
	<a id="createblog" class="pure-button pure-button-primary" href="createblog?username=<c:out value="${user.getUsername()}"/>" role="button">Create a new blog here!	
	</a>
</div>
<div class="row">
<div class="table-responsize col-sm-4 col-md-8 col-md-offset-1">
        <table class="table table-striped">             
        <tr>
                <th>BlogId</th>
                <th>Time_of_Creation</th>
                <th>UserName</th>                
            </tr>
                <tr>
                    <td><c:out value="${blog.getBlogId()}" /></td>
                    <td><c:out value="${blog.getCreatedTime()}" /></td>
                    <td><c:out value="${blog.getUser().getUsername()}" /></td>
                </tr>
       </table>
       </div></div>
 <div class="row">
 <div class="panel panel-primary col-sm-8 col-md-10 col-md-offset-1"></div>
 <div class="panel-heading col-sm-8 col-md-10 col-md-offset-1">
 <h2 class="panel-title col-sm-8 col-md-10 col-md-offset-1">Blog Content</h2>
 </div>
 <div class="panel panel-body col-sm-8 col-md-10 col-md-offset-1">
 <c:out value="${blog.getContent()}"/>
 </div>
 </div>
<div class="row">
	<a id="createcomment" class="pure-button pure-button-primary" href="updateblogs?blogId=<c:out value="${blog.getBlogId()}"/>" role="button">UpdateBlog	
	</a>
</div>
<div class="row">
	<a id="createcomment" class="pure-button pure-button-primary" href="createcomment?blogId=<c:out value="${blog.getBlogId()}"/>" role="button">Share Your Comment Here!!	
	</a>
</div>
<div class="row">
 <div class="panel panel-primary col-sm-8 col-md-10 col-md-offset-1"></div>
 <div class="panel-heading col-sm-8 col-md-10 col-md-offset-1">
 <h2 class="panel-title col-sm-8 col-md-10 col-md-offset-1">Blog Comments</h2>
 </div>
 <div class="panel panel-body col-sm-8 col-md-10 col-md-offset-1">
 <div class="table-responsize col-sm-8 col-md-10 col-md-offset-1">
<table class="table table-striped">  
            <tr>
                <th>Time_of_Creation</th>
                <th>UserName</th>
                <th>Content</th>  
                <th>Vote</th>
                <th>Update</th>
                <th>Delete</th>                                          
            </tr>
            <c:forEach items="${comments}" var="comment" >
                <tr>
                    <td><c:out value="${comment.getCreatedTime()}" /></td>
                    <td><c:out value="${comment.getUser().getUsername()}" /></td>
                    <td><c:out value="${comment.getContent()}" /></td> 
                    <td><c:out value="${comment.getVote()}" /></td>                    
                    <td><a href="updatecomments?blogId=<c:out value="${blog.getBlogId()}"/>&commentId=<c:out value="${comment.getCommentId()}"/>">Edit</a></td>                    
                    <td><a href="deletecomment?blogId=<c:out value="${blog.getBlogId()}"/>&commentId=<c:out value="${comment.getCommentId()}"/>">Delete</a></td>                    
                                       
                </tr>
            </c:forEach>
       </table>
 </div>
 </div>
</body>
</html>
