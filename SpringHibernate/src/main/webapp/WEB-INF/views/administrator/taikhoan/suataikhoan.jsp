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
					<h3 class="box-title">Sửa Quyền</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formQuyen"
					action="${contextPath }/admin/quyen" method="patch"
					modelAttribute="quyen">
					<form:hidden path="id" />
					<div class="box-body">
						<div class="form-group">
							<label for="maquyen">Mã Quyền</label>
							<form:input path="maquyen" type="text" class="form-control"
								placeholder="Mã Quyền" />
							<label id="error" class="error" style="display: none;"></label>
						</div>
						


						<div class="form-group">
							<label for="tenquyen">Tên Quyền</label>
							<form:input path="tenquyen" type="text" class="form-control"
								placeholder="Tên Quyền" />

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

