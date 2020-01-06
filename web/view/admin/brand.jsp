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

<div id="brand-list">
    <table>
        <tr>
            <td width="50%" style="">
                <el-table
                        :data="brandList"
                        style="width: 100%">
                    <el-table-column
                            label="品牌名称"
                            width="180"
                            prop="name">
                    </el-table-column>
                    <el-table-column label="操作">
                        <template slot-scope="scope">
                            <el-button
                                    size="mini"
                                    type="danger"
                                    @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </td>
            <td width="50%">
                <el-form label-position="right" label-width="80px" :model="formData">
                    <el-form-item label="品牌id">
                        <el-input v-model="formData.id" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="创建时间">
                        <el-input v-model="formData.create_time" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="品牌名称">
                        <el-input v-model="formData.name"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="edit()">修改</el-button>
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
        method:{
            getBrandList(){
                var that = this;
                $.get("",function (result) {
                    if(result.code){
                        that.brandList = result.data;
                    }else {
                        that.$message.error(result.errmsg);
                    }
                })
            }
        }
    });
</script>


<jsp:include page="base/foot.jsp"></jsp:include>