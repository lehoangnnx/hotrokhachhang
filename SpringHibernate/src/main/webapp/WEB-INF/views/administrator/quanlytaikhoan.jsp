<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Data Tables
        <small>advanced tables</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Tables</a></li>
        <li class="active">Data tables</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Data Table With Full Features</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Tên đăng nhập</th>
                  <th>Email</th>
                  <th>Thông tin khác</th>
                  <th>Trạng thái</th>
                  <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="tk" items="${lsttaikhoan }" >
                	<tr>
	                  <td><c:out value="${tk.getUsername() }"/></td>
	                  <td><c:out value="${tk.getEmail() }"/>
	                  </td>
	                  <td><c:out value="${tk.getThongtinkhac() }"/></td>
	                  <td> <c:out value="${tk.getNhanvien().getTennhanvien() }"/></td>
	                  <td>X</td>
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
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->