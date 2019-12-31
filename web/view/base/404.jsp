<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2019/11/18
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>页面走失了。。。</title>

    <%--引入基本css/js样式--%>
    <jsp:include page="base.jsp"></jsp:include>

    <link href="static/ui_lib/404/css/404.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="fullScreen" id="fullScreen">
    <!-- <a href="#" class="logo"><img src="images/homepage-logo.png"></a> -->
    <img class="rotating" src="static/ui_lib/404/spaceman.svg">
    <div class="pagenotfound-text">
        <h1>访问的路径不存在~</h1>
        <h2><a href="">返回首页</a></h2>
    </div>
    <canvas id="canvas2d" width="1142" height="844"></canvas>

    <%-- 我是快乐的小阿楠~ 哦耶~ --%>
</div>
<script type="text/javascript" src="static/ui_lib/404/js/404.js"></script>
</body></html>