<%@include file="/common/taglibs.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: wbmark
  Date: 9/26/11
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Simple jsp page</title>
    <meta name="menu" content="ConsoleMenu">
</head>
<body>
    <div>
        <s:form id="proform" action="add" method="post" validate="true">
         <s:hidden  key="product.id"></s:hidden>
        <s:textfield key="product.productName" cssClass="input_a" required="true"/>
         <br>
        <s:textfield key="product.productUrl" cssClass="input_a" required="true"/>
         <br>
         <br>
        <li class="buttonBar bottom">
            <s:submit key="button.save" cssClass="button" theme="simple"/>
        </li>
        </s:form>
     </div>
</body>
</html>