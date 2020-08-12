<%@page import="domain.OrdersEntity"%>
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
		OrdersEntity orders = (OrdersEntity) request.getAttribute("orders");
	%>
	<form action="OrdersController" method="post">
		<table>
			<tr>
				<td>Order Number</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"ordernumber\" readonly value=" + orders.getOrdernumber());
					%>
				</td>
			</tr>
			<tr>
				<td>Order Date</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"orderdate\" value=" + orders.getOrderdate());
					%>
				</td>
			</tr>
			<tr>
				<td>Required Date</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"requireddate\" value=" + orders.getRequireddate());
					%>
				</td>
			</tr>
			<tr>
				<td>Shipped Date</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"shippeddate\" value=" + orders.getShippeddate());
					%>
				</td>
			</tr>
			<tr>
				<td>Comments</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"comments\" value=" + orders.getComments());
					%>
				</td>
			</tr>
			<tr>
				<td>Status</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"status\" value=" + orders.getStatus());
					%>
				</td>
			</tr>
			<tr>
				<td>Customer Number</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"customernumber\" readonly value=" + orders.getCustomernumber());
					%>
				</td>
			</tr>
		</table>
		<input type="submit" name="UPDATE" value="UPDATE" /> <input
			type="submit" name="DELETE" value="DELETE" />
	</form>
</body>
</html>