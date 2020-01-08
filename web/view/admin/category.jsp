<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2020/1/6
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageName", "分类管理");
%>
<jsp:include page="base/head.jsp"></jsp:include>

<style>
    #category-list table {
        width: 100%;
        min-height: 400px;
    }

    #category-list table td > div {
        padding: 5px;
    }

    #category-list table td:nth-child(1) {
        background-color: #fff;
    }

    #category-list table tr {
        /*height: 400px;*/
    }

    #category-list table td {
        /*height: 100%;*/
        vertical-align: top;
    }

    #category-list table td > div {
        /*height: 100%;*/
    }

    .custom-tree-node {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: space-between;
        font-size: 14px;
        padding-right: 8px;
    }

    .el-tree-node {
        padding: 12px 0;
    }

    .el-tree-node__content {
         height: auto;
    }
</style>

<div id="category-list">
    <table>
        <tr>
            <td width="50%" style="">
                <div>
                    <el-tree :data="categoryData" :props="defaultProps" @node-click="handleNodeClick">
                    <span class="custom-tree-node" slot-scope="{ node, data }">
                    <span v-html="data.name"></span>
                    <span>
                      <el-button
                              type="primary"
                              size="mini"
                              @click="() => append(data)">
                        添加
                      </el-button>
                      <el-button
                              type="danger"
                              size="mini"
                              @click="() => remove(data)">
                        删除
                      </el-button>
                    </span>
                  </span>
                    </el-tree>
                </div>
            </td>
            <td width="50%">
                <el-form label-position="right" label-width="80px" :model="formData">
                    <el-form-item label="分类id">
                        <el-input v-model="formData.id" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="创建时间">
                        <el-input v-model="formData.createTime" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="分类名称">
                        <el-input v-model="formData.name"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="edit()">修改</el-button>
                        <el-button type="primary" @click="append({id:0,name:'根节点'})">增加根节点</el-button>
                    </el-form-item>
                </el-form>
            </td>
        </tr>
    </table>
    <el-dialog title="新增分类" :visible.sync="dialogFormVisible">
        <el-form :model="newCategory">
            <el-form-item label="父级Id" label-width="150">
                <el-input v-model="newCategory.id" autocomplete="off" disabled></el-input>
            </el-form-item>
            <el-form-item label="父级名称" label-width="150">
                <el-input v-model="newCategory.name" autocomplete="off" disabled></el-input>
            </el-form-item>
            <el-form-item label="分类名称" label-width="150">
                <el-input v-model="newCategory.newName" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="submitAdd()">确 定</el-button>
        </div>
    </el-dialog>
</div>
<script>
    var vm = new Vue({
        el: '#category-list',
        data() {
            return {
                categoryData: [{id: 1, name: "11"}],
                defaultProps: {
                    children: 'child',
                    label: 'name'
                },
                formData: {},
                newCategory: {},
                dialogFormVisible: false
            };
        },
        methods: {
            handleNodeClick(data) {
                console.log(data);
                this.formData = data;
            },
            submitAdd(){
                var that = this;
                var subData = that.newCategory;
                if(subData.newName.length<=0||subData.personId.length<=0){
                    this.$message.error("请补充数据~");
                    return;
                }
                $.post("category/add.do", subData, function (result) {
                    if (result.code) {
                        that.$message.success("新增成功~");
                        //获取数据
                        that.getCategoryList();
                    } else {
                        that.$message.error(result.errmsg);
                    }
                    that.dialogFormVisible = false;
                })
            },
            getCategoryList() {
                var that = this;
                $.get("commodity/format.do", function (result) {
                    console.log(result);
                    if (result.code) {
                        that.categoryData = result.data;
                    } else {
                        that.$message.error(result.errmsg);
                    }
                });
            },
            remove(data) {
                var that = this;
                $.post("category/del.do", {id: data.id}, function (result) {
                    if (result.code) {
                        that.$message.success("删除成功~");
                        //获取数据
                        that.getCategoryList();
                    } else {
                        that.$message.error(result.errmsg);
                    }
                })
            },
            append(data) {
                console.log(11);
                var that = this;
                that.newCategory = data;
                that.newCategory.personId = data.id;
                that.dialogFormVisible = true;
            },
            edit(data) {
                var that = this;
                if (that.formData.name.length <= 0) {
                    that.$message.error("请输入名称~");
                }
                $.post("category/edit.do", that.formData, function (result) {
                    if (result.code) {
                        that.$message.success("修改成功~");
                        //获取数据
                        this.getCategoryList();
                    } else {
                        that.$message.error(result.errmsg);
                    }
                })
            }
        },
        created() {
            //获取数据
            this.getCategoryList();
        }
    });
</script>


<jsp:include page="base/foot.jsp"></jsp:include>