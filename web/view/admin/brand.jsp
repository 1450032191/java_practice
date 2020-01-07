<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2020/1/6
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageName","品牌管理");
%>
<jsp:include page="base/head.jsp"></jsp:include>
<style>
    #brand-list table {
        width: 100%;
        min-height: 400px;
    }

    #brand-list table td > div {
        padding: 5px;
    }

    #brand-list table td:nth-child(1) {
        background-color: #fff;
    }

    #brand-list table tr {
    }

    #brand-list table td {
        vertical-align: top;
    }

    #brand-list table td > div {

    }

    #list{
        list-style: none;
        padding: 15px;
    }
    #list li{
        overflow: hidden;
        padding: 5px;
        padding-right: 10px;
    }
    #list .brand-name{
         float: left;
     }
    #list .brand-cao{
        float: right;
    }
</style>
<div id="brand-list">
    <table>
        <tr>
            <td width="50%" style="">
              <ul id="list">
                  <li v-for="brand in brandList" @click="setEdit(brand)">
                      <div class="brand-name">{{ brand.brandName }}</div>
                      <div class="brand-cao">
                          <el-button
                                  type="danger"
                                  size="mini"
                                  @click="del(brand)">
                              删除
                          </el-button>
                      </div>
                  </li>
              </ul>
            </td>
            <td width="50%">
                <el-form label-position="right" label-width="80px" :model="formData">
                    <el-form-item label="品牌id">
                        <el-input v-model="formData.brandId" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="创建时间">
                        <el-input v-model="formData.create_time" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="品牌名称">
                        <el-input v-model="formData.brandName"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="edit()">修改</el-button>
                        <el-button type="primary" @click="add()">增加</el-button>
                    </el-form-item>
                </el-form>
            </td>
        </tr>
    </table>
</div>

<script>
    var vue = new Vue({
        el: '#brand-list',
        data(){
            return {
                brandList: [],
                formData: {}
            }
        },
        methods:{
            getBrandList(){
                var that = this;
                $.get("brand/list.do",function (result) {
                    if(result.code){
                        that.brandList = result.data;
                    }else {
                        that.$message.error(result.errmsg);
                    }
                })
            },
            edit(){
                var that = this;
                var data = this.formData;
                if(data.brandName.length == 0){
                    this.$message.error("请检查参数~");
                    return;
                }
                $.post("brand/edit.do",data,function (result) {
                    if(result.code){
                        that.$message.success("修改成功~");
                        that.getBrandList();
                    }else {
                        that.$message.error(result.errmsg);
                    }
                })
            },
            del(data){
                var that = this;
                $.post("brand/delete.do",data,function (result) {
                    if(result.code){
                        that.$message.success("删除成功~");
                        that.getBrandList();
                    }else {
                        that.$message.error(result.errmsg);
                    }
                })
            },
            setEdit(data){
                this.formData = data;
            },
            add(){
                var that = this;
                var data = this.formData;
                if(data.brandName.length == 0){
                    this.$message.error("请检查参数~");
                    return;
                }
                $.post("brand/add.do",data,function (result) {
                    if(result.code){
                        that.$message.success("增加成功~");
                        that.getBrandList();
                    }else {
                        that.$message.error(result.errmsg);
                    }
                })
            }
        },
        created(){
            this.getBrandList();
        }
    });
</script>


<jsp:include page="base/foot.jsp"></jsp:include>