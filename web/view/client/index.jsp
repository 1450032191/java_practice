<%@ page import="cn.edu.xmut.lgrg.config.SysConfig" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2019/10/31
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> [ <%=SysConfig.sysName%> ] 首页</title>

    <%--引入基本css/js样式--%>
    <jsp:include page="/view/base/base.jsp"></jsp:include>

    <link rel="stylesheet" href="static/css/index.css">
    <script src="static/ui_lib/swiper/js/swiper.min.js"></script>
    <link rel="stylesheet" href="static/ui_lib/swiper/css/swiper.css">
</head>
<body>

<jsp:include page="/view/base/activity.jsp"></jsp:include>


<jsp:include page="/view/base/shortcut.jsp"></jsp:include>


<jsp:include page="/view/base/shopcar.jsp"></jsp:include>

<div id="band-banner">
    <div class="band-banner-panel w">
        <div class="col1">
            <ul class="cate-menu">
                <c:forEach items="${categoryList}" var="var">
                    <li>
                        <a href="">${var.categoryTitle}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <div class="col2 center-swiper">
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <img class="focus-item-img" src="upload/sys_images/a0efd7b99f835407.jpg">
                    </div>
                    <div class="swiper-slide">Slide 2</div>
                    <div class="swiper-slide">Slide 3</div>
                    <div class="swiper-slide">Slide 4</div>
                    <!-- Add Arrows -->
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                    <!-- Add Pagination -->
                    <div class="swiper-pagination"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="miaosha" class="w">
    <div class="miaosha-countdown">
        <div class="countdown-title">一点秒杀</div>
        <div class="countdown-desc">
            <strong>20:00</strong>
            点场 倒计时
        </div>
        <div class="countdown-main timmer">
                <span class="timmer-util">
                    00
                </span>
            <span class="timmer-util">
                    00
                </span>
            <span class="timmer-util">
                    00
                </span>
        </div>
    </div>

    <div class="slider_list">
        <c:forEach var="var" items="${miaoList}">
            <div class="slider_item" onclick="location.href='item/${var.sku}.html'">
                <div class="slider_item_img">
                    <img src="<%=SysConfig.getImgBaseUrl()%>${var.commodityMainImg}">
                </div>
                <div class="slider_item_name">
                    ${var.commodityName}
                </div>
                <div class="slider_item_price">
                    <div class="miaosha-price">
                        <i>¥</i>
                        <span>${var.skuPrice}</span>
                    </div>
                    <div class="origin-price">
                        <i>¥</i>
                        <span>${var.skuOp}</span>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>


<div>

</div>

<div id="core" class="w">
    <div id="left-core">
        <div class="box-hd">
            <h3>排行榜</h3>
            <a href="https://www.baidu.com">
                <i class="box_hd_arrow"></i>
            </a>
        </div>

        <div class="box-content">
            <c:forEach var="var" items="${paiList}" varStatus="vs">
                <a class="box-rank-item" href="item/${var.sku}.html">
                    <div class="title">
                        <span>TOP</span>
                        <span>0${vs.index+1}</span>
                    </div>
                    <div class="commodity-img">
                        <img src="<%=SysConfig.getImgBaseUrl()%>${var.commodityMainImg}" alt="">
                    </div>
                    <span class="commodity-name">
                            ${var.commodityName}
                    </span>
                </a>
            </c:forEach>
        </div>
    </div>

    <div id="right-core">
        <div class="box-hd">
            <h3>品牌汇聚</h3>
            <a href="https://www.baidu.com">
                <i class="box_hd_arrow"></i>
            </a>
        </div>
        <div class="box-content">
            <c:forEach var="var" items="${brandList}" varStatus="vs">
                <a href="" class="brand-item">
                    <div class="brand-logo">
                        <img src="http:${var.img}" alt="大牌电脑配件专场">
                        <h6 class="brand-name">
                            ${var.name}
                        </h6>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</div>



<div id="recommend" class="w">
    <div class="recommend-hd w">
        <h3>为你推荐</h3>
    </div>

    <ul id="commodity-list" class="w">
        <c:forEach varStatus="vs" var="var" items="${skuList}">
            <li class="commodity-item">
                <a href="item/${var.sku}.html">
                    <div class="commodity-img">
                        <img src="<%=SysConfig.getImgBaseUrl()%>${var.commodityMainImg}" alt="">
                    </div>
                    <div class="commodity-info">
                        <p class="commodity-info-name">
                            <i class="commodity-just">正版</i>
                                ${var.commodityName}
                        </p>
                        <div class="commodity-info-other">
                            <div class="commodity-price">
                                <i>¥</i>
                                <span>${var.skuPrice}</span>
                            </div>
                            <div class="commodity-other-discount">
                                <div class="commodity-other-discount-item">券</div>
                                <div class="commodity-other-discount-item">无忧</div>
                            </div>
                        </div>
                    </div>
                </a>
            </li>
        </c:forEach>
    </ul>
</div>



<jsp:include page="/view/base/footer.jsp"></jsp:include>




<script>
    var swiper = new Swiper('.swiper-container', {
        loop: true,
        autoplay: {
            delay: 1000,
            stopOnLastSlide: false,
            disableOnInteraction: false,
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        pagination: {
            el: '.swiper-pagination',
            dynamicBullets: true,
        },
    });
</script>
</body>
</html>
