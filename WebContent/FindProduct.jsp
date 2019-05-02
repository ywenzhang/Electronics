<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Product</title>
</head>
<body>
	<form action="findproduct" method="post">
		<h1>Search for a Product by web_scraper_order</h1>
		<p>
			<label for="product">Product</label>
			<input id="web_scraper_order" name="web_scraper_order" value="${fn:escapeXml(param.web_scraper_order)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Matching Product</h1>
        <table border="1">
            <tr>
                <th>ProductId</th>
                <th>ProductName</th>
                <th>Product_href</th>
                <th>Price</th>
                <th>Rating</th>
                <th>Number of Reviews</th>
                <th>Delete Product</th>
                <th>Update Price</th>
            </tr>
                <tr>
                    <td><c:out value="${product.getWeb_scraper_order()}" /></td>
                    <td><c:out value="${product.getProductName()}" /></td>
                    <td><c:out value="${product.getName_hef()}" /></td>
                    <td><c:out value="${product.getPrice()}"/></td>
                    <td><c:out value="${product.getRating()}"/></td>
                    <td><c:out value="${product.getNumber_of_Reviews()}"/></td>
                    <td><a href="priceupdate?web_scraper_order=<c:out value="${product.getWeb_scraper_order()}"/>">Update Price</a></td>
                    <td><a href="productdelete?web_scraper_order=<c:out value="${product.getWeb_scraper_order()}"/>">Delete</a></td>
                </tr>
       </table>
</body>
</html>