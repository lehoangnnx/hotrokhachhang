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
					<h3 class="box-title">Thêm Tài Khoản</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formTaiKhoan"
					action="${contextPath }/admin/taikhoan" method="post"
					modelAttribute="taikhoan">
					<form:hidden path="id" />
					<div class="box-body">
						<div class="form-group">
							<label for="username">User Name</label>
							<form:input path="username" type="text" class="form-control"
								placeholder="User Name" />
							<label id="_username-error" class="error" style="display: none;"></label>
						</div>



						<div class="form-group">
							<label for="email">Email</label>
							<form:input path="email" type="email" class="form-control"
								placeholder="Email" />
							<label id="_email-error" class="error" style="display: none;"></label>
						</div>
						<div class="form-group">
							<label for="matkhau">Mật Khẩu</label>
							<form:input path="matkhau" type="password" class="form-control"
								placeholder="Mật Khẩu" />

						</div>
						<div class="form-group">
							<label for="thongtinkhac">Thông Tin Khác</label>
							<form:textarea path="thongtinkhac" type="text" class="form-control"
								placeholder="Thông Tin Khác" />
						</div>

						<div class="form-group">
							<label> Nhân Viên</label> <select class="form-control select2"
								name="nhanvien" style="width: 100%;">

								<c:forEach var="nv" items="${listNhanvien }">
									<option value="${nv.id }">${nv.manhanvien}-
										${nv.tennhanvien}</option>
								</c:forEach>

							</select>
						</div>
						<%-- <div class="form-group">
							<label> Quyền</label> <select class="form-control select2"
								name="quyen" style="width: 100%;">

								<c:forEach var="q" items="${listQuyen }">
									<option value="${q.id }">${q.maquyen} - ${q.tenquyen}</option>
								</c:forEach>

							</select>
						</div> --%>
						<div class="form-group">
							<label>Quyền</label> <select class="form-control select2"
								multiple="multiple" data-placeholder="Select a State" name="quyen"
								style="width: 100%;">
								<c:forEach var="q" items="${listQuyen }">
									<option value="${q.id }">${q.maquyen} - ${q.tenquyen}</option>
								</c:forEach>
							</select>
							<label id="quyen-error" class="error" for="quyen" style="display: none;"></label>
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

