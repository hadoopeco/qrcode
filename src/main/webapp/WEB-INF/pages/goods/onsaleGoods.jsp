<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
    <title>Simple jsp page</title>
    <meta name="menu" content="ConsoleMainMenu"/>
</head>
<body>

<h2>
    <fmt:message key="taobao.onsale.list.title"/>
</h2>
<c:set var="checkAll">
    <input type="checkbox" name="allbox" onclick="checkAll(this.form)" style="margin: 0 0 0 4px" />
</c:set>
<c:choose>
<c:when test="${!hasTopSession}">
  <div class="message warning" id="successMessages">
      <h2>Warning!</h2>
      <p><fmt:message key="warning.unlogin_taobao"/></p>
      <p><fmt:message key="warning.login_taobao"> <fmt:param value="http://container.api.tbsandbox.com/container?appkey=12007234"/> </fmt:message></p>
  </div>
</c:when>
<c:when test="${total eq 0}">
  <div class="message warning" id="successMessages">
      <h2>Warning!</h2>
      <p><fmt:message key="warning.no_records"/></p>
  </div>
</c:when>
<c:otherwise>
<form action="/goods/gencode" method="POST">
<display:table name="onsales" cellspacing="0" cellpadding="0" requestURI=""
    defaultsort="1" id="onsales" pagesize="10" class="fullwidth" export="false" style="width:100%" partialList="true" size="${total}" >
    <display:column style="width: 5%">
         <input type="hidden" name="items[<%=pageContext.getAttribute("onsales_rowNum")%>].id" value="${onsales.id}"/>
         <input type="checkbox" name="items[<%=pageContext.getAttribute("onsales_rowNum")%>].gen" <c:if test='${onsales.exist}'>disabled="disabled"  checked="checked" </c:if>/>
    </display:column>
    <display:column escapeXml="false" sortable="false" titleKey="activeUsers.fullName" style="width: 10%">
        <img src="<c:out value='${onsales.imgUrl}'/>" alt="${onsales.title}" width="100" height="100">
    </display:column>
     <display:column property="title" escapeXml="true" sortable="true" titleKey="activeUsers.fullName" style="width: 88%;text-align: center;"/>

</display:table>
    <input type="submit" value="<fmt:message key='taobao.onsale.list.generate'/>" class="button"/>
 </form>
</c:otherwise>
</c:choose>
</body>
</html>