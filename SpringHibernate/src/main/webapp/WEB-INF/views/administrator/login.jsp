<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="box box-login">
	<div class="box-header with-border">
		<h3 class="box-title">Đăng nhập quản trị</h3>
	</div>
	<!-- /.box-header -->
	<!-- form start -->
	<form action="/login" method="post" id="login-form"
		class="form-horizontal">
		<div class="box-body">
			<div class="form-group">
				<label for="username" class="col-sm-2 control-label">Tên
					đăng nhập</label>

				<div class="col-sm-10">
					<input type="text" class="form-control" name="username"
						id="username" placeholder="Tên đăng nhập">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label">Mật
					khẩu</label>

				<div class="col-sm-10">
					<input type="password" class="form-control" id="password"
						name="password" placeholder="Password">
				</div>
			</div>
			<div class="form-group clearfix">
				<span id="msgerror"></span>
				<c:if
					test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
					<div class="error">
						* Sai Tên Đăng Nhập Hoặc Mật Khẩu
					</div>
				</c:if>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox">
						<label> <input type="checkbox"> Ghi nhớ
						</label>
					</div>
				</div>
			</div>
		</div>
		<!-- /.box-body -->
		<div class="box-footer">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<button type="submit" class="btn btn-danger btn-block">Đăng
				nhập</button>


		</div>
		<!-- /.box-footer -->
	</form>
</div>


