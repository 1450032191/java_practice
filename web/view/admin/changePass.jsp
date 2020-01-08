<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2020/1/6
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageName","修改密码");
%>
<jsp:include page="base/head.jsp"></jsp:include>
<style>
    .margintop {
        margin-top: 60px;
    }
</style>


<%
    String msg = request.getParameter("msg");
    //先判断为不为空，为空不执行
    if (msg != null) {


        if (msg.equals("两次新密码不同")) {%>
<script>
    alert("两次输入的新密码不同，请重新输入~")
</script>
<%}else if (msg.equals("旧密码错误")) {%>
<script>
    alert("旧密码错误，请重新输入~")
</script>

<%}else if (msg.equals("更新成功")) {%>
<script>
    alert("更新成功~")
</script>

<%
        }else{

        }
    }
%>

<div class="container">
    <div class="margintop">
        <form action="user/changePasswordServlet?user_id=${UserUtil.getUserId(pageContext.request)}"
              class="form-horizontal" method="post">
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-5 control-label">旧密码</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="inputEmail3" name="oldpass" placeholder="请输入旧密码~">
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

<jsp:include page="base/foot.jsp"></jsp:include>