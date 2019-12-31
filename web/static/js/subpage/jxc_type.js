// 通知的配置
var notyf = new Notyf({
    // delay:100000,
    alertIcon: 'fa fa-exclamation-circle',
    confirmIcon: 'fa fa-check-circle'
});
var zTreeObj;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {
    view: {
        dblClickExpand: false
    },
    check: {
        enable: true
    },
    callback: {
        onRightClick: OnRightClick
    }
};
var zTree, rMenu;
function OnRightClick(event, treeId, treeNode) {
    if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
        zTree.cancelSelectedNode();
        showRMenu(event.clientX, event.clientY);
    } else if (treeNode && !treeNode.noR) {
        zTree.selectNode(treeNode);
        showRMenu(event.clientX, event.clientY);
    }
}
function showRMenu(x, y) {
    if(zTree.getSelectedNodes().length!=0){
        $("#rMenu ul").show();
        $("#m_add").show();
        $("#m_del").show();
        $("#m_edit").show();
        $("#m_reset").show();

        y += document.body.scrollTop;
        x += document.body.scrollLeft;
        rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

        $("body").bind("mousedown", onBodyMouseDown);
    }
}
function hideRMenu() {
    if (rMenu) rMenu.css({"visibility": "hidden"});
    $("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
    if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
        rMenu.css({"visibility" : "hidden"});
    }
}
// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
var zNodes = [
    {name:"test1", open:true, children:[
            {name:"test1_1"}, {name:"test1_2"}]},
    {name:"test2", open:true, children:[
            {name:"test2_1",id:12}, {name:"test2_2"}]}
];
$(document).ready(function(){
    resetTree();
});
function resetTree() {
    hideRMenu();
    var data={
        type:"POST",
        url:"/material/getAll.do",
        success:function (data) {
            if(data.code){
                $.fn.zTree.init($("#treeDemo"), setting, data.data);
                zTree = $.fn.zTree.getZTreeObj("treeDemo");
                rMenu = $("#rMenu");
            }else {
                notyf.alert(data.msg);
            }
        },
        dataType:"json"
    };
    $.ajax(data);
}
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
function addTreeNode(pid) {
    hideRMenu();
    var nodes =pid==undefined?zTree.getSelectedNodes()[0].id:pid;
    add(nodes);
}
function add(p_id) {
    showModal({modalName:"添加材料"},function () {
        var obj=ZZJS.serializeObject($("#modalForm"));
        if(!checkFormData(obj))
            return;
        obj.p_id=(p_id==undefined?0:p_id);
        var data={
            type:"POST",
            url:"/material/add.do",
            data:{
                name:obj.name,
                p_id:p_id
            },
            success:function (data) {
                if(data.code){
                    notyf.confirm(data.msg);
                    resetTree();
                }else {
                    notyf.alert(data.msg);
                }
            },
            dataType:"json"
        };
        $.ajax(data);
    })
}
function checkFormData(obj) {
    if(obj.name.length==0){
        notyf.alert("请正确输入名字");
        return false;
    }
    return true;
}

function removeTreeNode() {
    hideRMenu();
    var nodes = zTree.getSelectedNodes();
    if (nodes && nodes.length>0) {
        if (nodes[0].children && nodes[0].children.length > 0) {
            var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
            if (confirm(msg)==true){
                remove(nodes[0]);
            }
        } else {
            remove(nodes[0]);
        }
    }
}
function remove(el) {
    var id=el.id;
    if(id==null||id==undefined||id==0){
        notyf.alert("异常信息：0x000");
        return;
    }
    var data={
        type:"POST",
        url:"/material/del.do",
        data:{
            id:id
        },
        success:function (data) {
            if(data.code){
                notyf.confirm(data.msg);
                zTree.removeNode(el);
            }else {
                notyf.alert(data.msg);
            }
        },
        dataType:"json"
    };
    $.ajax(data);
}

function editTreeNode() {
    hideRMenu();
    var nodes = zTree.getSelectedNodes()[0];
    $("#inputName").val(nodes.name);
    showModal({modalName:"修改材料信息--"+nodes.name},function () {
        var obj=ZZJS.serializeObject($("#modalForm"));
        if(!checkFormData(obj))
            return;
        obj.id=nodes.id;
        $.post("/material/edit.do",obj,function (data) {
            if(data.code){
                notyf.confirm(data.msg);
                $("#modalForm")[0].reset();
                resetTree();
            }else {
                notyf.alert(data.msg);
            }
        },"json");
    })
}