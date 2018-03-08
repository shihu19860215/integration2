<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="/tlds/util.tld" %>
<script type="text/javascript">
    $(function () {
        $("#customerNameOrTel").keyup(function () {
            loadCustomer();
            $("#customerId").val("");
        });
        $("#customerSelect").change(function(){
            $("#customerName").val($(this).find("option:selected").html());
            $("#customerId").val($(this).find("option:selected").val());
        });
        initPage(${page.startPage},${page.endPage},${page.pageNum},${page.pageCount});
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
                            var tmp="<option>全部</option><option value='1' >个人</option>";
                            for (var i = 0; i < data.info.length; i++) {
                                var str=data.info[i].name;
                                if(null!=data.info[i].area&&""!=data.info[i].area.trim()){
                                    str=data.info[i].area+"-"+str;
                                }
                                if(null!=data.info[i].telephone&&""!=data.info[i].telephone.trim()){
                                    str=str+"-"+data.info[i].telephone;
                                }
                                tmp += "<option value='" + data.info[i].id + "'>" + str + "</option>";
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
            $("#customerSelect").hide().html("");
        }

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
    var clearAll=function () {
        $("#customerNameOrTel").val("");
        $("#pageNum").val("1");
        $("#productName").val("");
        $("#startDate").val("");
        $("#endDate").val("");
        $("#customerId").val("");
        $("#customerSelect").html("<option>全部</option><option value=\"1\">个人</option>");
        $("#retreatAll").attr("selected","selected")
        return false;
    }
</script>

<div>
    <div class="btn-toolbar">
        <form id="searchFrom" class="form-inline" action="${pageContext.request.contextPath}/order/search" method="post">
            <input id="customerNameOrTel" type="text" class="input-small" placeholder="客户名"  style="width:90px;">
            <input id="pageNum" type="hidden" name="pageNum" value="1">
            <c:choose>
                <c:when test="${null!=page.parm.customerId&&''!=page.parm.customerId}">
                    <input id="customerName" type="hidden" name="parm['customerName']" value="${page.parm.customerName}">
                    <input id="customerId" type="hidden" name="parm['customerId']" value="${page.parm.customerId}">
                    <select style="width: 280px;" id="customerSelect">
                        <option value="${page.parm.customerId}">${page.parm.customerName}</option>
                    </select>
                </c:when>
                <c:otherwise>
                    <input id="customerName" type="hidden" name="parm['customerName']">
                    <input id="customerId" type="hidden" name="parm['customerId']">
                    <select style="width: 280px;" id="customerSelect">
                        <option>全部</option>
                        <option value="1">个人</option>
                    </select>
                </c:otherwise>
            </c:choose>
            <input type="text" id="productName" name="parm['productName']" value="${page.parm.productName}" class="input-small" placeholder="商品名"  style="width: 80px;">
            <input type="text"id="startDate" name="parm['startDate']" value="${page.parm.startDate}" class="input-small" placeholder="开始日期"  style="width: 70px;">
            <input type="text"id="endDate" name="parm['endDate']" value="${page.parm.endDate}" class="input-small" placeholder="结束日期"  style="width: 70px;">
            <select name="parm['retreat']" style="width: 95px;"><option id="retreatAll" value="0">全部</option><option value="1" <c:if test="${page.parm.retreat=='1'}"> selected = "selected"</c:if>>有退换货</option></select>
            <input type="submit" class="btn" value="搜索"></input>
            <input type="reset" onclick="return clearAll()" class="btn" value="清除"></input>
        </form>
        <div class="btn-group">
        </div>
    </div>
    <div class="well">
        <table class="table">

            <thead>
            <tr>
                <th>序号</th>
                <th>时间</th>
                <th>客户名</th>
                <th>销售商品</th>
                <th>备注</th>
                <th></th>
            </tr>
            </thead>
            <tbody id="productTbady">
            <c:forEach items="${orderVOList}" varStatus="i" var="order">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${util:dateToString(order.createTime)}</td>
                    <td>${order.customerName}</td>
                    <td>${order.productNames}</td>
                    <td>${order.remarks}</td>
                    <td><a href="${pageContext.request.contextPath}/order/display/${order.id}">详情</a></td>
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