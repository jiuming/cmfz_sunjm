<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>
<!--顶部导航-->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">持明法洲管理系统</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <p class="navbar-text navbar-right">欢迎：${sessionScope.admin.username}&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"
                                                                               class="navbar-link">退出登录&nbsp;<span
                    class="glyphicon glyphicon-log-out"></span></a></p>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<!--栅格系统-->
<div class="row">
    <div class="col-md-2">
        <!--左边手风琴部分-->
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-info">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 align="center" class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                           aria-expanded="false" aria-controls="collapseOne">
                            用户管理
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                    <br>
                    <div class="panel-group" align="center">
                        <a href="javascript:$('#mainid').load('${path}/user/user.jsp')" class="btn btn-warning" role="button">用户信息</a>
                    </div>
                    <div class="panel-group" align="center">
                        <a href="javascript:$('#mainid').load('${path}/user/userChart.jsp')" class="btn btn-warning" role="button">用户统计</a>
                    </div>
                    <div class="panel-group" align="center">
                        <a href="javascript:$('#mainid').load('${path}/user/userMap.jsp')" class="btn btn-warning" role="button">用户分布</a>
                    </div>
                </div>
            </div>
            <br>
            <div class="panel panel-success">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 align="center" class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            轮播图管理
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <br>
                    <div class="panel-group" align="center">
                        <a href="javascript:$('#mainid').load('${path}/banner/banner.jsp')" class="btn btn-warning"
                           role="button">展示轮播图</a>
                    </div>
                </div>
            </div>
            <br>
            <div class="panel panel-warning">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 align="center" class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree"
                           aria-expanded="false" aria-controls="collapseThree">
                            专辑管理
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <br>
                    <div class="panel-group" align="center">
                        <a href="javascript:$('#mainid').load('${path}/album/album.jsp')" class="btn btn-warning" role="button">专辑信息</a>
                    </div>
                </div>
            </div>
            <br>
            <div class="panel panel-danger">
                <div class="panel-heading" role="tab" id="headingFour">
                    <h4 align="center" class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour"
                           aria-expanded="false" aria-controls="collapseFour">
                            文章管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                    <br>
                    <div class="panel-group" align="center">
                        <a href="javascript:$('#mainid').load('${path}/article/article.jsp')" class="btn btn-warning" role="button">文章信息</a>
                    </div>
                    <div class="panel-group" align="center">
                        <a href="javascript:$('#mainid').load('${path}/article/articleSearch.jsp')" class="btn btn-warning" role="button">文章搜索</a>
                    </div>
                </div>
            </div>
            <br>
            <div class="panel panel-primary">
                <div class="panel-heading" role="tab" id="headingFive">
                    <h4 align="center" class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive"
                           aria-expanded="false" aria-controls="collapseFive">
                            上师管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                    <br>
                    <div class="panel-group" align="center">
                        <a href="#" class="btn btn-warning" role="button">用户信息</a>
                    </div>
                    <div class="panel-group" align="center">
                        <a href="#" class="btn btn-warning" role="button">用户统计</a>
                    </div>
                    <div class="panel-group" align="center">
                        <a href="#" class="btn btn-warning" role="button">用户分布</a>
                    </div>
                </div>
            </div>
            <br>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingSix">
                    <h4 align="center" class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix"
                           aria-expanded="false" aria-controls="collapseSix">
                            管理员管理
                        </a>
                    </h4>
                </div>
                <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                    <br>
                    <div class="panel-group" align="center">
                        <a href="#" class="btn btn-warning" role="button">用户信息</a>
                    </div>
                    <div class="panel-group" align="center">
                        <a href="#" class="btn btn-warning" role="button">用户统计</a>
                    </div>
                    <div class="panel-group" align="center">
                        <a href="#" class="btn btn-warning" role="button">用户分布</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-10" id="mainid">
        <!--巨幕开始-->
        <div class="jumbotron">
            <h3>&nbsp;&nbsp;&nbsp;欢迎来到持明法洲后台管理系统</h3>
        </div>
        <!--右边轮播图部分-->
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="${path}/bootstrap/img/shouye.jpg" alt="...">
                </div>
            </div>

            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <!--页脚-->
        <div class="panel-footer" align="center">
            @百知教育 sunjm@zparkhr.com
        </div>
    </div>
</div>
<!--栅格系统-->

</body>
</html>
