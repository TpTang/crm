<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2021/6/10
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:choose>
        <c:when test="${empty customer}">
            <title>添加客户</title>
        </c:when>
        <c:otherwise>
            <title>编辑客户</title>
        </c:otherwise>
    </c:choose>
</head>
<body>

    <form action="/crm/customer/save" method="GET">
        <c:if test="${not empty customer}">
            <div> <input type="hidden" name="id" value="${customer.id}"> </div>
        </c:if>
        <div> 姓名：<input type="text" name="name" value="${customer.name}"> </div>
        <div> 年龄：<input type="text" name="age" value="${customer.age}"> </div>
        <div> 身高：<input type="text" name="height" value="${customer.height}"> </div>
        <div>
            <button type="submit">
                <c:choose>
                    <c:when test="${empty customer}">添加</c:when>
                    <c:otherwise>更新</c:otherwise>
                </c:choose>
            </button>
        </div>
    </form>

</body>
</html>
