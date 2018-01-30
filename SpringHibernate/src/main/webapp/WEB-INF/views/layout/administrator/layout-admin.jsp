<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>

<html lang="en">

<head>
<title><tiles:insertAttribute name="title" /></title>
<tiles:insertAttribute name="head" />
<tiles:insertAttribute name="headtag" />
<tiles:insertAttribute name="headtagmore" />
</head>
<body class="hold-transition skin-blue sidebar-mini">

	<div class="wrapper">
		<!-- Main Header -->
		<header class="main-header">
			<tiles:insertAttribute name="topheader" />
			<tiles:insertAttribute name="navbar" />
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<tiles:insertAttribute name="sidebar" />
		</aside>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<c:if test="${not empty  msg}">
				<div style="text-align: center; color: red; padding-top: 15px;">
					<label id="msg">${msg }</label>
				</div>
			</c:if>
			<tiles:insertAttribute name="body" />

		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<footer class="main-footer">
			<tiles:insertAttribute name="navbar" />
		</footer>
		<tiles:insertAttribute name="controlsidebar" />
	</div>
	<tiles:insertAttribute name="footertag" />
	<tiles:insertAttribute name="footertagmore" />
</body>
</html>