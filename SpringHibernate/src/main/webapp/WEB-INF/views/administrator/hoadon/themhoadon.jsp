<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<style>
.lb-flat-red {
	padding-left: 10px;
	padding-right: 20px;
}
</style>
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}
</style>
<section class="content">

	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">Thêm Hóa Đơn</h3>

					<button class="btn btn-info pull-right" type="button" onclick="goBack();">Quay Lại</button>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formHoaDon"
					action="${contextPath }/admin/hoadon" method="post"
					modelAttribute="hoadon">
					<form:hidden path="id" />
					<div class="box-body">

						<div class="form-group">
							<label>Nhân Viên Bán Hàng</label> <select
								class="form-control select2" name="nhanvienbanhang"
								style="width: 100%;">
							<option value="${taikhoan.nhanvien.id }">${taikhoan.nhanvien.manhanvien}-
									${taikhoan.nhanvien.tennhanvien }</option>
								<%--<c:forEach var="nv" items="${listNhanvien }">
									<option value="${nv.id }">${nv.manhanvien}-
										${nv.tennhanvien }</option>
								</c:forEach>--%>

							</select>
						</div>
						<div class="form-group">
							<label>Nhân Viên Chăm Sóc</label> <select
								class="form-control select2" name="nhanvienchamsoc"
								style="width: 100%;">

								<c:forEach var="nv" items="${listNhanvien }">
									<option value="${nv.id }">${nv.manhanvien}-
										${nv.tennhanvien }</option>
								</c:forEach>

							</select>
						</div>
						<div class="form-group">
							<label>Nhân Viên Giao Hàng</label> <select
								class="form-control select2" name="nhanviengiaohang"
								style="width: 100%;">

								<c:forEach var="nv" items="${listNhanvien }">
									<option value="${nv.id }">${nv.manhanvien}-
										${nv.tennhanvien }</option>
								</c:forEach>

							</select>
						</div>
						<div class="form-group">
							<label>Khách Hàng</label> <select class="form-control select2"
								name="khachhang" style="width: 100%;">

								<c:forEach var="kh" items="${listKhachhang }">
									<option value="${kh.id }">${kh.makh}- ${kh.ten }</option>
								</c:forEach>

							</select>
						</div>


						<div class="form-group">
							<label for="sohoadon">Số Hóa Đơn</label>
							<input id="sohoadon" name="sohoadon" type="text" class="form-control" readonly
								placeholder="Số Hóa Đơn" />
							<label id="_sohoadon-error" class="error" style="display: none;"></label>
						</div>


						<div class="form-group">
							<label for="hanghoa">Hàng Hóa</label>
						</div>
						<div class="col-md-12 margin ">
							<div class="form-group col-md-6">
								<select class="form-control select2" name="" id="hanghoa"
									style="width: 100%;">

									<c:forEach var="hh" items="${listHanghoa }">
										<option value="${hh.id }">${hh.mahang}-${hh.tenhang }</option>
									</c:forEach>

								</select> <label id="_hanghoa-error" class="error" style="display: none;"></label>
							</div>
							<div class="form-group col-md-3" >
								<select class="form-control select2" name="kieugia" id="kieugia"
										style="width: 100%;">

									<option value="giabansi">Giá Bán Sỉ</option>
									<option value="giabanle">Giá Bán Lẻ</option>
									<option value="giakhuyenmai">Giá Khuyến Mãi</option>

								</select>
							</div>
							<div class="col-md-3 form-group">
								<div class="input-group">
									<input type="number" id="soluong" class="form-control"
										value="0" placeholder="Nhập Số Lượng"> <span
										class="input-group-btn">
										<button id="btn-thhvcthd" type="button"
											class="btn btn-info btn-flat">Thêm</button>
									</span>
								</div>
							</div>

						</div>
						<%--<div class="form-group">
							<label id="_hanghoa-error" class="error" style="display: none;"></label>
						</div>--%>
						<div class="box-body table-responsive">

								<table border="1" style="overflow-x: auto;" id="tblcthd">
									<thead>
										<tr>
											<th>ID Hàng Hóa</th>
											<th>Mã Hàng Hóa</th>
											<th>Tên Hàng Hóa</th>
											<th>Giá Bán</th>
											<th>Số Lượng</th>
											<th>Thành Tiền</th>
											<th>Thao tác</th>
										</tr>
									</thead>
									<tbody>

									</tbody>
									<tfoot>
										<tr>
											<th>ID Hàng Hóa</th>
											<th>Mã Hàng Hóa</th>
											<th>Tên Hàng Hóa</th>
											<th>Giá Bán</th>
											<th>Số Lượng</th>
											<th>Thành Tiền</th>
											<th>Thao tác</th>
										</tr>
									</tfoot>
								</table>
                            <label id="_giaban-error" class="error" style="display: none;"></label>
						</div>


						<div class="form-group">
							<label for="hinhthucthanhtoan">Hình Thức Thanh Toán</label> <br>
							<label class="lb-flat-red"> <input value="tienmat"
								type="radio" name="hinhthucthanhtoan" class="flat-red" checked>
								Tiền Mặt
							</label> <label class="lb-flat-red"> <input value="chuyenkhoan"
								type="radio" name="hinhthucthanhtoan" class="flat-red">
								Chuyển Khoản
							</label>
						</div>



						<div class="form-group">
							<label>Ngày Lập</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input name="ngaylap" id="ngaylap" onchange="checkngay(event);"
									value="<fmt:formatDate
                                                pattern="dd-MM-yyyy" value="${hoadon.ngaylap}"/>"
									type="text" class="form-control"
									data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
							</div>
							<label id="ngaylap-error" class="error" for="ngaylap"></label>
						</div>
						<div class="form-group">
							<label>Ngày Xuất</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input name="ngayxuat" id="ngayxuat" onchange="checkngay(event);"
									value="<fmt:formatDate
                                                pattern="dd-MM-yyyy" value="${hoadon.ngayxuat}"/>"
									type="text" class="form-control"
									data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
							</div>
							<label id="ngayxuat-error" class="error" for="ngayxuat"></label>
						</div>
						<div class="form-group">
							<label>Ngày Thanh Toán</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input name="ngaythanhtoan" id="ngaythanhtoan" onchange="checkngay(event);"
									   value="<fmt:formatDate
                                                pattern="dd-MM-yyyy" value="${hoadon.ngaythanhtoan}"/>"
									   type="text" class="form-control"
									   data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
							</div>
							<label id="ngaythanhtoan-error" class="error" for="ngaythanhtoan"></label>
						</div>

						<div class="form-group">
							<label for="tongtien">Tổng Tiền</label>
						<div class="input-group">


								<input  path="tongtien" type="text" name="tongtien_money" id="tongtien"
											class="form-control" value="0" placeholder="Tổng Tiền" />
								<span class="input-group-addon">VNĐ</span>
						</div>
						</div>

						<div class="form-group">
							<label for="tiendatra">Tiền Đã Trả</label>
							<div class="input-group">

							<input id="tiendatra" type="text" class="form-control" name="tiendatra_money"
								 value="0" placeholder="Tiền Đã Trả" />
								<span class="input-group-addon">VNĐ</span>
						</div>
						</div>


						<div class="form-group">
							<label for="congno">Tiền Còn Nợ</label>
							<div class="input-group">

							<input id="congno" type="text" class="form-control" name="congno_money"
								 value="0" placeholder="Tiền Còn Nợ" />
								<span class="input-group-addon">VNĐ</span>
						</div>
						</div>

						<div class="form-group">
							<label for="diachigiaohang">Địa Chỉ Giao Hàng</label>
							<form:input path="diachigiaohang" type="text"
								class="form-control" placeholder="Địa Chỉ Giao Hàng" />
							<label id="_diachigiaohang-error" class="error"
								style="display: none;"></label>
						</div>

						<div class="form-group">
							<label>Số Điện Thoại</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-phone"></i>
								</div>
								<input type="text" class="form-control" name="sodienthoai"
									value="${hoadon.sodienthoai}"
									data-inputmask="&quot;mask&quot;: &quot;999-999-99999&quot;"
									data-mask=""> <label id="_sodienthoai-error"
									class="error" style="display: none;"></label>
							</div>
							<label id="sodienthoai-error" class="error" for="sodienthoai"></label>
						</div>



						<div class="form-group">
							<label for="trangthai">Trạng Thái</label> <br> <label
								class="lb-flat-red"> <input value="dagiaohang"
								type="radio" name="trangthai" class="flat-red"> Đã Giao
								Hàng
							</label> <label class="lb-flat-red"> <input value="chuagiaohang"
								type="radio" name="trangthai" class="flat-red" checked="checked">
								Chưa Giao Hàng
							</label>
						</div>


						<div class="form-group">
							<label for="ghichu">Ghi Chú</label>
							<form:textarea path="ghichu" type="text" class="form-control"
								placeholder="Ghi Chú" />
							<label id="_ghichu-error" class="error" style="display: none;"></label>
						</div>

					</div>
					<!-- /.box-body -->
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="box-footer">
						<button id="btn-submit" type="button" onclick="checkngay(event);" class="btn btn-primary">Xác
							Nhận</button>
						<button class="btn btn-info pull-right" type="button" onclick="goBack();">Quay Lại</button>
					</div>
				</form:form>
			</div>
			<!-- /.box -->




		</div>
	</div>
	<!-- /.row -->
</section>

