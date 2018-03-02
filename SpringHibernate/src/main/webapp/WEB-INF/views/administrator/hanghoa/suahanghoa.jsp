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
					<form:hidden path="trangthai" />
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
							<div class="input-group">

								<form:input path="gianhap" type="number" class="form-control" min="0"
											placeholder="Giá Nhập" />
								<span class="input-group-addon">VNĐ</span>
							</div>
						</div>
						<div class="form-group">
							<label for="giaban">Giá Bán</label>
							<div class="input-group">

								<form:input path="giaban" type="number" class="form-control" min="0"
											placeholder="Giá Bán" />
								<span class="input-group-addon">VNĐ</span>
								<label id="_giaban-error" class="error" style="display: none;"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="giakhuyenmai">Giá Khuyến Mãi</label>
							<div class="input-group">

								<form:input path="giakhuyenmai" type="number" min="0"
											class="form-control" placeholder="Giá Khuyến Mãi" />
								<span class="input-group-addon">VNĐ</span>
								<label id="_giakhuyenmai-error" class="error"
									   style="display: none;"></label>
							</div>
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
							<label id="nhomhang-error" class="error" for="nhomhang" style="display: none;"></label>
						</div>




						<div class="form-group">
							<label for="mota">Mô tả</label>
							<form:textarea path="mota" type="text" class="form-control"
								id="mota" name="mota" placeholder="Mô Tả" />
						</div>


					</div>
					<!-- /.box-body -->
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="box-footer">
						<c:if test="${hanghoa.trangthai != 'deleted' }">
							<div class="box-footer">
								<button id="btn-submit" name="update" type="submit"
									class="btn btn-primary">Xác Nhận</button>
							</div>
						</c:if>
						<c:if test="${hanghoa.trangthai == 'deleted' }">
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

