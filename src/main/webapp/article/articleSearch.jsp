<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script type="application/javascript">
    function search() {
        $("#result").empty();
        var word=$("#word").val();
        $.ajax({
            url:"${path}/article/search",
            type:"post",
            dataType:"JSON",
            data:{word:word},
            success:function (data) {
                for (var i = 0; i < data.length;i++) {
                    $("#result").append($("<tr><td>"+data[i].id+"</td><td>"+data[i].title+"</td><td>"+data[i].author+"</td><td>"+data[i].content+"</td><td>"+data[i].crea_date+"</td><td>"+data[i].guru_id+"</td></tr>"));
                }
            }
        })
    }
</script>

<div align="center">
    <div class="input-group" style="width: 30%">
        <input id="word" type="text" class="form-control" placeholder="请输入搜索内容">
        <span class="input-group-btn"><button class="btn btn-info" onclick="search()">查询</button></span>
    </div>
</div><br>
<div class="panel panel-default">
    <!-- 面板标题 -->
    <div class="panel-heading">搜索结果</div>

    <!-- 面板内容 -->
    <table id="result" class="table">
        <tr><td>ID</td><td>标题</td><td>作者</td><td>内容</td><td>创建时间</td><td>上师ID</td></tr>
    </table>
</div>