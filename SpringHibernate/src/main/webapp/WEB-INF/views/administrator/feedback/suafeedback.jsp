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
					<h3 class="box-title">Sửa Feedback</h3>
					<button class="btn btn-info pull-right" type="button" onclick="goBack();">Quay Lại</button>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formFeedBack"
					action="${contextPath }/admin/feedback" method="patch"
					modelAttribute="feedback">
					<form:hidden path="id" />
					<form:hidden path="trangthai" />
					<div class="box-body">
						<div class="form-group">
							<label>Feedback Nhân Viên</label> <select class="form-control select2"
								name="nhanvienId" style="width: 100%;">
								<option
								${feedback.nhanvienId == 0 ? 'selected' : '' }
								 value="0">Không</option>
								<c:forEach var="nv" items="${listNhanvien }">
									<option 
									${feedback.nhanvienId == nv.id  ? 'selected' : '' }
									value="${nv.id }">${nv.manhanvien} - ${nv.tennhanvien }</option>
								</c:forEach>

							</select>
						</div>
						<div class="form-group">
							<label>Feedback Hàng Hóa</label> <select class="form-control select2"
								name="hanghoaId" style="width: 100%;">
								<option 
								${feedback.hanghoaId == 0 ? 'selected' : '' }
								value="0">Không</option>
								<c:forEach var="hh" items="${listHanghoa }">
									<option
									${feedback.hanghoaId == hh.id ? 'selected' : '' }
									 value="${hh.id }">${hh.mahang} - ${hh.tenhang }</option>
								</c:forEach>

							</select>
						</div>
						
						<div class="form-group">
							<label>Khách Hàng Feedback</label> <select class="form-control select2"
								name="khachhangId" style="width: 100%;">
								
								<c:forEach var="kh" items="${listKhachhang }">
									<option
									${feedback.khachhangId == kh.id ? 'selected' : '' }
									 value="${kh.id }">${kh.makh} - ${kh.ten }</option>
								</c:forEach>

							</select>
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
						<c:if test="${feedback.trangthai != 'deleted' }">

								<button id="btn-submit" name="update" type="submit"
									class="btn btn-primary">Xác Nhận</button>


						</c:if>
						<c:if test="${feedback.trangthai == 'deleted' }">


								<button id="btn-submit" name="deleted" type="submit"
									class="btn btn-danger">Xóa Vĩnh Viễn</button>

						</c:if>
						<button class="btn btn-info pull-right" type="button" onclick="goBack();">Quay Lại</button>
					</div>
				</form:form>
			</div>
			<!-- /.box -->




		</div>
	</div>
	<!-- /.row -->
</section>

