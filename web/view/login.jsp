<%@ page import="cn.edu.xmut.lgrg.config.SysConfig" %><%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2019/12/5
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> [ <%=SysConfig.sysName%> ] 登陆</title>
    <jsp:include page="/view/base/base.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/login.css">
    <link rel="stylesheet" type="text/css" href="static/ui_lib/verifycode/css/verify.css">
    <style>
        body {
            background: url('images/base/index-back.gif');
            color: #666;
            font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Microsoft Yahei", Arial, sans-serif;
        }
    </style>
</head>
<body>

<div>
    <div style="width:100%;height:650px;margin-top:50px;">
        <div style="text-align:center">
            <a href="#"><img src="images/base/logo-admin.png"/></a>
        </div>
        <div style="width:422px;height:575px;margin:0 auto;padding:30px;margin-top:5px;background-color:#fff;">
            <span style="font-size: 18px;color: #B2B2B2;float:left;">登陆</span>
            <div style="float:right;position: relative;left:0px;top:-16px;">
                <img src="images/base/logo-64.ico"/>
            </div>
            <div style="width:100%;height:213px;margin:0 auto;margin-top:68px;">
                <div style="width:362px;height:40px;    border: 1px solid #e6e6e6; margin-bottom:15px;">
                    <label style="width:40px;height:40px;padding:8px;float:left;padding-left:13px;"><i
                            class="glyphicon glyphicon-user" style="font-size: 22px;" aria-hidden="true"></i></label>
                    <input class="form-control user" id="login_name" type="text" placeholder="请输入用户名"
                           style="float:right;width:320px;height:38px;border-radius:0px;outline: none;border-color: #fff;box-shadow: 0 0 5px rgba(207, 220, 0, -2.4);border-radius: 5px;"/>
                </div>
                <div style="width:362px;height:40px;    border: 1px solid #e6e6e6; margin-bottom:15px;">
                    <label style="width:40px;height:40px;padding:8px;float:left;padding-left:13px;"><i
                            class="glyphicon glyphicon-lock" style="font-size: 22px;" aria-hidden="true"></i></label>
                    <input class="form-control password" id="login_password" type="password" placeholder="请输入密码"
                           style="float:right;width:320px;height:38px;border-radius:0px;outline: none;border-color: #fff;box-shadow: 0 0 5px rgba(207, 220, 0, -2.4);border-radius: 5px;"/>
                </div>
                <div style="width:362px;height:40px;    border: 1px solid #e6e6e6; margin-bottom:15px;">
                    <div id="mpanel4">
                    </div>
                </div>
                <div style="width:362px;height:40px;margin-top:217px;">
                    <button type="button" id="sign" class="btn btn-default btn-block" style="
                    background-color:#42B4F2;font-size: 18px;width:362px;height:40px;color: #fff;outline:none;">登&nbsp;&nbsp;&nbsp;陆
                    </button>
                </div>
            </div>
            <!-- foot -->
            <div style="width:360px;height:20px;margin-top:200px;">
                <a style="    margin-left: 220px;color: #878787;" onclick="alert('暂不开放~')">忘记密码</a>
                <a style="    margin-left: 10px;color: #878787;" onclick="$('#register').modal('show');"> <font
                        color="red">新用户注册</font></a>
            </div>
            <div class="error-data" style="width:360px;height:20px;text-align:center;margin-top:15px">设计制作：&nbsp;16201332</div>

        </div>

    </div>

</div>

<!-- //注册的模态框 -->
<div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">注册</h4>
            </div>
            <div class="modal-body">
                <div class="signin-icon-container">
                    <i class="glyphicon glyphicon-user"></i>
                    <label class="sr-only">用户名</label>
                    <input type="text" class="form-control input-lg registeruser" id="re-userName" placeholder="请设置用户名"
                           required autofocus>
                </div>
                <div class="signin-icon-container">
                    <i class="glyphicon glyphicon-modal-window"></i>
                    <label class="sr-only">手机号</label>
                    <input type="text" class="form-control input-lg registeronlinepay" id="re-userPhone"
                           placeholder="请输入手机号" required>
                </div>
                <div class="signin-icon-container">
                    <i class="glyphicon glyphicon-lock"></i>
                    <label class="sr-only">登录密码</label>
                    <input type="password" class="form-control input-lg registerpassword" id="re-userPass"
                           placeholder="请设置密码" required>
                </div>
                <div class="signin-icon-container">
                    <i class="glyphicon glyphicon-lock"></i>
                    <label class="sr-only">登录密码</label>
                    <input type="password" class="form-control input-lg registerpasswordone" id="re-userPass-ag"
                           placeholder="请重新输入密码" required>
                </div>
                <div class="signin-icon-container">
                    <i class="glyphicon glyphicon-envelope"></i>
                    <label class="sr-only">邮箱地址</label>
                    <input type="email" class="form-control input-lg registeremail" id="re-userEmail"
                           placeholder="请设置邮箱地址" required autofocus>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary register" id="registerUser">注册</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="static/ui_lib/verifycode/js/verify.min.js"></script>
<script>
    var item = 0;
    $(function () {
        $('#mpanel4').slideVerify({
            type: 2,		//类型
            vOffset: 5,	//误差量，根据需求自行调整
            vSpace: 5,	//间隔
            imgName: [
                'base/vi/Pic0.jpg',
                'base/vi/Pic1.jpg',
                'base/vi/Pic2.jpg',
                'base/vi/Pic3.jpg',
                'base/vi/Pic4.jpg'
            ],
            imgSize: {
                width: '362px',
                height: '200px',
            },
            blockSize: {
                width: '40px',
                height: '40px',
            },
            barSize: {
                width: '362px',
                height: '40px',
            },
            ready: function () {

            },
            success: function () {
                item = 1;
            },
            error: function () {
                alert('验证失败！');
            }

        });
    })

    $("#sign").click(function () {
        var data = {
            name: $("#login_name").val(),
            pass: $("#login_password").val(),
            yangzheng: $("#login_yangzheng").val()
        }

        //验证数据
        if (data.name.length == 0) {
            error("请输入用户名/手机号/邮箱！");
        } else if (data.pass.length == 0) {
            error("请输入密码！");
        } else if (item != 1) {
            error("请验证！");
        } else {
            $.post("user/login.do", data, function (result) {
                if (result.code) {
                    success("登陆成功~");
                    //跳转到根目录
                    location.href = '${basePath}';
                } else {
                    error(result.errmsg);
                }
            });
        }
    });
    $("#registerUser").click(function () {
        var data = {
            userName: trim($("#re-userName").val()),
            userEmail: trim($("#re-userEmail").val()),
            userPhone: trim($("#re-userPhone").val()),
            userPass: trim($("#re-userPass").val())
        }

        if (!checkEmail(data.userEmail)) {
            error("邮箱错误~");
            return
        }

        if (!checkPhone(data.userPhone)) {
            error("手机号码错误~");
            return
        }

        if (data.userName.length == 0) {
            error("请输入用户名~");
            return
        }

        if (data.userPass.length < 0) {
            error("请输入密码~");
            return
        }

        if (data.userPass != trim($("#re-userPass-ag").val())) {
            error("两次密码不正确~");
            return
        }

        $.post("user/register.do", data, function (result) {
            if (result.code) {
                success("注册成功，请登陆~");
                $("#register").modal('hide');
            } else {
                error(result.errmsg)
            }
        });
    });
</script>
<%
    if (request.getParameter("re") != null) {
        out.print("<script>$(\"#register\").modal();</script>");
    }
%>

</body>
</html>
