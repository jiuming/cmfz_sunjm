<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script>
    $(function(){
        $("#abtable").jqGrid({
            url : '${path}/album/showAll',
            editurl:"${path}/album/edit",
            datatype : "json",
            autowidth:true,
            styleUI:"Bootstrap",
            height : "auto",
            rowNum : 8,
            rowList : [ 8, 10, 20, 30 ],
            pager : '#abpage',
            viewrecords : true,
            colNames : [ 'ID', '名称', '封面', '作者', '分数','集数','内容', '上传时间' ],
            colModel : [
                {name : 'id', width : 55},
                {name : 'title',editable:true,width : 90},
                {name : 'cover',editable:true,edittype:"file",width : 100,
                    formatter:function(cellValue){
                        return "<img src='${path}/upload/cover/"+cellValue+"' style='width:100px;height:80px' >";
                    }
                },
                {name : 'author',editable:true,width : 80,align : "right"},
                {name : 'score',editable:true,width : 80,align : "right"},
                {name : 'count',width : 80,align : "right"},
                {name : 'content',editable:true,width : 80,align : "right"},
                {name : 'crea_date',width : 150,sortable : false}
            ],
            subGrid : true,  //是否开启子表格  // subgrid_id是在表数据中创建的div标签的id
            subGridRowExpanded : function(subgrid_id, row_id) {  //subgrid_id  子表格id  row_id 行id
                //开启子表格
                addSubGrid(subgrid_id,row_id);
            }
        });
        //处理增删改方法
        $("#abtable").jqGrid('navGrid', '#abpage',
            {add : true,edit : true,del : true},
            {
                //关闭对话框
                closeAfterEdit:true,
                afterSubmit:function(data){
                    $.ajaxFileUpload({
                        url:"${path}/album/upload",
                        datatype:"JSON",
                        type:"post",
                        fileElementId:"cover", // 需要上传的文件域的ID
                        data:{id:data.responseText},
                        success:function(){
                            //刷新页面
                            $("#abtable").trigger("reloadGrid");
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
                        url:"${path}/album/upload",
                        datatype:"json",
                        type:"post",
                        fileElementId:"cover", // 需要上传的文件域的ID
                        data:{id:data.responseText},
                        success:function(){
                            //刷新页面
                            $("#abtable").trigger("reloadGrid");
                        }
                    });
                    return "555";
                }
            }, //执行添加操作的额外配置
            {
                closeAfterDel:true,
                afterSubmit:$("#abtable").trigger("reloadGrid")
            }
        );
    });

    //子表格
    function addSubGrid(subgridId,rowId){

        //tableId
        var subgridTableId = subgridId+"table";

        //工具栏Id
        var pagerId = subgridId+"page";

        //在子表格中创建一个表单table，创建一个工具栏div
        $("#"+subgridId).html("" +
            "<table id='"+subgridTableId+"'/>" +
            "<div id='"+pagerId+"' />"
        );

        //初始子表格
        $("#" + subgridTableId).jqGrid({
            //url : "/chapter/queryByPage?AlbumId="+rowId,
            url : "${path}/chapter/showAll?albumId="+rowId,
            editurl:"${path}/chapter/edit?album_id="+rowId,
            datatype : "json",
            rowNum : 20,
            pager : "#"+pagerId,
            autowidth:true,
            styleUI:"Bootstrap",
            height : "auto",
            colNames : [ 'Id', '名称', '路径', '大小','时长','上传时间','专辑Id','操作'],
            colModel : [
                {name : "id", width : 80,key : true},
                {name : "name",editable:true, width : 130},
                {name : "url",editable:true,edittype:"file",width : 70,align : "right"},
                {name : "size",width : 70,align : "right"},
                {name : "duration",width : 70},
                {name : "up_date",width : 70},
                {name : "album_id",width : 70},
                {name : "url",width : 70,
                    formatter:function(value){
                        return "<a href='#' onclick='play(\""+value+"\")'><span class='glyphicon glyphicon-play-circle'/></a> &emsp; &nbsp; " +
                            "<a href='#' onclick='downloads(\""+value+"\")'><span class='glyphicon glyphicon-download'/></a> ";
                    }
                }
            ]
        });

        //增删改方法
        $("#" + subgridTableId).jqGrid('navGrid',"#" + pagerId,
            {edit : true,add : true,del : true},
            {
                //关闭对话框
                closeAfterEdit:true,
                afterSubmit:function(data){
                    $.ajaxFileUpload({
                        url:"${path}/chapter/upload",
                        datatype:"JSON",
                        type:"post",
                        fileElementId:"url", // 需要上传的文件域的ID
                        data:{id:data.responseText},
                        success:function(){
                            //刷新页面
                            $("#" + subgridTableId).trigger("reloadGrid");
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
                        url:"${path}/chapter/upload",
                        datatype:"json",
                        type:"post",
                        fileElementId:"url", // 需要上传的文件域的ID
                        data:{id:data.responseText},
                        success:function(){
                            //刷新页面
                            $("#" + subgridTableId).trigger("reloadGrid");
                        }
                    });
                    return "555";
                }
            }, //执行添加操作的额外配置
            {
                closeAfterDel:true,
                afterSubmit:$("#" + subgridTableId).trigger("reloadGrid")
            }
        );
    }

    //在线播放
    function play(name){
        $("#myModal").modal("show");
        $("#myAudio").attr("src","${path}/upload/audio/"+name);
    }

    //下载
    function downloads(name){
        location.href="${path}/chapter/download?fileName="+name;
    }

</script>

<%--初始化面板--%>
<div class="panel panel-warning">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h3>专辑信息</h3>
    </div>

    <ul class="nav nav-tabs" >
        <li class="active"><a href="#">专辑信息</a></li>
    </ul>

    <%--初始表单--%>
    <table id="abtable" />

    <%--分页工具栏--%>
    <div id="abpage" />

    <%--点击播放模态框--%>
    <div id="myModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <audio id="myAudio" controls/>
        </div>
    </div>

</div>