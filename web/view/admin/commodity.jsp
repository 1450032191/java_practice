<%--
  Created by IntelliJ IDEA.
  User: ZiNan
  Date: 2020/1/6
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("pageName","商品管理");
    request.setAttribute("sum","商品管理");
%>
<jsp:include page="base/head.jsp"></jsp:include>
<style>
    .form-img{
        text-align: center;
    }
    .form-img img{
        width: 150px;
        height: 150px;
    }
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

    #commodity-list .note-editor.note-frame {
        border: 1px solid #a9a9a9 !important;
        background-color: #f5f5f5 !important;
    }

    #commodity-list .note-editor.note-frame .note-btn{
    }

    #commodity-list .btn-default {
        color: #333 !important;
        background-color: #fff !important;
        border-color: #ccc !important;
    }

    #commodity-list .note-popover .popover-content,#commodity-list .panel-heading.note-toolbar {
        padding: 0 0 5px 5px !important;
    }

</style>

<div id="commodity-list">
    <div id="user-list">
        <div class="search">
            <el-input class="search-key"
                      placeholder="请输入内容"
                      v-model="serachKey"
                      clearable>
            </el-input>
            <el-select v-model="serachBrand" placeholder="请选择活动区域">
                <el-option v-for="item in brands" :label="item.brandName" :value="item.brandId"></el-option>
            </el-select>
            <el-button style="margin-left: 10px;" type="primary" icon="el-icon-search" @click="serach(0)">搜索</el-button>
            <el-button style="margin-left: 10px;" type="danger" @click="serach(1)">库存预警</el-button>
            <el-button type="primary" @click="exportToExcel()">导出Excel</el-button>
        </div>
        <el-table
                :data="commodityList"
                border
                style="width: 100%" id="">
            <el-table-column
                    prop="date"
                    label="#"
                    type="index"
                    width="50" align="center">
            </el-table-column>
            <el-table-column
                    label="商品主图"
                    width="180" align="center">
                <template slot-scope="scope">
                    <el-avatar shape="square" :size="100" fit="fill" :src="scope.row.comImg"></el-avatar>
                </template>
            </el-table-column>
            <el-table-column
                    prop="comName"
                    label="商品名称">
            </el-table-column>
            <el-table-column
                    prop="brandName"
                    label="品牌名称" width="120">
            </el-table-column>
            <el-table-column
                    prop="categoryName"
                    label="分类名称" width="120">
            </el-table-column>
            <el-table-column
                    prop="sales"
                    label="销量" width="80">
            </el-table-column>
            <el-table-column
                    prop="comStock"
                    label="库存" width="80">
                <template slot-scope="scope">
                    <el-tag :type="scope.row.comStock<=10?'danger':'success'">{{scope.row.comStock}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" fixed="right" width="170">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="del(scope.row.comId)">编辑</el-button>
                    <el-button
                            size="mini"
                            @click="del(scope.row.comId)" type="danger">删除</el-button>
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

    <el-dialog title="商品管理" :visible.sync="dialogFormVisible" width="830px">
        <el-form :model="commodityForm">
            <div class="form-img">
                <img :src="commodityForm.comImg" alt="商品图片" id="comImg">
                <input type="file" style="display: none" id="img-file">
            </div>
            <el-form-item label="商品名称">
                <el-input v-model="commodityForm.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="品牌">
                <el-select v-model="commodityForm.comBrandId" placeholder="请选择活动区域">
                    <el-option v-for="item in brands" :label="item.brandName" :value="item.brandId"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="分类">
                <el-cascader :options="categorys" :props="defaultProps" :value="commodityForm.cotegory">
                    <template slot-scope="{ node, data }">
                        <span>{{ data.name }}</span>
                        <span v-if="!node.isLeaf"> ({{ data.child.length }}) </span>
                    </template>
                </el-cascader>
            </el-form-item>
            <el-form-item label="售价">
                <el-row>
                    <el-col :span="8">
                        <el-input v-model="commodityForm.comPrice"></el-input>
                    </el-col>
                </el-row>
            </el-form-item>
            <el-form-item label="原价">
                <el-row>
                    <el-col :span="8">
                        <el-input v-model="commodityForm.comOp"></el-input>
                    </el-col>
                </el-row>
            </el-form-item>
            <el-form-item label="库存">
                <el-row>
                    <el-col :span="8">
                        <el-input v-model="commodityForm.comStock"></el-input>
                    </el-col>
                </el-row>
            </el-form-item>
            <el-form-item>
                <div class="summernote" id="service_detail">summernote 1</div>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
    </el-dialog>
</div>

<script type="text/javascript" src="static/ui_lib/excel/external/jszip.min.js"></script>
<script type="text/javascript" src="static/ui_lib/excel/external/FileSaver.js"></script>
<script type="text/javascript" src="static/ui_lib/excel/scripts/excel-gen.js"></script>
<script type="text/javascript" src="static/ui_lib/excel/scripts/demo.page.js"></script>

<script>
    var vue = new Vue({
        el: '#commodity-list',
        data(){
            return {
                serachBrand:"",
                commodityList:[],
                serachKey:"",
                page:{
                    pageIndex:0,
                    pageSize:10,
                    total:400,
                },
                dialogFormVisible: true,
                commodityForm: {
                    comImg: 'https://img20.360buyimg.com/jdcms/s150x150_jfs/t1/102716/5/9530/313425/5e10912fE995fddec/1d4c821fdee7d59e.jpg.webp'
                },
                defaultProps: {
                    children: 'child',
                    label: 'name',
                    value: 'id'
                },
                brands:[],
                categorys:[]
            }
        },
        methods:{
            filterStatus(value, row) {
                return row.status === value;
            },
            getCategoryList() {
                var that = this;
                $.get("commodity/format.do", function (result) {
                    console.log(result);
                    if (result.code) {
                        that.categorys = result.data;
                    } else {
                        that.$message.error(result.errmsg);
                    }
                });
            },
            getBrandList(){
                var that = this;
                var pageIndex = this.page.pageIndex;
                var pageSize = this.page.pageSize;
                var url = 'brand/list.do';
                $.get(url,function (result) {
                    console.log(result);
                    that.brands = result.data;
                });
            },
            getCommodityList(){
                var that = this;
                var pageIndex = this.page.pageIndex;
                var pageSize = this.page.pageSize;
                var url = 'commodity/list.do?pageIndex='+pageIndex+"&pageSize="+pageSize;
                $.get(url,function (result) {
                    console.log(result);
                    that.commodityList = result.data.list;
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
            serach(data){
                var that = this;
                if(this.serachKey.length==0&&this.serachBrand.length==0){

                }else {
                    var pageIndex = this.page.pageIndex;
                    var pageSize = this.page.pageSize;
                    var seachKey = this.serachKey;
                    var serachBrand = this.serachBrand;
                    var url = 'commodity/list.do?pageIndex='+pageIndex+"&pageSize="+pageSize;
                    if(serachBrand.length>0){
                        url+="&seachKey="+seachKey;
                    }
                    if(serachBrand.length>0){
                        url+="&serachBrand="+serachBrand;
                    }
                    if(data==1){
                        url+="&stock=true";
                    }
                    $.get(url,function (result) {
                        console.log(result);
                        if(result.code){
                            that.commodityList = result.data.list;
                            that.page = result.data.page;
                        }
                    });
                }
            },
            pageChange(val){
                this.page.pageIndex=val-1;
                this.getCommodityList();
            },
            handleSizeChange(val){
                this.page.pageSize=val;
                this.getCommodityList();
            },
            del(id){
                var that = this;
                $.post("commodity/del.do",{comId:id},function (result) {
                    if(result.code){
                        that.$message.success("删除成功~");
                        that.getCommodityList();
                    }else {
                        that.$message.error("删除成功~");
                    }
                });
            },
            edit(){
                var that = this;
                var data = that.commodityForm;
            }
        },
        created() {
            //获取数据
            this.getCommodityList();
            this.getBrandList();
            this.getCategoryList();
        }
    });
</script>

<script>
    $("#comImg").click(function () {
        $("#img-file").click();
    });
    $("#img-file").change(function () {
        var formData = new FormData();
        formData.append('file', $('#img-file')[0].files[0]);  //添加图片信息的参数
        $.ajax({
            url: "util/upImg.do",
            type: "post",
            dataType: "json",
            cache: false,
            data: formData,
            processData: false,// 不处理数据
            contentType: false, // 不设置内容类型
            success: function (result) {
                if(result.code){
                    alert("上传成功~");
                    vue.commodityForm.comImg = result.data.path;
                }else {
                    alert("上传失败~");
                }
            }
        })
    });

    var $summernote=$('.summernote').summernote({
        height: 200,
        width: 794,
        tabsize: 2,
        lang: 'zh-CN',
        callbacks: {
            onImageUpload: function (files) {
                sendFile($summernote, files[0]);
            }
        }
    });

    function sendFile($summernote, file) {
        var formData = new FormData();
        formData.append("pic", file);
        $.ajax({
            url: "util/upImg.do",//路径是你控制器中上传图片的方法，下面controller里面我会写到
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            type: 'POST',
            success: function (data) {
                console.log(data);
                $("#summernote").summernote('insertImage', data.url, function ($image) {
                    $image.attr('src', data.url);
                    $("#myModal").css("overflow-x", "hidden");
                    $("#myModal").css("overflow-y", "auto");
                });
            }
        });
    }
</script>


<jsp:include page="base/foot.jsp"></jsp:include>