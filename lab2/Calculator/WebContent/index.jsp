<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calculator</title>
</head>
<body>
 
	<!-- Example Entity Body num1=13&num22=17&add=Add -->
	<form method="post" action="./Calculator">
 
		<table>
			<tr>
				<td>Num 1:</td> <!-- name is the key to the values in servlets -->
				<td><input name="num1" type="text"></td>
			</tr>
 
			<tr>
				<td>Num 2:</td>
				<td><input name="num2" type="text"></td>
			</tr>
 
			<tr>
				<td colspan="2"><input type="submit" name="add" value="Add"></td>
			</tr>
		</table>
	</form>
</body>
</html>