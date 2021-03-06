<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Historia BZWBK</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link media="screen" href="/historia/css/style.css" type="text/css" rel="stylesheet" />
    <link media="screen" href="/historia/css/jquery-ui-smoothness.css" type="text/css" rel="stylesheet" />
    <script language="javascript" src="/historia/js/jquery-core.js" type="text/javascript"></script>
    <script language="javascript" src="/historia/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript">
        $(document).ready(function() {
          var dt = $('table').dataTable({    
                     "aLengthMenu": [[-1, 10, 25, 50, 100, 200, 500], ["Wszystkie", 10, 25, 50, 100, 200, 500]],
                     "iDisplayLength" : -1,
                     "bJQueryUI": true,
                   });
                           
          var select = $('<select style="float: right;"><option value="">wszystko</option><option value="+">wpływy</option><option value="-">wydatki</option></select>');
          $(".dataTables_filter").before(select);
          $('select', this).change(function () {
            dt.fnFilter($(this).attr('value'), 5);
          });
        });
    </script>
</head>
<body>
  <p><a href="/">Zacznij od nowa</a></p>
  <h1>Historia BZWBK</h1>
  <table class="display">
    <thead>
      <tr>
        <th>Data 1</th>
        <th>Data 2</th>
        <th>Wykonujący</th>
        <th>Rachunek</th>
        <th>Tytuł</th>
        <th>Ilość</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${history}" var="entry">
        <tr>
          <td><fmt:formatDate value="${entry.startDate}" pattern="yyyy-MM-dd"/></td>
          <td><fmt:formatDate value="${entry.endDate}" pattern="yyyy-MM-dd"/></td>
          <td>${entry.sender}</td>
          <td>${entry.account}</td>
          <td>${entry.title}</td>
          <td>${entry.amount}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>