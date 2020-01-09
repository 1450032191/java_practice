<%@ page import="cn.edu.xmut.lgrg.config.SysConfig" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2019/12/12
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>[ <%=SysConfig.sysName %>] 订单详情</title>
    <jsp:include page="/view/base/base.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/client/orderDetail.css">
    <style>
        .state-time{
            display: none;
        }
    </style>
</head>
<body>

<jsp:include page="/view/base/shortcut.jsp"></jsp:include>
<div id="confrim-order">
    <div class="banner_x center w">
        <div class="wdgwc fl ml20">订单详情</div>
        <div class="wxts fl ml20">温馨提示：订单详情可能有延迟~</div>
        <div class="clear"></div>
    </div>
</div>

<div class="order-page">
    <div class="green-line"></div>

    <!-- 订单状态 -->
    <div class="order-part">


        <div class="order-state">
            <!-- 订单状态 -->
            <div class="state-left">
                <div class="state-inner">


                    <div class="state-top">
                        订单号：<span class="orderNo"></span>
                    </div>
                    <div class="left-content">
                        <p class="state-txt" id="state-txt">

                        </p>
                            <button class="btn-grey" id="pay-btn" onclick="pay()">付款</button>
                        <!-- <button class="">评价</button> -->
                    </div>
                </div>
            </div>

            <!-- 订单流程 -->
            <div class="state-right">
                <div class="state-inner">
                    <div class="state-top">
                        订单已经完成，感谢您在一点商城购物，欢迎您对本次交易及所购商品进行评价。
                    </div>

                    <!-- 订单进度 -->
                    <div class="right-content">
                        <div class="state-process">
                            <div class="state-item sub-order">

                                <div class="state-icon"></div>
                                <div class="state-detail">
                                    <div class="state-title">提交订单</div>
                                    <div class="state-time">
                                        2019-11-25
                                        <br> 17:27:43
                                    </div>
                                </div>

                                <div class="process-line"></div>
                            </div>
                            <div class="state-item order-paid">

                                <div class="state-icon"></div>
                                <div class="state-detail">
                                    <div class="state-title">付款成功</div>
                                    <div class="state-time">
                                        2019-11-25
                                        <br> 17:27:43
                                    </div>
                                </div>

                                <div class="process-line"></div>
                            </div>
                            <div class="state-item goods-shipped">

                                <div class="state-icon"></div>
                                <div class="state-detail">
                                    <div class="state-title">商品出库</div>
                                    <div class="state-time">
                                        2019-11-25
                                        <br> 17:27:43
                                    </div>
                                </div>

                                <div class="process-line"></div>
                            </div>
                            <div class="state-item goods-delivered">

                                <div class="state-icon"></div>
                                <div class="state-detail">
                                    <div class="state-title">等待收货</div>
                                    <div class="state-time">
                                        2019-11-25
                                        <br> 17:27:43
                                    </div>
                                </div>

                                <div class="process-line"></div>
                            </div>
                            <div class="state-item order-finished">

                                <div class="state-icon"></div>
                                <div class="state-detail">
                                    <div class="state-title">完成</div>
                                    <div class="state-time">
                                        2019-11-25
                                        <br> 17:27:43
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="flower-line"></div>
    </div>
    <div class="order-part">
        <div class="payment-info">
            <ul class="info-items">
                <li>
                    <div class="infor-inner">
                        <div class="pay-info-title">
                            收货人信息
                        </div>
                        <div class="pay-info-content">
                            <ul class="pay-items">
                                <li>
                                    <span class="pay-item-title">收货人：</span>
                                    <span class="pay-item-detail"> 收货人 </span>
                                </li>
                                <li>
                                    <span class="pay-item-title">地址：</span>
                                    <span class="pay-item-detail">天津/市辖区/河东区 3</span>
                                </li>
                                <li>
                                    <span class="pay-item-title">手机号码：</span>
                                    <span class="pay-item-detail"> 17759993196 </span>
                                </li>
                                <li>
                                    <span class="pay-item-title">邮箱：</span>
                                    <span class="pay-item-detail">1450032191@qq.com</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="infor-inner">
                        <div class="pay-info-title">
                            配送信息
                        </div>
                        <div class="pay-info-content">
                            <ul class="pay-items">
                                <li>
                                    <span class="pay-item-title">配送方式：</span>
                                    <span class="pay-item-detail">普通快递</span>
                                </li>
                                <li>
                                    <span class="pay-item-title">运费：</span>
                                    <span class="pay-item-detail">¥0.00</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="infor-inner">
                        <div class="pay-info-title">
                            付款信息
                        </div>
                        <div class="pay-info-content">
                            <ul class="pay-items">
                                <li>
                                    <span class="pay-item-title">付款方式：</span>
                                    <span class="pay-item-detail">在线支付</span>
                                </li>
                                <li>
                                    <span class="pay-item-title">付款时间：</span>
                                    <span class="pay-item-detail createTime"></span>
                                </li>
                                <li>
                                    <span class="pay-item-title">商品总额：</span>
                                    <span class="pay-item-detail">¥<span class="orderPrice"></span></span>
                                </li>
                                <li>
                                    <span class="pay-item-title">应支付金额：</span>
                                    <span class="pay-item-detail">¥<span class="orderPrice"></span></span>
                                </li>
                                <li>
                                    <span class="pay-item-title">优惠券：</span>
                                    <span class="pay-item-detail">¥0.00</span>
                                </li>
                                <li>
                                    <span class="pay-item-title">订单优惠：</span>
                                    <span class="pay-item-detail">¥0.00</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="infor-inner">
                        <div class="pay-info-title">
                            发票信息
                        </div>
                        <div class="pay-info-content">
                            <ul class="pay-items">
                                <li>
                                    <span class="pay-item-title">发票类型：</span>
                                    <span class="pay-item-detail">不开发票</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>

            </ul>
        </div>
    </div>
    <div class="shop-name">一点商城</div>
    <div class="order-part">
        <div class="goods-list">
            <div class="goods-inner">
                <table>
                    <tr id="order-item">
                        <th class="grap"></th>
                        <th>商品</th>
                        <th>商品编号</th>
                        <th>一点价</th>
                        <th>商品数量</th>
                        <th>操作</th>
                        <th class="grap"></th>
                    </tr>


                    <!-- 运费险 -->
                    <tr>
                        <td class="grap"></td>
                        <td>
                            <div class="goods-list-item">
                                <div class="goods-img">
                                    <img src="static/css/imgs/insurance.jpg" alt="">
                                </div>
                                <div class="goods-info">
                                    <span class="goods-info-detail">运费险</span>
                                    <div class="goods-info-more">
                                        最高赔付520元呀
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td class="goods-num">
                        </td>
                        <td class="goods-price">
                            <span>卖家赠送</span>
                        </td>
                        <td class="goods-quantity">
                            1
                        </td>
                        <td class="goods-operate">

                        </td>
                        <td class="grap"></td>


                    </tr>
                </table>
                <div class="payment-total">
                    <div class="payment-total-inner">
                        <div class="pay-details">
                            <ul>
                                <li>
                                    <span class="pay-label">商品总额：</span>
                                    <span class="pay-txt ">¥<span class="orderPrice"></span></span>
                                </li>
                                <li>
                                    <span class="pay-label">商品优惠：</span>
                                    <span class="pay-txt">¥0.00</span>
                                </li>
                                <li>
                                    <span class="pay-label">运　　费：</span>
                                    <span class="pay-txt">¥0.00</span>
                                </li>
                                <li class="color-red">
                                    <span class="pay-label">应付总额：</span>
                                    <span class="pay-txt total-fare">¥<span class="orderPrice"></span></span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var orderId;
    $(function () {
        orderId = getQueryVariable("orderId");
        $.get("order/detail.do?orderId="+orderId,function (result) {
            console.log(result);
            if(result.code){
                var data = result.data;
                var orderItem = data.orderItems;
                $(".orderPrice").html(data.orderPrice);
                $(".createTime").html(data.createTime);
                $(".orderNo").html(data.orderNo);
                if(data.orderStatus == 1){
                    $("#pay-btn").show();
                }else {
                    $("#pay-btn").hide();
                }
                // 传入state:
                setPos(data.orderStatus);
                $("#state-txt").html(data.orderStatusText);
                var html = "";
                for (let i = 0; i < orderItem.length; i++) {
                    html+="<tr>\n" +
                        "                            <td class=\"grap\"></td>\n" +
                        "                            <td>\n" +
                        "                                <div class=\"goods-list-item\">\n" +
                        "                                    <div class=\"goods-img\">\n" +
                        "                                        <%--<img src=\"http://img14.360buyimg.com/N4/${var.commodityMainImg}\" alt=\"\">--%>\n" +
                        "                                        <img src=\""+orderItem[i].commodity.comImg+"\" alt=\"\">\n" +
                        "                                    </div>\n" +
                        "                                    <div class=\"goods-info\">\n" +
                        "                                        <span class=\"goods-info-detail\">"+orderItem[i].commodity.comName+"</span>\n" +
                        "                                        <div class=\"goods-info-more\">\n" +
                        "                                            标准规格\n" +
                        "                                        </div>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                            </td>\n" +
                        "                            <td class=\"goods-num\">\n" +
                        "                                "+orderItem[i].commodity.comId+"\n" +
                        "                            </td>\n" +
                        "                            <td class=\"goods-price\">\n" +
                        "                                ¥"+orderItem[i].comPrice+"\n" +
                        "                            </td>\n" +
                        "                            <td class=\"goods-quantity\">\n" +
                        "                                X "+orderItem[i].comCount+"\n" +
                        "                            </td>\n" +
                        "                            <td class=\"goods-operate\">\n" +
                        "                                <div class=\"op-btns\">\n" +
                        "                                    <div class=\"op-btn\">\n" +
                        "                                        <a href=\"javascript:void(0)\">申请售后</a>\n" +
                        "                                    </div>\n" +
                        "                                    <div class=\"op-btn\">\n" +
                        "                                        <span><a href=\"javascript:void(0)\">评价</a></span>|<span><a\n" +
                        "                                            href=\"javascript:void(0)\">晒单</a></span>\n" +
                        "                                    </div>\n" +
                        "                                    <div class=\"op-btn\">\n" +
                        "                                        <a href=\"view/client/commodity.jsp?comId="+orderItem[i].commodity.comId+"\" class=\"grey-btn\">\n" +
                        "                                            <span class=\"ico\"></span>\n" +
                        "                                            立即购买\n" +
                        "                                        </a>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                            </td>\n" +
                        "                            <td class=\"grap\"></td>\n" +
                        "                        </tr>";
                }
                $("#order-item").after(html);
            }else {
                // location.href = ' ';
                error("获取失败~")
            }
        })
    })
    
    function pay() {
        $.post("order/pay.do",{order_id:orderId},function (result) {
            if(result.code){
                success("支付成功~");
                location.reload();
            }else {
                error("支付失败~");
                error(result.errmsg);
            }
        })
    }

    // 订单流程图标的background-position:
    var bgPos = {
        wait: ['0 0px', '-54px 0px', '-108px 0px', '-162px 0px', '-270px 0px', '-216px 0px'],
        shipping: ['0 -54px', '-54px -54px', '-108px -54px', '-162px -54px', '-270px -54px', '-216px -54px'],
        done: ['0 -108px', '-54px -108px', '-108px -108px', '-162px -108px', '-270px -108px', '-216px -54px'],
    };

    // 订单流程线的background-position-Y:
    var linePos = {
        wait: '-15px',
        shipping: '0px',
        done: '-30px'
    }

    // 初始化流程图标和线:
    function setDefault() {
        $.each($('.state-icon'), function (i, el) {
            $(el).css('backgroundPosition', bgPos.wait[i + 1]);
        })
        $.each($('.state-process .process-line'), function (i, el) {
            $(el).css('backgroundPositionY', linePos.wait);
        })

    }

    // 以state状态修改流程图标和流程线:
    function setDonePos(state) {

        // 初始化:
        setDefault();

        // 流程图标
        $.each($('.state-icon:lt(' + state + ')'), function (i, el) {
            $(el).css('backgroundPosition', bgPos.done[i + 1])
        })

        // 已完成流程线
        $.each($('.state-process .process-line:lt(' + state + ')'), function (i, el) {
            $(el).css('backgroundPositionY', linePos.done)
        })

        if (state > 0) {


            // 进行中流程图标(绿底色圆圈):
            $('.state-icon').eq(state - 1).css('backgroundPosition', bgPos.shipping[state + 1])

            // 进行中线:
            $('.state-process .process-line').eq(state - 1).css('backgroundPositionY', linePos.shipping)

        }
    }

    function setPos(state) {

        if (state > 5) {
            state = 5
        }

        // 调用设置流程图标/流程线 函数:
        setDonePos(state);

    }


</script>
<jsp:include page="/view/base/footer.jsp"></jsp:include>
</body>
</html>
