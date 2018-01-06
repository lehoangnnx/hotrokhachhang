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
					<h3 class="box-title">Sửa Hàng Hóa</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formHangHoa"
					action="${contextPath }/admin/hanghoa" method="patch"
					modelAttribute="hanghoa">
					<form:hidden path="id" />
					<div class="box-body">
						<div class="form-group">
							<label for="mahang">Mã Hàng</label>
							<form:input path="mahang" type="text" class="form-control"
								placeholder="Mã Hàng" />
							<label id="error" class="error" style="display: none;"></label>
						</div>

						<div class="form-group">
							<label for="tenhang">Tên Hàng</label>
							<form:input path="tenhang" type="text" class="form-control"
								placeholder="Tên Nhóm Hàng" />

						</div>

						<div class="form-group">
							<label for="gianhap">Giá Nhập</label>
							<form:input path="gianhap" type="number" class="form-control"
								placeholder="Giá Nhập" />

						</div>
						<div class="form-group">
							<label for="giaban">Giá Bán</label>
							<form:input path="giaban" type="number" class="form-control"
								placeholder="Giá Bán" />
							<label id="_giaban-error" class="error" style="display: none;"></label>
						</div>
						<div class="form-group">
							<label for="giakhuyenmai">Giá Khuyến Mãi</label>
							<form:input path="giakhuyenmai" type="number"
								class="form-control" placeholder="Giá Khuyến Mãi" />
							<label id="_giakhuyenmai-error" class="error"
								style="display: none;"></label>
						</div>

						<div class="form-group">
							<label for="donvitinh">Đơn Vị Tính</label>
							<form:input path="donvitinh" type="text" class="form-control"
								placeholder="Đơn Vị Tính" />

						</div>

						<div class="form-group">
							<label> Nhóm Hàng</label> <select class="form-control select2"
								name="nhomhang" style="width: 100%;">

								<c:forEach var="nh" items="${listNhomhang }">
									<option ${nh.id == hanghoa.nhomhang.id ? 'selected' : '' } 
									value="${nh.id }">${nh.manhom }-${nh.tennhom }</option>
								</c:forEach>

							</select>
						</div>




						<div class="form-group">
							<label for="mota">Mô tả</label>
							<form:input path="mota" type="text" class="form-control"
								id="mota" name="mota" placeholder="Mô Tả" />
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

