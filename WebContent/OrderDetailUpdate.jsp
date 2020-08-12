<%@page import="domain.Orderdetail"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<body>
	<%
		Orderdetail orders = (Orderdetail) request.getAttribute("orderdetails");
	%>
	<form action="OrderdetailController" method="post">
		<table>
			<tr>
				<td>Order Number</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"ordernumber\" readonly value=" + orders.getOrder().getOrdernumber());
					%>
				</td>
			</tr>
			<tr>
				<td>Product ID</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"productcode\" readonly value=" + orders.getId().getProductcode());
					%>
				</td>
			</tr>
			<tr>
				<td>Quantity Order</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"quantityordered\" value=" + orders.getQuantityordered());
					%>
				</td>
			</tr>
			<tr>
				<td>Price Each</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"priceeach\" value=" + orders.getPriceeach());
					%>
				</td>
			</tr>
			<tr>
				<td>Order Line Number</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"orderlinenumber\" value=" + orders.getOrderlinenumber());
					%>
				</td>
			</tr>
		
		</table>
		<input type="submit" name="UPDATE" value="UPDATE" /> <input
			type="submit" name="DELETE" value="DELETE" />
	</form>
</body>
</html>