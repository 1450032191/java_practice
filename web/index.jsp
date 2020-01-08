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
    <title>一点在线商城</title>

    <%--引入基本css/js样式--%>
    <jsp:include page="/view/base/base.jsp"></jsp:include>

    <link rel="stylesheet" href="static/css/index.css">
    <script src="static/ui_lib/swiper/js/swiper.min.js"></script>
    <link rel="stylesheet" href="static/ui_lib/swiper/css/swiper.css">

    <style>
        .focus-item-img{
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>

<jsp:include page="/view/base/activity.jsp"></jsp:include>


<jsp:include page="/view/base/shortcut.jsp"></jsp:include>


<jsp:include page="/view/base/shopcar.jsp"></jsp:include>

<div id="band-banner">
    <div class="band-banner-panel w">
        <div class="col1">
            <ul class="cate-menu">
                <li>
                    <a href="">${var.categoryTitle}</a>
                </li>
            </ul>
        </div>

        <div class="col2 center-swiper">
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <img class="focus-item-img" src="upload/sys_images/LiningRoll.jpg">
                    </div>
                    <div class="swiper-slide">
                        <img class="focus-item-img" src="upload/sys_images/newbalanceRoll.jpg">
                    </div>
                    <div class="swiper-slide">
                        <img class="focus-item-img" src="upload/sys_images/NIKERoll.jpg">
                    </div>
                    <div class="swiper-slide">
                        <img class="focus-item-img" src="upload/sys_images/converseRoll.jpg">
                    </div>
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

    <div class="slider_list" id="miao_list">

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

        <div class="box-content" id="paiList">

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
            <a href="" class="brand-item">
                <div class="brand-logo">
                    <img src="images/nike.png" alt="NIKE">
                    <h6 class="brand-name">
                        NIKE
                    </h6>
                </div>
            </a>
            <a href="" class="brand-item">
                <div class="brand-logo">
                    <img src="images/adidas.jpg" alt="adidas">
                    <h6 class="brand-name">
                        adidas
                    </h6>
                </div>
            </a>
            <a href="" class="brand-item">
                <div class="brand-logo">
                    <img src="images/特步.png" alt="特步">
                    <h6 class="brand-name">
                        特步
                    </h6>
                </div>
            </a>
            <a href="" class="brand-item">
                <div class="brand-logo">
                    <img src="images/New_Balance.png" alt="New Balance">
                    <h6 class="brand-name">
                        NEW BALANCE
                    </h6>
                </div>
            </a>
            <a href="" class="brand-item">
                <div class="brand-logo">
                    <img src="images/LI-NING.png" alt="LI-NING">
                    <h6 class="brand-name">
                        LI-NING
                    </h6>
                </div>
            </a>
            <a href="" class="brand-item">
                <div class="brand-logo">
                    <img src="images/FILA.png" alt="FILA">
                    <h6 class="brand-name">
                        FILA
                    </h6>
                </div>
            </a>
        </div>
    </div>
</div>


<div id="recommend" class="w">
    <div class="recommend-hd w">
        <h3>为你推荐</h3>
    </div>

    <ul id="commodity-list" class="w">

    </ul>
</div>


<jsp:include page="/view/base/footer.jsp"></jsp:include>

<script>
    $(function () {
        $.get("index.do", function (result) {
            if (result.code) {
                var miao = result.data.miaosha;
                var html = "";
                for (let i = 0; i < miao.length; i++) {
                    html += "<div class=\"slider_item\" onclick=\"location.href='view/client/commodity.jsp?comId=" + miao[i].comId + "'\">\n" +
                        "        <div class=\"slider_item_img\">\n" +
                        "          <img src=\"" + miao[i].comImg + "\">\n" +
                        "        </div>\n" +
                        "        <div class=\"slider_item_name\">\n" +
                        "            " + miao[i].comName + "\n" +
                        "        </div>\n" +
                        "        <div class=\"slider_item_price\">\n" +
                        "          <div class=\"miaosha-price\">\n" +
                        "            <i>¥</i>\n" +
                        "            <span>" + miao[i].comPrice + "</span>\n" +
                        "          </div>\n" +
                        "          <div class=\"origin-price\">\n" +
                        "            <i>¥</i>\n" +
                        "            <span>" + miao[i].comOp + "</span>\n" +
                        "          </div>\n" +
                        "        </div>\n" +
                        "      </div>";
                }
                $("#miao_list").html(html);
                html = "";
                var skuList = result.data.skuList;
                for (let i = 0; i < skuList.length; i++) {
                    html += "      <li class=\"commodity-item\">\n" +
                        "        <a href=\"view/client/commodity.jsp?comId=" + skuList[i].comId + "\">\n" +
                        "          <div class=\"commodity-img\">\n" +
                        "            <img src=\"" + skuList[i].comImg + "\" alt=\"\">\n" +
                        "          </div>\n" +
                        "          <div class=\"commodity-info\">\n" +
                        "            <p class=\"commodity-info-name\">\n" +
                        "              <i class=\"commodity-just\">正版</i>\n" +
                        "                " + skuList[i].comName + "\n" +
                        "            </p>\n" +
                        "            <div class=\"commodity-info-other\">\n" +
                        "              <div class=\"commodity-price\">\n" +
                        "                <i>¥</i>\n" +
                        "                <span>" + skuList[i].comPrice + "</span>\n" +
                        "              </div>\n" +
                        "              <div class=\"commodity-other-discount\">\n" +
                        "                <div class=\"commodity-other-discount-item\">券</div>\n" +
                        "                <div class=\"commodity-other-discount-item\">无忧</div>\n" +
                        "              </div>\n" +
                        "            </div>\n" +
                        "          </div>\n" +
                        "        </a>\n" +
                        "      </li>\n";
                }
                $("#commodity-list").html(html);
                html = "";
                var paiList = result.data.paiList;
                for (let i = 0; i < paiList.length; i++) {
                    html += "<a class=\"box-rank-item\" href=\"view/client/commodity.jsp?comId=" + paiList[i].comId + "\">\n" +
                        "          <div class=\"title\">\n" +
                        "            <span>TOP</span>\n" +
                        "            <span>0" + (i + 1) + "</span>\n" +
                        "          </div>\n" +
                        "          <div class=\"commodity-img\">\n" +
                        "            <img src=\"" + paiList[i].comImg + "\" alt=\"\">\n" +
                        "          </div>\n" +
                        "          <span class=\"commodity-name\">\n" +
                        "              " + paiList[i].comName + "\n" +
                        "          </span>\n" +
                        "        </a>";
                }
                $("#paiList").html(html);
            }
        });
    })
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
