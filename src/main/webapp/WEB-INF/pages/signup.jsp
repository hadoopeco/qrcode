<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="signup.title"/></title>
    <meta name="heading" content="<fmt:message key='signup.heading'/>"/>
    <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/${appConfig["csstheme"]}/layout-1col.css'/>" />
</head>

<body id="signup" class="regedit">

<div class="separator"></div>
<fieldset>
<s:form name="signupForm" action="signup" method="post" validate="true">

    <li class="info">
        <fmt:message key="signup.message"/>
    </li>

    <s:textfield key="user.username" cssClass="input_a" required="true"/>

    <s:textfield key="user.email" required="true" cssClass="input_a"/>

    <s:password key="user.password" showPassword="true"  required="true"
                    cssClass="input_a"/>

    <s:password key="user.confirmPassword"  required="true"
                    showPassword="true" cssClass="input_a"/>
    <li>
        <div>
            <div class="left">

            </div>
            <div>

            </div>
        </div>
    </li>

    <%--<s:textfield key="user.passwordHint" required="true" cssClass="text large"/>--%>

    <%--<li>--%>
        <%--<div>--%>
            <%--<div class="left">--%>
                <%--<s:textfield key="user.firstName" theme="xhtml" required="true" cssClass="text medium"/>--%>
            <%--</div>--%>
            <%--<div>--%>
                <%--<s:textfield key="user.lastName" theme="xhtml" required="true" cssClass="text medium"/>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</li>--%>



    <%--<s:textfield key="user.website" required="true" cssClass="text large"/>--%>

    <%--<li>--%>
        <%--<label class="desc"><fmt:message key="user.address.address"/></label>--%>
        <%--<div class="group">--%>
            <%--<div>--%>
                <%--<s:textfield key="user.address.address" theme="xhtml" cssClass="text large" labelposition="bottom"/>--%>
            <%--</div>--%>
            <%--<div class="left">--%>
                <%--<s:textfield key="user.address.city" theme="xhtml" required="true" cssClass="text medium"--%>
                    <%--labelposition="bottom"/>--%>
            <%--</div>--%>
            <%--<div>--%>
                <%--<s:textfield key="user.address.province" theme="xhtml" required="true" cssClass="text state"--%>
                    <%--labelposition="bottom"/>--%>
            <%--</div>--%>
            <%--<div class="left">--%>
                <%--<s:textfield key="user.address.postalCode" theme="xhtml" required="true" cssClass="text medium"--%>
                    <%--labelposition="bottom"/>--%>
            <%--</div>--%>
            <%--<div>--%>
                <%--<s:set name="country" value="user.address.country" scope="page"/>--%>
                <%--<appfuse:country name="user.address.country" prompt="" default="${country}"/>--%>
                <%--<p>--%>
                    <%--<label for="user.address.country">--%>
                        <%--<fmt:message key="user.address.country"/> <span class="req">*</span>--%>
                    <%--</label>--%>
                <%--</p>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</li>--%>
    <li class="buttonBar bottom">
        <s:submit key="button.register" cssClass="button" theme="simple"/>
        <s:submit key="button.cancel" name="cancel" cssClass="button" theme="simple"/>
    </li>

</s:form>
 </fieldset>

<%@include file="/common/partner.jsp"%>

<script type="text/javascript">
    Form.focusFirstElement(document.forms["signupForm"]);
</script>
</body>