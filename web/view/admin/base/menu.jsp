<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2020/1/2
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<li class="nav-item">
    <a href="view/admin/index.jsp">
        <i class="fas fa-home"></i>
        <p>首页</p>
        <%--<span class="badge badge-count">5</span>--%>
    </a>
</li>
<li class="nav-section">
    <span class="sidebar-mini-icon">
        <i class="fa fa-ellipsis-h"></i>
    </span>
    <h4 class="text-section">基础管理</h4>
</li>

<li class="nav-item">
    <a href="view/admin/user.jsp">
        <i class="fas fa-user-alt"></i>
        <p>用户管理</p>
    </a>
</li>

<li class="nav-item">
    <a data-toggle="collapse" href="#charts">
        <i class="fas fa-th-large"></i>
        <p>产品运营</p>
        <span class="caret"></span>
    </a>
    <div class="collapse" id="charts">
        <ul class="nav nav-collapse">
            <li>
                <a href="view/admin/commodity.jsp">
                    <span class="sub-item">产品管理</span>
                </a>
            </li>
            <li>
                <a href="view/admin/category.jsp">
                    <span class="sub-item">分类管理</span>
                </a>
            </li>
            <li>
                <a href="view/admin/brand.jsp">
                    <span class="sub-item">品牌管理</span>
                </a>
            </li>
        </ul>
    </div>
</li>

<script>
    $(document).ready(function () {
        //获取当前地址
        var href = window.location.pathname;
        console.log("当前路径："+href);
        $(".nav-item").each(function () {
            //移除所有的submenu
            var nav = $(this);
            var toHref = $(this).children("a").attr("href");
            var item = false;
            if("javascript:void(0);"!=toHref){
                //寻找下一个
                nav.find("div a").each(function () {
                    var tohref = $(this).attr("href");
                    if(href.indexOf(tohref)!=-1){
                        $(this).parent().addClass("active");
                        item = true;
                    }
                });
            }
            if(item||href.indexOf(toHref)!=-1){
                nav.addClass("active");
                if(item){
                    nav.addClass("submenu");
                    nav.find("div").addClass("show");
                }
            }else {
                nav.removeClass("active");
                nav.removeClass("submenu");
                nav.find("div").removeClass("show");
            }

        });
    });
</script>

<%--<li class="nav-item">--%>
    <%--<a data-toggle="collapse" href="#base">--%>
        <%--<i class="fas fa-layer-group"></i>--%>
        <%--<p>Base</p>--%>
        <%--<span class="caret"></span>--%>
    <%--</a>--%>
    <%--<div class="collapse" id="base">--%>
        <%--<ul class="nav nav-collapse">--%>
            <%--<li>--%>
                <%--<a href="../components/avatars.html">--%>
                    <%--<span class="sub-item">Avatars</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="../components/buttons.html">--%>
                    <%--<span class="sub-item">Buttons</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="../components/gridsystem.html">--%>
                    <%--<span class="sub-item">Grid System</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="../components/panels.html">--%>
                    <%--<span class="sub-item">Panels</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="../components/notifications.html">--%>
                    <%--<span class="sub-item">Notifications</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="../components/sweetalert.html">--%>
                    <%--<span class="sub-item">Sweet Alert</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="../components/font-awesome-icons.html">--%>
                    <%--<span class="sub-item">Font Awesome Icons</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="../components/flaticons.html">--%>
                    <%--<span class="sub-item">Flaticons</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="../components/typography.html">--%>
                    <%--<span class="sub-item">Typography</span>--%>
                <%--</a>--%>
            <%--</li>--%>
        <%--</ul>--%>
    <%--</div>--%>
<%--</li>--%>
<%--<li class="nav-item">--%>
    <%--<a data-toggle="collapse" href="#forms">--%>
        <%--<i class="fas fa-pen-square"></i>--%>
        <%--<p>Forms</p>--%>
        <%--<span class="caret"></span>--%>
    <%--</a>--%>
    <%--<div class="collapse" id="forms">--%>
        <%--<ul class="nav nav-collapse">--%>
            <%--<li>--%>
                <%--<a href="../forms/forms.html">--%>
                    <%--<span class="sub-item">Basic Form</span>--%>
                <%--</a>--%>
            <%--</li>--%>

        <%--</ul>--%>
    <%--</div>--%>
