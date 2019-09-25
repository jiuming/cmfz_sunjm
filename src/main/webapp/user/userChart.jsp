<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script src="${path}/echarts/echarts.js"></script>
<script type="application/javascript">
    $(function(){

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart'));

        $.get("${path}/user/showUserCount",function(data){

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '用户注册量趋势图',  //标题内容
                    show:true,
                    link:"${path}/main/main.jsp",
                    subtext:"纯属虚构",
                    sublink:"${path}/main/main.jsp",
                    subtarget:"self",
                },
                tooltip: {},  //鼠标提示
                legend: {
                    data:['男','女']   //选项卡
                },
                xAxis: {
                    data: data.month  //横轴展示
                },
                yAxis: {},   //纵轴展示   自适应
                series: [{
                    name: '男',
                    type: 'bar',
                    data: data.boys  //数据
                },{
                    name: '女',
                    type: 'line',
                    data: data.girls  //数据
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

        },"json");
    });
</script>

<div id="chart" style="width: 600px;height:400px;"></div>