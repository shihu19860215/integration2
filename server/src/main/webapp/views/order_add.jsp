<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="/tlds/util.tld" %>
<script type="text/javascript">

    $(function () {
        $("#customerNameOrTel").keyup(function () {
            loadCustomer();
        });
    });

    var loadCustomer=function(){
        var customerNameOrTel = $("#customerNameOrTel").val();
        if ("" != customerNameOrTel) {
            $.post("${pageContext.request.contextPath}/ajax/customer/saerchlikenametel",
                {'customerNameOrTel': customerNameOrTel},
                function (data) {
                    if (null != data){
                        if(data.state==1) {
                            $("#customerSelect").show();
                            var tmp = "<option value='1' customername='个人'>个人</option>";
                            for (var i = 0; i < data.info.length; i++) {
                                var str = data.info[i].name;
                                if (null != data.info[i].area && "" != data.info[i].area.trim()) {
                                    str = data.info[i].area + "-" + str;
                                }
                                if (null != data.info[i].telephone && "" != data.info[i].telephone.trim()) {
                                    str = str + "-" + data.info[i].telephone;
                                }
                                tmp += "<option value='" + data.info[i].id + "'customername='" + data.info[i].name + "'>" + str + "</option>";
                            }
                            $("#customerSelect").html(tmp);
                        }else if(null!=data.info){
                            alert(data.info);
                            return false;
                        }
                    }
                }
            );
        } else {
            $("#customerSelect").html("<option value='1' customername='个人'>个人</option>");
        }

    }



    var delOrderProduct = function (obj) {
        $(obj).parents("tr").remove();
        return false;
    }
    var delOtherProduct=function(obj){
        $(obj).parents("tr").remove();
        return false;
    }

    var addCount = function (obj) {
        var spanObj=$(obj).next();
        var max=parseInt(spanObj.attr("max"));
        var now=parseInt(spanObj.html());
        if(now<max){
            spanObj.html(now+1)
        }else {
            alert("库存达到最大;");
        }
    }

    var addOtherProduct=function () {
        $("#tbody2").append("<tr><td><input type=\"text\" name=\"otherProductName\" style=\"width:300px;\"></td>" +
                                "<td><input type=\"text\" name=\"otherProductNum\" style=\"width:30px;\"></td>" +
            "<td><input type=\"text\" name=\"otherProductUnit\" style=\"width:30px;\"></td>" +
            "<td><input type=\"text\" name=\"otherProductPrice\" style=\"width:50px;\"></td><td></td>" +
             "<td ><select style=\"width: 65px;\">\n" +
            "<option value=\"true\">售货</option>\n" +
            "<option value=\"false\">退换</option>\n" +
            "</select>"+
            "<td>" +
            "<a onclick=\"delOtherProduct(this)\" role=\"button\"" +
            "data-toggle=\"modal\">" +
            "<i class=\"icon-remove\"></i>" +
            "</a>" +
            "</td></tr>");
    }

    var validate =function(){
        var inputObjs=$("#tbody2 input");
        for(var i=0;i<inputObjs.length;i++){
            var inputObj=$(inputObjs[i]);
            if(null==inputObj.val()||""==inputObj.val().trim()){
                alert("数据未填写完整");
                return false;
            }
        }
        return true;
    }

    var subCount = function (obj) {
        var spanObj=$(obj).prev();
        var max=parseInt(spanObj.attr("max"));
        var now=parseInt(spanObj.html());
        if(now>1){
            spanObj.html(now-1)
        }
    }

    var addOrder = function (carId) {
        if(!validate()){
            return false;
        }
        $.post("${pageContext.request.contextPath}/ajax/order/add",
            getPostData(),
            function (data) {
                if(null!=data){
                    if(1==data.state){
                        location = "/order/search";
                    }else {
                        alert(data.info);
                    }
                };
            }
        );
        return false;
    }

    var saveOrder=function(){
        if(!validate()){
            return false;
        }
        $.post("${pageContext.request.contextPath}/ajax/order/save",
            getPostData(),
            function (data) {
                if(null!=data){
                    if(1==data.state){
                        alert("暂存成功");
                    }
                };
            }
        );
        return false;
    }

    var getPostData=function () {
        var result={};
        if(""!=$("#customerSelect").val()){
            result.customerVO={}
            result["customerVO.id"]=$("#customerSelect").val();
            result["customerVO.name"]=$("#customerSelect option:selected").html();
            result["customerName"]=$("#customerSelect option:selected").attr("customername");
        }
        var trObjs=$("#tbody tr");
        var productNames="";
        for(var i=0;i<trObjs.length;i++){
            var trObj=$(trObjs[i]);
            var orderProductList='orderProductList['+i+']';
            result[orderProductList+'.product.id']=trObj.find("#productId").val();
            productNames=productNames+trObj.find("#productName").html()+",";
            result[orderProductList+'.num']=trObj.find("span").html();
            if(""!=trObj.find("#price").val()){
                result[orderProductList+'.price']=trObj.find("#price").val();
            }
            result[orderProductList+'.sell']=trObj.find("select").val();
        }

        result.productNames=productNames.substr(0,productNames.length-1);
        if(countTotal()){
            result.total=$("#totalH1").html().split(":")[1];
        }
        if(""!=$("#remarks").val()){
            result.remarks=$("#remarks").val();
        }
        return result;
    }

    var showTotal=function () {
        if(!countTotal()){
            alert("数量价格非法或为空");
        }
    }

    var  countTotal=function(){
        var result=true;
        var count=0;
        var trObjs=$("#tbody tr");
        for(var i=0;i<trObjs.length;i++){
            var trObj=$(trObjs[i]);
            var num=parseInt(trObj.find("span").html());
            var price=parseInt(trObj.find("#price").val());
            if(!isNaN(num)&&num>0&&!isNaN(price)&&price>0){
                $(trObj.find("td")[4]).html(num*price);
                if("true"==trObj.find("select").val()){
                    count=count+num*price;
                }else{
                    count=count-num*price;
                }
                $(trObj.find("td")[4]).html(num*price);
            }else {
                result=false;
            }
        }
        if(result){
            $("#totalH1").html("总价:"+count);
            return true;
        }else {
            $("#totalH1").html("总价:");
            return false;
        }
    }
