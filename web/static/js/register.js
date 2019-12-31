//日期选择器的初始化
$(function () {
    $("#birthday").dcalendarpicker({format: 'yyyy-mm-dd'});
});
// 通知的配置
var notyf = new Notyf({
    // delay:100000,
    alertIcon: 'fa fa-exclamation-circle',
    confirmIcon: 'fa fa-check-circle'
});
// 头像触发上传图片事件
$("#imgContainer").click(function () {
    $("#imgFile").click();
});
//上传图片的操作
$("#imgFile").change(function () {
    var img = document.getElementById("imgFile").files[0];
    var formData = new FormData();
    formData.append("file", img);
    ZZJS.ajax("post", "/upload/userImg.do", formData, function (data) {
        var updata=JSON.parse(data);
        if(updata.code){
            notyf.confirm(updata.msg);
            $("#imgContainer").prop("src",updata.imgUrl);
            $("#photo").val(updata.imgUrl);
        }else {
            notyf.alert(updata.msg);
        }
    });
});
//注册的操作
$("#register").click(function () {
    var formData=ZZJS.serializeObject($("#registerForm"));
    console.info(formData);
    if(checkFormData(formData)){

        //提交数据
        $.post("/login/register.do",formData,function (data) {
            if(data.code){
                notyf.confirm(data.msg);
                notyf.confirm("1秒后自动跳转！");
                setTimeout(function () {
                    location.href="/view/login.html";
                },1500);
            }else {
                notyf.alert(data.msg);
            }
        },"json");
    }
});
function checkFormData(obj) {
    if(obj.account.length==0){
        notyf.alert("请输入账户名");
        return false;
    }
    if(!ZZJS.checkEmail(obj.email)){
        notyf.alert("邮箱地址错误！");
        return false;
    }
    if(obj.name.length==0){
        notyf.alert("请输入昵称");
        return false;
    }
    if(obj.pass.length==0){
        notyf.alert("请输入密码");
        return false;
    }
    if(!ZZJS.checkDate(obj.birthday)){
        notyf.alert("请正确输入日期！(yyyy-mm-dd)");
        return false;
    }
    return true;
}