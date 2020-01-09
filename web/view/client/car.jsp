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
</head>
<body>
<jsp:include page="/view/base/shortcut.jsp"></jsp:include>

<div id="cart">
    <div class="banner_x center w">
        <div class="wdgwc fl ml20">购物车</div>
        <div class="wxts fl ml20">温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</div>
        <div class="clear"></div>
    </div>
    <div class="line-green w"></div>

    <div id="cart-filter-bar" class="w clear">

    </div>

    <div id="cart-main" class="w">
        <div class="cart-thead" id="cart-list">
            <div class="column t-checkbox">
                <div class="car-checkbox">
                    <input type="checkbox" class="all-sel">
                </div>
                全选
            </div>
            <div class="column t-goods">商品</div>
            <div class="column t-props"></div>
            <div class="column t-price">单价</div>
            <div class="column t-quantity">数量</div>
            <div class="column t-sum">小计</div>
            <div class="column t-action">操作</div>
        </div>


    </div>

    <div class="cart-foot w clear">
        <div class="select-all">
            <input type="checkbox" class="all-sel">
            全选
        </div>
        <div class="operation">
            <a href="javascript:void(0);" onclick="delAll()">删除选中商品</a>
            <%--<a href="#none">移到关注</a>--%>
        </div>
        <div class="toolbar-right clear">
            <div class="btn-area">
                <a href="javascript:void(0);" onclick="return false;" id="to-buy" class="not-change">
                    去结算
                </a>
            </div>
            <div class="price-sum">
                <span class="sum-title">总价：</span>
                <span class="price">
                    <em>¥<span class="totalPrice">0.00</span></em>
                </span>
            </div>
            <div class="amount-sum">
                已选择
                <em><span class="totalCount">0</span></em>
                件商品
            </div>
        </div>
    </div>
</div>

