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
					<h3 class="box-title">Sửa Tiêu Chí Chăm Sóc</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formTieuChiChamSoc"
					action="${contextPath }/admin/tieuchichamsoc" method="patch" modelAttribute="tieuchichamsoc">
					<form:hidden path="id"/>
					<div class="box-body">
						<div class="form-group">
							<label for="tentieuchi">Tên Tiêu Chí</label> <form:input path="tentieuchi"
								type="text" class="form-control" 
								placeholder="Tên Tiêu Chí" />
								<label id="error" class="error" style="display: none;" ></label>
						</div>
						<div class="form-group">
							<label for="kieutieuchi">Kiểu Tiêu Chí</label> <br> <label
								class="lb-flat-red"> <input value="so" type="radio"
								${tieuchichamsoc.kieutieuchi == 'so' ? 'checked' : '' }
								name="kieutieuchi" class="flat-red" > Số 
							</label>
							<label
								class="lb-flat-red"> <input value="cokhong" type="radio"
								${tieuchichamsoc.kieutieuchi == 'cokhong' ? 'checked' : '' }
								name="kieutieuchi" class="flat-red"> Có Không
							</label>
							<label
								class="lb-flat-red"> <input value="tien" type="radio"
								${tieuchichamsoc.kieutieuchi == 'tien' ? 'checked' : '' }
								name="kieutieuchi" class="flat-red"> Tiền
							</label>
						</div>
						
						<div class="form-group">
							<label for="khac">Khác</label> <form:textarea path="khac" type="text"
								class="form-control" 
								placeholder="Khác" />
						</div>
						<div class="form-group">
							<label for="mota">Mô tả</label> <form:textarea path="mota" type="text"
								class="form-control" 
								placeholder="Mô Tả" />
						</div>


					</div>
					<!-- /.box-body -->
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="box-footer">
						<c:if test="${tieuchichamsoc.trangthai != 'deleted' }">
							<div class="box-footer">
								<button id="btn-submit" name="update" type="submit"
									class="btn btn-primary">Xác Nhận</button>
							</div>
						</c:if>
						<c:if test="${tieuchichamsoc.trangthai == 'deleted' }">
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

