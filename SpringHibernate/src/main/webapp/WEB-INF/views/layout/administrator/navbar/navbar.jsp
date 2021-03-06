<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
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
			<security:authorize access="hasAnyRole('ADMIN')">

				<li class="dropdown notifications-menu">
					<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
												   data-toggle="dropdown"> <i class="fa  fa-exclamation-triangle"></i> <span
						class="label label-danger">${fn:length(listHoaDonCount) }</span>
				</a>
					<ul class="dropdown-menu">
						<li class="header">Bạn Có ${fn:length(listHoaDonCount) } Cảnh Báo Hóa Đơn</li>
						<li>
							<!-- Inner menu: contains the tasks -->
							<ul class="menu">
								<c:forEach var="lhdc" items="${listHoaDonCount }">
									<li>
										<!-- start notification --> <a
											href="javascript:void(0);"> <i class="fa  fa-exclamation-circle text-red"></i>

												Có <b>${lhdc.count}</b> Hóa Đơn Của Khách Hàng <br>
											<b>${lhdc.khachhang.makh } - ${lhdc.khachhang.ten }</b> Nhân <br>
												  Viên <b>${lhdc.nhanvien.manhanvien} - ${lhdc.nhanvien.tennhanvien}</b> <br>
										Chưa Thanh Toán

									</a>
									</li>
								</c:forEach>
								<!-- end task item -->
							</ul>
						</li>
						<!-- <li class="footer"><a href="#">View all tasks</a></li> -->
					</ul>
				</li>

			<li class="dropdown notifications-menu">
				<!-- Menu toggle button --> <a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="fa fa-bell"></i> <span
					class="label label-success">${fn:length(listNhanVienKpi) }</span>
			</a>
				<ul class="dropdown-menu">
					<li class="header">Bạn Có ${fn:length(listNhanVienKpi) } Thông Báo Nhân Viên KPI</li>
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
			</security:authorize>
			<!-- /.messages-menu -->

			<!-- Notifications Menu -->
			<security:authorize access="hasAnyRole('ADMIN','CHAMSOC')">
			<li class="dropdown notifications-menu">
				<!-- Menu toggle button --> <a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="fa fa-group"></i> <span
					class="label label-warning">${fn:length(listChamSoc) }</span>
			</a>
				<ul class="dropdown-menu">
					<li class="header">Bạn Có ${fn:length(listChamSoc) } Thông Báo Chăm Sóc</li>
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
			</security:authorize>
			<!-- Tasks Menu -->
			<security:authorize access="hasAnyRole('ADMIN','CHAMSOC')">
			<li class="dropdown notifications-menu">
				<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="fa fa-birthday-cake"></i> <span
					class="label label-danger">${fn:length(listKhachHang) }</span>
			</a>
				<ul class="dropdown-menu">
					<li class="header">Bạn Có ${fn:length(listKhachHang) } Thông
						Báo Sinh Nhật</li>
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
			</security:authorize>

			<!-- ==================================================================-->
			<!-- Notifications Menu -->
			<security:authorize access="hasAnyRole('BANHANG') && !hasAnyRole('CHAMSOC')">
				<li class="dropdown notifications-menu">
					<!-- Menu toggle button --> <a href="#" class="dropdown-toggle"
												   data-toggle="dropdown"> <i class="fa fa-group"></i> <span
						class="label label-warning">${fn:length(listChamSoc) }</span>
				</a>
					<ul class="dropdown-menu">

						<li class="header">Bạn Có ${fn:length(listChamSoc) } Thông Báo Chăm Sóc</li>
						<li>
							<!-- Inner Menu: contains the notifications -->
							<ul class="menu">
								<c:forEach var="cs" items="${listChamSoc }">

									<li>
										<!-- start notification --> <a
										<%-- onclick="updateThongBaoChamSoc(${cs.id });" --%>
											href="javasctipt:void(0);"> <i class="fa fa-users text-aqua"></i>
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
			</security:authorize>
			<!-- Tasks Menu -->
			<security:authorize access="hasAnyRole('BANHANG') && !hasAnyRole('CHAMSOC')">
				<li class="dropdown notifications-menu">
					<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
												   data-toggle="dropdown"> <i class="fa fa-birthday-cake"></i> <span
						class="label label-danger">${fn:length(listKhachHang) }</span>
				</a>
					<ul class="dropdown-menu">
						<li class="header">Bạn Có ${fn:length(listKhachHang) } Thông
							Báo Sinh Nhật</li>
						<li>
							<!-- Inner menu: contains the tasks -->
							<ul class="menu">
								<c:forEach var="lkh" items="${listKhachHang }">
									<li>
										<!-- start notification --> <a

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
			</security:authorize>




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