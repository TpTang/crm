<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2021/6/10
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑客户</title>
</head>
<body>

    <form action="/crm/customer/update" method="GET">
        <%--    hidden:隐藏域    --%>
        <div> <input type="hidden" name="id" value="${customer.id}"> </div>
        <div> 姓名：<input type="text" name="name" value="${customer.name}"> </div>
        <div> 年龄：<input type="text" name="age" value="${customer.age}"> </div>
        <div> 身高：<input type="text" name="height" value="${customer.height}"> </div>
        <div> <button type="submit">更新</button> </div>
    </form>

</body>
</html>
