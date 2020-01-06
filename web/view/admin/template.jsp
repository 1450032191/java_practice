<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2020/1/6
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageName","首页");
%>
<jsp:include page="base/head.jsp"></jsp:include>


<script>
    var vue = new Vue({
        el: '#',
    });
</script>


<jsp:include page="base/foot.jsp"></jsp:include>