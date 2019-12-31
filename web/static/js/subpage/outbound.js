// 通知的配置
var notyf = new Notyf({
    // delay:100000,
    alertIcon: 'fa fa-exclamation-circle',
    confirmIcon: 'fa fa-check-circle'
});
//展示模态框
function showModal(opts,callback) {
    var defaultOpts = {
        modalName: "提示"//标题
    };
    opts = $.extend({}, defaultOpts, opts);
    $("#modalTitle").text(opts.modalName);
    $("#MyModal").modal('show');
    $("#confirmBtn").off("click").on("click", function () {
        callback instanceof Function ? callback() : "";
        $("#MyModal").modal('hide');
    });
}
$(document).on("click",".edit",function () {
    var parsent=$(this).parents("tr");
    var data1={
        id:$(this).data("spy"),
        name:parsent.children().eq(1).text(),
        number:parsent.children().eq(2).text(),
        price:parsent.children().eq(3).text()
    };
    $("#price").val(data1.price);
    $("#number").val(data1.number);
    showModal({modalName:data1.name},function () {
        data1.price=$("#price").val();
        data1.number=$("#number").val();
        if(!checkFormData(data1)){
            return;
        }
        var data={
            type:"POST",
            url:"/JxcProductOrder/out/edit.do",
            data:data1,
            success:function (data) {
                if(data.code){
                    notyf.confirm(data.msg);
                    res();
                }else {
                    notyf.alert(data.msg);
                }
            },
            dataType:"json"
        };
        $.ajax(data);
    })
});
$(document).ready(function () {
    res({});
});
$(document).on("click",".revoke",function () {
    var data={
        type:"POST",
        url:"/JxcProductOrder/out/revoke.do",
        data:{id:$(this).data("spy")},
        success:function (data) {
            notyf.confirm("撤回成功！");
        },
        dataType:"json"
    };
    $.ajax(data);
});
function res(productData) {
    var data={
        type:"POST",
        url:"/JxcProductOrder/out/getNowData.do",
        data:productData,
        success:function (data) {
            if(data.code){
                notyf.confirm(data.msg);
                var sqlData=data.data;
                var da="";
                for (var i = 0; i < sqlData.length; i++) {
                    da+="<tr>\n" +
                        "                                <td>"+(i+1)+"</td>\n" +
                        "                                <td>"+(sqlData[i].name)+"</td>\n" +
                        "                                <td>"+(sqlData[i].number)+"</td>\n" +
                        "                                <td>"+(sqlData[i].price)+"</td>\n" +
                        "                                <td>"+(ZZJS.timestampToTime(sqlData[i].create_date))+"</td>\n" +
                        "                                <td>"+(sqlData[i].status==1?"在途":(sqlData[i].status==2?"驳回":"结束"))+"</td>\n" +
                        "                                <td>\n" ;
                    if(sqlData[i].status!=3){
                        da += "                                    <button type=\"button\" class=\"btn btn-link revoke\" data-spy=\""+(sqlData[i].id)+"\">撤销</button>\n";
                        if(sqlData[i].status==2){
                            da+="                                    <button type=\"button\" class=\"btn btn-link edit\" data-spy=\""+(sqlData[i].id)+"\">修改</button>\n" +
                                "                                    <button type=\"button\" class=\"btn btn-link\" data-toggle=\"tooltip\" data-placement=\"left\" title="+(sqlData[i].rejectMsg)+"\">查看原因</button>\n";
                        }
                    }
                    da+="                                </td>\n" +
                        "                            </tr>";
                }
                $("#myData").html(da);
                ZZJS.paging(data.pageNum,3,data.pageCount,$("#padding"),productData,res);
            }else {
                notyf.alert(data.msg);
            }
        },
        dataType:"json"
    };
    $.ajax(data);
}

function checkFormData(obj) {
    if(obj.number==null||obj.number.length==0){
        notyf.alert("数据错误！");
        return false;
    }
    if(obj.price==null||obj.price.length==0){
        notyf.alert("数据错误！");
        return false;
    }
    return true;
}
$("#search").click(function () {
    var data={
        name:$("#productName").val()
    };
    if(data.name==undefined||data.name.length==0){
        notyf.alert("内容不可为空！");
        return;
    }
    console.log(data);
    res(data);
});
