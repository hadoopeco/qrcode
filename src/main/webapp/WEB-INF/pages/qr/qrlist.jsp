<%@ include file="/common/taglibs.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: wbmark
  Date: 9/28/11
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Simple jsp page</title>
    <meta name="menu" content="ConsoleMenu"/>
</head>
<body>

    <display:table name="querys" cellspacing="0" cellpadding="0" requestURI=""
            defaultsort="1" id="querys" pagesize="10" class="fullwidth" export="false" style="width:100%" partialList="true" size="${total}" >
        <%--<display:column style="width: 5%">--%>
             <%--<input type="hidden" name="items[<%=pageContext.getAttribute("products_rowNum")%>].id" value="${products.id}"/>--%>
             <%--<input type="checkbox" name="items[<%=pageContext.getAttribute("products_rowNum")%>].gen" <c:if test='${products.existed}'>disabled="disabled"  checked="checked" </c:if>/>--%>
        <%--</display:column>--%>
        <display:column property="qrImg.name" titleKey="qrImg.name"/>
        <display:column property="queryDate" titleKey="qrImg.QueryDate"/>
    </display:table>

</body>
</html>