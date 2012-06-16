<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
    <title>Simple jsp page</title>
    <meta name="menu" content="ConsoleMenu"/>
</head>
<body>
<h2>
    <fmt:message key="product.list.title"/>
</h2>
    <form action="/product/generate" method="POST">
        <display:table name="products" cellspacing="0" cellpadding="0" requestURI=""
                defaultsort="1" id="products" pagesize="10" class="fullwidth" export="false" style="width:100%" partialList="true" size="${total}" >
            <display:column style="width: 5%">
                 <input type="hidden" name="items[<%=pageContext.getAttribute("products_rowNum")%>].id" value="${products.id}"/>
                 <input type="checkbox" name="items[<%=pageContext.getAttribute("products_rowNum")%>].gen" <c:if test='${products.existed}'>disabled="disabled"  checked="checked" </c:if>/>
            </display:column>
            <display:column property="productName" titleKey="product.productName"/>
            <display:column property="productUrl" titleKey="product.productUrl"/>
            <display:column style="width: 10s%">
                <a href="/product/edit?id=<c:out value='${products.id}'/>"><fmt:message key="button.edit"/></a>
                <a href="/product/delete?id=<c:out value='${products.id}'/>"><fmt:message key="button.delete"/></a>
            </display:column>
        </display:table>
        <input type="submit" value="<fmt:message key='taobao.onsale.list.generate'/>" class="button"/>
    </form>
</body>
</html>