<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script>
    $(function () {
        //初始化表单
        $("#uetable").jqGrid({
            url : "${path}/user/showAll",
            editurl:"${path}/user/edit",
            datatype : "json",
            rowNum : 5,  //每页展示条数   page   rows
            rowList : [ 3,5,10, 20, 30 ],  //可选展示条数
            styleUI:"Bootstrap",
            pager : '#uepage',  //分页工具栏
            viewrecords : true,  //是否显示总条数
            autowidth:true,
            height : "auto",
            colNames : [ 'Id', '头像','名字','昵称','性别', '状态', '手机号','注册时间'],
            colModel : [
                {name : 'id',width : 55,align : "center"},
                {name : 'avatar',width : 100,align : "center",edittype:"file",
                    formatter:function(cellValue){
                        return "<img src='${path}/upload/image/"+cellValue+"' style='width:100px;height:80px' >";
                    }
                },
                {name : 'law_name',width : 55,align : "center"},
                {name : 'name',width : 55,align : "center"},
                {name : 'sex',width : 55,align : "center"},
                {name : 'status',width : 80,align : "center",
                    formatter:function(cellValue,option,row){
                        if (cellValue==-1)
                            return "<button class='btn btn-danger' onclick='modify(\""+row.id+"\",\""+cellValue+"\")'>解除冻结</button>";
                        else
                            return "<button class='btn btn-success' onclick='modify(\""+row.id+"\",\""+cellValue+"\")'>点击冻结</button>";
                    }
                },
                {name : 'phone',width : 80,align : "center"},
                {name : 'crea_date',width : 80,align : "center"}
            ]
        });
    });
    function modify(id,status) {
        $.ajax({
            url:"${path}/user/modify",
            type:"post",
            datatype:"JSON",
            data:{"id":id,"status":status},
            success:function () {
                $("#uetable").trigger("reloadGrid");
            }
        });
    }

</script>
<div class="panel panel-info">
    <div class="panel-heading"><h3>用户信息</h3></div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="#">用户信息</a></li>
        </ul>
        <br>
        <a class="btn btn-success" href="${path}/user/outExcel">导出用户信息</a>&emsp;<button class="btn btn-info">导入用户</button>&emsp;<button class="btn btn-warning">测试按钮</button>
        <br><br>
        <table id="uetable"></table>
        <div id="uepage"></div>
    </div>

</div>