</script>


<div>

    <div class="btn-toolbar">
        <input type="text" id="customerNameOrTel" class="input-small" placeholder="客户名"  style="width:90px;">
        <c:choose>
            <c:when test="${null!=order.customerVO}">
                <select style="width: 280px;" id="customerSelect">
                    <option value="${order.customerVO.id}" customername="${order.customerName}">${order.customerVO.name}</option>
                </select>
            </c:when>
            <c:otherwise>
                <select style="width: 280px;" id="customerSelect">
                    <option value='1' customername="个人">个人</option>
                </select>
            </c:otherwise>
        </c:choose>
        <div class="btn-group">
        </div>
    </div>

    <div class="btn-toolbar">
        <button class="btn btn-primary" onclick="saveOrder()">暂存</button>
        <div class="btn-group">
        </div>
    </div>
    <div class="well">
        <ul class="breadcrumb" style="width: 58px;">
            <li class="active">专用商品</li>
        </ul>
        <table class="table">
            <thead>
            <tr>
                <th>商品名</th>
                <th>型号</th>
                <th>个数</th>
                <th>通用车型</th>
                <th>单价</th>
                <th>价格</th>
                <th>销售</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${order.orderProductList}" varStatus="index" var="orderProduct">
                <tr>
                    <input type="hidden" id="productId" value="${orderProduct.product.id}"/>
                    <td id="productName">${orderProduct.product.name}</td>
                    <td>${orderProduct.product.version}${orderProduct.product.remark}</td>
                    <td>
                            <a onclick="addCount(this)">
                                <i class="icon-plus"></i>
                            </a>
                        <span max="${orderProduct.product.num}">${orderProduct.num}</span>
                            <a onclick="subCount(this)">
                                <i class="icon-minus"></i>
                            </a>
                    </td>
                    <th id="carstrs"><c:forEach items="${orderProduct.product.carVOList}" var="car">${car.name} </c:forEach></th>
                    <td><input id="price" type="text" value="${orderProduct.price}" style="width: 40px;"/></td>
                    <td>${util:mul(orderProduct.num,orderProduct.price)}</td>
                    <td ><select style="width: 65px;">
                        <option value="true">售货</option>
                        <option value="false"<c:if test="${!orderProduct.sell}"> selected = "selected"</c:if>>退换</option>
                    </select>
                     </td>
                    <td>
                            <a onclick="delOrderProduct(this)" role="button"
                               data-toggle="modal">
                                <i class="icon-remove"></i>
                            </a>
                    </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </div>

    <div class="btn-toolbar">
        <h1 id="totalH1" style="font-size: 25px">总价:${order.total}</h1>
        <button onclick="showTotal()">计算</button><br><br>
        <h1 style="font-size: 25px">备注:</h1>
    </div>
    <form class="bs-docs-example form-inline">
        <textarea id="remarks" rows="3" style="width: 60%">${order.remarks}</textarea>
    </form>

    <div class="btn-toolbar pull-right">
        <button class="btn btn-primary" onclick="addOrder()"> 确认订单</button>
        <div class="btn-group">
        </div>
    </div>

</div>