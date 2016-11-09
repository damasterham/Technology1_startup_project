<%--
  Created by IntelliJ IDEA.
  User: DaMasterHam
  Date: 25-09-2016
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Blog Posts</title>
</head>
<body>
    <c:forEach items="${posts}" var="p" >
        <h3>${p.title}</h3>
        <h5>By: ${p.user}</h5>
        <p>${p.content}</p>
    </c:forEach>

    ${adminstuff}
</body>
</html>