<script>
    getCarList();

    function getCarList() {
        $.get("car/list.do", function (result) {
            console.log(result);
            if (result.code) {
                var html = "";
                var data = result.data;
                for (let i = 0; i < data.length; i++) {
                    html += "<div class=\"cart-body cart-item\" data-sku=\"" + data[i].comId + "\">\n" +
                        "                <div class=\"item-from\">\n" +
                        "                    <div class=\"p-checkbox cell\">\n" +
                        "                        <input type=\"checkbox\" class=\"com-check\">\n" +
                        "                    </div>\n" +
                        "                    <div class=\"p-goods cell\">\n" +
                        "                        <div class=\"goods-item\">\n" +
                        "                            <div class=\"goods-img\">\n" +
                        "                                <img src=\"" + data[i].commodity.comImg + "\" alt=\"\">\n" +
                        "                            </div>\n" +
                        "                            <div class=\"goods-name\">\n" +
                        "                                <a href=\"view/client/commodity.jsp?comId=" + data[i].comId + "\">" + data[i].commodity.comName + "</a>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"cell p-props\">\n" +
                        "                            <div class=\"prop-text\">标准规格</div>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"p-price cell\">\n" +
                        "                        <p>\n" +
                        "                            <span class=\"skuPrice\">" + data[i].commodity.comPrice + "</span>\n" +
                        "                        </p>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"p-quantity cell\">\n" +
                        "                        <div class=\"quantiy-form\">\n" +
                        "                            <a href=\"javascript:;\"class=\"left\">-</a>\n" +
                        "                            <input type=\"text\" class=\"itxt skuCount\"\n" +
                        "                                   onblur=\"setCount($(this),'" + data[i].comId + "',parseInt($(this).siblings('.skuCount').val()))\"\n" +
                        "                                   value=\"" + data[i].comCount + "\">\n" +
                        "                            <a href=\"javascript:;\"class=\"right\">+</a>\n" +
                        "                        </div>\n" +
                        "                        <div class=\"quantity-txt\">\n" +
                        "                            有货\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"p-sum cell\">\n" +
                        "                        <strong>¥<span class=\"skuAllPrice\">" + data[i].comPrice + "</span></strong>\n" +
                        "                    </div>\n" +
                        "\n" +
                        "                    <div class=\"p-ops cell\">\n" +
                        "                        <a href=\"javascript:;\" onclick=\"del('" + data[i].comId + "')\">删除</a>\n" +
                        "                        <%--<a href=\"javascript:;\">移到我的关注</a>--%>\n" +
                        "                    </div>\n" +
                        "                </div>\n" +
                        "            </div>";
                }
                $("#cart-list").after(html);
            }
        })
    }

    $(document).on("click", "#to-buy", function () {
        var arr = [];
        var str = "";
        $(".com-check").each(function () {
            if ($(this).is(':checked')) {
                var sku = $(this).parents(".cart-item").data("sku");
                arr.push(sku);
                str = str + sku + ",";
            }
        })
        if (arr.length > 0) {
            // location.href = "order/confirm.html?skus="+str;
            $.post("order/add.do", {payList: str, uaId: "2"}, function (result) {
                if (result.code) {
                    success("即将跳转订单页面，请支付！");
                    location.href = 'view/client/orders.jsp';
                } else {
                    error("失败~");
                }
            });
        } else {
            error("挑选下单商品~");
        }
    });

    $(document).on("click", ".all-sel", function () {
        if ($(this).is(':checked')) {
            console.log("选中了");
            $(".all-sel").prop('checked', true);
            $(".cart-item").addClass('select');
            $(".cart-item .com-check").prop('checked', true);
        } else {
            console.log("取消了");
            $(".all-sel").prop('checked', false);
            $(".cart-item").removeClass('select');
            $(".cart-item .com-check").prop('checked', false);
        }
        setOrderPrice();
    });

    $(document).on("click", ".com-check", function () {
        if ($(this).is(':checked')) {
            console.log("选中了");
            //判断其他是不是选中了
            var item = true;
            $(this).parents('.cart-item').addClass('select');
            $(".com-check").each(function () {
                if (!$(this).is(':checked')) {
                    item = false;
                    return false;
                }
            })
            $(".all-sel").prop('checked', item);
        } else {
            console.log("取消了");
            $(this).parents('.cart-item').removeClass('select');
            $(".all-sel").prop('checked', false);
        }
        setOrderPrice();
    })


    //商品减少按钮
    $(document).on("click", ".quantiy-form .left", function () {
        var count = parseInt($(this).siblings(".skuCount").val());
        var sku = $(this).parents(".cart-item").data("sku");
        if (count > 1) {
            count--;
            setCount($(this), sku, count);
        } else {
            del(sku);
        }
    })

    $(document).on("click", ".quantiy-form .right", function () {
        var count = parseInt($(this).siblings(".skuCount").val());
        count++;
        var sku = $(this).parents(".cart-item").data("sku");
        setCount($(this), sku, count);
    })

    //请求后端接口，设置购物车商品数量
    function setCount(el, sku, count) {
        $.post("cart/set.do", {sku: sku, skuCount: count}, function (result) {
            if (result.code) {
                $("#buy-count").val(count);
                el.siblings(".skuCount").val(count);
                setOrderPrice();
            } else {
                error(result.errmsg);
            }
        });
    }

    function del(sku) {
        //删除
        $.post("cart/set.do", {sku: sku, skuCount: 0}, function (result) {
            if (result.code) {
                location.reload();
            } else {
                error(result.errmsg);
            }
        });
    }

    function setOrderPrice() {
        var num = 0.0;
        var count = 0;
        $(".com-check").each(function () {
            var ci = $(this).parents(".cart-item");
            var skuCount = parseInt(ci.find(".skuCount").val());
            var skuPrice = parseInt(ci.find(".skuPrice").text());
            var itemPrice = toDecimal2(skuPrice * skuCount);
            if ($(this).is(':checked')) {
                count = count + skuCount;
                num = num + parseInt(itemPrice);
            }
            ci.find(".skuAllPrice").text(itemPrice);
        });
        $(".totalPrice").text(toDecimal2(num));
        $(".totalCount").text(count);
    }

    function delAll() {
        var arr = [];
        $(".com-check").each(function () {
            if ($(this).is(':checked')) {
                var sku = $(this).parents(".cart-item").data("sku");
                arr.push(sku);
            }
        });
        $.post("cart/delArr.do", {skuList: JSON.stringify(arr)}, function (result) {
            if (result.code) {
                location.reload();
            } else {
                error(result.errmsg);
            }
        });
    }

</script>

<jsp:include page="/view/base/footer.jsp"></jsp:include>
</body>
</html>
