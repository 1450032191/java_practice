<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2020/1/6
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageName","首页");
%>
<jsp:include page="base/head.jsp"></jsp:include>
<style>
    #user-list,.search,.page{
        padding: 10px 0;
    }
    .search{

    }
    .search-key{
        width: 300px;
        display: inline-block;
    }
    .page{
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
                prop="orderNo"
                label="订单号"
                align="center">
        </el-table-column>
        <el-table-column
                prop="userName"
                label="用户名"
                align="center">
        </el-table-column>
        <el-table-column
                prop="userPhone"
                label="联系电话"
                align="center">
        </el-table-column>
        <el-table-column
                prop="orderCount"
                label="商品总数"
                align="center">
        </el-table-column>
        <el-table-column
                prop="orderPrice"
                label="商品总价"
                align="center">
        </el-table-column>
        <el-table-column
                prop="createTime"
                label="创建时间"
                align="center">
        </el-table-column>
        <el-table-column
                prop="status"
                label="订单状态"
                align="center">
            <template slot-scope="scope">
                <el-tag
                        type="success"
                        effect="dark">
                    {{ scope.row.orderStatusText }}
                </el-tag>
            </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
            <template slot-scope="scope">
                <el-button v-if="scope.row.orderStatus == 2"
                           size="mini"
                           @click="deliver(scope.row.orderId)">发货</el-button>
                <el-button v-if="scope.row.orderStatus == 3"
                           size="mini"
                           @click="end(scope.row.orderId)">结束订单</el-button>
                <!--                    <el-button size="mini"-->
                <!--                               @click="">详情</el-button>-->
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

<script type="text/javascript" src="static/ui_lib/excel/external/jszip.min.js"></script>
<script type="text/javascript" src="static/ui_lib/excel/external/FileSaver.js"></script>
<script type="text/javascript" src="static/ui_lib/excel/scripts/excel-gen.js"></script>

<script>
    var vue = new Vue({
        el: '#user-list',
        data(){
            return {
                userList:[],
                serachKey:"",
                page:{
                    pageIndex:0,
                    pageSize:10,
                    total:400,
                }
            }
        },
        methods:{
            getOrderList(){
                var that = this;
                var pageIndex = this.page.pageIndex;
                var pageSize = this.page.pageSize;
                var url = 'admin/order/list.do?pageIndex='+pageIndex+"&pageSize="+pageSize;
                $.get(url,function (result) {
                    console.log(result);
                    that.userList = result.data.list;
                    that.page = result.data.page;
                });
            },
            exportToExcel() {
                $("#user-list").find("table").attr("id","user-table");
                var excel = new ExcelGen({
                    "src_id": "user-table",
                    "show_header": true
                });
                excel.generate();
            },
            serach(){
                var that = this;
                if(this.serachKey.length==0){

                }else {
                    var pageIndex = this.page.pageIndex;
                    var pageSize = this.page.pageSize;
                    var seachKey = this.serachKey;
                    var url = 'admin/order/list.do?pageIndex='+pageIndex+"&pageSize="+pageSize;
                    if(seachKey.length>0){
                        url+="&key="+seachKey;
                    }
                    $.get(url,function (result) {
                        console.log(result);
                        if(result.code){
                            that.userList = result.data.list;
                            that.page = result.data.page;
                        }
                    });
                }
            },
            pageChange(val){
                this.page.pageIndex=val-1;
                this.getOrderList();
            },
            handleSizeChange(val){
                this.page.pageSize=val;
                this.getOrderList();
            },
            deliver(id){
                $.post("order/deliver.do",{order_id:id}).then((result)=>{
                    if(result.code){
                        this.getOrderList();
                    }else {
                        that.$message.error(result.errmsg);
                    }
                })
            },
            end(id){
                var that = this;
                $.post("order/end.do",{order_id:id}).then((result)=>{
                    if(result.code){
                        that.getOrderList();
                    }else {
                        that.$message.error(result.errmsg);
                    }
                })
            }
        },
        created() {
            //获取数据
            this.getOrderList();
        }
    });
</script>


<jsp:include page="base/foot.jsp"></jsp:include>