<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Price</title>
</head>
<body>
	<h1>Update Price</h1>
	<form action="priceupdate" method="post">
		<p>
			<label for="web_scraper_order">web_scraper_order</label>
			<input id="web_scraper_order" name="web_scraper_order" value="${fn:escapeXml(param.web_scraper_order)}">
		</p>
		<p>
			<label for="newPrice">New Price</label>
			<input id="newPrice" name="newPrice" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>