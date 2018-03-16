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
					<h3 class="box-title">Sửa Khách Hàng</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formKhachHang"
					action="${contextPath }/admin/khachhang" method="patch"
					modelAttribute="khachhang">
					<form:hidden path="id" />
<form:hidden path="trangthai" />
<form:hidden path="trangthainhac" />
					<div class="box-body">
						<div class="form-group">
							<label>Loại Khách Hàng</label> <select
								class="form-control select2" name="loaikhachhang"
								style="width: 100%;">

								<c:forEach var="lkh" items="${listLoaikhachhang }">
									<option
										${khachhang.loaikhachhang.id == lkh.id ? 'selected' : '' }
										value="${lkh.id }">${lkh.tenloai}</option>
								</c:forEach>

							</select>
						</div>
						<div class="form-group">
							<label>Nhóm Khách Hàng</label> <select
								class="form-control select2" name="nhomkhachhang"
								style="width: 100%;">

								<c:forEach var="nkh" items="${listNhomkhachhang }">
									<option
										${khachhang.nhomkhachhang.id == nkh.id ? 'selected' : '' }
										value="${nkh.id }">${nkh.tennhom}</option>
								</c:forEach>

							</select>
						</div>
						<div class="form-group">
							<label for="makh">Mã Khách Hàng</label>
							<form:input path="makh" type="text" class="form-control"
								placeholder="Mã Khách Hàng" />
							<label id="error" class="error" style="display: none;"></label>
						</div>

						<div class="form-group">
							<label for="ten">Tên Khách Hàng</label>
							<form:input path="ten" type="text" class="form-control"
								placeholder="Tên Khách Hàng" />

						</div>
						<div class="form-group">
							<label for="manganhnghe">Mã Ngành Nghề</label>
							<form:input path="manganhnghe" type="number" class="form-control"
								placeholder="Mã Ngành Nghề" />

						</div>
						<div class="form-group">
							<label for="sotaikhoan">Số Tài Khoản</label>
							<form:input path="sotaikhoan" type="number" class="form-control"
								placeholder="Số Tài Khoản" />

						</div>
						<div class="form-group">
							<label for="diachi">Địa Chỉ</label>
							<form:input path="diachi" type="text" class="form-control"
								placeholder="Địa Chỉ" />

						</div>
						<div class="form-group">
							<label>Số Điện Thoại</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-phone"></i>
								</div>
								<input type="text" class="form-control" name="sodienthoai"
									value="${khachhang.sodienthoai}"
									data-inputmask="&quot;mask&quot;: &quot;999-999-99999&quot;"
									data-mask=""> <label id="_sodienthoai-error"
									class="error" style="display: none;"></label>
							</div>
							<label id="sodienthoai-error" class="error" for="sodienthoai"></label>
						</div>

						<div class="form-group">
							<label for="tendaidien">Tên Người Đại Diện</label>
							<form:input path="tendaidien" type="text" class="form-control"
								placeholder="Tên Người Đại Diện" />

						</div>
						<div class="form-group">
							<label>Số Điện Thoại Người Đại Diện</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-phone"></i>
								</div>
								<input type="text" class="form-control" name="dienthoaidaidien"
									value="${khachhang.dienthoaidaidien}"
									data-inputmask="&quot;mask&quot;: &quot;999-999-99999&quot;"
									data-mask=""> <label id="_sodienthoai-error"
									class="error" style="display: none;"></label>
							</div>
							<label id="dienthoaidaidien-error" class="error"
								for="dienthoaidaidien"></label>
						</div>
						<div class="form-group">
							<label>Ngày Sinh Người Đại Diện</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input name="ngaysinhnhatndd"
									value="<fmt:formatDate
                                                pattern="dd-MM-yyyy" value="${khachhang.ngaysinhnhatndd}"/>"
									type="text" class="form-control"
									data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
							</div>
							<label id="ngaysinhnhatndd-error" class="error"
								for="ngaysinhnhatndd"></label>
						</div>


						<div class="form-group">
							<label for="tenphutrach">Tên Người Phụ Trách</label>
							<form:input path="tenphutrach" type="text" class="form-control"
								placeholder="Tên Người Phụ Trách" />

						</div>
						<div class="form-group">
							<label>Số Điện Thoại Người Phụ Trách</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-phone"></i>
								</div>
								<input type="text" class="form-control" name="dienthoaiphutrach"
									value="${khachhang.dienthoaiphutrach}"
									data-inputmask="&quot;mask&quot;: &quot;999-999-99999&quot;"
									data-mask=""> <label id="_sodienthoai-error"
									class="error" style="display: none;"></label>
							</div>
							<label id="dienthoaiphutrach-error" class="error"
								for="dienthoaiphutrach"></label>
						</div>
						<div class="form-group">
							<label>Ngày Sinh Người Phụ Trách</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input name="ngaysinhphutrach"
									value="<fmt:formatDate
                                                pattern="dd-MM-yyyy" value="${khachhang.ngaysinhphutrach}"/>"
									type="text" class="form-control"
									data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
							</div>
							<label id="ngaysinhphutrach-error" class="error"
								for="ngaysinhphutrach"></label>
						</div>

						<div class="form-group">
							<label for="sogpkd">Số Giấy Phép Kinh Doanh</label>
							<form:input path="sogpkd" type="text" class="form-control"
								placeholder="Số Giấy Phép Kinh Doanh" />
							<label id="_sogpkd-error" class="error" style="display: none;"></label>
						</div>
						<div class="form-group">
							<label for="noicap">Nơi Cấp</label>
							<form:input path="noicap" type="text" class="form-control"
								placeholder="Nơi Cấp" />

						</div>

						<div class="form-group">
							<label>Ngày Cấp</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input name="ngaycap"
									value="<fmt:formatDate
                                                pattern="dd-MM-yyyy" value="${khachhang.ngaycap}"/>"
									type="text" class="form-control"
									data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
							</div>
							<label id="ngaycap-error" class="error" for="ngaycap"></label>
						</div>




						<div class="form-group">
							<label for="sotienchamsoc">Số Tiền Chăm Sóc</label>
							<div class="input-group">

								<input id="sotienchamsoc" name="sotienchamsoc_money" type="text"
									   value="<fmt:formatNumber type="number" pattern="###,###"
											value="${khachhang.sotienchamsoc}" />"
									   class="form-control" placeholder="Số Tiền Chăm Sóc" />
								<span class="input-group-addon">VNĐ</span>
								<label id="_sotienchamsoc-error" class="error"
									   style="display: none;"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="sotiendachamsoc">Số Tiền Đã Chăm Sóc</label>
							<div class="input-group">

								<input id="sotiendachamsoc" name="_money" type="text"
									   value="<fmt:formatNumber type="number" pattern="###,###"
											value="${khachhang.sotiendachamsoc}" />"
									   class="form-control" placeholder="Số Đã Tiền Chăm Sóc" />
								<span class="input-group-addon">VNĐ</span>
								<label id="_sotiendachamsoc-error" class="error"
									   style="display: none;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="diem">Điểm</label>
							<form:input path="diem" type="number" class="form-control"
								min="0" placeholder="Điểm" />
							<label id="_diem-error" class="error" style="display: none;"></label>
						</div>

						<div class="form-group">
							<label for="solanchamsoc">Số Lần Chăm Sóc</label>
							<form:input path="solanchamsoc" type="number" min="0"
								class="form-control" placeholder="Số Lần Chăm Sóc" />
							<label id="_solanchamsoc-error" class="error"
								style="display: none;"></label>
						</div>
						<div class="form-group">
							<label for="solandamphan">Số Lần Đàm Phán</label>
							<form:input path="solandamphan" type="number" min="0"
								class="form-control" placeholder="Số Lần Đàm Phán" />
							<label id="_solandamphan-error" class="error"
								style="display: none;"></label>
						</div>

						<div class="form-group">
							<label for="diemtiemnang">Điểm Tiềm Năng</label>
							<form:input path="diemtiemnang" type="number" min="0"
								class="form-control" placeholder="Điểm Tiềm Năng" />
							<label id="_diemtiemnang-error" class="error"
								style="display: none;"></label>
						</div>

						<div class="form-group">
							<label for="uutien">Ưu Tiên</label> <br> <label
								class="lb-flat-red"> <input value="co" type="radio"
								${khachhang.uutien == 'co' ? 'checked' : '' }
								name="uutien" class="flat-red" > Có
							</label> <label class="lb-flat-red"> <input value="khong"
							${khachhang.uutien == 'khong' ? 'checked' : '' }
								type="radio" name="uutien" class="flat-red"> Không
							</label>
						</div>
						<div class="form-group">
							<label for="ghichu">Ghi Chú</label>
							<form:textarea path="ghichu" type="text" class="form-control"
								placeholder="Ghi Chú" />
						</div>


					</div>
					<!-- /.box-body -->
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="box-footer">
						<c:if test="${khachhang.trangthai != 'deleted' }">
							<div class="box-footer">
								<button id="btn-submit" name="update" type="submit"
									class="btn btn-primary">Xác Nhận</button>
							</div>
						</c:if>
						<c:if test="${khachhang.trangthai == 'deleted' }">
							<div class="box-footer">

								<button id="btn-submit" name="deleted" type="submit"
									class="btn btn-danger">Xóa Vĩnh Viễn</button>
							</div>
						</c:if>
					</div>
				</form:form>
			</div>
			<!-- /.box -->




		</div>
	</div>
	<!-- /.row -->
</section>

