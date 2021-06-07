<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2021/5/26
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <a href="page/add.html">添加</a>
    <%--  点击链接：在项目路径下直接+href  --%>

    <table>
        <thead>
        <tr>
            <td>姓名</td>
            <td>年龄</td>
            <td>身高</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${customers}" var="customer">
            <%--    通过customer键拿到List（request.getAttribute(customer)
                    通过customer变量拿集合里的每个对象--%>
            <tr>
                <%--       bean.name -> bean.getName()         --%>
                <td>${customer.name}</td>
                <td>${customer.age}</td>
                <td>${customer.height}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</body>
</html>
