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
<section class="content">

	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">Sửa Chăm Sóc</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formChamSoc"
					action="${contextPath }/admin/chamsoc" method="patch"
					modelAttribute="chamsoc">
					<form:hidden path="id" />
					<div class="box-body">

						<div class="form-group">
							<label>Nhân Viên Bán Hàng</label> <select
								class="form-control select2" name="nhanvienbanhang"
								style="width: 100%;">

								<c:forEach var="nv" items="${listNhanvien }">
									<option ${chamsoc.nhanvienbanhang == nv.id ? 'selected' : '' }
										value="${nv.id }">${nv.manhanvien}-${nv.tennhanvien }</option>
								</c:forEach>

							</select>
						</div>
						<div class="form-group">
							<label>Nhân Viên Giao Hàng</label> <select
								class="form-control select2" name="nhanviengiaohang"
								style="width: 100%;">

								<c:forEach var="nv" items="${listNhanvien }">
									<option ${chamsoc.nhanviengiaohang == nv.id ? 'selected' : '' }
										value="${nv.id }">${nv.manhanvien}-${nv.tennhanvien }</option>
								</c:forEach>

							</select>
						</div>
						<div class="form-group">
							<label>Khách Hàng</label> <select class="form-control select2"
								name="khachhang" style="width: 100%;">

								<c:forEach var="kh" items="${listKhachhang }">
									<option ${chamsoc.khachhang.id == kh.id ? 'selected' : '' }
										value="${kh.id }">${kh.makh}-${kh.ten }</option>
								</c:forEach>

							</select>
						</div>


						<div class="form-group">
							<label>Hóa Đơn</label> <select class="form-control select2"
								name="hoadon" style="width: 100%;">

								<c:forEach var="hd" items="${listHoadon }">
									<option ${chamsoc.hoadonId == hd.id ? 'selected' : '' }
										value="${hd.id }">${hd.sohoadon}</option>
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

									<c:forEach var="tccs" items="${listTieuchichamsoc }">
										<option value="${tccs.id }">${tccs.tentieuchi}</option>
									</c:forEach>

								</select>
							</div>
							<div class="col-md-4 form-group">
								<div class="input-group">
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
						<div class="box-body">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>ID</th>
										<th>Tên Tiêu Chí Chăm Soc</th>
										<th>Điểm</th>

										<th>Thao tác</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="ctcs" items="${listChitietchamsoc }">
										<tr>
											<td><input hidden value="${ctcs.id }" name="idctcs">
												<input hidden value="${ctcs.tieuchichamsoc.id}"
												name="idtccs"> <span
												id="idtccs${ctcs.tieuchichamsoc.id}">${ctcs.tieuchichamsoc.tentieuchi}</span></td>
											<td><span id="tentieuchitccs${ctcs.tieuchichamsoc.id}">${ctcs.tieuchichamsoc.tentieuchi}</span></td>


											<td><input name="diemtccs"
												id="diemtccs${ctcs.tieuchichamsoc.id}" type="number"
												value="${ctcs.diem}"></td>

											<td><a
												onclick="Remove(this,${ctcs.tieuchichamsoc.id},${ctcs.id });"
												href="javascript:void(0);"> <i style="color: red;"
													class="fa fa-close" aria-hidden="true" title="Sửa"> </i></a></td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th>ID</th>
										<th>Tên Tiêu Chí Chăm Soc</th>
										<th>Điểm</th>

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
								<input name="ngay"
									value="<fmt:formatDate pattern="dd-MM-yyyy" value="${chamsoc.ngay}"/>"
									type="text" class="form-control"
									data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
							</div>
							<label id="ngay-error" class="error" for="ngay"></label>
						</div>

						<div class="form-group">
							<label for="lan">Lần Chăm Sóc</label>
							<form:input path="lan" type="number" class="form-control"
								placeholder="Lần Chăm Sóc" />

						</div>
						<div class="form-group">
							<label for="noidung">Nội Dung</label>
							<form:input path="noidung" type="text" class="form-control"
								placeholder="Nội Dung" />

						</div>

						<div class="form-group">
							<label>Ngày Chăm Sóc Tiếp Theo</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input name="ngaycstiep"
									value="<fmt:formatDate pattern="dd-MM-yyyy" value="${chamsoc.ngaycstiep}"/>"
									type="text" class="form-control"
									data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
							</div>
							<label id="ngaycstiep-error" class="error" for="ngaycstiep"></label>
						</div>










						<div class="form-group">
							<label for="ghichu">Ghi Chú</label>
							<form:input path="ghichu" type="text" class="form-control"
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

