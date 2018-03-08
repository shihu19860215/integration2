<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript">
    $(function () {
        initPage(${page.startPage},${page.endPage},${page.pageNum},${page.pageCount});
    });
    var addCustomer = function () {
        location = "${pageContext.request.contextPath}/customer/toaddpage";
    }

    var topage=function(page){
        $("#pageNum").val(page);
        $("#searchFrom").submit();
    }
    var initPage=function(startPage,endPage,pageNum,pageCount){
        if(endPage<=startPage)return;//如果只有一页就不显示
        var str=""
        if(1==pageNum){
            str=str+"<li class=\"active\"><a>«</a></li>";
        }else {
            str=str+"<li><a onclick=\"topage("+(pageNum-1)+")\">«</a></li>";
        }
        if(1!=startPage){
            str=str+"<li class=\"active\"><a>...</a></li>";
        }
        for(var i=startPage;i<=endPage;i++){
            if(i==pageNum){
                str=str+"<li class=\"active\"><a>"+i+"</a></li>";
            }else {
                str=str+"<li><a onclick=\"topage("+i+")\">"+i+"</a></li>";
            }
        }

        if(endPage!=pageCount){
            str=str+"<li class=\"active\"><a>...</a></li>";
        }
        if(pageNum==endPage){
            str=str+"<li class=\"active\"><a>»</a></li>";
        }else {
            str=str+"<li><a onclick=\"topage("+(pageNum+1)+")\">»</a></li>";
        }
        $("#pageUl").html(str);
    }
</script>


<div>

    <form id="searchFrom" class="form-inline" action="${pageContext.request.contextPath}/customer/list" method="post">
        <input id="pageNum" type="hidden" name="pageNum">
    </form>
    <ul class="breadcrumb">
        <li>客户列表</li>
    </ul>

    <div class="btn-toolbar">
        <button class="btn btn-primary" onclick="addCustomer()"><i class="icon-plus"></i> 添加</button>
        <div class="btn-group">
        </div>
    </div>

    <div class="well">
        <table class="table">
            <thead>
            <tr>
                <th></th>
                <th>客户名</th>
                <th>客户电话</th>
                <th>备用电话</th>
                <th>地区</th>
                <th>地址</th>
                <shiro:authenticated>
                <th>操作</th>
                </shiro:authenticated>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="customerVO" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${customerVO.name}</td>
                    <td>${customerVO.telephone}</td>
                    <td>${customerVO.telephone2}</td>
                    <td>${customerVO.area}</td>
                    <td>${customerVO.address}</td>
                    <shiro:authenticated>
                    <td>
                        <shiro:hasPermission name="customer:toupdatepage">
                            <a href="${pageContext.request.contextPath}/customer/toupdatepage/${customerVO.id}"><i
                                    class="icon-pencil"></i></a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="customer:delete">
                            <a href="${pageContext.request.contextPath}/customer/del/${customerVO.id}" role="button"
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
    <div class="pagination">
        <ul id="pageUl">
        </ul>
    </div>


</div>