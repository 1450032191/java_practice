<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2019/12/2
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> [一点商城] 地址管理</title>
    <jsp:include page="/view/base/base.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/client/adress.css">
    <%--地区选择插件--%>
    <script src="static/ui_lib/area/area.js"></script>

</head>
<body>
<jsp:include page="/view/base/shortcut.jsp"></jsp:include>
<div id="adress">
    <%--地址管理--%>
        <div class="banner_x center w">
            <div class="wdgwc fl ml20">收货地址</div>
            <div class="wxts fl ml20">温馨提示：您已创建 <span> ${size} </span>个收货地址，最多可创建20个</div>
            <div class="addAdress fr">
                <%--新增按钮--%>
                <a href="javascript:;" class="add-btn">新增收货地址</a>
            </div>
            <div class="clear"></div>
        </div>
        <div class="line-green w"></div>
        <div class="adress-list w">

            <c:choose>
                <c:when test="${not empty list and size > 0}">
                    <c:forEach items="${list}" var="var" varStatus="vs">
                        <div class="adress-item">
                            <div class="item-t clear">
                                <h3>${var.uaName}</h3>
                                <c:if test="${var.isDefault!=null and var.isDefault == 1}">
                                    <span class="default-address">默认地址</span>
                                </c:if>
                                <div class="extra" onclick="del('${var.uaId}')">
                                    <i class="glyphicon glyphicon-remove"></i>
                                </div>
                            </div>
                            <div class="item-body clear">
                                <div class="items clear">
                                    <div class="lcol">
                                        <div class="item clear">
                                            <span>收货人：</span>
                                            <div class="fl">${var.uaName}</div>
                                        </div>
                                        <div class="item clear">
                                            <span>所在区域：</span>
                                            <div class="fl">${var.areaAdress}</div>
                                        </div>
                                        <div class="item clear">
                                            <span>详细地址：</span>
                                            <div class="fl">
                                                    ${var.adress}
                                            </div>
                                        </div>
                                        <div class="item clear">
                                            <span>手机号码：</span>
                                            <div class="fl">${var.uaPhone}</div>
                                        </div>
                                        <div class="item clear">
                                            <span>邮箱地址：</span>
                                            <div class="fl">${var.email==null?'':var.email}</div>
                                        </div>
                                    </div>
                                    <div class="rcol">
                                        <div class="extra">
                                            <a href="javascript:;" onclick="setDefault(${var.uaId})">设为默认</a>
                                            <a href="javascript:;" onclick="edit(${var.uaId})">编辑</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="adress-item not-adress">
                        没找到地址······
                    </div>
                </c:otherwise>
            </c:choose>

            <%--<div class="adress-item">--%>
                <%--<div class="item-t clear">--%>
                    <%--<h3>曾子楠</h3>--%>
                    <%--<span class="default-address">默认地址</span>--%>
                    <%--<div class="extra">--%>
                        <%--<i class="glyphicon glyphicon-remove"></i>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="item-body clear">--%>
                    <%--<div class="items clear">--%>
                        <%--<div class="lcol">--%>
                            <%--<div class="item clear">--%>
                                <%--<span>收货人：</span>--%>
                                <%--<div class="fl">陈晴</div>--%>
                            <%--</div>--%>
                            <%--<div class="item clear">--%>
                                <%--<span>所在区域：</span>--%>
                                <%--<div class="fl">福建莆田市涵江区城区</div>--%>
                            <%--</div>--%>
                            <%--<div class="item clear">--%>
                                <%--<span>详细地址：</span>--%>
                                <%--<div class="fl">--%>
                                    <%--涵西街道下林小区10号楼--%>
                                    <%--曾子楠是猪 嘿嘿 /打死你--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="item clear">--%>
                                <%--<span>手机号码：</span>--%>
                                <%--<div class="fl">130****9880</div>--%>
                            <%--</div>--%>
                            <%--<div class="item clear">--%>
                                <%--<span>邮箱地址：</span>--%>
                                <%--<div class="fl">陈晴</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="rcol">--%>
                            <%--<div class="extra">--%>
                                <%--<a href="javascript:;" onclick="setDefault('${var.uaId}')">设为默认</a>--%>
                                <%--<a href="javascript:;" onclick="edit('${var.uaId}')">编辑</a>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>


            <div class="adress-bottom">
                <a href="javascript:;" class="add-btn">
                    新增收货地址
                </a>
                <span>
                     您已创建 ${size} 个收货地址，最多可创建20个
                </span>
            </div>
        </div>
