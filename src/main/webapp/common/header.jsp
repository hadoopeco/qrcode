<%@ include file="/common/taglibs.jsp"%>



<div id="top">
					<!-- Logo -->
					<div class="logo">
						<a href="#" title="Administration Home" class="tooltip"><img src="<c:url value='/images/logo.png'/>" alt="Wide Admin" /></a>
					</div>
					<!-- End of Logo -->


					<!-- Meta information -->
					<div class="meta">
                        <c:if test="${! empty pageContext.request.remoteUser}">
						   <p>Welcome, ${pageContext.request.remoteUser} !</p>
						<ul>
							<li><a href="<c:url value='/logout'/>" title="End administrator session" class="tooltip"><span class="ui-icon ui-icon-power"></span>Logout</a></li>
							<li><a href="#" title="Change current settings" class="tooltip"><span class="ui-icon ui-icon-wrench"></span>Settings</a></li>
							<li><a href="<c:url value='/editProfile'/>"  title="Go to your account" class="tooltip"><span class="ui-icon ui-icon-person"></span>My account</a></li>
						</ul>
                        </c:if>
					</div>

					<!-- End of Meta information -->
				</div>

</div>
<%--<c:if test="${pageContext.request.locale.language ne 'en'}">--%>
    <%--<div id="switchLocale"><a href="<c:url value='/?locale=en'/>"><fmt:message key="webapp.name"/> in English</a></div>--%>
<%--</c:if>--%>

<%--<div id="branding">--%>
    <%--<h1><a href="<c:url value='/'/>"><fmt:message key="webapp.name"/></a></h1>--%>
    <%--<p><fmt:message key="webapp.tagline"/></p>--%>
<%--</div>--%>
<%--<hr />--%>

<%-- Put constants into request scope --%>
<appfuse:constants scope="request"/>