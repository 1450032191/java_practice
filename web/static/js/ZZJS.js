/// <reference path="jquery-1.10.2.js" />

var ZZJS = (function (mod, window, undefined) {
    //ajax方法（（get/post）,url地址，传参，回调函数）
    mod.ajax = function (method, url, parmas, callback) {
        var xhr;
        if (window.XMLHttpRequest) {
            xhr = new XMLHttpRequest();
        } else {
            xhr = new ActiveXObject("Microsoft.XMLHTTP");
        }

        method = method || "get";
        url = url || "";
        xhr.open(method, url);
        if (parmas instanceof Function){
            xhr.send();
            callback = parmas;
        } else {
            parmas = parmas || {};
            xhr.send(parmas);
        }

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                if (xhr.status == 200) {
                    callback(xhr.response);
                } else {
                    console.error("AJAX请求错误！");
                }
            } 
        }
    };
    //cooike方法
    mod.Cookie = {
        //获取对映的键的Cookie
        getCookie: function (name) {
            var cookies = document.cookie.split(";");
            for (var i = 0; i < cookies.length; i++) {
                var cookieArray = cookies[i].split("=");
                if (cookieArray[0] === name) {
                    return cookieArray[1];
                }
            }
            return "";
        },
        //name：cookie键
        //name：cookie值
        //et: cookie过期时间（单位：毫秒）
        setCookie: function (name,val,et) {
            var date = new Date(),
                ms = date.getTime(),
                time = new Date(ms + et);
            document.cookie = name+"="+val+";expires="+time.toUTCString();
        },
        //删除对映的键的Cookie
        deleteCookie: function (name) {
            this.setCookie(name,"",-1);
        }
    };
    //表单序列化为对象
    //jqueryForm   jquery对象的表单
    mod.serializeObject=function (jqueryForm) {
        var o = {};
        var a = jqueryForm.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [ o[this.name] ];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
    //获取get参数
    mod.getParameByURL=function () {
        var url = window.document.location.href.toString();
        var u = url.split("?");
        if (typeof(u[1]) == "string") {
            u = u[1].split("&");
            var get = {};
            for (var i in u) {
                var j = u[i].split("=");
                get[j[0]] = j[1];
            }
            return get;
        } else {
            return {};
        }
    };
    //分页插件  pagnum--当前页数,displayPag--要显示的页数,pagSize--分页长度,el--要添加的元素
    //搭配bootstrap使用
    mod.paging=function (pagNum,displayPag,pagSize,el,url,res) {
        displayPag--;
        console.log(url);
        var data="<ul class=\"pagination pagination-lg\" id='myPadding'>";
        if(pagNum!=1){
            var num=pagNum-1;
            data+="<li>\n" +
                "                        <a href=\"JavaScript:void(0)\" data-toggle='"+num+"' aria-label=\"Previous\">\n" +
                "                            <span aria-hidden=\"true\">上一页</span>\n" +
                "                        </a>\n" +
                "                    </li>";
        }
        var start=pagNum,end=pagNum;
        for (var i = 0; i < displayPag; i++) {
            if(i%2==0){
                start--;
                if(start<=0){
                    start++;
                    end=end+1>pagSize?end:end+1;
                }
            }else {
                end++;
                if(end>pagSize){
                    end--;
                    start=start-1<=0?start:start-1;
                }
            }
        }
        if(start!=1){
            data+="<li class=\"disabled\"><a href=\"JavaScript：void(0)\">···</a></li>"
        }
        for (var i = start; i <=end; i++) {
            if(i==pagNum){
                data+="<li class=\"active\"><a href=\"JavaScript:void(0)\"" +
                    "data-toggle='"+i+"'" +
                    ">"+i+"</a></li>"
            }else {
                data+="<li ><a href=\"JavaScript:void(0)\" data-toggle='"+i+"'>"+i+"</a></li>"
            }
        }
        if(end!=pagSize){
            data+="<li class=\"disabled\">\n" +
                "                        <a href=\"JavaScript:void(0)\" aria-label=\"Previous\">\n" +
                    "                            <span aria-hidden=\"true\">···</span>\n" +
                "                        </a>\n" +
                "                    </li>";
        }
        if(pagNum!=pagSize){
            var num=pagNum+1;
            data+="<li>\n" +
                "                        <a href=\"JavaScript:void(0)\" data-toggle='"+num+"' aria-label=\"Previous\">\n" +
                "                            <span aria-hidden=\"true\">下一页</span>\n" +
                "                        </a>\n" +
                "                    </li>";
        }
        data+="</ul>";
        el.html(data);
        $("#myPadding a").off("click").on("click",function () {
            var data=url;
            url.pageNum=$(this).data("toggle");
            console.log(data);
            res(data);
        });
    };
    //获取当前url
    //item:boolean类型 true为获取包括get后面的地址，默认不获取
    mod.getUrl=function (item) {
        if(item){
            var url=window.location.pathname+"?";
            var urlData=ZZJS.getParameByURL();
            for (key in urlData){
                url+=key+"="+urlData[key]+"&";
            }
            return url;
        }else {
            return window.location.pathname;
        }
    };
    //字符串是否已某个特定的字符结束
    mod.endWith=function (str,ch) {
        var d=str.length-ch.length;
        return (d>=0&&this.lastIndexOf(endStr)==d)
    };
    //ajax上传图片
    mod.ajax = function (method, url, parmas, callback) {
        var xhr;
        if (window.XMLHttpRequest) {
            xhr = new XMLHttpRequest();
        } else {
            xhr = new ActiveXObject("Microsoft.XMLHTTP");
        }
        method = method || "get";
        url = url || "";
        xhr.open(method, url);
        if (parmas instanceof Function){
            xhr.send();
            callback = parmas;
        } else {
            parmas = parmas || {};
            xhr.send(parmas);
        }
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                if (xhr.status == 200) {
                    callback(xhr.response);
                } else {
                    console.error("AJAX请求错误！");
                }
            }
        }
    };
    //验证邮箱
    mod.checkEmail=function (data) {
        var mailReg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
        return mailReg.test(data);
    };
    //验证时间格式是否正确 (yyyy-mm-dd)
    mod.checkDate=function (data) {
        var a = /^(\d{4})-(\d{2})-(\d{2})$/;
        return a.test(data);
    };
    //向字符串中的指定字符加红加粗
    mod.addColor=function (chang,duan) {
        var start=chang.indexOf(duan);
        if (start==-1)
            return -1;
        var end=start+duan.length-1;
        var da="";
        for (var i = 0; i < chang.length; i++) {
            if(i==start&&end==i){
                da+="<span style='color: #ed4014;font-weight: bold'>"+chang.charAt(i)+
                    "</span>";
            }else if(i==start){
                da+="<span style='color: #ed4014;font-weight: bold'>"+chang.charAt(i);
            }else if(i==end){
                da+=chang.charAt(i)+"</span>"
            }else {
                da+=chang.charAt(i);
            }
        }
        return da;
    };
    //删除url中指定的参数
    mod.funcUrlDel=function (sename) {
        var url = location.pathname;
        var urls = url.split("?");
        myUrl = urls[0] + "?";
        parmes = urls.length >= 2 ? urls[1].split("&") : [];
        for (var i = 0; i < parmes.length; i++) {
            var parme = parmes.split("=");
            var name = parme[0];
            var value = parme[1];
            console.log(name + "----" + value);
            if (sename != name) {
                myUrl += name + "=" + value;
                myUrl += (i == (parmes.length - 1) ? "" : "&");
            }
        }
        return myUrl;
    };
    mod.timestampToTime=function (timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        var D = date.getDate() + ' ';
        var h = date.getHours() + ':';
        var m = date.getMinutes() + ':';
        var s = date.getSeconds();
        return Y+M+D+h+m+s;
    }
    return mod;
})(ZZJS || {},window,undefined);