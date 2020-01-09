<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2020/1/6
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageName","通知页面");
%>
<jsp:include page="base/head.jsp"></jsp:include>
<style>
    #user-list, .search, .page {
        padding: 10px 0;
    }

    .search {

    }

    .search-key {
        width: 300px;
        display: inline-block;
    }

    .page {
        text-align: right;
    }
</style>

<div id="user-list">
    <div class="search">
        <el-input class="search-key"
                  placeholder="请输入内容"
                  v-model="serachKey"
                  clearable>
        </el-input>
        <el-button style="margin-left: 10px;" type="primary" icon="el-icon-search" @click="serach()">搜索</el-button>
        <el-button type="primary" @click="exportToExcel()">导出Excel</el-button>
        <el-button type="primary" @click="sendNotic()">发送公告</el-button>
    </div>

    <el-table
            :data="userList"
            border
            style="width: 100%" id="user-table">
        <el-table-column
                prop="date"
                label="#"
                type="index"
                width="50">
        </el-table-column>
        <el-table-column
                prop="userHeadline"
                label="标题"
                width="180">
        </el-table-column>
        <el-table-column
                prop="userNotice"
                label="内容">
        </el-table-column>
        <el-table-column
                prop="sendUserName"
                label="发送人">
        </el-table-column>
        <el-table-column
                prop="acceptCount"
                label="接受人数"
                width="100">
        </el-table-column>
        <el-table-column
                prop="readCount"
                label="已读人数"
                width="100">
        </el-table-column>
        <el-table-column
                prop="noReadCount"
                label="未读人数"
                width="100">
        </el-table-column>
    </el-table>

    <div class="page">
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="pageChange"
                :current-page="page.pageIndex + 1"
                :page-sizes="[10, 20, 30, 40]"
                :page-size="page.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="page.total">
        </el-pagination>
    </div>

    <el-dialog title="发送公告" :visible.sync="dialogFormVisible">
        <el-form :model="newNotic">

            <el-form-item label="目标人群">
                <el-select v-model="newNotic.sendUser" placeholder="请选择活动区域">
                    <el-option label="所有用户" value="1"></el-option>
                    <el-option label="管理员" value="2"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="公告标题">
                <el-input v-model="newNotic.userHeadline" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="公告内容">
                <el-input
                        type="textarea"
                        :rows="2"
                        placeholder="请输入内容"
                        v-model="newNotic.userNotice">
                </el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="send()">确 定</el-button>
        </div>
    </el-dialog>
</div>


<script>
    var vue = new Vue({
        el: '#user-list',
        data() {
            return {
                userList: [],
                serachKey: "",
                page: {
                    pageIndex: 0,
                    pageSize: 10,
                    total: 400,
                },
                dialogFormVisible: false,
                newNotic:{
                    userHeadline: "",
                    userNotice: "",
                    sendUser: ""
                }
            }
        },
        methods: {
            getUserList() {
                var that = this;
                var pageIndex = this.page.pageIndex;
                var pageSize = this.page.pageSize;
                var url = 'admin/notic/list.do?pageIndex='+pageIndex+"&pageSize="+pageSize;
                $.get(url,function (result) {
                    console.log(result);
                    if (result.code) {
                        that.userList = result.data.list;
                        that.page = result.data.page;
                    }
                });
            },
            exportToExcel() {
                // 使用outerHTML属性获取整个table元素的HTML代码（包括<table>标签），然后包装成一个完整的HTML文档，设置charset为urf-8以防止中文乱码
                var html = "<html><head><meta charset='utf-8' /></head><body>" + document.getElementById("user-table").outerHTML + "</body></html>";
                // 实例化一个Blob对象，其构造函数的第一个参数是包含文件内容的数组，第二个参数是包含文件类型属性的对象
                var blob = new Blob([html], {type: "application/vnd.ms-excel"});
                var a = document.getElementsByTagName("a")[0];
                // 利用URL.createObjectURL()方法为a元素生成blob URL
                a.href = URL.createObjectURL(blob);
                // 设置文件名，目前只有Chrome和FireFox支持此属性
                a.download = "用户信息表.xls";
            },
            send(){
                var data = this.newNotic;
                var that = this;
                if(data.userHeadline.length == 0 || data.userNotice.length == 0){
                    this.$message.error("请传入参数~");
                    return;
                }
                $.post("admin/notic/send.do",data,function (result) {
                    if(result.code){
                        that.$message.success("成功~");
                        that.getUserList();
                        that.dialogFormVisible = false;
                    }else {
                        that.$message.error(result.errmsg);
                    }
                });
            },
            serach() {
                var that = this;
                if (this.serachKey.length == 0) {
                    error("请输入内容~");
                } else {
                    var pageIndex = this.page.pageIndex;
                    var pageSize = this.page.pageSize;
                    var seachKey = this.serachKey;
                    var url = 'admin/notic/list.do?pageIndex='+pageIndex+"&pageSize="+pageSize;
                    if(seachKey.length>0){
                        url+="&key="+seachKey;
                    }
                    $.get(url,function (result) {
                        console.log(result);
                        if (result.code) {
                            that.userList = result.data.list;
                            that.page = result.data.page;
                        }
                    });
                }
            },
            pageChange(val) {
                this.page.pageIndex = val - 1;
                this.getUserList();
            },
            handleSizeChange(val) {
                this.page.pageSize = val;
                this.getUserList();
            },
            sendNotic(){
                this.dialogFormVisible = true;
            }
        },
        created() {
            //获取数据
            this.getUserList();
        }
    });
</script>


<jsp:include page="base/foot.jsp"></jsp:include>