</div>

<!-- Modal -->
<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                <div class="thickcon">
                    <div class="body clear">
                        <div class="item">
                            <span class="lable">
                                <em class="styly-red">*</em>
                                    收货人：
                            </span>
                            <div class="fl">
                                <input id="consigneeName" onblur="checkConsigneeName()">
                                <span class="err-msg" id="consigneeNameNote"></span>
                            </div>
                        </div>
                        <div class="item">
                            <span class="lable">
                                <em class="styly-red">*</em>
                                    手机号码：
                            </span>
                            <div class="fl">
                                <input id="consigneeTel" onblur="checkConsigneeTel()">
                                <span class="err-msg" id="consigneeTelNote"></span>
                            </div>
                        </div>
                        <div class="item">
                            <span class="lable">
                                <em class="styly-red">*</em>
                                    所在地区：
                            </span>
                            <div class="fl">
                                <input id="consigneeArea" style="display: none;">
                                <input id="consigneeAreaId" style="display: none;">
                                <div id="address">
                                    <select class="select" id="province" name="province">
                                        <option value="">选择省份</option>
                                    </select>
                                    <select name="city" id="city">
                                        <option value="">选择城市</option>
                                    </select>
                                    <select name="town" id="town">
                                        <option value="">选择区域</option>
                                    </select>
                                </div>
                                <span class="err-msg" id="consigneeAreaNote"></span>
                            </div>
                        </div>
                        <div class="item">
                            <span class="lable">
                                <em class="styly-red">*</em>
                                    详细地址：
                            </span>
                            <div class="fl">
                                <input id="consigneeAdress" style="width: 484px;" onblur="checkConsigneeArea()">
                                <span class="err-msg" id="consigneeAdressNote"></span>
                            </div>
                        </div>
                        <div class="item">
                            <span class="lable">
                                    邮箱地址：
                            </span>
                            <div class="fl">
                                <input id="consigneeEmail" style="width: 484px;">
                                <span class="err-msg" id="consigneeEmailNote"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="confirmBtn" class="btn btn-primary">保存收货地址</button>
            </div>
        </div>
    </div>
</div>

