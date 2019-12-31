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
//驳回
$(document).on("click",".reject",function () {
    var id=$(this).data("spy");
    showModal({},function () {
        var obj={
            id:id,
            con:$("#con").val()
        };
        var data={
            type:"POST",
            url:"/JxcProductOrder/bill/reject.do",
            data:obj,
            success:function (data) {
                if(data.code){
                    notyf.confirm("驳回成功！");
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
//通过！
$(document).on("click",".adopt",function () {
    var id=$(this).data("spy");
    var data={
        type:"POST",
        url:"/JxcProductOrder/bill/adopt.do",
        data:{
            id:$(this).data("spy")
        },
        success:function (data) {
            if(data.code){
                notyf.confirm("通过成功！");
                res();
            }else {
                notyf.alert(data.msg);
            }
        },
        dataType:"json"
    };
    $.ajax(data);
});
$(document).ready(function () {
    res({});
});
function res(productData) {
    var data={
        type:"POST",
        url:"/JxcProductOrder/bill/getTask.do",
        data:productData,
        success:function (data) {
            if(data.code){
                var sqlData=data.data;
                var da="";
                for (var i = 0; i < sqlData.length; i++) {
                    da+="<tr>\n" +
                        "                                <td>"+(i+1)+"</td>\n" +
                        "                                <td>"+(sqlData[i].name)+"</td>\n" +
                        "                                <td>"+(sqlData[i].number)+"</td>\n" +
                        "                                <td>"+(sqlData[i].price)+"</td>\n" +
                        "                                <td>"+(ZZJS.timestampToTime(sqlData[i].create_date))+"</td>\n" +
                        "                                <td>"+(sqlData[i].order_type==1?"入库单":"出库单")+"</td>\n" +
                        "                                <td>"+(sqlData[i].status==1?"在途":(sqlData[i].status==2?"驳回":"结束"))+"</td>\n" +
                        "                                <td>"+(sqlData[i].creater)+"</td>\n" +
                        "                                <td>\n" +
                        "                                    <button type=\"button\" class=\"btn btn-link adopt\"  data-spy='"+(sqlData[i].id)+"'>通过</button>\n" +
                        "                                    <button type=\"button\" class=\"btn btn-link reject\" data-spy=\""+(sqlData[i].id)+"\">驳回</button>\n" +
                        "                                </td>\n" +
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