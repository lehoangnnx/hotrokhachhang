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
		<%-- <div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-aqua">
				<div class="inner">
					<h3>${fn:length(listHoadonAll) }</h3>

					<p>Hóa Đơn</p>
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
						538888888<sup style="font-size: 20px">%</sup>
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
		</div> --%>


		<!-- ./col -->
		<form:form id="formThongKe" action="${contextPath }/admin/thongke"
			method="get">
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
									<input name="tungay" id="tungay" onchange="checkNgay();"
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
									<input name="denngay" id="denngay" onchange="checkNgay();"
										value="<fmt:formatDate
                                                pattern="dd-MM-yyyy" value="${denngay }"/>"
										type="text" class="form-control"
										data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
								</div>
								<label id="denngay-error" class="error" for="denngay"></label>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Nhân Viên Bán Hàng</label> <select
									class="form-control select2" name="nhanvienbanhang"
									style="width: 100%;">
									<option value="0">Không</option>
									<c:forEach var="nv" items="${listNhanvien }">
										<option ${param.nhanvienbanhang == nv.id ? 'selected' : '' }
											value="${nv.id }">${nv.manhanvien}-${nv.tennhanvien }</option>
									</c:forEach>

								</select>
							</div>
						</div>
						<div class="col-md-12">
							<button style="margin-top: 25px;" type="submit" id="btn-submit"
								class="btn btn-block btn-success btn-flat">Thống Kê</button>
						</div>
					</div>
				</div>
			</div>
		</form:form>
		<div class="col-md-12">
			<div class="box box-primary">
				<c:set var="tongtien" value="0" />
				<c:set var="tiendatra" value="0" />
				<c:set var="congno" value="0" />
				<c:forEach var="hd" items="${listHoadon }">
					<c:set var="tongtien" value="${tongtien + hd.tongtien }" />
					<c:set var="tiendatra" value="${tiendatra + hd.tiendatra }" />
					<c:set var="congno" value="${congno + hd.congno }" />
				</c:forEach>
				<div class="box-body">
					<div class="col-md-12 margin" style="border-bottom: 1px solid;">
						<div class="col-md-4">
							<div class="form-group">
								<label>Tổng Hóa Đơn : <span>${fn:length(listHoadon) }</span>
								</label>

							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<c:set var="hddtt" value="0" />
								<c:forEach var="hd" items="${listHoadon }">
									<c:if test="${hd.tongtien == hd.tiendatra && hd.congno == 0 }">
										<c:set var="hddtt" value="${hddtt + 1 }" />
									</c:if>

								</c:forEach>
								<label>Hóa Đơn Đã Thanh Toán : <span>${hddtt }</span>
								</label>

							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<c:set var="hdctt" value="0" />
								<c:forEach var="hd" items="${listHoadon }">
									<c:if test="${hd.tongtien > hd.tiendatra && hd.congno > 0 }">
										<c:set var="hdctt" value="${hdctt + 1 }" />
									</c:if>

								</c:forEach>
								<label>Hóa Đơn Chưa Thanh Toán : <span>${hdctt }</span>
								</label>

							</div>
						</div>
						
					</div>
					<div class="col-md-12 margin">
						<div class="col-md-4">
							<div class="form-group">
								<label for="congno">Tổng Tiền : <span><fmt:formatNumber
											type="number" pattern="###,###" value="${tongtien }" /></span>
									&#8363;
								</label>

							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="congno">Tiền Đã Trả : <span><fmt:formatNumber
											type="number" pattern="###,###" value="${tiendatra }" /></span>
									&#8363;
								</label>

							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="congno">Đang Nợ : <span><fmt:formatNumber
											type="number" pattern="###,###" value="${congno }" /></span>
									&#8363;
								</label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<%-- <ul class="nav nav-tabs">
		<li
			class="tablinks ${param.trangthai == 'dathanhtoan' ? 'active' : '' }">
			<a
			href="${contextPath }/admin/thongke?trangthai=dathanhtoan&limit=100&page=1">Đã
				Thanh Toán</a>
		</li>
		<li
			class="tablinks ${param.trangthai == 'chuathanhtoan' ? 'active' : '' }">
			<a
			href="${contextPath }/admin/thongke?trangthai=chuathanhtoan&limit=100&page=1">Chưa
				Thanh Toán</a>
		</li>
		<li class="tablinks ${param.trangthai == 'dangno' ? 'active' : '' }">
			<a
			href="${contextPath }/admin/thongke?trangthai=dangno&limit=100&page=1">Đang
				Nợ</a>
		</li>
		<li class="tablinks ${param.trangthai == 'deleted' ? 'active' : '' }">
			<a
			href="${contextPath }/admin/thongke?trangthai=deleted&limit=100&page=1">Đã
				Xóa</a>
		</li>
	</ul> --%>
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">Thống Kê Hóa Đơn</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body">
		 <div class="table-responsive">
			<table id="example2" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>Số Hóa Đơn</th>
								<th>Khách Hàng</th>
								<th>Nhân Viên Bán Hàng</th>
								<th>Tổng Tiền</th>
								<th>Đã Trả</th>

								<th>Ngày Lập</th>
								<th>Trạng Thái</th>
								<th>Thao Tác</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="hd" items="${listHoadon }">
								<tr>
									<td>${hd.sohoadon }</td>
									<td>${hd.khachhang.makh }- ${hd.khachhang.ten }</td>
									<td>${hd.nhanvienByIdnhanvienban.manhanvien }-
										${hd.nhanvienByIdnhanvienban.tennhanvien }</td>
									<td><fmt:formatNumber type="number" pattern="###,###"
											value="${hd.tongtien }" /> &#8363;</td>
									<td><fmt:formatNumber type="number" pattern="###,###"
											value="${hd.tiendatra }" /> &#8363;</td>

									<td><fmt:formatDate pattern="dd-MM-yyyy"
											value="${hd.ngaylap }" /></td>

									<c:if test="${hd.trangthai == 'dagiaohang' }">
										<td>Đã Giao Hàng</td>
									</c:if>
									<c:if test="${hd.trangthai == 'chuagiaohang' }">
										<td>Chưa Giao Hàng</td>
									</c:if>
									<c:if test="${hd.trangthai == 'deleted' }">
										<td>Đã Xóa</td>
									</c:if>
									<td><a href="${contextPath }/admin/hoadon/${hd.id}"> <i
											style="color: blue;" class="fa fa-pencil fa-lg"
											aria-hidden="true" title="Sửa"> </i>
									</a> <%-- <a onclick="deleteOne(${hd.id});" href="#" data-toggle="modal"
										data-target="#myModal" style="color: red; margin-left: 10px;">
											<i class="fa fa-trash-o fa-lg" aria-hidden="true" title="Xóa"></i>
									</a> --%></td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<th>Số Hóa Đơn</th>
								<th>Khách Hàng</th>
								<th>Nhân Viên Bán Hàng</th>
								<th>Tổng Tiền</th>
								<th>Đã Trả</th>
								
								<th>Ngày Lập</th>
								<th>Trạng Thái</th>
								<th>Thao Tác</th>
							</tr>
						</tfoot>
					</table>
					</div>
		</div>





		<!-- /.box-body -->
	</div>
	<!-- /.row -->
	<!-- Main row -->

	<!-- /.row (main row) -->

</section>