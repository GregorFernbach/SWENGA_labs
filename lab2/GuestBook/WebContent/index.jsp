<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>STEFAN</title>
</head>
<body>
 
	<form method="post" action="addEntry">
 
		<table>
			<tr>
				<td>User:</td>
				<td><input type="text" name="user"></td>
			</tr>
			<tr>
				<td>Headline:</td>
				<td><input type="text" name="headline"></td>
			</tr>
			<tr>
				<td>Text:</td>
				<td><input type="text" name="text"></td>
			</tr>
 
			<tr>
				<td>Scope:</td>
				<td><select name="scope">
						<option value="session">Session</option>
						<option value="servletContext">ServletContext</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="submit"
					value="Submit"></td>
			</tr>
 
		</table>
 
	</form>
 
</body>
</html>