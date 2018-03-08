<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript">
    var addProduct = function (carId) {
        location = "${pageContext.request.contextPath}/product/toaddpage/" + carId;
    }

    var delProduct = function () {
        if (confirm('确定要删除嘛?')) {
            return true;
        } else {
            return false;
        }
    }

    var addCount = function (productId, o) {
        $.post("${pageContext.request.contextPath}/ajax/product/add",
            {'productId': productId},
            function (data) {
                if(null!=data){
                    if(1==data.state) {
                        $(o).next().html(" " + data.info + " ");
                    }
                }
                //location = location;
            }
        );
    }

    var subCount = function (productId, o) {
        $.post("${pageContext.request.contextPath}/ajax/product/sub",
            {'productId': productId},
            function (data) {
                if(null!=data){
                    if(1==data.state) {
                        $(o).prev().html(" " + data.info + " ");
                    }else if(-1==data.state){
                        alert(data.info)
                    }
                }
            }
        );
    }
</script>


<div>
    <ul class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/cartype/list">品牌车型</a> <span class="divider">/</span></li>
        <li><a href="${pageContext.request.contextPath}/car/list?carTypeId=${carType.id}">${carType.name}</a> <span
                class="divider">/</span></li>
        <li class="active">${car.name}</li>
    </ul>

    <shiro:hasPermission name="product:toaddpage">
        <div class="btn-toolbar">
            <button class="btn btn-primary" onclick="addProduct(${car.id})"><i class="icon-plus"></i> 添加</button>
            <div class="btn-group">
            </div>
        </div>
    </shiro:hasPermission>
    <div class="well">
        <table class="table">
            <thead>
            <tr>
                <th>商品名</th>
                <th>个数</th>
                <th>型号</th>
                <th>备注</th>
                <th>批发价</th>
                <th>零售价</th>
                <th>通用车型</th>
                <shiro:authenticated>
                    <th>操作</th>
                </shiro:authenticated>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${productList}" varStatus="i" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>
                        <shiro:hasPermission name="product:ajax.addcount">
                            <a href="javascript:void(0)" onclick="addCount(${item.id},this)">
                                <i class="icon-plus"></i>
                            </a>
                        </shiro:hasPermission>
                        <span> ${item.num}</span>
                        <shiro:hasPermission name="product:ajax.subcount">
                            <a href="javascript:void(0)" onclick="subCount(${item.id},this)">
                                <i class="icon-minus"></i>
                            </a>
                        </shiro:hasPermission>
                    </td>
                    <td>${item.version}</td>
                    <td>${item.remark}</td>
                    <td>${item.ownerprice}</td>
                    <td>${item.otherprice}</td>
                    <th><c:forEach items="${item.carVOList}" var="car">${car.name} </c:forEach></th>

                    <shiro:authenticated>
                        <td>
                            <shiro:hasPermission name="product:toupdatepage">
                                <a href="${pageContext.request.contextPath}/product/toupdatepage/${item.id}?carId=${car.id}"><i
                                        class="icon-pencil"></i></a>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="product:delete">
                                <a href="${pageContext.request.contextPath}/product/del/${item.id}?carId=${car.id}&carTypeId=${carType.id}" role="button"
                                   data-toggle="modal">
                                    <i class="icon-remove" onclick="return confirm('确定要删除嘛?')"></i>
                                </a>
                            </shiro:hasPermission>
                        </td>
                    </shiro:authenticated>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </div>


</div>