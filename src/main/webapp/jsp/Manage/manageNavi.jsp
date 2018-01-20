<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!--导航栏开始-->
 <nav class="navbar navbar-inverse" role="navigation">
	<div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">后台管理</a>
    </div>
    <div>
        <ul class="nav navbar-nav" id="naviUL">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    	设备管理<b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/Drift_wechat/api/manage/deviceList">查看设备</a></li>
                    <li class="divider"></li>
                    <li><a href="/Drift_wechat/api/manage/addDevice">增加设备</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    	订单管理<b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/Drift_wechat/api/manage/orderList">查看订单</a></li>
                    <li class="divider"></li>
                    <li><a href="/Drift_wechat/api/manage/companySend">公司发货</a></li>
                    <li class="divider"></li>
                    <li><a href="/Drift_wechat/api/manage/companyReceive">公司收货</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    	社区管理<b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">提问</a></li>
                    <li class="divider"></li>
                    <li><a href="#">回答</a></li>
                    <li class="divider"></li>
                    <li><a href="/Drift_wechat/jsp/Manage/Community.jsp">置顶</a></li>
                </ul>
            </li>
        </ul>
    </div>
	</div>
</nav>
<!--导航区域结束-->

