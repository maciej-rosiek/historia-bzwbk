<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Historia BZWBK</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link media="screen" href="/historia/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
  <h1>Historia BZWBK</h1>
  <form action="${url}" method="POST" enctype="multipart/form-data">
    <input type="file" name="csv" />
    <input type="submit" value="Kliknij tu!"/>
  </form>
</body>
</html>