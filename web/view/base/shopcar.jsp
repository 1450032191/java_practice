<%@ page import="cn.edu.xmut.lgrg.dao.impl.SysCarImpl" %>
<%@ page import="cn.edu.xmut.lgrg.util.BeanUtil" %><%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2019/11/18
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    #header{
        background: #fff;
        border-bottom: 1px solid #ddd;
    }

    #header .w{
        position: relative;
        z-index: 11;
        height: 105px;
    }

    #serach{
        height: 60px;
        position: relative;
        z-index: 1;
    }

    #serach .form{
        position: absolute;
        left: 260px;
        top: 25px;
        width: 546px;
        height: 36px;
        border: 2px solid #e2231a;
        background: #fff;
    }

    #serach .form input{
        left: 0;
        padding: 2px 44px 2px 17px;
        width: 425px;
        height: 32px;
        border: 1px solid transparent;
        line-height: 26px;
        font-size: 12px;
        color: #333;
        position: absolute;
        top: 0;
        outline: none;
        font-family: Microsoft YaHei,tahoma,arial,Hiragino Sans GB,\\5b8b\4f53,sans-serif;
    }

    #serach .form button{
        border-radius: 0;
        right: 0;
        width: 58px;
        height: 32px;
        line-height: 32px;
        border: none;
        background-color: #e1251b;
        font-size: 20px;
        font-weight: 700;
        color: #fff;
        -webkit-transition: background .2s ease;
        transition: background .2s ease;
        position: absolute;
        top: 0;
        outline: none;
    }

    #settleup{
        position: absolute;
        right: 230px;
        top: 25px;
        z-index: 21;
    }

    #settleup>div{
        width: 130px;
        height: 36px;
        background-color: #fff;
        text-align: center;
        line-height: 34px;
        border-color: #eee;
        overflow: hidden;
        position: relative;
        z-index: 1;
        float: left;
        border: 1px solid #e3e4e5;
    }

    #settleup i{
        color: #e1251b;
        transition: color .2s ease;
        margin-right: 13px;
        font-size: 17px;
    }

    #settleup a:hover,#settleup a:hover{
        color: #e1251b;
    }

    #settleup .ci-count{
        position: absolute;
        top: 1px;
        left: 25px;
        right: auto;
        display: inline-block;
        padding: 1px 3px;
        font-size: 12px;
        line-height: 12px;
        color: #fff;
        background-color: #e1251b;
        border-radius: 7px;
        min-width: 12px;
        text-align: center;
        font-style: normal;
        padding: 2px;
    }

    #hotwords{
        left: 264px;
        width: 450px;
        overflow: hidden;
        position: absolute;
        top: 65px;
        height: 20px;
        line-height: 20px;
    }

    #hotwords a{
        float: left;
        margin-right: 10px;
        color: #999;
    }

    #hotwords a:first-child{
        color: #e1251b;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    #hotwords a:hover,#hotwords a:hover{
        color: #e1251b;
    }
    #shop_title{
        position: absolute;
        display: table;
        width: 200px;
        height: 60px;
        text-align: center;
        padding: 5px;
        left: 7px;
        top: 17px;
        border: solid 1px;
    }
    #shop_title>div{
        display: table-cell;
        vertical-align: middle;
        text-align: center;
        width: 100%;
        height: 100%;
        font-size: 25px;
        border: dashed 1px;
    }
</style>

<div id="header">
    <div class="w">
        <div id="shop_title" onclick="location.href='${basePath}'">
            <div>
                安&nbsp;福&nbsp;鞋&nbsp;城
            </div>
        </div>
        <div id="serach">
            <div class="form">
                <input type="text" name="" id="">
                <button>
                    <i class="glyphicon glyphicon-search"></i>
                </button>
            </div>

            <div id="settleup">
                <div>
                    <%
                        SysCarImpl cartService = BeanUtil.getInstance(SysCarImpl.class);
                        Integer cartCount = cartService.getCarCount(request);
                    %>
                    <i class="glyphicon glyphicon-shopping-cart"></i>
                    <a href="view/client/car.jsp" class="styly-red">我的购物车</a>
                    <i class="ci-count" style="<%
                        if(cartCount<=0){
                            out.print("display:none");
                        }
                    %>"><%=cartCount%></i>
                </div>
            </div>
        </div>
        <div id="hotwords">
            <a href="">5G手机</a>
            <a href="">火力全开</a>
            <a href="">自营产品</a>
            <a href="">美妆产品</a>
        </div>
    </div>
</div>