<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="myshiro" uri="/tlds/myShiro.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${pageContext.request.contextPath}新毅汽车用品批发商行</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/lib/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/stylesheets/theme.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/lib/font-awesome/css/font-awesome.css"/>

    <script src="${pageContext.request.contextPath}/resources/lib/jquery-1.8.1.min.js" type="text/javascript"></script>

    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height: 300px;
            width: 800px;
            margin: 0px auto;
            margin-top: 1em;
        }

        .brand {
            font-family: georgia, serif;
        }

        .brand .first {
            color: #ccc;
            font-style: italic;
        }

        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

</head>

<body>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">

            <ul class="nav pull-right">

                <li id="fat-menu" class="dropdown">
                    <shiro:authenticated>
                        <a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i> <myshiro:principalName/>
                            <i class="icon-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="${pageContext.request.contextPath}/loginout">Logout</a></li>
                        </ul>
                    </shiro:authenticated>
                    <shiro:notAuthenticated>
                        <a href="${pageContext.request.contextPath}/login" id="drop3" role="button"
                           class="dropdown-toggle">
                            <i class="icon-user"></i>登陆
                        </a>
                    </shiro:notAuthenticated>
                </li>

            </ul>
            <a class="brand" href="${pageContext.request.contextPath}/home"><span class="first">Your</span> <span
                    class="second">Company</span></a>
        </div>
    </div>
</div>


<div class="container-fluid">

    <div class="row-fluid">
        <div class="span3">
            <div class="sidebar-nav">
                <div class="nav-header" data-toggle="collapse" data-target="#dashboard-menu"><i
                        class="icon-dashboard"></i>
                    汽车用品
                </div>
                <ul id="dashboard-menu" class="nav nav-list collapse in">

                    <li
                            <c:if test="${homePageBean.index==1}">class="active"</c:if> ><a
                            href="${pageContext.request.contextPath}/cartype/list">品牌车型</a></li>

                    <li
                            <c:if test="${homePageBean.index==2}">class="active"</c:if> ><a
                            href="${pageContext.request.contextPath}/search/product/search">商品搜索</a></li>
                    <shiro:hasPermission name="product:toquickaddpage">
                        <li
                                <c:if test="${homePageBean.index==3}">class="active"</c:if> ><a
                                href="${pageContext.request.contextPath}/product/toquickaddpage">快速添加</a>
                        </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="order:toaddpage">
                    <li
                            <c:if test="${homePageBean.index==4}">class="active"</c:if> ><a
                            href="${pageContext.request.contextPath}/order/toaddpage">当前订单</a>
                    </li>
                </ul>
                </shiro:hasPermission>

                <shiro:authenticated>

                    <div class="nav-header" data-toggle="collapse" data-target="#accounts-menu"><i
                            class="icon-briefcase"></i>管理
                    </div>
                    <ul id="accounts-menu" class="nav nav-list collapse in">
                        <shiro:hasPermission name="order:search">
                            <li
                                    <c:if test="${homePageBean.index==11}">class="active"</c:if> ><a
                                    href="${pageContext.request.contextPath}/order/search">订单管理</a>
                            </li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="customer:list">
                            <li
                                    <c:if test="${homePageBean.index==12}">class="active"</c:if> ><a
                                    href="${pageContext.request.contextPath}/customer/list">客户管理</a>
                            </li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="log:search">
                            <li
                                    <c:if test="${homePageBean.index==13}">class="active"</c:if> ><a
                                    href="${pageContext.request.contextPath}/log/search">查看日志</a>
                            </li>
                        </shiro:hasPermission>
                    </ul>
                </shiro:authenticated>
            </div>
        </div>
        <div class="span9">
            <jsp:include page="${homePageBean.includePage}.jsp"></jsp:include>


        </div>
    </div>

    <script src="${pageContext.request.contextPath}/resources/lib/bootstrap/js/bootstrap.js"></script>

</div>
</body>
</html>
