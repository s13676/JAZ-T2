<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form action="/login" method="POST" style="width: 1%">
<fieldset>
  <legend>Login:</legend>
  Username:<br>
  <input type="text" name="username" required>
  <br>
  Hasło:<br>
  <input type="password" name="password" required>
  <br><br>
  <input type="submit" value="Zaloguj">
 </fieldset>
</form>
Nie masz konta? <a href="/register"> Zarejestruj się</a>
</body>
</html>