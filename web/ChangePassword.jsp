<!--
@author azx
@create 2020-01-07-20:25
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->


    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">


    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="bootstrap/js/jquery-3.2.1.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>


    <title>修改密码</title>

    <style>
        .margintop {
            margin-top: 60px;
        }
    </style>
</head>

<%
    String msg = request.getParameter("msg");
    if (msg.equals("两次新密码不同")) {%>
<script>
    alert("两次输入的新密码不同，请重新输入~")
</script>
<%} else if (msg.equals("旧密码错误")) {%>
<script>
    alert("旧密码错误，请重新输入~")
</script>

<% } else if(msg.equals("更新成功")){%>
<script>
    alert("更新成功~")
</script>

<%}else{

}
%>
<body>
<div class="container">
    <div class="margintop">
        <form action="user/changePasswordServlet?user_id=${UserUtil.getUserId(pageContext.request)}"
              class="form-horizontal" method="post">
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-5 control-label">旧密码</label>
                <div class="col-sm-4">
                    <input type="email" class="form-control" id="inputEmail3" name="oldpass" placeholder="请输入旧密码~">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-5 control-label">新密码</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="inputPassword3" name="newpass"
                           placeholder="请输入新密码~">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-5 control-label">重复新密码</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="inputPassword4" name="renewpass"
                           placeholder="请再次输入新密码~">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-6">
                    <button type="submit" class="btn btn-primary">提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
