<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2019/11/29
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> [一点商城] 我的订单</title>
    <jsp:include page="/view/base/base.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/client/orders.css">

</head>
<body>
    <jsp:include page="/view/base/shortcut.jsp"></jsp:include>
    <div id="orders">
        <div class="banner_x center w">
            <div class="wdgwc fl ml20">我的订单</div>
            <div class="wxts fl ml20">温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</div>
            <div class="clear"></div>
        </div>
        <div class="line-green w"></div>

        <div class="w" id="order-main">
            <div class="mt">
                <ul class="extra-l">
                    <li>
                        <a class="txt curr" href="javascript:void(0);" onclick="return false;">全部订单</a>
                    </li>
                    <%--<li>--%>
                        <%--<a class="txt" href="javascript:void(0);" onclick="return false;">待付款</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a class="txt" href="javascript:void(0);" onclick="return false;">待收货</a>--%>
                    <%--</li>--%>
                </ul>

                <%--<div class="extra-r">--%>
                    <%--<div class="serach">--%>
                        <%--<input type="text" value="商品名称/商品编号/订单号">--%>
                        <%--<a href="" class="search-btn">--%>
                            <%--搜索--%>
                            <%--<i class="glyphicon glyphicon-search"></i>--%>
                        <%--</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
            <table id="order-list">
                <colgroup>
                    <col class="number-col">
                    <col class="consignee-col">
                    <col class="amount-col">
                    <col class="status-col">
                    <col class="operate-col">
                </colgroup>

                <thead>
                    <tr style="font-size: 12px">
                        <th>
                            <div class="ordertime-count">
                                最近订单
                            </div>

                            <div class="order-detail-txt">
                                订单详情
                            </div>
                        </th>
                        <th>收货人</th>
                        <th>金额</th>
                        <th>订单状态</th>
                        <th>操作</th>
                    </tr>
                </thead>

                <tbody>
                    <tr class="sep-row" id="order-ll">
                        <td colspan="5"></td>
                    </tr>

                    <c:forEach var="var" items="${orderList}" varStatus="vs">
                        <tr class="tr-th">
                            <td colspan="5">
                                <span class="gap"></span>
                                <span class="dealtime">${var.createTime}</span>
                                <span class="number">
                                订单号：
                                <a href="order/${var.orderNo}.html">${var.orderNo}</a>
                            </span>
                                <span class="delete">
                                <i class="glyphicon glyphicon-trash"></i>
                            </span>
                            </td>
                        </tr>

                        <c:forEach var="sku" varStatus="skuItem" items="${var.skuList}">
                            <tr class="tr-bd">
                                <td>
                                    <div class="goods-item">
                                        <div class="p-img">
                                            <img src="http://img10.360buyimg.com/N6/s60x60_${sku.commodityMainImg}" alt="">
                                        </div>
                                        <div class="p-msg">
                                            <div class="p-name">
                                                <a href="item/${sku.sku}.html">${sku.skuTitle}</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="goods-number">
                                        X${sku.skuCount}
                                    </div>
                                    <div class="goods-repair">
                                            <%--<a href="">申请退货</a>--%>
                                    </div>
                                </td>
                                <c:if test="${skuItem.index == 0}">
                                    <td rowspan="${var.skuSize}">
                                        <div class="tooltip">
                                            <span class="txt">${var.uaName}</span>
                                            <i class="glyphicon glyphicon-user"></i>
                                        </div>
                                    </td>

                                    <td rowspan="${var.skuSize}">
                                        <div class="amount">
                                            <span>总额 ¥${var.price}</span>
                                            <br>
                                            <span class="ftx-13">在线支付</span>
                                        </div>
                                    </td>

                                    <td rowspan="${var.skuSize}">
                                        <div class="status">
                                            <span class="txt">${var.orderStatusText}</span>
                                        </div>
                                    </td>

                                    <td rowspan="${var.skuSize}">
                                        <div class="operate">
                                            <a href="order/${var.orderId}.html" class="btn-again btn-again-show" target="_blank"><b></b>
                                                订单详情
                                            </a><br>
                                        </div>
                                    </td>
                                </c:if>

                            </tr>
                        </c:forEach>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    
    <script>
        getOrderList();
        function getOrderList() {
            $.get("order/list.do",function (result) {
                console.log(result);
                if(result.code){
                    var data = result.data;
                    var html = "";
                    for (let i = 0; i < data.length;i++) {
                        html += "<tr class=\"tr-th\">\n" +
                            "                            <td colspan=\"5\">\n" +
                            "                                <span class=\"gap\"></span>\n" +
                            "                                <span class=\"dealtime\">"+data[i].createTime+"</span>\n" +
                            "                                <span class=\"number\">\n" +
                            "                                订单号：\n" +
                            "                                <a href=\"order/${var.orderNo}.html\">"+data[i].orderNo+"</a>\n" +
                            "                            </span>\n" +
                            "                                <span class=\"delete\">\n" +
                            "                                <i class=\"glyphicon glyphicon-trash\"></i>\n" +
                            "                            </span>\n" +
                            "                            </td>\n" +
                            "                        </tr>";
                            var orderItems = data[i].orderItems;
                        for (let j = 0; j < orderItems.length; j++) {
                            console.log(orderItems[j]);
                            html+="<tr class=\"tr-bd\">\n" +
                                "                                <td>\n" +
                                "                                    <div class=\"goods-item\">\n" +
                                "                                        <div class=\"p-img\">\n" +
                                "                                            <img src=\""+orderItems[j].commodity.comImg+"\" style=\"width:60px;height:60px;\" alt=\"\">\n" +
                                "                                        </div>\n" +
                                "                                        <div class=\"p-msg\">\n" +
                                "                                            <div class=\"p-name\">\n" +
                                "                                                <a href=\"view/client/commodity.jsp?comId="+orderItems[j].comId+"\">"+orderItems[j].commodity.comName+"</a>\n" +
                                "                                            </div>\n" +
                                "                                        </div>\n" +
                                "                                    </div>\n" +
                                "                                    <div class=\"goods-number\">\n" +
                                "                                        X"+orderItems[j].comCount+"\n" +
                                "                                    </div>\n" +
                                "                                    <div class=\"goods-repair\">\n" +
                                "                                            <%--<a href=\"\">申请退货</a>--%>\n" +
                                "                                    </div>\n" +
                                "                                </td>";
                                if(j==0){
                                    html+= "<td rowspan=\""+data[i].comCount+"\">\n" +
                                        "                                        <div class=\"tooltip\">\n" +
                                        "                                            <span class=\"txt\">收货人</span>\n" +
                                        "                                            <i class=\"glyphicon glyphicon-user\"></i>\n" +
                                        "                                        </div>\n" +
                                        "                                    </td>\n" +
                                        "\n" +
                                        "<td rowspan=\""+data[i].comCount+"\">\n"+
                                        "                                        <div class=\"amount\">\n" +
                                        "                                            <span>总额 ¥"+data[i].orderPrice+"</span>\n" +
                                        "                                            <br>\n" +
                                        "                                            <span class=\"ftx-13\">在线支付</span>\n" +
                                        "                                        </div>\n" +
                                        "                                    </td>\n" +
                                        "\n" +
                                        "                                    <td rowspan=\""+data[i].comCount+"\">\n" +
                                        "                                        <div class=\"status\">\n" +
                                        "                                            <span class=\"txt\">"+data[i].orderStatusText+"</span>\n" +
                                        "                                        </div>\n" +
                                        "                                    </td>\n" +
                                        "\n" +
                                        "                                    <td rowspan=\""+data[i].comCount+"“”\">\n";

                                        if(data[i].orderStatus== 1){
                                            html += "                                        <div class=\"operate\">\n" +
                                                "                                            <a href=\"javascript:void(0);\" onclick=\"pay('"+data[i].orderId+"')\" class=\"btn-again btn-again-show\"><b></b>\n" +
                                                "                                                支付\n" +
                                                "                                            </a><br>\n" +
                                                "                                        </div>\n" ;
                                        }

                                        html += "                                    </td>";
                                }
                                html+="</tr>";
                        }
                    }
                    $("#order-ll").after(html);
                }
            });
        }

        function pay(orderId) {
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
    </script>

    <jsp:include page="/view/base/footer.jsp"></jsp:include>
</body>
</html>
