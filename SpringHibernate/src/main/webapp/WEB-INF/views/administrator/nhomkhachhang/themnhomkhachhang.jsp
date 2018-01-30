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
					<h3 class="box-title">Thêm Nhóm Khách Hàng</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formNhomKhachHang"
					action="${contextPath }/admin/nhomkhachhang" method="post" modelAttribute="nhomkhachhang">
					<form:hidden path="id"/>
					<div class="box-body">
						<div class="form-group">
							<label for="tennhom">Tên Nhóm Khách Hàng</label> <form:input path="tennhom"
								type="text" class="form-control" 
								placeholder="Tên Nhóm Khách Hàng" />
								<label id="error" class="error" style="display: none;" ></label>
						</div>
						<div class="form-group">
							<label for="diem">Điểm</label> <form:input path="diem" min="0" value ="0"
								type="number" class="form-control" 
								placeholder="Điểm" />
								
						</div>
						<div class="form-group">
							<label for="phantram">Phần Trắm</label> <form:input path="phantram" min="0" value ="0"
								type="number" class="form-control"
								placeholder="Phần Trăm" />
								
						</div>
						
						<div class="form-group">
							<label for="phantram">Số Điểm/Tiền</label> <br>
							<div class="col-md-5"><form:input  path="sodiemtrentien" min="0" value ="0"
								type="number" class="form-control" 
								placeholder="Số Điểm" /></div>
								<span class="col-md-2">/</span>
								<div class="col-md-5"><form:input path="sotientrendiem" min="0" value ="0"
								type="number" class="form-control"
								placeholder="Số Tiền" /></div>
								
						</div>
						
						<div class="form-group">
							<label for="phantramtien">Phần Trăm Trên Hóa Đơn</label> <form:input path="phantramtien"
								type="number" class="form-control" min="0" value ="0"
								placeholder="Phần Trăm Trên Hóa Đơn" />
								
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

