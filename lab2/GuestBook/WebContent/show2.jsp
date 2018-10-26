<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
!DOCTYPE html>
<%@page import="lab2_GuestBook.GuestBookModel"%>
<%@page import="lab2_GuestBook.GuestBookManager"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 
	<p>
		<a href="index.jsp">BACK</a>
	</p>
 
	<table style="width: 100%; padding: 10px;">
		<thead>
			<tr>
				<td><h1>Guestbook Session</h1></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td style="border: 1px solid;">
					<%
						GuestBookManager gbmSession = (GuestBookManager) session.getAttribute("gbm");
						if (gbmSession == null || gbmSession.isEmpty()) {
							out.println("no data available");
						} else {
							for (GuestBookModel guestBookModel : gbmSession.getGuestBookEntries()) {
								out.println("<h3>" + guestBookModel.getHeadline() + "</h3>");
								out.println("<p>" + guestBookModel.getUser() + "</p>");
								out.println("<p>" + guestBookModel.getText() + "</p>");
								out.println("<hr>");
							}
						}
					%>
				</td>
			</tr>
		</tbody>
	</table>
 
	<br>
	<hr>
	<br>
 
 
	<table style="width: 100%; padding: 10px;">
		<thead>
			<tr>
				<td><h1>Guestbook ServletContext</h1></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td style="border: 1px solid;">
					<%
						GuestBookManager gbmServletContext = (GuestBookManager) application.getAttribute("gbm");
						if (gbmServletContext == null || gbmServletContext.isEmpty()) {
							out.println("no data available");
						} else {
							for (GuestBookModel guestBookModel : gbmServletContext.getGuestBookEntries()) {
								out.println("<h3>" + guestBookModel.getHeadline() + "</h3>");
								out.println("<p>" + guestBookModel.getUser() + "</p>");
								out.println("<p>" + guestBookModel.getText() + "</p>");
								out.println("<hr>");
							}
						}
					%>
				</td>
			</tr>
		</tbody>
	</table>
 
	<p>
		<a href="index.jsp">BACK</a>
	</p>
 
 
</body>
</html>