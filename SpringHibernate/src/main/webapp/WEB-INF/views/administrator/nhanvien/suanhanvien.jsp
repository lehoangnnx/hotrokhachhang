<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<section class="content">

	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">Sửa Nhân Viên</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formNhanVien"
					action="${contextPath }/admin/nhanvien" method="patch"
					modelAttribute="nhanvien">
					<form:hidden path="id" />
					<div class="box-body">
						<div class="form-group">
							<label for="manhanvien">Mã Nhân Viên</label>
							<form:input path="manhanvien" type="text" class="form-control"
								placeholder="Mã Nhân Viên" />
							<label id="_manhanvien-error" class="error"
								style="display: none;"></label>
						</div>

						<div class="form-group">
							<label for="tennhanvien">Tên Nhân Viên</label>
							<form:input path="tennhanvien" type="text" class="form-control"
								placeholder="Tên Nhân Viên" />
							<label id="_tennhanvien-error" class="error"
								style="display: none;"></label>
						</div>



						<div class="form-group">
							<label>Số Chứng Minh Nhân Dân</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-credit-card"></i>
								</div>
								<input type="text" class="form-control" name="socmnd"
									value="${nhanvien.socmnd}"
									data-inputmask="&quot;mask&quot;: &quot;999-999-999&quot;"
									data-mask="">
							</div>
							<label id="_socmnd-error" class="error" style="display: none;"></label>
							<label id="socmnd-error" class="error" for="socmnd"></label>
						</div>
						<div class="form-group">
							<label for="noicap">Nơi Cấp</label>
							<form:input path="noicap" type="text" class="form-control"
								placeholder="Nơi Cấp" />
							<label id="_noicap-error" class="error" style="display: none;"></label>
						</div>

						<div class="form-group">
							<label>Ngày Cấp</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input name="ngaycap"
									value="<fmt:formatDate
                                                pattern="dd-MM-yyyy" value="${nhanvien.ngaycap}"/>"
									type="text" class="form-control"
									data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
							</div>
							<label id="ngaycap-error" class="error" for="ngaycap"></label>
						</div>


						<div class="form-group">
							<label>Số Điện Thoại</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-phone"></i>
								</div>
								<input type="text" class="form-control" name="sodienthoai"
									value="${nhanvien.sodienthoai}"
									data-inputmask="&quot;mask&quot;: &quot;999-999-99999&quot;"
									data-mask=""> <label id="_sodienthoai-error"
									class="error" style="display: none;"></label>
							</div>
							<label id="sodienthoai-error" class="error" for="sodienthoai"></label>
						</div>
						<div class="form-group">
							<label for="diachi">Địa Chỉ</label>
							<form:input path="diachi" type="text" class="form-control"
								placeholder="Địa Chỉ" />
							<label id="_diachi-error" class="error" style="display: none;"></label>
						</div>
						<div class="form-group">
							<label>Ngày Vào Làm</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input name="ngayvaolam"
									value="<fmt:formatDate
                                                pattern="dd-MM-yyyy" value="${nhanvien.ngayvaolam}"/>"
									type="text" class="form-control"
									data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
							</div>
							<label id="ngayvaolam-error" class="error" for="ngayvaolam"></label>
						</div>

						<div class="form-group">
							<label for="loainhanvien">Loại Nhân Viên</label>
							<form:input path="loainhanvien" type="text" class="form-control"
								placeholder="Loại Nhân Viên" />
							<label id="_loainhanvien-error" class="error"
								style="display: none;"></label>
						</div>
						<div class="form-group">
							<label for="luong">Lương</label>
							<form:input path="luong" min="0" type="number"
								class="form-control" placeholder="Lương" />

						</div>
						<div class="form-group">
							<label for="chietkhau">Chiết Khấu</label>
							<form:input path="chietkhau" type="number" class="form-control"
								placeholder="Chiết Khấu" />

						</div>
						<div class="form-group">
							<label for="thongtinkhac">Thông Tin Khác</label>
							<form:input path="thongtinkhac" type="text" class="form-control"
								placeholder="Thông Tin Khác" />
						</div>
						<div class="form-group">
							<label for="ghichu">Ghi Chú</label>
							<form:input path="ghichu" type="text" class="form-control"
								placeholder="Ghi Chú" />
						</div>
						<div class="form-group">
							<label>Bộ Phận</label> <select class="form-control select2"
								name="bophan" style="width: 100%;">

								<c:forEach var="bp" items="${listBophan }">
									<option value="${bp.id }">${bp.tenbophan}</option>
								</c:forEach>

							</select>
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

