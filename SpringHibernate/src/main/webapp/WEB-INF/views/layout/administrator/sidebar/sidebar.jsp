<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- sidebar: style can be found in sidebar.less -->
<section class="sidebar">

	<!-- Sidebar user panel (optional) -->
	<div class="user-panel">
		<div class="pull-left image">
			<img src="${contextPath }/images/avatar.png" class="img-circle"
				alt="User Image">
		</div>
		<div class="pull-left info">
			<p>${taikhoan.username }</p>
			<!-- Status -->
			<!-- <a href="#"><i class="fa fa-circle text-success"></i> Online</a> -->
		</div>
	</div>

	<!-- search form (Optional) -->
	<%-- <form action="#" method="get" class="sidebar-form">
		<div class="input-group">
			<input type="text" name="q" class="form-control"
				placeholder="Search..."> <span class="input-group-btn">
				<button type="submit" name="search" id="search-btn"
					class="btn btn-flat">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
	</form> --%>
	<!-- /.search form -->

	<!-- Sidebar Menu -->
	<ul class="sidebar-menu">
		<li class="header">Quản Lý</li>

		<!-- Optionally, you can add icons to the links -->
		<li class="treeview"><a href="${contextPath }/admin"><i
				class="fa fa-link"></i> <span>Trang Chủ</span></a></li>
		<security:authorize access="hasAnyRole('ADMIN','TAICHINH')">
			<li class="treeview"><a href="${contextPath }/admin/thongke"><i
					class="fa fa-link"></i> <span>Thống Kê</span></a></li>
		</security:authorize>
		<security:authorize access="hasAnyRole('ADMIN')">
			<li class="treeview"><a href="${contextPath }/admin/saokehoadon"><i
					class="fa fa-link"></i> <span>Sao Kê Hóa Đơn</span></a></li>
		</security:authorize>
		<security:authorize access="hasAnyRole('ADMIN')">
			<li class="treeview"><a
				href="${contextPath }/admin/thongkekpi?kpi=0"><i
					class="fa fa-link"></i> <span>Thống Kê KPI</span></a></li>
		</security:authorize>
		<security:authorize access="hasAnyRole('ADMIN','CHAMSOC')">
		<li class="treeview"><a
			href="${contextPath }/admin/feedback?trangthai=active"><i
				class="fa fa-link"></i> <span>Feedback</span></a></li>
				</security:authorize>
				<security:authorize access="hasAnyRole('ADMIN','BANHANG','GIAOHANG')">
		<li class="treeview"><a
			href="${contextPath }/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1&hthd=on"><i
				class="fa fa-link"></i> <span>Hóa Đơn</span></a></li>
				</security:authorize>
				<security:authorize access="hasAnyRole('ADMIN','CHAMSOC')">
		<li class="treeview"><a href="#"><i class="fa fa-link"></i> <span>Chăm
					Sóc</span> <span class="pull-right-container"> <i
					class="fa fa-angle-left pull-right"></i>
			</span> </a>
			<ul class="treeview-menu">
				<li><a
					href="${contextPath }/admin/chamsoc?trangthai=chochamsoc&limit=100&page=1">Chăm
						Sóc</a></li>
				<li><a
					href="${contextPath }/admin/tieuchichamsoc?trangthai=active">Tiêu
						Chí Chăm Sóc</a></li>
			</ul></li>
			</security:authorize>
			<security:authorize access="hasAnyRole('ADMIN')">
		<li class="treeview"><a href="#"><i class="fa fa-link"></i> <span>Tài
					khoản</span> <span class="pull-right-container"> <i
					class="fa fa-angle-left pull-right"></i>
			</span> </a>
			<ul class="treeview-menu">
				<li><a href="${contextPath }/admin/taikhoan?trangthai=active">Tài
						Khoản</a></li>
				<li><a href="${contextPath }/admin/quyen?trangthai=active">Quyền</a></li>
			</ul></li>
</security:authorize>
<security:authorize access="hasAnyRole('ADMIN','CHAMSOC','BANHANG')">
		<li class="treeview"><a href="#"><i class="fa fa-link"></i> <span>Khách
					Hàng</span> <span class="pull-right-container"> <i
					class="fa fa-angle-left pull-right"></i>
			</span> </a>
			<ul class="treeview-menu">
				<li><a
					href="${contextPath }/admin/khachhang?trangthai=active&loaikhachhang=0&nhomkhachhang=0&limit=100&page=1">Khách
						Hàng</a></li>
					<security:authorize access="hasAnyRole('ADMIN')">	
				<li><a
					href="${contextPath }/admin/loaikhachhang?trangthai=active">Loại
						Khách Hàng</a></li>
				<li><a
					href="${contextPath }/admin/nhomkhachhang?trangthai=active">Nhóm
						Khách Hàng</a></li>
						</security:authorize>

			</ul></li>
			</security:authorize>
			<security:authorize access="hasAnyRole('ADMIN','BANHANG')">
		<li class="treeview"><a href="#"><i class="fa fa-link"></i> <span>Hàng
					Hóa</span> <span class="pull-right-container"> <i
					class="fa fa-angle-left pull-right"></i>
			</span> </a>
			<ul class="treeview-menu">


				<li><a href="${contextPath }/admin/hanghoa?trangthai=active">Hàng
						Hóa</a></li>
				<security:authorize access="hasAnyRole('ADMIN')">
				<li><a href="${contextPath }/admin/nhomhang?trangthai=active">Nhóm
						Hàng</a></li>
				</security:authorize>

			</ul></li>
</security:authorize>
<security:authorize access="hasAnyRole('ADMIN')">
		<li class="treeview"><a href="#"><i class="fa fa-link"></i> <span>Nhân
					Viên</span> <span class="pull-right-container"> <i
					class="fa fa-angle-left pull-right"></i>
			</span> </a>
			<ul class="treeview-menu">
				<li><a href="${contextPath }/admin/nhanvien?trangthai=active">Nhân
						Viên</a></li>
				<li><a href="${contextPath }/admin/bophan?trangthai=active">Bộ
						Phận</a></li>
				<li><a
					href="${contextPath }/admin/luong?trangthai=chuatraluong">Lương</a></li>
				<li><a
						href="${contextPath }/admin/ungluong?trangthai=active">Ứng Lương</a></li>
			</ul></li>
			</security:authorize>
			<security:authorize access="hasAnyRole('ADMIN')">
		<%-- <li class="treeview"><a href="#"><i class="fa fa-link"></i> <span>KPI</span>
				<span class="pull-right-container"> <i
					class="fa fa-angle-left pull-right"></i>
			</span> </a>
			<ul class="treeview-menu">
				<li><a
					href="${contextPath }/admin/nhanvienkpi?trangthai=inactive">Nhân
						Viên KPI </a></li>
				<li><a href="${contextPath }/admin/kpi?trangthai=active">KPI</a></li>

			</ul></li> --%>
				<li class="treeview"><a href="${contextPath }/admin/kpi?trangthai=active">
					<i class="fa fa-link"></i> <span>KPI</span>
				 </a></li>

			</security:authorize>
		<li class="treeview"><a href="${contextPath }/admin/nhanvienkpi?trangthai=inactive">
			<i class="fa fa-link"></i> <span>Nhân Viên KPI</span>  </a></li>

	</ul>
	<!-- /.sidebar-menu -->
</section>
<!-- /.sidebar -->