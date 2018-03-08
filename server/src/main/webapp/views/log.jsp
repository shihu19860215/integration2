<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="/tlds/util.tld" %>
<script type="text/javascript">
    $(function () {
        initPage(${page.startPage},${page.endPage},${page.pageNum},${page.pageCount});
    });


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
        $("#pageNum").val("1");
        $("#startDate").val("");
        $("#endDate").val("");
    }
</script>

<div>
    <div class="btn-toolbar">
        <form id="searchFrom" class="form-inline" action="${pageContext.request.contextPath}/log/search" method="post">
            <input id="pageNum" type="hidden" name="pageNum" value="1">
            <input type="text" id="startDate" name="parm['startDate']" value="${page.parm.startDate}" class="input-small" placeholder="开始日期:例20170501"  style="width: 140px;">
            <input type="text" id="endDate" name="parm['endDate']" value="${page.parm.endDate}" class="input-small" placeholder="结束日期:例20170601"  style="width: 140px;">
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
                <th style="width: 30px;">序号</th>
                <th style="width: 140px;">时间</th>
                <th>日志</th>
            </tr>
            </thead>
            <tbody id="productTbady">
            <c:forEach items="${logList}" varStatus="i" var="log">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${util:dateToString(log.createTime)}</td>
                    <td>${log.info}</td>
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