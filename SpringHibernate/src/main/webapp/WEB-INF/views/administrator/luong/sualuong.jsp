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
					<h3 class="box-title">Sửa Lương</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formLuong"
					action="${contextPath }/admin/luong" method="patch" modelAttribute="luong">
					<form:hidden path="id"/>
					<div class="box-body">
						
						
						<div class="form-group">
							<label>Nhân Viên</label> <select class="form-control select2" id="nhanvien"
								name="nhanvien" style="width: 100%;">

								<c:forEach var="nv" items="${listNhanvien }">
									<option
									${luong.nhanvien.id == nv.id ? 'selected' : '' }
									 value="${nv.id }">${nv.manhanvien} - ${nv.tennhanvien }</option>
								</c:forEach>

							</select>
							<label id="error" class="error" style="display: none;" ></label>
						</div>
						<div class="form-group">
							<label for="luong">Lương</label> <form:input path="luong" min="0" 
								type="number" class="form-control" 
								placeholder="Lương" />
								
						</div>
						<div class="form-group">
							<label for="thuong">Thưởng</label> <form:input path="thuong" min="0"
								type="number" class="form-control"
								placeholder="Thưởng" />
								
						</div>
						<div class="form-group">
							<label>Tháng</label> <select class="form-control select2" id="thang"
								name="thang" style="width: 100%;">
								
								<c:forEach var="i" begin="1" end="12">
									<option
									${luong.thang == i ? 'selected' : '' }
									 value="${i }">Tháng - ${i}</option>
								</c:forEach>

							</select>
						</div>
						<div class="form-group">
							<label>Năm</label> <select class="form-control select2" id="nam"
								name="nam" style="width: 100%;">

								<c:forEach var="i" begin="1990" end="2030">
									<option
									${luong.nam == i ? 'selected' : '' }
									 value="${i }">Năm - ${i}</option>
								</c:forEach>

							</select>
						</div>
						<div class="form-group">
							<label for="chietkhau">Chiết Khấu</label> <form:input path="chietkhau"
								type="number" class="form-control"
								placeholder="Chiết Khấu" />
								
						</div>
						<div class="form-group">
							<label for="trangthai">Trạng Thái</label> <br> <label
								class="lb-flat-red"> <input value="datraluong" type="radio"
								${luong.trangthai == 'datraluong' ? 'checked' : '' }
								name="trangthai" class="flat-red"> Đã Trả Lương
							</label> <label class="lb-flat-red"> <input value="chuatraluong"
								${luong.trangthai == 'chuatraluong' ? 'checked' : '' }
								type="radio" name="trangthai" class="flat-red" >
								Chưa Trả Lương
							</label>
						</div>
						<div class="form-group">
							<label for="ghichu">Ghi Chú</label> <form:input path="ghichu" type="text"
								class="form-control"
								placeholder="Ghi Chú" />
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

