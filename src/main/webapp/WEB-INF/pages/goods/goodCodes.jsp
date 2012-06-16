<%--
  Created by IntelliJ IDEA.
  User: wbmark
  Date: 8/14/11
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
    <title>Simple jsp pageasdasasdasdasddsad</title>
    <meta name="menu" content="ConsoleMainMenu"/>
</head>
<body>
<h2>
    <fmt:message key="taobao.goods.list.title"/>
</h2>
<c:choose>
    <c:when test="${empty goods}">
            <fmt:message key="goods.record.notfound"/>
    </c:when>
    <c:otherwise>

        <display:table name="goods" cellspacing="0" cellpadding="0" requestURI=""
            defaultsort="1" id="goods" pagesize="10" class="fullwidth" export="false"  sort="external" style="width:100%" partialList="true" size="${total}" >
                <display:column property="name" escapeXml="false" sortable="false" titleKey="goods.name" style="width: 34%"/>
                <display:column property="created_dt" escapeXml="false" sortable="true" titleKey="goods.created.date" format="{0,date,yyyy-MM-dd hh:mm:ss}" style="width: 34%"/>
                <display:column property="accesscount" escapeXml="false" sortable="true" titleKey="goods.access" style="width: 34%"/>
                <display:column  escapeXml="false" sortable="false" titleKey="goods.qrcode" style="width: 34%">
                   <img src="<c:url value='/goods/download'/>?docid=${goods.docId}"/>
                </display:column>
                <display:column  escapeXml="false" sortable="false"  style="width: 34%" titleKey="goods.download">
                           <a href="<c:url value='/goods/download'/>?docid=${goods.docId}"><img src="<c:url value='/images/download.jpg'/>"/> </a>
                </display:column>
             <%--<display:column property="num_iid" escapeXml="true" sortable="true" titleKey="activeUsers.fullName" style="width: 34%"/>--%>
             <%--<display:column property="goods_url" escapeXml="true" sortable="true" titleKey="activeUsers.fullName" style="width: 34%"/>--%>
             <%--<display:column sortable="false" titleKey="user.enabled" style="width: 16%; padding-left: 15px" media="html">--%>
             <%--<input type="hidden" name="items[<%=pageContext.getAttribute("onsales_rowNum")%>].id" value="${onsales.numIid}"/>--%>
             <%--<input type="checkbox" name="items[<%=pageContext.getAttribute("onsales_rowNum")%>].gen" <c:if test='${users.enabled}'>checked="checked"</c:if>/>--%>
            <%--</display:column>--%>
        </display:table>
    </c:otherwise>
</c:choose>
</body>
</html>