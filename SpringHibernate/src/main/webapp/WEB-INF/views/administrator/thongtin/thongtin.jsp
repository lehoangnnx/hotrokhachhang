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

		<!-- ./col -->
		<form:form id="formThongTin" action="${contextPath }/admin/thongtin"
			method="get">
			<div class="col-md-12">
				<div class="box box-primary">

					<div class="box-body">
						<div class="col-md-4">
							<label>Tháng</label> <select class="form-control select2"
								id="thang" name="thang" style="width: 100%;">

								<c:forEach var="i" begin="1" end="12">
									<option ${thang == i ? 'selected' : '' }
										value="<fmt:formatNumber value="${i}" type="number"
											minIntegerDigits="2" />">Tháng
										-
										<fmt:formatNumber value="${i}" type="number"
											minIntegerDigits="2" />
									</option>
								</c:forEach>

							</select>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Năm</label> <select class="form-control select2" id="nam"
									name="nam" style="width: 100%;">

									<c:forEach var="i" begin="1990" end="2030">
										<option ${nam == i ? 'selected' : '' } value="${i }">Năm
											- ${i}</option>
									</c:forEach>

								</select>
							</div>
						</div>

						<div class="col-md-4">
							<button style="margin-top: 25px;" type="submit" id="btn-submit"
								class="btn btn-block btn-success btn-flat">Xem Lương</button>
						</div>
					</div>
				</div>
			</div>
		</form:form>



	</div>
	<div class="row">


		<!-- Main content -->
		<section class="invoice">
			<!-- title row -->
			<div class="row">
				<div class="col-xs-12">
					<h2 class="page-header">
						<i class="fa fa-globe"></i> Thông Tin
					</h2>
				</div>
				<!-- /.col -->
			</div>
			<!-- info row -->
			<div class="row invoice-info">
				<div class="col-sm-4 invoice-col">
					Mã Nhân Viên : <strong>${nhanvien.manhanvien }</strong> <br>
					Tên Nhân Viên : <strong>${nhanvien.tennhanvien }</strong> <br>
					Số CMND : ${nhanvien.socmnd } <br> Ngày Cấp :
					<fmt:formatDate pattern="dd/MM/yyyy" value="${nhanvien.ngaycap }" />
					<br> Nơi Cấp : ${nhanvien.noicap }
				</div>
				<!-- /.col -->
				<div class="col-sm-4 invoice-col">
					Ngày Vào Làm :
					<fmt:formatDate pattern="dd/MM/yyyy"
						value="${nhanvien.ngayvaolam }" />
					<br> Số Điện Thoại : ${nhanvien.sodienthoai }<br> Địa Chỉ
					: ${nhanvien.diachi }<br>
				</div>
				<!-- /.col -->
				<div class="col-sm-4 invoice-col">
					Lương :<strong><fmt:formatNumber type="number"
							pattern="###,###" value="${nhanvien.luong }" /> &#8363; </strong> <br>
					Chiết Khấu : <strong>${nhanvien.chietkhau } % </strong> <br>
					Bộ Phận : ${nhanvien.bophan.tenbophan }
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->

			<!-- Table row -->
			<div class="row">
				<div class="col-xs-12 table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Tháng/Năm</th>
								<th>Lương</th>
								<th>Thưởng</th>
								<th>Tổng</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty luong }">
								<tr>
									<td>${luong.thang }/${luong.nam }</td>
									<td><fmt:formatNumber type="number" pattern="###,###"
											value="${luong.luong }" /> &#8363;</td>
									<td><fmt:formatNumber type="number" pattern="###,###"
											value="${luong.thuong }" /> &#8363;</td>

									<td><fmt:formatNumber type="number" pattern="###,###"
											value="${luong.luong + luong.thuong }" /> &#8363;</td>
								</tr>
							</c:if>
							<c:if test="${empty luong }">
							<tr>
							<td valign="top" colspan="8" class="dataTables_empty">Không Có Lương</td>
							</tr>
							</c:if>
						</tbody>
					</table>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->

			<div class="row">
				<!-- accepted payments column -->
				
				<!-- /.col -->
				<div class="col-xs-6">
					<p class="lead">Thông Tin Tài Khoản</p>

					<div class="table-responsive">
						<table class="table">
							<tr>
								<th style="width: 50%">UserName : </th>
								<td>${taikhoan.username }</td>
							</tr>
							<tr>
								<th>Email : </th>
								<td>${taikhoan.email }</td>
							</tr>
							
						</table>
					</div>
				</div>
				
				<div class="col-xs-6">
					<p class="lead">Đổi Mật Khẩu</p>

					<div class="table-responsive">
						<table class="table">
							<tr>
								<th style="width: 50%">Mật Khẩu Mới : </th>
								<td><input id="pwd" type="password" class="form-control" placeholder="Mật Khẩu Mới" /></td>
							</tr>
							<tr>
								<th>Nhập Lại Mật Khẩu Mới : </th>
								<td><input id="rpwd" type="password" class="form-control" 
								placeholder="Nhập Lại Mật Khẩu Mới" />
								
								</td>
								
							</tr>
							
						</table>
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->

			<!-- this row will not appear when printing -->
			<div class="row no-print">
				<div class="col-xs-12">
					<label id="error" class="error" ></label>
					<button type="button" id="btn-doimatkhau" class="btn btn-success pull-right">
					 Đổi Mật Khẩu
					</button>
					
				</div>
			</div>
		</section>

		<!-- /.content -->
		<div class="clearfix"></div>

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

	<!-- /.row -->
	<!-- Main row -->

	<!-- /.row (main row) -->

</section>