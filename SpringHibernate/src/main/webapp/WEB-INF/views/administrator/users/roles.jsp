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
        Quyền người dùng
        <small><a href="${contextPath }/admin/roles?status=active">Danh sách quyền</a></small>
        <small><a class="btn btn-success" href="${contextPath }/admin/roles/add">Thêm mới</a></small>
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
      	<c:choose>
      		<c:when test="${isshowlist != '0' }">
		        <!-- Table roles -->
		        <div class="col-xs-12">
		          <ul class="nav nav-tabs">
					<li class="tablinks ${param.status == 'active' ? 'active' : '' }">
					<a href="${contextPath }/admin/roles?status=active"   >Đã Kích Hoạt</a>
					</li>
					<li class="tablinks ${param.status == 'inactive' ? 'active' : '' }" >
					<a href="${contextPath }/admin/roles?status=inactive">Chưa Kích Hoạt</a>
					</li>
					<li class="tablinks ${param.status == 'draft' ? 'active' : '' }">
					<a href="${contextPath }/admin/roles?status=draft" >Nháp</a>
					</li>
					<li class="tablinks ${param.status == 'deleted' ? 'active' : '' }">
					<a href="${contextPath }/admin/roles?status=deleted"  >Đã Xóa</a>
					</li>
				</ul>
		          <div class="box">
		            <div class="box-header">
		              <h3 class="box-title">Danh sách quyền</h3>
		            </div>
		            <!-- /.box-header -->
		            <div class="box-body">
		              <table id="example1" class="table table-bordered table-striped">
		                <thead>
		                <tr>
		                  <th>Tên đăng nhập</th>
		                  <th>Email</th>
		                  <th>Mô tả</th>
		                  <th>Trạng thái</th>
		                  <th>Thao tác</th>
		                </tr>
		                </thead>
		                <tbody>
		                <c:forEach var="tk" items="${lstquyen }" >
		                	<tr>
			                  	<td><c:out value="${tk.getMaquyen() }"/></td>
			                  	<td><c:out value="${tk.getTenquyen() }"/></td>
			                  	<td><c:out value="${tk.getMota() }"/></td>
			                  	<td><c:out value="${tk.getTrangthai() }"/></td>
			                  	<td>
			                  		<a href="${contextPath }/admin/roles/${tk.getId()}">
			                  			<i style="color: blue;" class="fa fa-pencil fa-lg" aria-hidden="true" title="Sửa">
			                  			</i>
			                  		</a> 
			                  		<a onclick="deleteOne(${tk.getId()});" href="#" data-toggle="modal" data-target="#myModal"
										style="color: red; margin-left: 10px;"> 
										<i class="fa fa-trash-o fa-lg" aria-hidden="true" title="Xóa"></i>
									</a>
								</td>
			                </tr>
		                </c:forEach>
		                </tbody>
		                <tfoot>
		                <tr>
		                  <th>Tên đăng nhập</th>
		                  <th>Email</th>
		                  <th>Thông tin khác</th>
		                  <th>Trạng thái</th>
		                  <th>Thao tác</th>
		                </tr>
		                </tfoot>
		              </table>
		            </div>
		            <!-- /.box-body -->
		          </div>
		          <!-- /.box -->
		          </div>
		          <!-- /.col -->
          	</c:when>
      		<c:otherwise>	
		          <!-- Form - roles management -->
		      	<div class="col-xs-12">
		        	<!-- general form elements -->
		          <div class="box box-primary">
		            <div class="box-header with-border">
		              <h3 class="box-title">Quản lý quyền</h3>
		            </div>
		            <!-- /.box-header -->
		            <!-- form start -->
		            <form:form id="formRoles"  role="form" action="${contextPath }/admin/roles" method="post">
		              <div class="box-body">
		              	 <div class="form-group hidden">
		                  <label for="idquyen">ID</label>
		                  <input name="idquyen" type="text" class="form-control " id="idquyen" value="${quyen.getId()}" >
		                </div>
		                <div class="form-group">
		                  <label for="maquyen">Mã quyền</label>
		                  <input name="maquyen" type="text" class="form-control" id="maquyen" value="${quyen.getMaquyen()}"  placeholder="Mã quyền">
		                </div>
		                <div class="form-group">
		                  <label for="tenquyen">Tên quyền</label>
		                  <input name="tenquyen" type="text" class="form-control" id="tenquyen" value="${quyen.getTenquyen()}" placeholder="Tên quyền">
		                  <div id="spanName" class=" error"></div>
		                </div>
		                <div class="form-group">
		                  <label for="mota">Trạng thái</label>
		                  <div class="md-radio-inline">
								<div class="md-radio">
									<input type="radio" id="statusactive" name="trangthai" value="active"
										${quyen.trangthai == 'active' ? 'checked' : '' }
										class="md-radiobtn radio-inline"> 
									<label for="statusactive" class="label label-primary"> <span></span>
										<span class="check  "></span> <span class="box"></span>Kích Hoạt
									</label>
							
									<input type="radio" id="statusinactive" name="trangthai" value="inactive"
										${quyen.trangthai == 'inactive' ? 'checked' : '' }
										checked="checked" class="md-radiobtn radio-inline"> 
										
										<label  class="label label-success"
										for="statusinactive"> <span class="inc"></span> <span
										class="check "></span> <span class="box"></span> Chưa Kích Hoạt
									</label>
							
									<input type="radio" id="statusdraft" name="trangthai" value="draft"
										${quyen.trangthai == 'draft' ? 'checked' : '' }
										class="md-radiobtn radio-inline"> 
										<label for="statusdraft" class="label label-warning"> <span
										class="inc"></span> <span class="check"></span> <span
										class="box"></span> Chờ Kích Hoạt
									</label>
								
									<input type="radio" id="statusdeleted" name="trangthai" value="deleted"
										${quyen.trangthai == 'deleted' ? 'checked' : '' }
										class="md-radiobtn radio-inline"> 
										<label for="statusdeleted" class="label label-danger"> <span></span>
										<span class="check"></span> <span class="box"></span> Xóa
									</label>
								</div>
							</div>
		                </div>
		                <div class="form-group">
		                  <label for="mota">Mô tả</label>
		                  <input name="mota" type="text" class="form-control" id="mota" value="${quyen.getMota()}" placeholder="Mô tả">
		                </div>
		              
		              </div>
		              <!-- /.box-body -->
		
		              <div class="box-footer">
		              <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		                <button type="submit" id="btn-submit" class="btn btn-primary">Cập nhật</button>
		              </div>
		            </form:form>
		          </div>
		          <!-- /.box -->
		        </div>
		      
		        <!-- /.col -->
        	</c:otherwise>
        </c:choose>
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