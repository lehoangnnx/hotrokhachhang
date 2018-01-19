<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<input hidden="" id="msg" value="${msg }"></input>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
       Hóa Đơn
        <small><a href="${contextPath }/admin/roles?status=active">Danh Sách Hóa Đơn</a></small>
        <small><a class="btn btn-success" href="${contextPath }/admin/hoadon/add">Thêm mới</a></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${contextPath }/"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="${contextPath }/roles">Quyền</a></li>
        <li class="active">Quản lý quyền</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
      	
		        <div class="col-xs-12">
		          <ul class="nav nav-tabs">
					<li class="tablinks ${param.trangthai == 'dathanhtoan' ? 'active' : '' }">
					<a href="${contextPath }/admin/hoadon?trangthai=dathanhtoan"   >Đã Thanh Toán</a>
					</li>
					<li class="tablinks ${param.trangthai == 'chuathanhtoan' ? 'active' : '' }" >
					<a href="${contextPath }/admin/hoadon?trangthai=chuathanhtoan">Chưa Thanh Toán</a>
					</li>
					<li class="tablinks ${param.trangthai == 'dangno' ? 'active' : '' }">
					<a href="${contextPath }/admin/hoadon?trangthai=dangno" >Đang Nợ</a>
					</li>
					<li class="tablinks ${param.trangthai == 'deleted' ? 'active' : '' }">
					<a href="${contextPath }/admin/hoadon?trangthai=deleted"  >Đã Xóa</a>
					</li>
				</ul>
		          <div class="box">
		            <div class="box-header">
		              <h3 class="box-title">Danh Sách Hóa Đơn</h3>
		            </div>
		            <!-- /.box-header -->
		            <div class="box-body">
		             	<table  id="example2" class="table table-bordered table-hover">
		                <thead>
		                <tr>
		                  <th>Số Hóa Đơn</th>
		                  <th>Trạng Thái</th>
		                   <th>Thao Tác</th>
		                </tr>
		                </thead>
		                <tbody>
		                <c:forEach var="hd" items="${listHoadon }" >
		                	<tr>
			                  	<td>${hd.sohoadon }</td>
			                  	<td>${hd.trangthai }</td>
			                  	<td>
			                  		<a href="${contextPath }/admin/hoadon/${hd.id}">
			                  			<i style="color: blue;" class="fa fa-pencil fa-lg" aria-hidden="true" title="Sửa">
			                  			</i>
			                  		</a> 
			                  		<a onclick="deleteOne(${hd.id});" href="#" data-toggle="modal" data-target="#myModal"
										style="color: red; margin-left: 10px;"> 
										<i class="fa fa-trash-o fa-lg" aria-hidden="true" title="Xóa"></i></a>
								</td>
			                </tr>
		                </c:forEach>
		                </tbody>
		                <tfoot>
		                <tr>
		                   <th>Số Hóa Đơn</th>
		                  <th>Trạng Thái</th>
		                   <th>Thao Tác</th>
		                </tr>
		                </tfoot>
		              </table>
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
					<button class="btn btn-danger" style="float: left;">
						Xóa
					</button>
				</form:form>
				

				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>