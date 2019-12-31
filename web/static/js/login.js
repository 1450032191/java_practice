// 通知的配置
var notyf = new Notyf({
    // delay:100000,
    alertIcon: 'fa fa-exclamation-circle',
    confirmIcon: 'fa fa-check-circle'
});
$("#login").click(function () {
    var formData=ZZJS.serializeObject($("#loginForm"));
    if(!checkFormData(formData))
        return;
    $.post("/login/check.do",formData,function (data) {
        if(data.code){
            notyf.confirm(data.msg);
            location.href='/view/pages/main.jsp';
        }else {
            notyf.alert(data.msg);
        }
    },"json")
});
function checkFormData(obj) {
    if(obj.account.length==0){
        notyf.alert("请输入账户名称!");
        return false;
    }
    if(obj.pass.length==0){
        notyf.alert("请输入账户密码!");
        return false;
    }
    return true;
}