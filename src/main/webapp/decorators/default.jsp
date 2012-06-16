<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<%--<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">--%>
<html>
    <head>
        <%@ include file="/common/meta.jsp" %>
        <title><decorator:title/> | <fmt:message key="webapp.name"/></title>

        <link rel="stylesheet" type="text/css" href="<c:url value='/styles/${appConfig["csstheme"]}/theme.css'/>" />
        <%--<link rel="stylesheet" type="text/css" media="print" href="<c:url value='/styles/${appConfig["csstheme"]}/print.css'/>" />--%>

        <script type="text/javascript" src="<c:url value='/scripts/prototype.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/scriptaculous.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/global.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/jquery-1.6.2.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/easyTooltip.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/jquery-ui-1.8.16.custom.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/jquery.wysiwyg.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/superfish.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/hoverIntent.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/custom.js'/>"></script>

        <%--<decorator:head/>--%>
    </head>
<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>
    <div id="page">
        <div id="header" class="clearfix">
            <jsp:include page="/common/header.jsp"/>
        </div>

        <div id="content">
           <!-- Background wrapper -->
		    <div id="bgwrap">

                <div id="main">
                    <%@ include file="/common/messages.jsp" %>
                    <div style="width: 500px;margin: 5px auto">
                        <h1><decorator:getProperty property="meta.heading"/></h1>
                    </div>
                    <decorator:body/>
                </div>


                <c:if test="${pageContext.request.remoteUser != null}">

				<!-- Sidebar -->
				<div id="sidebar">
                    <div id="accordion" class="ui-accordion ui-widget ui-helper-reset">
                        <c:set var="currentMenu" scope="request"><decorator:getProperty property="meta.menu"/></c:set>
                        <c:if test="${!empty currentMenu}">
                            <menu:useMenuDisplayer name="Velocity" config="cssVerticalMenu.vm" permissions="rolesAdapter">
                                <menu:displayMenu name="${currentMenu}"/>
                            </menu:useMenuDisplayer>
                        </c:if>
						<%--<div>--%>
							<%--<h3><a href="#" title="Second slide" class="tooltip">Second</a></h3>--%>
							<%--<div>Sed sem elit, porttitor quis vestibulum ut, euismod id purus. Praesent vulputate dolor vel nisi mattis sollicitudin. Curabitur placerat quam at sem tempor ac sodales nunc dapibus. Nullam mi purus, adipiscing in facilisis sed, posuere ut ipsum.</div>--%>
						<%--</div>--%>
						<%--<div>--%>
							<%--<h3><a href="#" title="Third slide" class="tooltip">Third</a></h3>--%>

							<%--<div>Praesent augue urna, vehicula sed sollicitudin quis, dignissim nec est. Quisque dignissim lorem at metus vehicula ut feugiat eros vestibulum. Suspendisse ultrices, massa luctus aliquam faucibus, sem quam fermentum nisl, non posuere quam nunc vel tellus.</div>--%>
						<%--</div>--%>
					</div>
					<!-- End of Accordion-->

					<h2>Datepicker</h2>
					<!-- Datepicker -->
					<div id="datepicker"></div>
					<!-- End of Datepicker -->


					<!-- Sortable Dialogs -->
					<h2></h2>
					<div class="sort">
						<iframe frameborder="no" marginheight="0" marginwidth="0" scrolling="no" width="200" height="200" src="Http://union.vancl.com/GetVerticalLabelBarAd.aspx?source=wbmark&psid=88,93,98,103,108,113,123,128"></iframe>
					</div>
					<!-- End of Sortable Dialogs -->

				</div>
                </c:if>
				<!-- End of Sidebar -->



                    <div id="nav">
                        <div class="wrapper">
                            <h2 class="accessibility">Navigation</h2>
                            <jsp:include page="/common/menu.jsp"/>
                        </div>
                        <hr/>
                    </div><!-- end nav -->

            </div>
			<!-- End of bgwrap -->
        </div>
        <!-- End of content -->
        <div id="footer" class="clearfix">
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </div>
</body>
</html>
