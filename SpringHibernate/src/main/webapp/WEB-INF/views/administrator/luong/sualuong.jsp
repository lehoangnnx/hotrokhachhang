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
					action="${contextPath }/admin/luong" method="patch"
					modelAttribute="luong">
					<form:hidden path="id" />
					<div class="box-body">


						<div class="form-group">
							<label>Nhân Viên</label> <select class="form-control select2"
								id="nhanvien" name="nhanvien" style="width: 100%;">

								<c:forEach var="nv" items="${listNhanvien }">
									<option ${luong.nhanvien.id == nv.id ? 'selected' : '' }
										value="${nv.id }">${nv.manhanvien}-${nv.tennhanvien }</option>
								</c:forEach>

							</select> <label id="error" class="error" style="display: none;"></label>
						</div>
						<div class="form-group">
							<label for="luong">Lương</label>
							<div class="input-group">

								<input id="luong" name="luong_money" type="text"
									   value="<fmt:formatNumber type="number" pattern="###,###"
									    value="${luong.luong}"/>"
									   class="form-control" placeholder="Lương" />
								<span class="input-group-addon">VNĐ</span>
							</div>
							<label id="_luong-error" class="error" style="display: none;"></label>
						</div>
						<div class="form-group">
							<label for="thuong">Thưởng</label>
							<div class="input-group">

								<input id="thuong" name="thuong_money" type="text"
									   value="<fmt:formatNumber type="number" pattern="###,###"
									    value="${luong.thuong}"/>"
									   class="form-control" placeholder="Thưởng" />
								<span class="input-group-addon">VNĐ</span>
							</div>

						</div>
						<div class="form-group">
							<label for="thuongcuahoadon_money">Thưởng Của Hóa Đơn</label>
							<div class="input-group">
								<form:hidden path="thuongcuahoadon" />
								<input id="thuongcuahoadon_money" name="thuongcuahoadon_money" type="text" readonly
									   value="<fmt:formatNumber type="number" pattern="###,###"
									    value="${luong.thuongcuahoadon}"/>"
									   class="form-control" placeholder="Thưởng Của Hóa Đơn" />
								<span class="input-group-addon">VNĐ</span>
							</div>

						</div>
						<div class="form-group">
							<label>Tháng</label> <select class="form-control select2"
								id="thang" name="thang" style="width: 100%;">

								<c:forEach var="i" begin="01" end="12">

									<option ${luong.thang == i ? 'selected' : '' }
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

								<c:forEach var="i" begin="1990" end="2030">
									<option ${luong.nam == i ? 'selected' : '' } value="${i }">Năm
										- ${i}</option>
								</c:forEach>

							</select>
						</div>

						<div class="form-group">
							<label for="trangthai">Trạng Thái</label> <br> <label
								class="lb-flat-red"> <input value="datraluong"
								type="radio"
								${luong.trangthai == 'datraluong' ? 'checked' : '' }
								name="trangthai" class="flat-red"> Đã Trả Lương
							</label> <label class="lb-flat-red"> <input value="chuatraluong"
								${luong.trangthai == 'chuatraluong' ? 'checked' : '' }
								type="radio" name="trangthai" class="flat-red"> Chưa Trả
								Lương
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
						<c:if test="${luong.trangthai != 'deleted' }">
							<div class="box-footer">
								<button id="btn-submit" name="update" type="submit"
									class="btn btn-primary">Xác Nhận</button>
							</div>
						</c:if>
						<c:if test="${luong.trangthai == 'deleted' }">
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

