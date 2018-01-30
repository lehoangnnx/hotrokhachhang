<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<input hidden="" id="msg" value="${msg }"></input>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Feedback
		<%-- <small><a
			href="${contextPath }/admin/roles?status=active">Danh Sách Nhóm
				Hàng</a></small> <small><a class="btn btn-success"
			href="${contextPath }/admin/nhomhang/add">Thêm mới</a></small> --%>
	</h1>
	<ol class="breadcrumb">
		<a class="btn btn-success" href="${contextPath }/admin/feedback/add">Thêm
			mới</a>
		<%-- <li><a href="${contextPath }/"><i class="fa fa-dashboard"></i>
				Home</a></li>
		<li><a href="${contextPath }/roles">Quyền</a></li>
		<li class="active">Quản lý quyền</li> --%>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">

		<div class="col-xs-12">
			<ul class="nav nav-tabs">
				<li class="tablinks ${param.trangthai == 'active' ? 'active' : '' }">
					<a href="${contextPath }/admin/feedback?trangthai=active">Đã
						Kích Hoạt</a>
				</li>

				<li
					class="tablinks ${param.trangthai == 'deleted' ? 'active' : '' }">
					<a href="${contextPath }/admin/feedback?trangthai=deleted">Đã
						Xóa</a>
				</li>
			</ul>
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Danh Sách Feedback</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
				 <div class="table-responsive">
					<table id="example2" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>Feedback Nhân Viên</th>
								<th>Feedback Hàng Hóa</th>
								<th>Khách Hàng Feedback</th>
								<th>Ngày</th>
								<th>Thao tác</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="fb" items="${listFeedback }">
								<tr>
									<c:choose>
										<c:when test="${fb.nhanvienId == 0 }">
											<td>Không</td>
										</c:when>
										<c:otherwise>
											<c:forEach var="nv" items="${listNhanvien }">
												<c:if test="${fb.nhanvienId == nv.id }">
													<td>${nv.manhanvien }- ${nv.tennhanvien }</td>
												</c:if>
											</c:forEach>
										</c:otherwise>
									</c:choose>

									<c:choose>
										<c:when test="${fb.hanghoaId == 0 }">
											<td>Không</td>
										</c:when>
										<c:otherwise>
											<c:forEach var="hh" items="${listHanghoa }">
												<c:if test="${fb.hanghoaId == hh.id }">
													<td>${hh.mahang}- ${hh.tenhang}</td>
												</c:if>
											</c:forEach>
										</c:otherwise>
									</c:choose>


									<c:forEach var="kh" items="${listKhachhang }">
										<c:if test="${fb.khachhangId == kh.id }">
											<td>${kh.makh}- ${kh.ten}</td>
										</c:if>
									</c:forEach>


									<td><fmt:formatDate pattern="dd-MM-yyyy"
											value="${fb.ngaytao }" /></td>
									<td><a href="${contextPath }/admin/feedback/${fb.id}">
											<i style="color: blue;" class="fa fa-pencil fa-lg"
											aria-hidden="true" title="Sửa"> </i>
									</a>
									<c:if test="${fb.trangthai != 'deleted' }">
									 <a onclick="deleteOne(${fb.id});" href="#" data-toggle="modal"
										data-target="#myModal" style="color: red; margin-left: 10px;">
											<i class="fa fa-trash-o fa-lg" aria-hidden="true" title="Xóa"></i>
									</a>
									</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<th>Feedback Nhân Viên</th>
								<th>Feedback Hàng Hóa</th>
								<th>Khách Hàng Feedback</th>
								<th>Ngày</th>
								<th>Thao tác</th>
							</tr>
						</tfoot>
					</table>
					</div>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->



<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 style="text-align: center; font-weight: bold; color: red;"
					class="modal-title">Bạn Muốn Xóa ?</h4>
			</div>

			<div class="modal-footer">
				<form:form action="" method="delete">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<input value="" id="arrayId" hidden="" name="arrayId" />
					<button class="btn btn-danger" style="float: left;">Xóa</button>
				</form:form>


				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>