<%--</li>--%>
<%--<li class="nav-item active submenu">--%>
    <%--<a data-toggle="collapse" href="#tables">--%>
        <%--<i class="fas fa-table"></i>--%>
        <%--<p>Tables</p>--%>
        <%--<span class="caret"></span>--%>
    <%--</a>--%>
    <%--<div class="collapse show" id="tables">--%>
        <%--<ul class="nav nav-collapse">--%>
            <%--<li>--%>
                <%--<a href="../tables/tables.html">--%>
                    <%--<span class="sub-item">Basic Table</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li class="active">--%>
                <%--<a href="../tables/datatables.html">--%>
                    <%--<span class="sub-item">Datatables</span>--%>
                <%--</a>--%>
            <%--</li>--%>
        <%--</ul>--%>
    <%--</div>--%>
<%--</li>--%>
<%--<li class="nav-item">--%>
    <%--<a data-toggle="collapse" href="#maps">--%>
        <%--<i class="fas fa-map-marker-alt"></i>--%>
        <%--<p>Maps</p>--%>
        <%--<span class="caret"></span>--%>
    <%--</a>--%>
    <%--<div class="collapse" id="maps">--%>
        <%--<ul class="nav nav-collapse">--%>
            <%--<li>--%>
                <%--<a href="../maps/googlemaps.html">--%>
                    <%--<span class="sub-item">Google Maps</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="../maps/fullscreenmaps.html">--%>
                    <%--<span class="sub-item">Full Screen Maps</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="../maps/jqvmap.html">--%>
                    <%--<span class="sub-item">JQVMap</span>--%>
                <%--</a>--%>
            <%--</li>--%>
        <%--</ul>--%>
    <%--</div>--%>
<%--</li>--%>
<%--<li class="nav-item">--%>
    <%--<a data-toggle="collapse" href="#charts">--%>
        <%--<i class="far fa-chart-bar"></i>--%>
        <%--<p>Charts</p>--%>
        <%--<span class="caret"></span>--%>
    <%--</a>--%>
    <%--<div class="collapse" id="charts">--%>
        <%--<ul class="nav nav-collapse">--%>
            <%--<li>--%>
                <%--<a href="../charts/charts.html">--%>
                    <%--<span class="sub-item">Chart Js</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="../charts/sparkline.html">--%>
                    <%--<span class="sub-item">Sparkline</span>--%>
                <%--</a>--%>
            <%--</li>--%>
        <%--</ul>--%>
    <%--</div>--%>
<%--</li>--%>

<%--<li class="nav-item">--%>
    <%--<a href="../widgets.html">--%>
        <%--<i class="fas fa-desktop"></i>--%>
        <%--<p>Widgets</p>--%>
        <%--<span class="badge badge-count badge-success">4</span>--%>
    <%--</a>--%>
<%--</li>--%>
<%--<li class="nav-item">--%>
    <%--<a data-toggle="collapse" href="#custompages">--%>
        <%--<i class="fas fa-paint-roller"></i>--%>
        <%--<p>Custom Pages</p>--%>
        <%--<span class="caret"></span>--%>
    <%--</a>--%>
    <%--<div class="collapse" id="custompages">--%>
        <%--<ul class="nav nav-collapse">--%>
            <%--<li>--%>
                <%--<a href="../login.html">--%>
                    <%--<span class="sub-item">Login & Register 1</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="../login2.html">--%>
                    <%--<span class="sub-item">Login & Register 2</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="../userprofile.html">--%>
                    <%--<span class="sub-item">User Profile</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="../404.html">--%>
                    <%--<span class="sub-item">404</span>--%>
                <%--</a>--%>
            <%--</li>--%>
        <%--</ul>--%>
    <%--</div>--%>
<%--</li>--%>
