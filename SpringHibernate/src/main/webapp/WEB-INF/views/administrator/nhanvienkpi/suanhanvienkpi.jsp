<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
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
					<h3 class="box-title">Sửa Nhân Viên KPI</h3>
					<button class="btn btn-info pull-right" type="button" onclick="goBack();">Quay Lại</button>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form:form role="form" id="formNhanVienKpi"
					action="${contextPath }/admin/nhanvienkpi" method="patch"
					modelAttribute="nhanvienkpi">
					<form:hidden path="id" />
					<div class="box-body">

						<%--<div class="form-group">
							<label>Nhân Viên</label> <select class="form-control select2" disabled
								name="nhanvien" style="width: 100%;">

								<c:forEach var="nv" items="${listNhanvien}">
									<option  ${nhanvienkpi.nhanvien.id == nv.id ? 'selected' : ''}
											value="${nv.id }">${nv.manhanvien}-
										${nv.tennhanvien }</option>
								</c:forEach>

							</select>
						</div>--%>
							<div class="form-group">
								<label>Nhân Viên</label> <select class="form-control select2" id="nhanvien"
																 name="nhanvien" style="width: 100%;">


									<option
											value="${nhanvienkpi.nhanvien.id }">${nhanvienkpi.nhanvien.manhanvien}-
											${nhanvienkpi.nhanvien.tennhanvien }</option>


							</select>
							</div>
						<div class="form-group">
							<label>KPI</label> <select class="form-control select2" id="kpi"
								name="kpi" style="width: 100%;">

								<c:forEach var="kpi" items="${listKpi}">
									<option ${nhanvienkpi.kpi.id == kpi.id ? 'selected' : ''}
											value="${kpi.id }">${kpi.ten}</option>
								</c:forEach>

							</select>
						</div>

							<c:if test="${nhanvienkpi.kpi.kieukpi == 'so' || nhanvienkpi.kpi.kieukpi == 'phantram'}">

								<div class="form-group">
									<label for="chitieudangky">Chỉ Tiêu Đăng Ký</label>
									<div class="input-group">
										<input id="chitieudangky" name="_chitieudangky" type="number" class="form-control"
											   value="${nhanvienkpi.chitieudangky}" placeholder="Chỉ Tiêu Đăng Ký"/>

										<span id="addon" class="input-group-addon">
                                            <c:if test="${nhanvienkpi.kpi.kieukpi == 'so'}">
												SỐ
											</c:if>
                                              <c:if test="${nhanvienkpi.kpi.kieukpi == 'phantram'}">
												  %
											  </c:if>
                                        </span>


									</div>
									<label id="chitieudangky-error" class="error" for="chitieudangky"></label>
								</div>
							</c:if>
							<c:if test="${nhanvienkpi.kpi.kieukpi == 'tien'}">
								<div class="form-group">
									<label for="chitieudangky">Chỉ Tiêu Đăng Ký</label>
									<div class="input-group">
										<input id="chitieudangky" name="_chitieudangky" type="text" class="form-control"
											   onkeyup="showNumberToString();"
											   value="<fmt:formatNumber type="number" pattern="###,###"
														  value="${nhanvienkpi.chitieudangky }" />" placeholder="Chỉ Tiêu Đăng Ký"/>
										<span id="addon" class="input-group-addon">VNĐ</span>

									</div>
									<label id="chitieudangky-error" class="error" for="chitieudangky"></label>
								</div>
							</c:if>
						<div class="form-group">
							<label>Ngày Hoàn Thành</label>

							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input name="ngayhoanthanh"
									value="<fmt:formatDate
                                                pattern="dd-MM-yyyy" value="${nhanvienkpi.ngayhoanthanh}"/>"
									type="text" class="form-control"
									data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
							</div>
							<label id="ngayhoanthanh-error" class="error" for="ngayhoanthanh"></label>
						</div>
						<security:authorize access="hasAnyRole('ADMIN')">

							<%--<div class="form-group">
								<label for="mucdohoanthanh">Mức Độ Hoàn Thành (%)</label>
								<div class="input-group">

									<form:input path="mucdohoanthanh" type="number" min="0"
												 class="form-control" placeholder="Mức Độ Hoàn Thành" />
									<span class="input-group-addon">%</span>
								</div>
							</div>--%>



							<div class="form-group">
								<label for="trangthai">Trạng Thái</label> <br> <label
									class="lb-flat-red"> <input value="active" type="radio"
									${nhanvienkpi.trangthai == 'active' ? 'checked' : '' }
									name="trangthai" class="flat-red"> Kích Hoạt
								</label> <label class="lb-flat-red"> <input value="inactive"
									${nhanvienkpi.trangthai == 'inactive' ? 'checked' : '' }
									type="radio" name="trangthai" class="flat-red"> Chưa
									Kích Hoat
								</label>
							</div>
						</security:authorize>
						<div class="form-group">
							<label for="mota">Mô tả</label>
							<form:textarea path="mota" type="text" class="form-control"
								 placeholder="Mô Tả" />
						</div>

							<div class="form-group">
								<label id="error" class="error" style="display: none;" ></label>
							</div>
					</div>
					<!-- /.box-body -->
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="box-footer">
						<c:if test="${nhanvienkpi.trangthai != 'deleted' }">

								<button id="btn-submit" name="update" type="button" onclick="checkchitieudangky();"
									class="btn btn-primary">Xác Nhận</button>

						</c:if>
						<c:if test="${nhanvienkpi.trangthai == 'deleted' }">


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

