<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- Header Navbar -->
<nav class="navbar navbar-static-top" role="navigation">
	<!-- Sidebar toggle button-->
	<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
		role="button"> <span class="sr-only">Toggle navigation</span>
	</a>
	<!-- Navbar Right Menu -->
	<div class="navbar-custom-menu">
		<ul class="nav navbar-nav">
			<!-- Messages: style can be found in dropdown.less-->
			<li class="dropdown notifications-menu">
				<!-- Menu toggle button --> <a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="fa fa-envelope-o"></i> <span
					class="label label-success">${fn:length(listNhanVienKpi) }</span>
			</a>
				<ul class="dropdown-menu">
					<li class="header">Bạn Có ${fn:length(listNhanVienKpi) } Thông Báo</li>
					<li>
						<!-- inner menu: contains the messages -->
						<ul class="menu">
							<c:forEach var="nvk" items="${listNhanVienKpi }">
								<li>
									<!-- start notification --> <a
									href="${contextPath }/admin/nhanvienkpi/${nvk.id}"> <i class="fa fa-users text-aqua"></i>
										Ngày <b><fmt:formatDate pattern="dd-MM-yyyy"
									value="${nvk.ngaydangky }" /></b>  Nhân Viên <br> ${nvk.manhanvien } - ${nvk.tennhanvien }
										Đăng Ký KPI <br> <b>${nvk.tenkpi }</b>

								</a>
								</li>
							</c:forEach>

							<!-- end message -->
						</ul> <!-- /.menu -->
					</li>
					<!-- <li class="footer"><a href="#">See All Messages</a></li> -->
				</ul>
			</li>
			<!-- /.messages-menu -->

			<!-- Notifications Menu -->
			<li class="dropdown notifications-menu">
				<!-- Menu toggle button --> <a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="fa fa-bell-o"></i> <span
					class="label label-warning">${fn:length(listChamSoc) }</span>
			</a>
				<ul class="dropdown-menu">
					<li class="header">Bạn Có ${fn:length(listChamSoc) } Thông Báo</li>
					<li>
						<!-- Inner Menu: contains the notifications -->
						<ul class="menu">
							<c:forEach var="cs" items="${listChamSoc }">
								<li>
									<!-- start notification --> <a
									<%-- onclick="updateThongBaoChamSoc(${cs.id });" --%>
									href="${contextPath }/admin/chamsoctieptheo/add/${cs.id}"> <i class="fa fa-users text-aqua"></i>
										<c:choose>
											<c:when test="${cs.ngaycstiep == 0}">
												<b>Hôm Nay</b> Là Lần Chăm Sóc <br>
											Tiếp Theo Của <b>${cs.khachhang }</b>
											</c:when>
											<c:when test="${cs.ngaycstiep < 0}">
												<b><fmt:formatDate pattern="dd/MM/yyyy" value="${cs.ngay}"/></b> Là Lần Chăm Sóc <br>
												Tiếp Theo Của <b>${cs.khachhang }</b>
											</c:when>
											<c:otherwise>
											Còn <b>${cs.ngaycstiep }</b> Ngày Nữa Tới Lần Chăm Sóc <br>
											Tiếp Theo Của <b>${cs.khachhang }</b>
											</c:otherwise>
										</c:choose>

								</a>
								</li>
							</c:forEach>
							<!-- end notification -->
						</ul>
					</li>
					<!-- <li class="footer"><a href="#">View all</a></li> -->
				</ul>
			</li>
			<!-- Tasks Menu -->
			<li class="dropdown notifications-menu">
				<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="fa fa-flag-o"></i> <span
					class="label label-danger">${fn:length(listKhachHang) }</span>
			</a>
				<ul class="dropdown-menu">
					<li class="header">Bạn Có ${fn:length(listKhachHang) } Thông
						Báo</li>
					<li>
						<!-- Inner menu: contains the tasks -->
						<ul class="menu">
							<c:forEach var="lkh" items="${listKhachHang }">
								<li>
									<!-- start notification --> <a
									onclick="updateThongBaoKhachHang(${lkh.id });"
									href="javascript:void(0);"> <i class="fa fa-user text-red"></i>
										<c:choose>
											<c:when test="${lkh.ngaysinhnhat == 0}">
												 <b>Hôm Nay</b> Là Sinh Nhật Của <br>
										<b>${lkh.makh } - ${lkh.ten }</b>
											</c:when>
											<c:when test="${lkh.ngaysinhnhat < 0}">
												<b><fmt:formatDate pattern="dd/MM/yyyy" value="${lkh.ngay}"/></b> Là Sinh Nhật Của <br>
												<b>${lkh.makh } - ${lkh.ten }</b>
											</c:when>
											<c:otherwise>
											Còn <b>${lkh.ngaysinhnhat }</b> Ngày Nữa Là Sinh Nhật Của <br>
												<b>${lkh.makh } - ${lkh.ten }</b>
											</c:otherwise>
										</c:choose>
								</a>
								</li>
							</c:forEach>
							<!-- end task item -->
						</ul>
					</li>
					<!-- <li class="footer"><a href="#">View all tasks</a></li> -->
				</ul>
			</li>
			<!-- User Account Menu -->
			<li class="dropdown user user-menu">
				<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <!-- The user image in the navbar--> <img
					src="${contextPath }/images/avatar.png" class="user-image"
					alt="User Image"> <!-- hidden-xs hides the username on small devices so only the image appears. -->
					<span class="hidden-xs">${taikhoan.username }</span>
			</a>
				<ul class="dropdown-menu">
					<!-- The user image in the menu -->
					<li class="user-header"><img
						src="${contextPath }/images/avatar.png" class="img-circle"
						alt="User Image">

						<p>
							${taikhoan.nhanvien.tennhanvien } <small>${taikhoan.nhanvien.bophan.tenbophan }</small>
						</p></li>
					<!-- Menu Body -->
					<!-- <li class="user-body">
						<div class="row">
							<div class="col-xs-4 text-center">
								<a href="#">Followers</a>
							</div>
							<div class="col-xs-4 text-center">
								<a href="#">Sales</a>
							</div>
							<div class="col-xs-4 text-center">
								<a href="#">Friends</a>
							</div>
						</div> /.row
					</li> -->
					<!-- Menu Footer-->
					<li class="user-footer">
						<div class="pull-left">
							<a href="${contextPath }/admin/thongtin" class="btn btn-default btn-flat">Thông Tin</a>
						</div>
						<div class="pull-right">
							<a href="${contextPath }/logout" class="btn btn-default btn-flat">Đăng Xuất</a>
						</div>
					</li>
				</ul>
			</li>
			<!-- Control Sidebar Toggle Button -->
			<!-- <li><a href="#" data-toggle="control-sidebar"><i
					class="fa fa-gears"></i></a></li> -->
		</ul>
	</div>
</nav>