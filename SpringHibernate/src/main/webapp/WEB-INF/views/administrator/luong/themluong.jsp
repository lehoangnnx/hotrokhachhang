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
					<h3 class="box-title">Thêm Lương</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formLuong"
					action="${contextPath }/admin/luong" method="post"
					modelAttribute="luong">
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
							<label for="luong">Lương</label>
							<div class="input-group">

							<form:input path="luong" min="0" type="number"  value="0"
								class="form-control" placeholder="Lương" />
								<span class="input-group-addon">VNĐ</span>
							</div>
						</div>
						<div class="form-group">
							<label for="thuong">Thưởng</label>
							<div class="input-group">

							<form:input path="thuong" min="0" type="number"  value="0"
								class="form-control" placeholder="Thưởng" />
								<span class="input-group-addon">VNĐ</span>
							</div>

						</div>
						<div class="form-group">
							<label>Tháng</label> <select class="form-control select2"
								id="thang" name="thang" style="width: 100%;">

								<c:forEach var="i" begin="1" end="12">
									<option ${ i == month ? "selected" : ""}
											value="<fmt:formatNumber value="${i}" type="number"

											minIntegerDigits="2" />">Tháng - 
										<fmt:formatNumber value="${i}" type="number"
											minIntegerDigits="2" />
									</option>
								</c:forEach>

							</select>
						</div>

						<div class="form-group">
							<label>Năm</label> <select class="form-control select2" id="nam"
								name="nam" style="width: 100%;">

								<c:forEach  var="i" begin="1918" end="2118">
									<option ${ i == year ? "selected" : ""} value="${i }">Năm - ${i}</option>
								</c:forEach>

							</select>
						</div>

						<div class="form-group">
							<label for="trangthai">Trạng Thái</label> <br> <label
								class="lb-flat-red"> <input value="datraluong"
								type="radio" name="trangthai" class="flat-red"> Đã Trả
								Lương
							</label> <label class="lb-flat-red"> <input value="chuatraluong"
								type="radio" name="trangthai" class="flat-red" checked>
								Chưa Trả Lương
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
						<button id="btn-submit" type="button" class="btn btn-primary">Xác
							Nhận</button>
					</div>
				</form:form>
			</div>
			<!-- /.box -->




		</div>
	</div>
	<!-- /.row -->
</section>