<script>

    function setDefault(uaId){
        $.post("user/addr/setDefault.do",{uaId:uaId},function (result) {
            if(result.code){
                success("设置成功~");
                location.reload();
            }else {
                error(result.errmsg);
            }
        });
    }
    
    function getModalVal() {
        if(!checkConsigneeName()||!checkConsigneeArea()||!checkConsigneeTel()||!checkConsigneeAdress()){
            return;
        }
        var data={
            uaName:$("#consigneeName").val(),
            uaPhone:$("#consigneeTel").val(),
            adress:$("#consigneeAdress").val(),
            areaAdress:$("#consigneeArea").val(),
            areaId:$("#consigneeAreaId").val(),
            email:$("#consigneeEmail").val(),
        };
        var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        if(data.email.length>0&&!reg.test(data.email)){
            $("#consigneeEmailNote").text("邮箱格式错误");
            return false;
        }else {
            $("#consigneeEmailNote").text("");
        }
        return data;
    }
    
    function checkConsigneeName() {
        var val = $("#consigneeName").val();
        if(val.length == 0){
            $("#consigneeNameNote").text("请输入收货人姓名");
        }else{
            $("#consigneeNameNote").text("");
        }
        return val.length != 0;
    }

    function checkConsigneeTel() {
        var val = $("#consigneeTel").val();
        if(val.length == 0||!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(val))){
            $("#consigneeTelNote").text("请输入收货人手机号码");
        }else{
            $("#consigneeTelNote").text("");
        }
        return val.length != 0;
    }
    
    function checkConsigneeArea() {
        var val = $("#consigneeArea").val();
        if(val.length == 0){
            $("#consigneeAreaNote").text("请选择送货区域");
        }else{
            $("#consigneeAreaNote").text("");
        }
        return val.length != 0;
    }

    function checkConsigneeAdress() {
        var val = $("#consigneeAdress").val();
        if(val.length == 0){
            $("#consigneeAdressNote").text("请输入详细地址");
        }else{
            $("#consigneeAdressNote").text("");
        }
        return val.length != 0;
    }

    $(".add-btn").click(function () {
        var count = ${size == null ? 0:size};
        if(count >= 20){
            alert("请删除一个后在进行创建~");
            return;
        }
        showModal("新增地址",function () {
            var data = getModalVal();
            if(data == false){
                return;
            }
            $.post("user/addr/add.do",data,function (result) {
                if(result.code){
                    success("增加成功~");
                    location.reload();
                }else {
                    error(result.errmsg);
                }
            });
        })
    });
    
    function edit(uaId) {
        //获取一下数据
        $.post("user/addr/get.do",{uaId:uaId},function (result) {
            if(result.code){
                var resultData = result.data;
                //设置值
                $("#consigneeName").val(resultData.uaName);
                $("#consigneeTel").val(resultData.uaPhone);
                $("#consigneeAdress").val(resultData.adress);
                $("#consigneeArea").val(resultData.areaAdress);
                $("#consigneeAreaId").val(resultData.areaId);
                $("#email").val(resultData.email);

                //选择框
                var arr = resultData.areaAdress.split("/");
                console.log(arr);
                if(arr.length == 3){
                    // $("#province").val(arr[0]);
                    // $("#city").val(arr[1]);
                    // $("#town").val(arr[2]);
                }
                $("#address").setAddress(resultData.areaAdress);

                showModal("修改地址",function () {
                    var data = getModalVal();
                    if(data == false){
                        return;
                    }
                    data.uaId = uaId;
                    $.post("user/addr/edit.do",data,function (result) {
                        if(result.code){
                            success("增加成功~");
                            location.reload();
                        }else {
                            error(result.errmsg);
                        }
                    });
                });
            }else {
                error(result.errmsg);
            }
        });
    }

    function del(uaId) {
        $.post("user/addr/del.do",{uaId:uaId},function (result) {
            if(result.code){
                success("删除成功~");
                location.reload();
            }else {
                error(result.errmsg);
            }
        });
    }

    function showModal(title,success) {
        $("#myModal").modal('show');
        $("#myModalLabel").text(title);
        if("function" == typeof success){
            $("#confirmBtn").unbind();
            $("#confirmBtn").click(success);
        }
    }

    $("#address").selectAddress();
    $("#town").focusout(function(){

        var province = $("#province option:selected").html();
        var provinceId = $("#province option:selected").val();
        var city = $("#city option:selected").html();
        var cityId = $("#city option:selected").val();
        var town = $("#town option:selected").html();
        var townId = $("#town option:selected").val();

        if(province!= '选择省份' && city!="选择城市" && town!='选择区域'){
            console.log('省份/直辖市：'+province+'\n城市:'+city+'\n区/县：'+town);
            $("#consigneeArea").val(province+"/"+city+"/"+town);
            $("#consigneeAreaId").val(provinceId+"/"+cityId+"/"+townId);
        }
    })


</script>

<jsp:include page="/view/base/footer.jsp"></jsp:include>

</body>
</html>
