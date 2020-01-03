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
        <div class="cart-thead">
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
        <c:forEach items="${list}" var="var" varStatus="vs">
            <div class="cart-body cart-item" data-sku="${var.sku}">
                <div class="item-from">
                    <div class="p-checkbox cell">
                        <input type="checkbox" class="com-check">
                    </div>
                    <div class="p-goods cell">
                        <div class="goods-item">
                            <div class="goods-img">
                                    <%--<img src="http://img13.360buyimg.com/n1/s450x450_${var.commodityMainImg}" alt="">--%>
                                <img src="${var.commodityMainImg}" alt="">
                            </div>
                            <div class="goods-name">
                                <a href="item.html?id=">${var.skuTtile}</a>
                            </div>
                        </div>
                    </div>
                    <div class="cell p-props">
                        <c:forEach items="${var.fieldList}" var="fieldList" varStatus="fieldVs">
                            <div class="prop-text">${fieldList}</div>
                        </c:forEach>
                    </div>
                    <div class="p-price cell">
                        <p>
                            <span class="skuPrice">${var.skuPrice}</span>
                        </p>
                    </div>
                    <div class="p-quantity cell">
                        <div class="quantiy-form">
                            <a href="javascript:;"class="left">-</a>
                            <input type="text" class="itxt skuCount"
                                   onblur="setCount($(this),${var.sku},parseInt($(this).siblings('.skuCount').val()))"
                                   value="${var.skuCount}">
                            <a href="javascript:;"class="right">+</a>
                        </div>
                        <div class="quantity-txt">
                            有货
                        </div>
                    </div>
                    <div class="p-sum cell">
                        <strong>¥<span class="skuAllPrice">${var.skuAllPrice}</span></strong>
                    </div>

                    <div class="p-ops cell">
                        <a href="javascript:;" onclick="del(${var.sku})">删除</a>
                            <%--<a href="javascript:;">移到我的关注</a>--%>
                    </div>
                </div>
            </div>
        </c:forEach>

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
                    <em>¥<span class="totalPrice">${totalPrice}</span></em>
                </span>
            </div>
            <div class="amount-sum">
                已选择
                <em><span class="totalCount">${totalCount}</span></em>
                件商品
            </div>
        </div>
    </div>
</div>

<script>

    $("#to-buy").click(function () {
        var arr = [];
        var str = "";
        $(".com-check").each(function () {
            if($(this).is(':checked')){
                var sku = $(this).parents(".cart-item").data("sku");
                arr.push(sku);
                str = str + sku + ",";
            }
        })
        if(arr.length>0){
            location.href = "order/confirm.html?skus="+str;

        }else {
            error("挑选下单商品~");
        }
    });

    $(".all-sel").click(function () {
        if($(this).is(':checked')){
            console.log("选中了");
            $(".all-sel").prop('checked',true);
            $(".cart-item").addClass('select');
            $(".cart-item .com-check").prop('checked',true);
        }else {
            console.log("取消了");
            $(".all-sel").prop('checked',false);
            $(".cart-item").removeClass('select');
            $(".cart-item .com-check").prop('checked',false);
        }
        setOrderPrice();
    });

    $(".com-check").click(function () {
        if($(this).is(':checked')){
            console.log("选中了");
            //判断其他是不是选中了
            var item = true;
            $(this).parents('.cart-item').addClass('select');
            $(".com-check").each(function () {
                if(!$(this).is(':checked')){
                    item = false;
                    return false;
                }
            })
            $(".all-sel").prop('checked',item);
        }else{
            console.log("取消了");
            $(this).parents('.cart-item').removeClass('select');
            $(".all-sel").prop('checked',false);
        }
        setOrderPrice();
    });

    //商品减少按钮
    $(".quantiy-form .left").click(function () {
        var count = parseInt($(this).siblings(".skuCount").val());
        var sku = $(this).parents(".cart-item").data("sku");
        if(count > 1){
            count--;
            setCount($(this),sku,count);
        }else {
            del(sku);
        }
    });
    //商品增加按钮
    $(".quantiy-form .right").click(function () {
        var count = parseInt($(this).siblings(".skuCount").val());
        count++;
        var sku = $(this).parents(".cart-item").data("sku");
        setCount($(this),sku,count);
    });

    //请求后端接口，设置购物车商品数量
    function setCount(el,sku,count) {
        $.post("cart/set.do",{sku:sku,skuCount:count},function (result) {
            if(result.code){
                $("#buy-count").val(count);
                el.siblings(".skuCount").val(count);
                setOrderPrice();
            }else {
                error(result.errmsg);
            }
        });
    }

    function del(sku) {
        //删除
        $.post("cart/set.do",{sku:sku,skuCount:0},function (result) {
            if(result.code){
                location.reload();
            }else {
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
            if($(this).is(':checked')){
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
            if($(this).is(':checked')){
                var sku = $(this).parents(".cart-item").data("sku");
                arr.push(sku);
            }
        });
        $.post("cart/delArr.do",{skuList:JSON.stringify(arr)},function (result) {
            if(result.code){
                location.reload();
            }else {
                error(result.errmsg);
            }
        });
    }

</script>

<jsp:include page="/view/base/footer.jsp"></jsp:include>
</body>
</html>
