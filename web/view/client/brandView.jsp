<%@ page import="java.sql.Connection" %>
<%@ page import="cn.edu.xmut.lgrg.util.MySqlUtil" %>
<%@ page import="java.sql.*" %>
<%@ page import="cn.edu.xmut.lgrg.config.SysConfig" %>
<%--
  Created by IntelliJ IDEA.
  User: 60181
  Date: 2020/1/8
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> [<%=SysConfig.sysName%>] 品牌商品详情</title>
    <jsp:include page="/view/base/base.jsp"></jsp:include>
    <style>
        .sort {
            width: 1200px;
            border: 5px royalblue solid;
            margin: auto;
        }

        .dh {
            width: 1200px;
            height: 40px;
            font-family: verdana, sans-serif;
            font-size: 14px;
            margin: 40px 15px auto;
            /*border: 1px red solid;*/
            color: #000;
        }

        .dh p {
            font-size: 25px;
            font-family: 华文彩云;
            font-weight: bold;
        }

        .hr1 {
            /*border: 1px red solid;*/
            background: #313474;
            width: 40%;
            height: 15px;
            margin-top: 25px;
            margin-left: 15px;
            float: left;
        }

        .hr2 {
            /*border: 1px red solid;*/
            background: greenyellow;
            width: 55%;
            height: 10px;
            margin-top: 27px;
            float: left;
        }

        .dt {
            border: 1px springgreen solid;
        }

        .tb1 {
            width: 1020px;
            height: auto;
            margin: 80px auto;
            /*border: 5px brown solid;*/
        }

        .tb1 tr td {
            line-height: 40px;
        }

        .tb2 {
            margin: auto;
            border: 1px black solid;
            width: 1020px;
            height: 320px;
            padding: auto;
        }

        .tb2 img {
            width: 220px;
            height: 290px;
            margin-left: 20px;
            border: 1px brown solid;
        }

        .neir {
            /*border: 1px red solid;*/
            padding-right: 100px;
        }

        .tl {
            font-family: 华文中宋;
            font-weight: bold;
            margin: auto;
        }

        .at {
            font-family: 仿宋;
            font-size: 20px;
        }

        .pr {
            font-family: Dubai;
            font-size: 20px;
        }

        .neir hr {
            width: 600px;
        }

        #btn_dt {
            width: 150px;
            height: 40px;
            background: #53a4d4;
            border: 1px #53a4d4 solid;
        }

        #btn_atc {
            width: 150px;
            height: 40px;
            background: #eb6100;
            border: 1px #eb6100 solid;
        }
    </style>
</head>
<body>
<jsp:include page="/view/base/shortcut.jsp"></jsp:include>
<jsp:include page="/view/base/shopcar.jsp"></jsp:include>
<div class="dt">
    <table class="tb1">
        <%
            int brand = Integer.parseInt(request.getParameter("brand"));
            Connection con = MySqlUtil.getCon();
            String sql3 = "select * from sys_commodity where com_brand_id=" + brand;
            PreparedStatement pstm = con.prepareStatement(sql3);
            ResultSet comrs = pstm.executeQuery(sql3);
            while (comrs.next()) {
        %>
        <tr>
            <td>
                <table class="tb2">
                    <tr>
                        <td rowspan="6">
                            <img src="<%=comrs.getString("com_img")%>" alt="">
                        </td>
                        <td class="neir tl">
                            <h1><%=comrs.getString("com_name")%>
                            </h1>
                        </td>
                    </tr>
                    <tr>
                        <td class="neir">
                            <hr style="background: palevioletred">
                        </td>
                    </tr>
                    <tr>
                        <td class="neir at">
                            店铺：安福自营旗舰店<%--<%=comrs.getString("BAuth")%>--%>
                        </td>
                    </tr>
                    <tr>
                        <td class="neir at">
                            评论：<font color="red">9999+</font>评论<%--<%=comrs.getString("Page")%>页--%>
                        </td>
                    </tr>
                    <tr>
                        <td class="neir pr">
                            定价：
                            <font color="red" style="font-weight: bold" size="5">
                                ￥<%=comrs.getString("com_price")%>
                            </font>
                        </td>
                    </tr>
                    <tr>
                        <td class="neir">
                            <a href="view/client/commodity.jsp?comId=<%=comrs.getInt("com_id")%>">
                                <input type="button" name="detail" id="btn_dt" value="查看详情">
                            </a>
                            <%-- <input type="submit" name="atc" id="btn_atc" value="添加至购物车">--%>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

<jsp:include page="/view/base/footer.jsp"></jsp:include>
</body>
</html>
