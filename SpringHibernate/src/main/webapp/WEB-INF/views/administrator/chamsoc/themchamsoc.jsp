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
					<h3 class="box-title">Thêm Chăm Sóc</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formChamSoc"
					action="${contextPath }/admin/chamsoc" method="post"
					modelAttribute="chamsoc">
					<form:hidden path="id" />
					<div class="box-body">

						<div class="form-group">
							<label>Nhân Viên Bán Hàng</label> <select
								class="form-control select2" name="nhanvienbanhang"
								style="width: 100%;">
								<option value="0" selected="selected">Không</option>
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
								<option value="0" selected="selected">Không</option>
								<c:forEach var="nv" items="${listNhanvien }">
									<option value="${nv.id }">${nv.manhanvien}-
										${nv.tennhanvien }</option>
								</c:forEach>

							</select>
						</div>
						<div class="form-group">
							<label>Khách Hàng</label> <select class="form-control select2"
								id="khachhang" name="khachhang" style="width: 100%;">
	
								<c:forEach var="kh" items="${listKhachhang }">
									<option
									${kh.id == param.khachhang ? 'selected' : '' }
									 value="${kh.id }">${kh.makh}-${kh.ten }</option>
								</c:forEach>

							</select>
						</div>


						<div class="form-group">
							<label>Hóa Đơn</label> <select class="form-control select2" id="hoadon"
								name="hoadon" style="width: 100%;">
								<option value="0" selected="selected">Không</option>
								<c:forEach var="hd" items="${listHoadon }">
									<option value="${hd.id }">${hd.sohoadon}</option>
								</c:forEach>

							</select>
						</div>


						<div class="form-group">
							<label for="tieuchichamsoc">Tiêu Chí Chăm Sóc</label>
						</div>
						<div class="col-md-12 margin ">
							<div class="form-group col-md-8">
								<select class="form-control select2" name="" id="tieuchichamsoc"
									style="width: 100%;">
									<option value="0" selected="selected">Không</option>
									<c:forEach var="tccs" items="${listTieuchichamsoc }">
										<option value="${tccs.id }">${tccs.tentieuchi}</option>
									</c:forEach>

								</select>
							</div>
							<div class="col-md-4 form-group">
								<div class="input-group" id="dkieutieuchi">
									<input type="number" id="diem" class="form-control" value="0"
										placeholder="Nhập Điểm"> <span class="input-group-btn">
										<button id="btn-ttccsvctcs" type="button"
											class="btn btn-info btn-flat">Thêm</button>
									</span>
								</div>
							</div>

						</div>
						<div class="form-group">
							<label id="_diem-error" class="error" style="display: none;"></label>
						</div>
						<div class="box-body table-responsive">
						 
							<table border="1" style="overflow-x: auto;" id="tblctcs">
								<thead>
									<tr>
										<th>ID</th>
										<th>Tên Tiêu Chí Chăm Soc</th>
										<th>Kiểu Tiêu Chí</th>

										<th>Thao tác</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
								<tfoot>
									<tr>
										<th>ID</th>
										<th>Tên Tiêu Chí Chăm Soc</th>
										<th>Kiểu Tiêu Chí</th>

										<th>Thao tác</th>
									</tr>
								</tfoot>
							</table>
							
						</div>


						<div class="form-group">
							<label>Ngày</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input name="ngay" id="ngay"
									value="<fmt:formatDate
                                                pattern="dd-MM-yyyy" value="${chamsoc.ngay}"/>"
									type="text" class="form-control"
									data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
							</div>
							<label id="ngay-error" class="error" for="ngay"></label>
						</div>
						<div class="form-group">
							<label  style="margin-right: 60px;">Số
								Lần Đã Chăm Sóc : <span id="spsolanchamsoc">${solanchamsoc }</span>
							</label> <label>Số Lần Đã Đàm Phán : <span id="spsolandamphan">${solandamphan }</span></label>

						</div>
						<div class="form-group">
							<label for="lan">Lần Chăm Sóc</label>
							<form:input path="lan" type="number" class="form-control" min="0"
								value="${solanchamsoc + solandamphan + 1}"
								placeholder="Lần Chăm Sóc" />

						</div>
						<div class="form-group">
							<label for="noidung">Nội Dung</label>
							<form:textarea path="noidung" type="text" class="form-control"
								placeholder="Nội Dung" />

						</div>

						<div class="form-group">
							<label>Ngày Chăm Sóc Tiếp Theo</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input name="ngaycstiep" id="ngaycstiep"
									value="<fmt:formatDate
                                                pattern="dd-MM-yyyy" value="${chamsoc.ngaycstiep}"/>"
									type="text" class="form-control"
									data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
							</div>
							<label id="ngaycstiep-error" class="error" for="ngaycstiep"></label>
						</div>










						<div class="form-group">
							<label for="ghichu">Ghi Chú</label>
							<form:textarea path="ghichu" type="text" class="form-control"
								placeholder="ghichu" />
							<label id="_ghichu-error" class="error" style="display: none;"></label>
						</div>

					</div>
					<!-- /.box-body -->
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="box-footer">
						<button id="btn-submit" type="submit" class="btn btn-primary">Xác
							Nhận</button>
					</div>
				</form:form>
			</div>
			<!-- /.box -->




		</div>
	</div>
	<!-- /.row -->
</section>

