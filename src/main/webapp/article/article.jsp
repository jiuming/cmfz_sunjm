<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<%--kindeditor添加以下脚本--%>
<script charset="utf-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.create('#editor_id',{
            uploadJson:"${path}/editor/uploadEditor",   //指定上传图片的路径
            filePostName:"photo",   //设置上传图片的名称
            allowFileManager:true, //是否展示浏览远程图片按钮
            fileManagerJson:"${path}/editor/queryPhotos", //指定浏览远程图片的路径
            afterBlur:function(){   //在kindeditor失去焦点之后执行的内容
                this.sync();  //将kindeditor中的内容同步到表单中
            }
        }
    );
</script>
<script>
    $(function () {
        //初始化表单
        $("#arttable").jqGrid({
            url : "${path}/article/showAll",
            editurl:"${path}/article/edit",
            datatype : "json",
            rowNum : 5,  //每页展示条数   page   rows
            rowList : [3,5,10, 20, 30],  //可选展示条数
            styleUI:"Bootstrap",
            pager : '#artpage',  //分页工具栏
            viewrecords : true,  //是否显示总条数
            autowidth:true,
            height : "auto",
            colNames : [ 'Id','标题', '作者','内容', '创建时间', '上师ID'],
            colModel : [
                {name : 'id',width : 55,align : "center"},
                {name : 'title',width : 55,align : "center"},
                {name : 'author',width : 55,align : "center"},
                {name : 'content',width : 100,align : "center"},
                {name : 'crea_date',width : 55,align : "center"},
                {name : 'guru_id',width : 55,align : "center"},
            ]
        });
        //处理增删改查操作
        $("#arttable").jqGrid('navGrid', '#artpage',
            {edit : false,add : false,del : false,search:false}
        );

        /*展示文章信息*/
        $("#infobtn").click(function(){

            //只读属性，最后选择行的id
            var rowId = $("#arttable").jqGrid("getGridParam","selrow");

            //判断是否选中一行
            if(rowId!=null){

                //根据行Id获取行数据
                var row= $("#arttable").jqGrid("getRowData",rowId);

                //给  title input框设置数据
                $("#title").val(row.title);

                //给  author input框设置数据
                $("#author").val(row.author);

                //给富文本编辑器设置内容
                KindEditor.html("#editor_id",row.content);

                //展示模态框
                $(".bs-example-modal-lg").modal("show");

                /*设置按钮*/
                $(".modal-footer").html("<button class='btn btn-primary' onclick='updateArticle(\""+rowId+"\")' >提交</button>" +
                    "<button class='btn btn-primary' data-dismiss='modal'>关闭</button>");
            }else{
                alert("请选择一行");
            }
        })

        /*添加文章*/
        $("#addbtn").click(function(){

            //清空表单
            $("#articleFrom")[0].reset();

            //清空kindeditor
            KindEditor.html("#editor_id","");

            //展示模态框
            $(".bs-example-modal-lg").modal("show");

            /*设置按钮*/
            $(".modal-footer").html("<button class='btn btn-primary' onclick='addArticle()' >提交</button>" +
                "<button class='btn btn-primary' data-dismiss='modal'>关闭</button>");
        });

        /*删除文章*/
        $("#delbtn").click(function(){
            //只读属性，最后选择行的id
            var rowId = $("#arttable").jqGrid("getGridParam","selrow");
            //判断是否选中一行
            if(rowId!=null){
                $.ajax({
                    url:"${path}/article/delArticle",
                    type:"post",
                    dataType:"json",
                    data:{"id":rowId},
                    success:function(){
                        //关闭模态框
                        $(".bs-example-modal-lg").modal('hide');
                        //刷新页面
                        $("#arttable").trigger("reloadGrid");
                    }
                });
            }else{
                alert("请选择一行");
            }
        });
    });
    //点击添加按钮 添加文章
    function addArticle(){
        $.ajax({
            url:"${path}/article/addArticle",
            type:"post",
            dataType:"json",
            data:$("#articleFrom").serialize(),
            success:function(){
                //关闭模态框
                $(".bs-example-modal-lg").modal('hide');
                //刷新页面
                $("#arttable").trigger("reloadGrid");
            }
        });
    }

    //点击展示详情 的提交按钮   修改文章信息
    function updateArticle(rowId){
        $.ajax({
            url:"${path}/article/updateArticle?id="+rowId,
            type:"post",
            dataType:"json",
            data:$("#articleFrom").serialize(),
            success:function(){

                //关闭模态框
                $(".bs-example-modal-lg").modal('hide');

                //刷新页面
                $("#arttable").trigger("reloadGrid");
            }
        });
    }

    //点击删除文章的提交按钮   删除文章信息
</script>
<div class="panel panel-danger">
    <div class="panel-heading"><h3>文章信息</h3></div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="#">文章信息</a></li>
        </ul>
        <br>
        <div class="row">
            <div class="col-lg-8">
                <button id="infobtn" class="btn btn-info">文章信息</button>&emsp;<button id="addbtn" class="btn btn-success">添加文章</button>&emsp;<button id="delbtn" class="btn btn-warning">删除文章</button>
            </div><!-- /.col-lg-6 -->
            <div class="col-lg-4">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入搜索内容">
                    <span class="input-group-btn">
                        <a class="btn btn-default" href="#">点击搜索</a>
                    </span>
                </div><!-- /input-group -->
            </div><!-- /.col-lg-6 -->
        </div><!-- /.row -->
        <br>
        <table id="arttable"></table>
        <div id="artpage"></div>
    </div>

    <div class="modal fade bs-example-modal-lg" role="dialog" align="center">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">文章信息</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="articleFrom">

                        <div class="input-group" style="width:400px">
                            <span class="input-group-addon">标题</span>
                            <input id="title" name="title" type="text" class="form-control" aria-describedby="basic-addon1">
                        </div><br>
                        <div class="input-group" style="width:400px">
                            <span class="input-group-addon">作者</span>
                            <input id="author" name="author" type="text" class="form-control" aria-describedby="basic-addon1">
                        </div><br>
                        <div class="input-group">
                            <%--2.添加textarea输入框--%>
                            <textarea id="editor_id" name="content" style="width:800px;height:300px;"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</div>