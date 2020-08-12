<%@page import="java.util.List"%>
<%@page import="domain.Orderdetail"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}
/* Button used to open the contact form - fixed at the bottom of the page */
.open-button {
	background-color: #555;
	color: white;
	padding: 16px 20px;
	border: none;
	cursor: pointer;
	opacity: 0.8;
	position: fixed;
	bottom: 23px;
	right: 28px;
	width: 280px;
}
/* The popup form - hidden by default */
.form-popup {
	overflow-x: hidden;
	overflow-y: auto;
	height: 400px;
	display: none;
	position: fixed;
	top: 60%;
	left: 50%;
	-webkit-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}
/* Add styles to the form container */
.form-container {
	max-width: 500px;
	padding: 10px;
	background-color: white;
}
/* Full-width input fields */
.form-container input[type=text], .form-container input[type=password] {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	border: none;
	background: #f1f1f1;
}
/* When the inputs get focus, do something */
.form-container input[type=text]:focus, .form-container input[type=password]:focus
	{
	background-color: #ddd;
	outline: none;
}
/* Set a style for the submit button */
.form-container .btn {
	background-color: #4CAF50;
	color: white;
	padding: 16px 20px;
	border: none;
	cursor: pointer;
	width: 100%;
	margin-bottom: 10px;
	opacity: 0.8;
}
/* Add a red background color to the cancel button */
.form-container .cancel {
	background-color: red;
}
/* Add some hover effects to buttons */
.form-container .btn:hover, .open-button:hover {
	opacity: 1;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body class="m-3">
	<div class="row col-md-6">
		<table class="table table-striped table-bordered table-sm">
			<tr>
				<th>Order Number</th>
				<th>Product Code</th>
				<th>Quantity Ordered</th>
				<th>Price Each</th>
				<th>Order Line Number</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
			<%
				//List<Orders> orders = (List<Orders>)request.getAttribute("orders");
			List<Orderdetail> orderdetail = (List<Orderdetail>)request.getAttribute("orderdetails");
				for (Orderdetail t : orderdetail) {
					out.println("<tr>");
					out.println("<td>" + t.getOrder().getOrdernumber() + "</td>");
					out.println("<td>" + t.getId().getProductcode() + "</td>");
					out.println("<td>" + t.getQuantityordered() + "</td>");
					out.println("<td>" + t.getPriceeach() + "</td>");
					out.println("<td>" + t.getOrderlinenumber() + "</td>");
					out.println("<td><a href=\"OrderdetailController?ordernumber="+ t.getOrder().getOrdernumber() + "&productcode=" + t.getId().getProductcode() + "\">Update</a></td>");
					out.println("<td><a href=\"OrderdetailController?ordernumber="+ t.getOrder().getOrdernumber() + "&productcode=" + t.getId().getProductcode() + "\">Delete</a></td>");
					out.println("</tr>");
				}
			%>
		</table>
	</div>
	<nav aria-label="Navigation for staffs">
		<ul class="pagination">
			<%
				int currentPage = (int) request.getAttribute("currentPage");
				int recordsPerPage = (int) request.getAttribute("recordsPerPage");
				int noOfPages = (int) request.getAttribute("noOfPages");
				if (currentPage != 1) {
			%>
			<li class="page-item">
				<%
					out.println("<a class=\"page-link\" href=\"" + "PaginationServlet?recordsPerPage=" + recordsPerPage
								+ "&currentPage=" + (currentPage - 1) + "\">Previous</a>");
				%>
			</li>
			<%
				}
			%>
			<%
				for (int i = 1; i <= noOfPages; i++) {
					if (currentPage == i) {
						out.println("<li class=\"page-item active\">");
						out.println("<a class=\"page-link\">" + i + "<span class=\"sr-only\">(current)</span></a></li>");
						out.println("</li>");
					} else {
						out.println("<li class=\"page-item\">");
						out.println("<a class=\"page-link\" href=\"" + "PaginationServlet?recordsPerPage=" + recordsPerPage
								+ "&currentPage=" + i + "\">" + i + "</a>");
						out.println("</li>");
					}
				}
			%>
			<%
				if (currentPage < noOfPages) {
					out.println("<li class=\"page-item\">");
					out.println("<a class=\"page-link\" href=\"" + "PaginationServlet?recordsPerPage=" + recordsPerPage
							+ "&currentPage=" + (currentPage + 1) + "\">Next</a>");
					out.println("</li>");
				}
			%>
		</ul>
	</nav>
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
	<button class="open-button" onclick="openForm()">Add Order Details</button>
	<div class="form-popup" id="myForm">
		<form action="OrdersController" class="form-container" method="post">
			<fieldset>
				<legend>Add Order Details:</legend>
				Order Number: <input type="text" name="ordernumber" /> <br> Product Code:
				<input type="text" name="productcode" /> <br> Quantity Ordered: <input
					type="text" name="quantityordered" /> <br> Price Each: <input type="text"
					name="priceeach" /> <br> Order Line Number: <input type="text" name="orderlinenumber" />
			</fieldset>
			<button type="submit" class="btn">Submit </button>
			<button type="button" class="btn cancel" onclick="closeForm()">Close</button>
			<button type="reset" class="btn">Reset</button>
		</form>
	</div>
	<script>
		function openForm() {
			document.getElementById("myForm").style.display = "block";
		}
		function closeForm() {
			document.getElementById("myForm").style.display = "none";
		}
	</script>
</body>
</html>