<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2020/1/6
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageName", "用户管理");
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
                prop="userName"
                label="用户名"
                width="180">
        </el-table-column>
        <el-table-column
                prop="userPhone"
                label="手机号码">
        </el-table-column>
        <el-table-column
                prop="userEmail"
                label="邮箱">
        </el-table-column>
        <el-table-column
                prop="isAdmin"
                :filters="[{ text: '管理员', value: '1' }, { text: '普通用户', value: '0' }]"
                :filter-method="filterAdmin"
                label="角色"
                width="100"
                align="center">
            <template slot-scope="scope">
                <el-tag
                        :type="scope.row.userIsAdmin === '1' ? 'primary' : 'success'"
                        disable-transitions>{{scope.row.userIsAdmin === '1' ? '管理员' : '普通用户'}}
                </el-tag>
            </template>
        </el-table-column>
        <el-table-column
                prop="status"
                :filters="[{ text: '启用', value: '1' }, { text: '禁用', value: '0' }]"
                :filter-method="filterStatus"
                label="状态"
                width="80"
                align="center">
            <template slot-scope="scope">
                <el-tag
                        :type="scope.row.status === '1' ? 'success' : 'error'"
                        disable-transitions>{{scope.row.status === '1' ? '启用' : '禁用'}}
                </el-tag>
            </template>
        </el-table-column>
        <el-table-column label="操作">
            <template slot-scope="scope">
                <el-button
                        size="mini"
                        @click="setIsAdmin(scope.row.userId)"
                        v-html="scope.row.userIsAdmin == 0 ?'设为管理员':'取消管理员'"></el-button>
                <el-button
                        size="mini"
                        @click="setStatus(scope.row.userId)" :type="scope.row.status == 0 ?'warning':'danger'"
                        v-html="scope.row.userIsAdmin == 0 ?'禁用':'启用'"></el-button>
            </template>
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
                }
            }
        },
        methods: {
            filterAdmin(value, row) {
                return row.isAdmin === value;
            },
            filterStatus(value, row) {
                return row.status === value;
            },
            getUserList() {
                var that = this;
                var pageIndex = this.page.pageIndex;
                var pageSize = this.page.pageSize;
                var url = 'user/list.do?pageIndex='+pageIndex+"&pageSize="+pageSize;
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
            serach() {
                var that = this;
                if (this.serachKey.length == 0) {
                    error("请输入内容~");
                } else {
                    var pageIndex = this.page.pageIndex;
                    var pageSize = this.page.pageSize;
                    var seachKey = this.serachKey;
                    var url = 'user/list.do?pageIndex='+pageIndex+"&pageSize="+pageSize;
                    if(seachKey.length>0){
                        url+="&seachKey="+seachKey;
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
            setIsAdmin(id) {
                $.post("user/setAdmin.do", {userId: id}).then((result) => {
                    if (result.code) {
                        this.getUserList();
                    }
                })
            },
            setStatus(id) {
                var that = this;
                $.post("user/setUserStatus.do", {userId: id}).then((result) => {
                    if (result.code) {
                        that.getUserList();
                    }
                })
            }
        },
        created() {
            //获取数据
            this.getUserList();
        }
    });
</script>


<jsp:include page="base/foot.jsp"></jsp:include>