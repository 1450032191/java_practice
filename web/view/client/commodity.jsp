<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2019/11/18
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title> [安福商城] </title>
    <jsp:include page="/view/base/base.jsp"></jsp:include>

    <link rel="stylesheet" href="static/css/client/commodity.css">
    <link rel="stylesheet" href="static/ui_lib/swiper/css/swiper.css">
    <script src="static/ui_lib/swiper/js/swiper.min.js"></script>

</head>
<body>

<jsp:include page="/view/base/shortcut.jsp"></jsp:include>

<jsp:include page="/view/base/shopcar.jsp"></jsp:include>

<%--分类栏--%>
<div id="crumb-wrap">
    <div class="w">
        <div class="crumb-fl">
            <div class="crumb-fl-item"><a>一点商城</a></div>
        </div>
        <div class="crumb-fr">
            <div class="help">
                联系客服
            </div>
        </div>
    </div>
</div>

<div class="w" id="commodity-intro" data-id="">

    <div class="commodity-preview">
        <div class="previews">
            <img src="https://img12.360buyimg.com/jdcms/s150x150_jfs/t1/104250/16/4381/186022/5de6faa0E09d72f29/2caa2532e24d0471.jpg.webp" alt="">
        </div>

    </div>
    <div class="commodity-wrap">
        <div class="sku-name">
            <i class="commodity-just green">新品</i>
            <span id="sku-name"></span>
        </div>

        <div class="summary">
            <div class="summary-price">
                <div class="dt">售价</div>
                <div class="dd">
                        <span>
                            <span>￥</span>
                            <span class="price" id="sku-price"></span>
                            <span class="pricing">[
                                <del>￥<span id="sku-op"></span></del>
                            ]</span>
                        </span>
                </div>
            </div>
        </div>

        <div class="summary other">
            <div class="dt">服务支持</div>
            <div class="dd" id="is_vip">
                <a href="">隔日达</a> <i>|</i>
                <a href="">无忧退货</a> <i>|</i>
                <a href="">极速退款</a> <i>|</i>
                <a href="">免费包邮</a>
            </div>
        </div>

        <div class="summary-line"></div>

        <div id="choose-btns">
            <div class="choose-amount">
                <input type="text" min="0" value="0" id="buy-count"
                       onblur="setBuyCount(parseInt($(this).val()))">
                <a class="reduce" id="buy-reduce">-</a>
                <a class="add" id="buy-add">+</a>
            </div>
            <a href="javascript:;" id="buy-btn" class="not-change">加入购物车</a>
        </div>

        <div class="summary other" style="padding-top: 0">
            <div class="dt">温馨提示</div>
            <div class="dd">
                <ol class="tips-list">
                    <li>
                        ·支持7天无理由退货(拆封后不支持)
                    </li>
                </ol>
            </div>
        </div>

    </div>
</div>

<div class="w" id="commodity-tab">
    <ul>
        <li class="current">商品介绍</li>
    </ul>
</div>

<div class="w" id="detail-content">
    ${commodityDesc}
</div>

<jsp:include page="/view/base/footer.jsp"></jsp:include>
<script>


    $("#buy-reduce").click(function () {
        var count = parseInt($("#buy-count").val());
        if(count == 0){
            error("不能在减啦~");
        }else {
            count--;
            setBuyCount(count);
        }
    });
    $("#buy-add").click(function () {
        var count = parseInt($("#buy-count").val());
        count++;
        setBuyCount(count);
    });
    $("#buy-btn").click(function () {
        var count = $("#buy-count").val();
        if(count == 1){
            setBuyCount(1,true);
        }else {
            location.href='cart/list.html';
        }
    });
    function setBuyCount(count,item) {
        var id = getCommodity();
        $.post("cart/set.do",{sku:id,skuCount:count},function (result) {
            if(result.code){
                $("#buy-count").val(count);
                if(item == true){
                    location.href='cart/list.html';
                }
            }else {
                error(result.errmsg);
            }
        });
    }
    
    function getCommodity() {
        var id = $("#commodity-intro").data("id");
        return id;
    }
    
    $(function () {
        $.get("/item.do?comId=<%=request.getAttribute("comId")%>",function (result) {
            if(result.code){
                var data = result.data;
                $("#commodity-intro").data("id",data.comId);
                $("#sku-price").text(data.comPrice);
                $("#sku-op").text(data.comOp);
                $("#detail-content").text(data.detailInfo);
                if(data.isVip){
                    $("#is_vip").append("<i>|</i>" +
                        "                <a href=\"\">Vip商品</a>")
                }
            }else {
                error(result.errmsg);
            }
        })
    })
</script>
</body>
</html>
