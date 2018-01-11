<%@ page pageEncoding="UTF-8"%>
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
			<p>Alexander Pierce</p>
			<!-- Status -->
			<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
		</div>
	</div>

	<!-- search form (Optional) -->
	<form action="#" method="get" class="sidebar-form">
		<div class="input-group">
			<input type="text" name="q" class="form-control"
				placeholder="Search..."> <span class="input-group-btn">
				<button type="submit" name="search" id="search-btn"
					class="btn btn-flat">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
	</form>
	<!-- /.search form -->

	<!-- Sidebar Menu -->
	<ul class="sidebar-menu">
		<li class="header">HEADER</li>
		<!-- Optionally, you can add icons to the links -->

		<li><a href="${contextPath }/admin/hoadon"><i class="fa fa-link"></i> <span>Hóa Đơn</span></a></li>
		<li class="treeview"><a href="#"><i class="fa fa-link"></i> <span>Chăm Sóc</span> <span class="pull-right-container"> <i
					class="fa fa-angle-left pull-right"></i>
			</span> </a>
			<ul class="treeview-menu">
				<li><a href="${contextPath }/admin/chamsoc">Chăm Sóc</a></li>
				<li><a href="${contextPath }/admin/tieuchichamsoc">Tiêu Chí Chăm Sóc</a></li>
			</ul></li>
		
		<li class="treeview"><a href="#"><i class="fa fa-link"></i> <span>Tài
					khoản</span> <span class="pull-right-container"> <i
					class="fa fa-angle-left pull-right"></i>
			</span> </a>
			<ul class="treeview-menu">
				<li><a href="${contextPath }/admin/taikhoan">Tài Khoản</a></li>
				<li><a href="${contextPath }/admin/quyen">Quyền</a></li>
			</ul></li>

		<li class="treeview"><a href="#"><i class="fa fa-link"></i> <span>Khách
					Hàng</span> <span class="pull-right-container"> <i
					class="fa fa-angle-left pull-right"></i>
			</span> </a>
			<ul class="treeview-menu">
<li><a href="${contextPath }/admin/khachhang">Khách Hàng</a></li>
				<li><a href="${contextPath }/admin/loaikhachhang">Loại
						Khách Hàng</a></li>
				<li><a href="${contextPath }/admin/nhomkhachhang">Nhóm
						Khách Hàng</a></li>

			</ul></li>
		<li class="treeview"><a href="#"><i class="fa fa-link"></i> <span>Hàng
					Hóa</span> <span class="pull-right-container"> <i
					class="fa fa-angle-left pull-right"></i>
			</span> </a>
			<ul class="treeview-menu">

				<li><a href="${contextPath }/admin/nhomhang">Nhóm Hàng</a></li>
				<li><a href="${contextPath }/admin/hanghoa">Hàng Hóa</a></li>

			</ul></li>
			
			<li class="treeview"><a href="#"><i class="fa fa-link"></i> <span>Nhân Viên</span> <span class="pull-right-container"> <i
					class="fa fa-angle-left pull-right"></i>
			</span> </a>
			<ul class="treeview-menu">
	<li><a href="${contextPath }/admin/nhanvien">Nhân Viên</a></li>
				<li><a href="${contextPath }/admin/bophan">Bộ Phận</a></li>
				<li><a href="${contextPath }/admin/luong">Lương</a></li>
			</ul></li>
			<li class="treeview"><a href="#"><i class="fa fa-link"></i> <span>KPI</span> <span class="pull-right-container"> <i
					class="fa fa-angle-left pull-right"></i>
			</span> </a>
			<ul class="treeview-menu">
	<li><a href="${contextPath }/admin/nhanvienkpi">Nhân Viên KPI </a></li>
				<li><a href="${contextPath }/admin/kpi">KPI</a></li>
				
			</ul></li>
	</ul>
	<!-- /.sidebar-menu -->
</section>
<!-- /.sidebar -->