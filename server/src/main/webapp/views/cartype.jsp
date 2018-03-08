<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="mytag" uri="/tlds/mytag.tld" %>
<script type="text/javascript">

    var delCarType = function () {
        if (confirm('确定要删除嘛?')) {
            return true;
        } else {
            return false;
        }
    }

    var showlist=new Array();
    var search = function () {
        while(showlist.length>0){
            showlist.pop().remove();
        }
        var str=$("#searchStr").val().toUpperCase();
        var cartypelist=$("#cartypes p");
        for(var i=0;i<cartypelist.length;i++){
            var pObject=$(cartypelist[i]);
            var name=$(pObject.children()[0]).html().toUpperCase();
            if(name.indexOf(str)>=0){
                showlist.push(pObject.clone());
                pObject.hide();
            }else {
                pObject.hide();
            }
        }
        var divs=$("#cartypes").children();
        for(var i=0;i<showlist.length;i++){
            var n=i%3;
            $(divs[n]).append(showlist[i].show());
        }
    }
    var addSubmit = function () {
        $("#formcartype").submit();
    }

</script>

<div>


    <div class="input-append">
        <form id="formcartype" action="${pageContext.request.contextPath}/cartype/add" method="post">
            <shiro:hasPermission name="cartype:add">
                <input class="span2" name="name" type="text"/>
                <button onclick="addSubmit()" class="btn" type="button"> 添加</button>
            </shiro:hasPermission>
            <input class="span2" id="searchStr" value="${searchStr}" type="text"/>
            <button onclick="search()" class="btn" type="button"> 过滤</button>
        </form>
    </div>
    <p class="text-error">${pagePrompt.message}</p>
    <div class="row-fluid">
        <div class="block span12">
            <ul class="breadcrumb">
                <li class="active">品牌车型</li>
            </ul>
            <div class="block-body">
                <div id="cartypes" class="row-fluid" style="text-align: center;">
                    <div class="pull-left span4 unstyled">
                        <mytag:collectionForEach items="${carTypeList}" var="item" inc="3" start="0">
                            <p>
                                <a href="${pageContext.request.contextPath}/car/list?carTypeId=${item.id}">${item.name}</a>
                                <shiro:hasPermission name="cartype:delete">
                                    <a href="${pageContext.request.contextPath}/cartype/del/${item.id}" onclick="return delCarType()"><i
                                            class="icon-remove"></i></a>
                                </shiro:hasPermission>
                            </p>
                        </mytag:collectionForEach>
                    </div>
                    <div class="pull-left span4 unstyled">
                        <mytag:collectionForEach items="${carTypeList}" var="item" inc="3" start="1">
                            <p>
                                <a href="${pageContext.request.contextPath}/car/list?carTypeId=${item.id}">${item.name}</a>
                                <shiro:hasPermission name="cartype:delete">
                                    <a href="${pageContext.request.contextPath}/cartype/del/${item.id}" onclick="return delCarType()"><i
                                            class="icon-remove"></i></a>
                                </shiro:hasPermission>
                            </p>
                        </mytag:collectionForEach>
                    </div>
                    <div>
                        <mytag:collectionForEach items="${carTypeList}" var="item" inc="3" start="2">
                            <p>
                                <a href="${pageContext.request.contextPath}/car/list?carTypeId=${item.id}">${item.name}</a>
                                <shiro:hasPermission name="cartype:delete">
                                    <a href="${pageContext.request.contextPath}/cartype/del/${item.id}" onclick="return delCarType()"><i
                                            class="icon-remove"></i></a>
                                </shiro:hasPermission>
                            </p>
                        </mytag:collectionForEach>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>

        </div>
    </div>

</div>