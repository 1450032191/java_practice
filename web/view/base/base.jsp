<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2019/11/18
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    request.setAttribute("basePath",basePath);
%>
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="static/css/bootstrap.css">
<script src="static/js/jquery-3.3.1.js"></script>
<script src="static/js/bootstrap.js"></script>
<link rel="stylesheet" href="static/ui_lib/notice/noticejs.css">
<script src="static/ui_lib/notice/notice.js"></script>

<script>
    function success(text) {
        new NoticeJs({
            text: text,
            position: 'topCenter',
            type: 'success',
            animation: {
                open: 'animated bounceInRight',
                close: 'animated bounceOutLeft'
            }
        }).show();
    }

    function error(text) {
        new NoticeJs({
            text: text,
            position: 'topCenter',
            type: 'error',
            animation: {
                open: 'animated bounceInRight',
                close: 'animated bounceOutLeft'
            }
        }).show();
    }

    function toDecimal2(num) {
        var result = parseFloat(num);
        if (isNaN(result)) {
            alert('传递参数错误，请检查！');
            return false;
        }
        result = Math.round(num * 100) / 100;
        var s_x = result.toString();
        var pos_decimal = s_x.indexOf('.');
        if (pos_decimal < 0) {
            pos_decimal = s_x.length;
            s_x += '.';
        }
        while (s_x.length <= pos_decimal + 2) {
            s_x += '0';
        }
        return s_x;
    }

    function checkPhone(phone) {
        var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
        return myreg.test(phone);
    }

    function checkEmail(email) {
        var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
        return reg.test(email);
    }

    function trim(s) {
        return s.replace(/(^\s*)|(\s*$)/g, "");
    }

    function getQueryVariable(variable)
    {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    }
</script>


<style>
    * {
        margin: 0;
        padding: 0;
    }
    em, i {
        font-style: normal;
    }
    a{
        color: #666;
        text-decoration: none;
    }
    a:focus,a:hover,input:focus,input:hover{
        outline: none;
        text-decoration: none !important;
    }
    a:hover,a:focus{
        color: #f10215 !important;
    }
    a.not-change:focus,a.not-change:hover{
        color: #fff !important;
    }

    img{
        border: 0;
        vertical-align: middle;
    }

    li{
        list-style: none    ;
    }

    body {
        -webkit-font-smoothing: antialiased;
        background-color: #fff;
        font: 12px/1.5 Microsoft YaHei, tahoma, arial, Hiragino Sans GB, \\5b8b\4f53, sans-serif;
        color: #666;
    }

    button, input {
        font-family: Microsoft YaHei,tahoma,arial,Hiragino Sans GB,\\5b8b\4f53,sans-serif;
    }

    .styly-red{
        color: #f10215 !important;
    }

    .commodity-just{
        background-color: #e1251b;
        border-radius: 2px;
        color: #fff;
        padding: 0 5px;
        margin-right: 4px;
        line-height: 16px;
        height: 16px;
        font-size: 12px;
        display: inline-block;
    }
    .green{
        background-color: #08af37;
    }

    .center {
        margin: 0 auto;
    }

    .w{
        margin: auto;
        width: 1190px;
    }


    .clear {
        clear: both;
    }

    .line-green {
        /*width: 100%;*/
        height: 2px;
        background: #1baeae;
    }

    .clearfix::after {
        content: "";
        height: 0;
        display: block;
        clear: both;
        visibility: hidden;
    }

    tbody {
        display: table-row-group;
        vertical-align: middle;
        border-color: inherit;
        border-collapse: collapse;
    }

    .hr{
        border-bottom: 1px solid #e6e6e6;
        height: 0;
        margin-bottom: 10px;
    }

    .mr20 {
        margin-right: 20px;
    }

    button:active,button:focus,button:hover{
        outline: none;
    }

</style>
