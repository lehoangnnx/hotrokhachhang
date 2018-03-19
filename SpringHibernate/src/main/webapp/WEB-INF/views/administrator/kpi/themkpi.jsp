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
					<h3 class="box-title">Thêm KPI</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formKpi"
					action="${contextPath }/admin/kpi" method="post" modelAttribute="kpi">
					<form:hidden path="id"/>
					<div class="box-body">
						<div class="form-group">
							<label for="ten">Tên </label> <form:input path="ten"
								type="text" class="form-control" 
								placeholder="Tên" />
								<label id="error" class="error" style="display: none;" ></label>
						</div>
						<div class="form-group">
							<label for="kieukpi">Kiểu Tiêu Chí</label> <br> <label
								class="lb-flat-red"> <input value="so" type="radio"
															name="kieukpi" class="flat-red" checked> Số
						</label>
							<label
									class="lb-flat-red"> <input value="phantram" type="radio"
																name="kieukpi" class="flat-red">Phần Trăm
							</label>
							<label
									class="lb-flat-red"> <input value="tien" type="radio"
																name="kieukpi" class="flat-red"> Tiền
							</label>
						</div>
						<div class="form-group">
							<label for="moTa">Mô tả</label> <form:textarea path="mota" type="text"
								class="form-control" id="mota" name="mota"
								placeholder="Mô Tả" />
						</div>


					</div>
					<!-- /.box-body -->
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="box-footer">
						<button id="btn-submit" type="submit" class="btn btn-primary">Xác Nhận</button>
					</div>
				</form:form>
			</div>
			<!-- /.box -->




		</div>
	</div>
	<!-- /.row -->
</section>

