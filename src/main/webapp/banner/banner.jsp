<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script>
    $(function () {
        //初始化表单
        $("#bntable").jqGrid({
            url : "${path}/banner/showAll",
            editurl:"${path}/banner/edit",
            datatype : "json",
            rowNum : 5,  //每页展示条数   page   rows
            rowList : [ 3,5,10, 20, 30 ],  //可选展示条数
            styleUI:"Bootstrap",
            pager : '#bnpage',  //分页工具栏
            viewrecords : true,  //是否显示总条数
            autowidth:true,
            height : "auto",
            colNames : [ 'Id','名字', '图片', '状态', '描述','上传时间'],
            colModel : [
                {name : 'id',width : 55,align : "center"},
                {name : 'title',editable:true,width : 55,align : "center"},
                {name : 'img_path',editable:true,width : 100,align : "center",edittype:"file",
                    formatter:function(cellValue){
                        return "<img src='${path}/upload/photo/"+cellValue+"' style='width:100px;height:80px' >";
                    }
                },
                {name : 'status',width : 80,align : "center",
                    formatter:function(cellValue,option,row){
                    if (cellValue==-1)
                        return "<button class='btn btn-danger' onclick='modify(\""+row.id+"\",\""+cellValue+"\")'>解除冻结</button>";
                    else
                        return "<button class='btn btn-success' onclick='modify(\""+row.id+"\",\""+cellValue+"\")'>点击冻结</button>";
                    }
                },
                {name : 'description',editable:true,width : 80,align : "center"},
                {name : 'up_date',width : 80,align : "center"}
            ]
        });

        //处理增删改查操作
        $("#bntable").jqGrid('navGrid', '#bnpage', {edit : true,add : true,del : false,addtext:"添加",edittext:"修改"},
            {
                //关闭对话框
                closeAfterEdit:true,
                afterSubmit:function(data){
                    $.ajaxFileUpload({
                        url:"${path}/banner/upload",
                        datatype:"JSON",
                        type:"post",
                        fileElementId:"img_path", // 需要上传的文件域的ID
                        data:{id:data.responseText},
                        success:function(){
                            //刷新页面
                            $("#bntable").trigger("reloadGrid");
                        }
                    });
                    return "666";
                }
            },  //执行修改操作的额外配置
            {
                //关闭对话框
                closeAfterAdd:true,
                afterSubmit:function(data){
                    $.ajaxFileUpload({
                        url:"${path}/banner/upload",
                        datatype:"json",
                        type:"post",
                        fileElementId:"img_path", // 需要上传的文件域的ID
                        data:{id:data.responseText},
                        success:function(){
                            //刷新页面
                            $("#bntable").trigger("reloadGrid");
                        }
                    });
                    return "555";
                }
            }, //执行添加操作的额外配置
            {}
        );

    });
    function modify(id,status) {
        $.ajax({
            url:"${path}/banner/modify",
            type:"post",
            datatype:"JSON",
            data:{"id":id,"status":status},
            success:function () {
                $("#bntable").trigger("reloadGrid");
            }
        });
    }

</script>
<div class="panel panel-success">
    <div class="panel-heading"><h3>轮播图信息</h3></div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="#">轮播图信息</a></li>
        </ul>
        <table id="bntable"></table>
        <div id="bnpage"></div>
    </div>

</div>