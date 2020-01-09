<%@ page import="cn.edu.xmut.lgrg.config.SysConfig" %><%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2020/1/2
  Time: 8:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> [ <%=SysConfig.sysName%> ] 购物车</title>
    <jsp:include page="/view/base/base.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/client/car.css">
    <style>
        .notic-head{
            height: 20px;
            padding: 0 5px;
            margin-top: 10px;
        }

        .head-left{
            float: left;
            height: 20px;
        }

        .head-right{
            float: right;
            height: 20px;
        }

        .notic-list{
            padding: 10px 10px;
        }

        .notic-item{
            margin-bottom: 5px;
        }

        .item-option i{
            margin-right: 5px;
        }
        .item-head{
            background-color: #f5f5f5;
            padding: 5px;
            font-size: 16px;
            height: 35px;


            border-top: 1px solid;
            border-left: 1px solid;
            border-right: 1px solid;
        }

        .msg_type{
            display: inline-block;
            height: 20px;
            line-height: 18px;
            padding: 0 5px;
            color: #4d4d4d;
            font-size: 16px;
            text-align: center;
            background-color: #fff;
            border: 1px solid #e0e0e0;
            vertical-align: top;
            margin-top: 2px;
        }

        .item-option{
            float: right;
            margin-right: 10px;
        }

        .msg_content{
            margin-bottom: -16px;
            padding: 15px;
            background-color: #fff;
            font-size: 14px;
            color: #4d4d4d;
            line-height: 22px;
            border-left: 1px solid;
            border-right: 1px solid;
            border-bottom: 1px solid;
        }

    </style>
</head>
<body>
<jsp:include page="/view/base/shortcut.jsp"></jsp:include>

<div id="cart">
    <div class="banner_x center w">
        <div class="wdgwc fl ml20">消息列表</div>
        <div class="wxts fl ml20">温馨提示：您有 <span class="noreadCount"></span> 未读信息</div>
        <div class="clear"></div>
    </div>
    <div class="line-green w"></div>

    <div class="w">
        <div class="notic-head">
            <span class="head-left">消息总数：<span class="noreadCount">9</span>条</span>
            <span class="head-right">
                <a href="javascript:void(0);" onclick="del(0)">清空所有消息</a>
                <a href="javascript:void(0);" onclick="read(0)">全部标记为已读</a>
            </span>
        </div>
        <div class="notic-list" id="notic-list">

            <div class="notic-item">
                <div class="item-head">
                    <span class="msg_type">系统</span>
                    <span class="msg_type styly-red">未读</span>
                    <span>第一个测试发送消息的内容，社区守则</span>

                    <div class="item-option">
                        <i class="glyphicon glyphicon-envelope"></i>
                        <i class="glyphicon glyphicon-trash"></i>
                        <span>2020-01-08 23:00:59</span>
                    </div>
                </div>
                <div class="msg_content">
                    第一个测试发送消息的内容
                </div>
            </div>

            
        </div>
    </div>
</div>

<script>
    getNoticList();
    function getNoticList() {
        $.get("notic/list.do",function (result) {
            if(result.code){
                var data = result.data;
                var html = "";
                var count = 0;
                for (let i = 0; i < data.length; i++) {
                    html+="<div class=\"notic-item\">\n" +
                        "                <div class=\"item-head\">\n" +
                        "                    <span class=\"msg_type\">"+data[i].sendUserName+"</span>\n" +
                        "                    <span class=\"msg_type styly-red\" style='"+(data[i].readStatus==0?"":"display:none;")+"'>未读</span>\n" +
                        "                    <span>"+data[i].userHeadline+"</span>\n" +
                        "\n" +
                        "                    <div class=\"item-option\">\n" +
                        "                        <i class=\"glyphicon glyphicon-envelope\" onclick='read("+data[i].noticId+")' style='"+(data[i].readStatus==0?"":"display:none;")+"'></i>\n" +
                        "                        <i class=\"glyphicon glyphicon-trash\" onclick='del("+data[i].noticId+")'></i>\n" +
                        "                        <span>"+data[i].noticeTime+"</span>\n" +
                        "                    </div>\n" +
                        "                </div>\n" +
                        "                <div class=\"msg_content\">\n" +
                        data[i]. userNotice +
                        "                </div>\n" +
                        "            </div>";
                    if(data[i].readStatus==0){
                        count++;
                    }
                }
                $("#notic-list").html(html);
                $(".noreadCount").text(count);
            }
        });
    }

    function del(id){
        $.post("notic/del.do",{noticId:id},function (result) {
            if(result.code){
                success("成功~");
                location.reload();
            }else {
                error(result.errmsg)
            }
        })
    }
    
    function read(id) {
        $.post("notic/read.do",{noticId:id},function (result) {
            if(result.code){
                success("成功~");
                location.reload();
            }else {
                error(result.errmsg)
            }
        })
    }
</script>

<jsp:include page="/view/base/footer.jsp"></jsp:include>
</body>
</html>
