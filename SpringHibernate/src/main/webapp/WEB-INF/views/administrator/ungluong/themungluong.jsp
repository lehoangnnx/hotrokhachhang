<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<fmt:formatDate var="month" value="${now}" pattern="MM" />

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
					<h3 class="box-title">Thêm Ứng Lương</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formUngLuong"
					action="${contextPath }/admin/ungluong" method="post"
					modelAttribute="ungluong">
					<form:hidden path="id" />
					<div class="box-body">


						<div class="form-group">
							<label>Nhân Viên</label> <select class="form-control select2"
								id="nhanvien" name="nhanvien" style="width: 100%;">

								<c:forEach var="nv" items="${listNhanvien }">
									<option value="${nv.id }">${nv.manhanvien}-
										${nv.tennhanvien }</option>
								</c:forEach>

							</select> <label id="error" class="error" style="display: none;"></label>
						</div>
						<div class="form-group">
							<label for="sotienung">Số Tiền Ứng</label>
							<div class="input-group">

							<input id="sotienung" name="sotienung_money" type="text"  value="0"
								class="form-control" placeholder="Số Tiền Ứng" />
								<span class="input-group-addon">VNĐ</span>
							</div>
							<label id="_sotienung-error" class="error" style="display: none;"></label>
						</div>



						<div class="form-group">
							<label>Ngày Ứng</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input name="ngayung" id="ngayung" readonly
									   value="<fmt:formatDate
                                                pattern="dd-MM-yyyy" value="${ungluong.ngayung}"/>"
									   type="text" class="form-control"
									   data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
							</div>
							<label id="ngaylap-error" class="error" for="ngayung"></label>
						</div>
						<div class="form-group">
							<label for="noidung">Nội Dung</label>
							<form:textarea path="noidung" type="text" class="form-control"
										   placeholder="Nội Dung" />
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

