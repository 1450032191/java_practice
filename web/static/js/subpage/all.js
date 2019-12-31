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
$("#add").click(function () {
    showModal({modalName:"采购入库"},function () {
        var obj=ZZJS.serializeObject($("#modalForm"));
        obj.type_id=$("#category").data("spy");
        obj.order_type=1;
        if(!checkFormData(obj)){
            return;
        }
        submitFormData(obj);
    })
});
$("#del").click(function () {
    showModal({modalName:"销售出库"},function () {
        var obj=ZZJS.serializeObject($("#modalForm"));
        obj.type_id=$("#category").data("spy");
        obj.order_type=2;
        if(!checkFormData(obj)){
            return;
        }
        submitFormData(obj);
    })
});

var zTreeObj;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {
    view: {
        dblClickExpand: false
    },
    check: {
        enable: true
    }
};
$("#showTree").click(function () {
    var data={
        type:"POST",
        url:"/material/getAll.do",
        success:function (data) {
            if(data.code){
                $.fn.zTree.init($("#treeDemo"), setting, data.data);
                zTree = $.fn.zTree.getZTreeObj("treeDemo");
                $("#treeModel").modal('show');
            }else {
                notyf.alert(data.msg);
            }
        },
        dataType:"json"
    };
    $.ajax(data);
});
$("#subSelect").click(function () {
    var node=zTree.getSelectedNodes()[0];
    if(node.children!=undefined){
        notyf.alert("请选择最底层！");
        return;
    }
    $("#category").val(node.name);
    $("#category").data("spy",node.id);
    $("#treeModel").modal('hide');
});

function checkFormData(obj) {
    if(obj.type_id==undefined||obj.type_id.length==0){
        notyf.alert("请选中类型");
        return false;
    }
    if(obj.price==undefined||obj.price.length==0){
        notyf.alert("请输入单价");
        return false;
    }
    if(obj.number==undefined||obj.number.length==0){
        notyf.alert("请输入数量");
        return false;
    }
    return true;
}
function submitFormData(obj) {
    var data={
        type:"POST",
        url:"/JxcProductOrder/add.do",
        data:obj,
        success:function (data) {
            if(data.code){
                notyf.confirm(data.msg);
            }else {
                notyf.alert(data.msg);
            }
        },
        dataType:"json"
    };
    $.ajax(data);
}
$(document).ready(function () {
    res({});
});
function res(productData) {
    var data={
        type:"POST",
        url:"/JxcProductOrder/getData.do",
        data:productData,
        success:function (data) {
            if(data.code){
                notyf.confirm(data.msg);
                var sqlData=data.data;
                var da="";
                for (var i = 0; i < sqlData.length; i++) {
                    da+="<tr>\n" +
                        "                                    <td>"+(i+1)+"</td>\n" +
                        "                                    <td>"+(sqlData[i].name)+"</td>\n" +
                        "                                    <td>"+(sqlData[i].total)+"</td>\n" +
                        "                                </tr>";
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
