<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2021/6/3
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>客户数据</title>
    <style>
        tr,td{
            border: 1px #000000 solid;
        }
    </style>
</head>
<body>

    <div>
        <a href="/crm/page/add.html">添加</a>
    </div>
    <table border="1">
        <thead>
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>身高</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${customers}" var="customer">
            <tr>
                <td>${customer.id}</td>
                <td>${customer.name}</td>
                <td>${customer.age}</td>
                <td>${customer.height}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</body>
</html>
