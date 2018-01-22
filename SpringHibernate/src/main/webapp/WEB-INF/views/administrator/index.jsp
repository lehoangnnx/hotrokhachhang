<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<section class="content">
	<!-- Small boxes (Stat box) -->
	<div class="row">
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-aqua">
				<div class="inner">
					<h3>150</h3>

					<p>New Orders</p>
				</div>
				<div class="icon">
					<i class="ion ion-bag"></i>
				</div>
				<a href="#" class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>
		<!-- ./col -->
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-green">
				<div class="inner">
					<h3>
						53<sup style="font-size: 20px">%</sup>
					</h3>

					<p>Bounce Rate</p>
				</div>
				<div class="icon">
					<i class="ion ion-stats-bars"></i>
				</div>
				<a href="#" class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>
		<!-- ./col -->
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-yellow">
				<div class="inner">
					<h3>44</h3>

					<p>User Registrations</p>
				</div>
				<div class="icon">
					<i class="ion ion-person-add"></i>
				</div>
				<a href="#" class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>
		<!-- ./col -->
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-red">
				<div class="inner">
					<h3>65</h3>

					<p>Unique Visitors</p>
				</div>
				<div class="icon">
					<i class="ion ion-pie-graph"></i>
				</div>
				<a href="#" class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>


		<!-- ./col -->
		<form:form id="formThongKe" action="${contextPath }/admin/thongke"
			method="post">
			<div class="col-md-12">
				<div class="box box-primary">

					<div class="box-body">
						<div class="col-md-4">
							<div class="form-group">
								<label>Từ Ngày</label>

								<div class="input-group">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input name="tungay" id="tungay"
										value="<fmt:formatDate value="${tungay }"
                                                pattern="dd-MM-yyyy" />"
										type="text" class="form-control"
										data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
								</div>
								<label id="tungay-error" class="error" for="tungay"></label>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Đến Ngày</label>

								<div class="input-group">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input name="denngay" id="denngay"
										value="<fmt:formatDate
                                                pattern="dd-MM-yyyy" value="${denngay }"/>"
										type="text" class="form-control"
										data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
								</div>
								<label id="denngay-error" class="error" for="denngay"></label>
							</div>
						</div>
						<div class="col-md-2">
							<button style="margin-top: 25px;" type="submit"
								class="btn btn-block btn-success btn-flat">Thống Kê</button>
						</div>
					</div>
				</div>
			</div>
		</form:form>


	</div>
	<ul class="nav nav-tabs">
		<li
			class="tablinks ${param.trangthai == 'dathanhtoan' ? 'active' : '' }">
			<a
			href="${contextPath }/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1">Đã
				Thanh Toán</a>
		</li>
		<li
			class="tablinks ${param.trangthai == 'chuathanhtoan' ? 'active' : '' }">
			<a
			href="${contextPath }/admin/hoadon?trangthai=chuathanhtoan&limit=100&page=1">Chưa
				Thanh Toán</a>
		</li>
		<li class="tablinks ${param.trangthai == 'dangno' ? 'active' : '' }">
			<a
			href="${contextPath }/admin/hoadon?trangthai=dangno&limit=100&page=1">Đang
				Nợ</a>
		</li>
		<li class="tablinks ${param.trangthai == 'deleted' ? 'active' : '' }">
			<a
			href="${contextPath }/admin/hoadon?trangthai=deleted&limit=100&page=1">Đã
				Xóa</a>
		</li>
	</ul>
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">Danh Sách Hóa Đơn</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body">
			<table id="example2" class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>Số Hóa Đơn</th>
						<th>Trạng Thái</th>
						<th>Thao Tác</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="hd" items="${listHoadon }">
						<tr>
							<td>${hd.sohoadon }</td>
							<td>${hd.trangthai }</td>
							<td><a href="${contextPath }/admin/hoadon/${hd.id}"> <i
									style="color: blue;" class="fa fa-pencil fa-lg"
									aria-hidden="true" title="Sửa"> </i>
							</a> <a onclick="deleteOne(${hd.id});" href="#" data-toggle="modal"
								data-target="#myModal" style="color: red; margin-left: 10px;">
									<i class="fa fa-trash-o fa-lg" aria-hidden="true" title="Xóa"></i>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<th>Số Hóa Đơn</th>
						<th>Trạng Thái</th>
						<th>Thao Tác</th>
					</tr>
				</tfoot>
			</table>
		</div>





		<!-- /.box-body -->
	</div>
	<!-- /.row -->
	<!-- Main row -->

	<!-- /.row (main row) -->

</section>