<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form action="/register" method="POST" style="width: 1%">
<fieldset>
  <legend>Rejestracja:</legend>
  Username:<br>
  <input type="text" name="username" pattern=".{3,}" required>
  <br>
  Password:<br>
  <input type="password" name="password" pattern=".{3,}" required>
  <br>
  Confirm Password:<br>
  <input type="password" name="password_confirm" pattern=".{3,}" required>
  <br>
  Email:<br>
  <input type="email" name="email" required>
  <br><br>
  <input type="submit" value="Submit">
</fieldset>
</form>
</body>
</html>