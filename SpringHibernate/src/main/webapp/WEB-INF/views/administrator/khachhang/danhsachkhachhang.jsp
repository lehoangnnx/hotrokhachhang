<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<style>
	.changetext {
		font-weight: bold;
		font-size: 15px;
		color: green;
	}
</style>
<input hidden="" id="msg" value="${msg }"></input>
<!-- Content Header (Page header) -->
<%--<section class="content-header">
	<h1>
		Khách Hàng
		&lt;%&ndash; <small><a
			href="${contextPath }/admin/roles?status=active">Danh Sách Khách
				Hàng</a></small> <small><a class="btn btn-success"
			href="${contextPath }/admin/khachhang/add">Thêm mới</a></small> &ndash;%&gt;
	</h1>
	<ol class="breadcrumb">
		<a class="btn btn-success" href="${contextPath }/admin/khachhang/add">Thêm
			mới</a>
		&lt;%&ndash; <li><a href="${contextPath }/"><i class="fa fa-dashboard"></i>
				Home</a></li>
		<li><a href="${contextPath }/roles">Quyền</a></li>
		<li class="active">Quản lý quyền</li> &ndash;%&gt;
	</ol>
</section>--%>

<!-- Main content -->
<section class="content">
	<div class="row">

		<div class="col-xs-12">
			<ul class="nav nav-tabs">
				<li class="tablinks ${param.trangthai == 'active' ? 'active' : '' }">
					<a
					href="${contextPath }/admin/khachhang?trangthai=active&loaikhachhang=0&nhomkhachhang=0&limit=100&page=1">Đã
						Kích Hoạt</a>
				</li>

				<li
					class="tablinks ${param.trangthai == 'deleted' ? 'active' : '' }">
					<a
					href="${contextPath }/admin/khachhang?trangthai=deleted&loaikhachhang=0&nhomkhachhang=0&limit=100&page=1">Đã
						Xóa</a>
				</li>
			</ul>
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Danh Sách Khách Hàng</h3>
					<a class="btn btn-success pull-right" href="${contextPath }/admin/khachhang/add">Thêm
						mới</a>
				</div>
				<div class="box-header">
					<form:form action="${contextPath }/admin/khachhang" method="get">
						<input hidden name="trangthai" value="${param.trangthai }">

						<div class="form-group col-md-5">
							<label>Loại Khách Hàng</label> <select
								class="form-control select2" name="loaikhachhang"
								style="width: 100%;">
								<option ${param.loaikhachhang == '0' ? 'selected' : '' }
									value="0">Không</option>
								<c:forEach var="lkh" items="${listLoaikhachhang }">
									<option ${param.loaikhachhang == lkh.id ? 'selected' : '' }
										value="${lkh.id }">${lkh.tenloai}</option>
								</c:forEach>

							</select>
						</div>
						<div class="form-group col-md-5">
							<label>Nhóm Khách Hàng</label> <select
								class="form-control select2" name="nhomkhachhang"
								style="width: 100%;">
								<option ${param.nhomkhachhang == '0' ? 'selected' : '' }
									value="0">Không</option>
								<c:forEach var="nkh" items="${listNhomkhachhang }">
									<option ${param.nhomkhachhang == nkh.id ? 'selected' : '' }
										value="${nkh.id }">${nkh.tennhom}</option>
								</c:forEach>

							</select>
						</div>
						<input hidden name="limit" value="100">
						<input hidden name="page" value="1">
						<div class="col-md-2">
							<button style="margin-top: 25px;" type="submit"
								class="btn btn-block btn-success btn-flat">Tìm Kiếm</button>
						</div>
					</form:form>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
				 <div class="table-responsive">
					<table id="example2" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>Ưu Tiên</th>
								<th>Mã Khách Hàng</th>
								<th>Tên Khách Hàng</th>
								<th>Loại Khách Hàng</th>
								<th>Nhóm Khách Hàng</th>
								<th>Số Hóa Đơn Đã Mua</th>
								<th>Tổng Tiền Đã Mua</th>
								<th>Tổng Tiền Đã Trả</th>
								<th>Tổng Nợ Còn</th>
								<th>Số Tiền Chăm Sóc</th>
								<th>Số Tiền Đã Chăm Sóc</th>
								<th>Thao Tác</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="kh" items="${listKhachhang }">
								<c:set var="sohoadon" value="0" />
								<c:set var="tongtien" value="0" />
								<c:set var="tiendatra" value="0" />
								<c:set var="congno" value="0" />

								<c:forEach var="hd" items="${hoaDonList }">
									<c:if test="${kh.id == hd.khachhang.id}">
										<c:set var="sohoadon" value="${sohoadon + 1}" />
										<c:set var="tongtien" value="${tongtien + hd.tongtien }" />
										<c:set var="tiendatra" value="${tiendatra + hd.tiendatra }" />
										<c:set var="congno" value="${congno + hd.congno }" />
									</c:if>
								</c:forEach>


								<tr>
									<td style="text-align: center;">
										<div class="btn-group" data-toggle="buttons">
											<label id="lbloption1${kh.id }" class="btn  btn-xs
											${kh.uutien == 'co' ? 'btn-success active' : 'btn-default' }
											"> <input 
												onchange="onUuTien(${kh.id });"
												type="radio" name="options" id="option1${kh.id }" value="1" /> <span
												class="glyphicon glyphicon-ok"></span>
											</label> <label id="lbloption2${kh.id }" class="btn  btn-xs
											${kh.uutien == 'khong' ? 'btn-danger active' : 'btn-default' }
											"> <input
											onchange="offUuTien(${kh.id });"
												type="radio" name="options" id="option2${kh.id }" value="0" /> <span
												class="glyphicon glyphicon-remove"></span>
											</label>
										</div>
									</td>
									<td>${kh.makh }</td>
									<td>${kh.ten }</td>
									<td>${kh.loaikhachhang.tenloai }</td>
									<td>${kh.nhomkhachhang.tennhom }</td>
									<td class="text-bold"><fmt:formatNumber
											type="number" pattern="###,###" value="${sohoadon }"/></td>
									<td class="changetext"><fmt:formatNumber
											type="number" pattern="###,###" value="${tongtien }"/> &#8363;</td>
									<td class="changetext"><fmt:formatNumber
											type="number" pattern="###,###" value="${tiendatra }"/> &#8363;</td>
									<td class="changetext text-red"><fmt:formatNumber
											type="number" pattern="###,###" value="${congno }"/> &#8363;</td>
									<td class="changetext"><fmt:formatNumber
											type="number" pattern="###,###" value="${kh.sotienchamsoc }"/> &#8363;</td>
									<td class="changetext"><fmt:formatNumber
											type="number" pattern="###,###" value="${kh.sotiendachamsoc }"/> &#8363;	</td>
									<td><a href="${contextPath }/admin/khachhang/${kh.id}">
											<i style="color: blue;" class="fa fa-pencil fa-lg"
											aria-hidden="true" title="Sửa"> </i>
									</a>
									<c:if test="${kh.trangthai != 'deleted' }">
									 <a onclick="deleteOne(${kh.id});" href="#" data-toggle="modal"
										data-target="#myModal" style="color: red; margin-left: 10px;">
											<i class="fa fa-trash-o fa-lg" aria-hidden="true" title="Xóa"></i>
									</a> <a href="${contextPath }/admin/chamsoc/add?khachhang=${kh.id}">
											<i style="color: green; margin-left: 10px;"
											class="fa fa-commenting-o" aria-hidden="true"
											title="Chăm Sóc"> </i>
									</a>
									</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<th>Ưu Tiên</th>
								<th>Mã Khách Hàng</th>
								<th>Tên Khách Hàng</th>
								<th>Loại Khách Hàng</th>
								<th>Nhóm Khách Hàng</th>
								<th>Số Hóa Đơn Đã Mua</th>
								<th>Tổng Tiền Đã Mua</th>
								<th>Tổng Tiền Đã Trả</th>
								<th>Tổng Nợ Còn</th>
								<th>Số Tiền Chăm Sóc</th>
								<th>Số Tiền Đã Chăm Sóc</th>
								<th>Thao Tác</th>
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
		<!-- Phan trang -->
		<div class="text-center">
			<ul class="pagination ">
				<c:choose>
					<c:when test="${currentpage > 1  }">
						<li class="page-item"><a class="page-link"
							href="${contextPath }/admin/khachhang?trangthai=${param.trangthai }&loaikhachhang=${param.loaikhachhang }&nhomkhachhang=${param.nhomkhachhang }&limit=${param.limit }&page=${currentpage -1 }">Previous</a></li>

					</c:when>
					<c:otherwise>
						<li class="page-item disabled"><a class="page-link"
							href="javascript:void(0);">Trước</a></li>

					</c:otherwise>

				</c:choose>

				<c:forEach begin="${(currentpage - 3) > 0 ? currentpage - 3 : 1 }"
					end="${currentpage + 3 < pagecount ? currentpage + 3 : pagecount }"
					varStatus="status">
					<c:choose>
						<c:when test="${status.index == currentpage }">
							<li class="page-item active"><a class="page-link">${status.index }</a></li>

						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="${contextPath }/admin/khachhang?trangthai=${param.trangthai }&loaikhachhang=${param.loaikhachhang }&nhomkhachhang=${param.nhomkhachhang }&limit=${param.limit }&page=${status.index }">${status.index }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${currentpage < pagecount }">
						<li class="page-item"><a class="page-link"
							href="${contextPath }/admin/khachhang?trangthai=${param.trangthai }&loaikhachhang=${param.loaikhachhang }&nhomkhachhang=${param.nhomkhachhang }&limit=${param.limit }&page=${currentpage + 1 }">Next</a></li>

					</c:when>
					<c:otherwise>
						<li class="page-item disabled"><a class="page-link"
							href="javascript:void(0);">Sau</a></li>


					</c:otherwise>

				</c:choose>

			</ul>
		</div>
		<!-- END PHÂN TRANG -->
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