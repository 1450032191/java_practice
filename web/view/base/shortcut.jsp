<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ page import="xin.zznzzn.web.util.UserUtil" %><%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2019/11/18
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    #shortcut{
        border-bottom: 1px solid #ddd;
        background-color: #e3e4e5;
        color: #999;
    }

    #shortcut>div{
        height: 30px;
        line-height: 30px;
        color: #999;
    }

    .dq-icon{
        overflow: hidden;
        position: relative;
        z-index: 1;
        float: left;
        border: 1px solid #e3e4e5;
        height: 28px;
        color: #f10215;
        margin-left: 7px;
        margin-right: 7px;
    }

    .dq-icon i{
        font-size: 14px;
        color: #f10215;
        margin-right: 4px;
    }

    #shortcut span{
        color: #999;
    }

    #shortcut .rightfr{
        float: right;
        display: block;
        list-style-type: disc;
        margin-block-start: 1em;
        margin-block-end: 1em;
        margin-inline-start: 0px;
        margin-inline-end: 0px;
        padding-inline-start: 40px;
        margin: 0;
    }


    #shortcut li{
        float: left;
    }

    .rightfr .right-item{
        margin-left: 8px;
        margin-right: 8px;
        z-index: 20;
        position: relative;
    }
    .right-item a{
        color: #999;
    }
    .right-item a:focus,.right-item a:hover{
        color: #e33333;
    }

    #shortcut li.spacer{
        overflow: hidden;
        margin: 11px 5px 0;
        width: 1px;
        height: 10px;
        background-color: #ccc;
    }
</style>

<div id="shortcut">
    <div class="w">
        <div class="dq-icon">
            <i class="glyphicon glyphicon-map-marker"></i>
            <span>厦门</span>
        </div>

        <ul class="rightfr">
            <li class="right-item">
                <%
                    String userName = UserUtil.getUserName(request);
                    HttpServletRequest request1 = request;
                    request1.setAttribute("userName",userName);
                %>
                <c:choose>
                    <c:when test="${not empty userName}">
                        <a href="javascript:void(0);">${userName}</a>
                    </c:when>

                    <c:otherwise>
                        <a href="user/login.html">你好，请登陆！</a>
                        <a href="user/login.html?re=1" class="styly-red">免费注册</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li class="spacer"></li>
            <li class="right-item">
                <a href="${basePath}">首页</a>
            </li>
            <li class="spacer"></li>
            <li class="right-item">
                <a href="order/list.html">我的订单</a>
            </li>
            <li class="spacer"></li>
            <li class="right-item">
                <a href="user/addr/list.html">地址管理</a>
            </li>
            <shiro:hasRole name="admin">
                <li class="spacer"></li>
                <li class="right-item">
                    <a href="admin/index.html">管理后台</a>
                </li>
            </shiro:hasRole>
            <c:if test="${not empty userName}">
                <li class="spacer"></li>
                <li class="right-item">
                    <a href="logout.do">退出登陆</a>
                </li>
            </c:if>
            <%--<li class="spacer"></li>--%>
            <%--<li class="right-item">--%>
                <%--<a href="">客户服务</a>--%>
            <%--</li>--%>
        </ul>
    </div>
</div>