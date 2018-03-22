<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<input hidden="" id="msg" value="${msg }"></input>
<!-- Content Header (Page header) -->
<%--<section class="content-header">
    <h1>
        Nhân Viên KPI
        &lt;%&ndash;  <small><a
            href="${contextPath }/admin/roles?status=active">Danh Sách Nhân
                Viên KPI</a></small> <small><a class="btn btn-success"
            href="${contextPath }/admin/nhanvienkpi/add">Thêm mới</a></small> &ndash;%&gt;
    </h1>
    <ol class="breadcrumb">
        <a class="btn btn-success"
           href="${contextPath }/admin/nhanvienkpi/add">Thêm mới</a>
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
                <li
                        class="tablinks ${param.trangthai == 'inactive' ? 'active' : '' }">
                    <a href="${contextPath }/admin/nhanvienkpi?trangthai=inactive">Chưa
                        Kích Hoạt</a>
                </li>
                <li class="tablinks ${param.trangthai == 'active' ? 'active' : '' }">
                    <a href="${contextPath }/admin/nhanvienkpi?trangthai=active">Đã
                        Kích Hoạt</a>
                </li>


                <li
                        class="tablinks ${param.trangthai == 'deleted' ? 'active' : '' }">
                    <a href="${contextPath }/admin/nhanvienkpi?trangthai=deleted">Đã
                        Xóa</a>
                </li>
            </ul>
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Danh Sách Nhân Viên KPI</h3>
                    <a class="btn btn-success pull-right"
                       href="${contextPath }/admin/nhanvienkpi/add">Thêm mới</a>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="table-responsive">
                        <table id="example2" class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Nhân Viên</th>
                                <th>KPI</th>
                                <th>Chỉ Tiêu Đăng Ký</th>
                                <th>Ngày Đăng Ký</th>
                                <th>Ngày Hoàn Thành</th>
                                <security:authorize access="hasAnyRole('ADMIN')">
                                    <th>Trạng Thái</th>
                                </security:authorize>
                                <!-- <th>Mức Độ Hoàn Thành</th>
                                <th>Mô Tả</th> -->

                                <th>Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="nvkpi" items="${listNhanvienkpi }">
                                <tr>
                                    <td>${nvkpi.nhanvien.manhanvien}-
                                            ${nvkpi.nhanvien.tennhanvien}</td>
                                    <td>${nvkpi.kpi.ten}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${nvkpi.kpi.kieukpi == 'so'}">
                                                ${nvkpi.chitieudangky}
                                            </c:when>
                                            <c:when test="${ nvkpi.kpi.kieukpi == 'phantram'}">
                                                ${nvkpi.chitieudangky} %
                                            </c:when>
                                            <c:when test="${ nvkpi.kpi.kieukpi == 'tien'}">
                                                <fmt:formatNumber type="number" pattern="###,###"
                                                                  value="${nvkpi.chitieudangky }"/> &#8363;
                                            </c:when>
                                        </c:choose>

                                    </td>
                                    <td><fmt:formatDate pattern="dd-MM-yyyy"
                                                        value="${nvkpi.ngaydangky}"/></td>
                                    <td><fmt:formatDate pattern="dd-MM-yyyy"
                                                        value="${nvkpi.ngayhoanthanh}"/></td>
                                    <security:authorize access="hasAnyRole('ADMIN')">
                                        <c:if test="${nvkpi.trangthai != 'deleted' }">
                                            <td style="text-align: center;">
                                                <div class="btn-group" data-toggle="buttons">
                                                    <label id="lbloption1${nvkpi.id }" class="btn  btn-xs
											${nvkpi.trangthai == 'active' ? 'btn-success active' : 'btn-default' }
											"> <input
                                                            onchange="active(${nvkpi.id });"
                                                            type="radio" name="options" id="option1${nvkpi.id }"
                                                            value="1"/> <span
                                                            class="glyphicon glyphicon-ok"></span>
                                                    </label> <label id="lbloption2${nvkpi.id }" class="btn  btn-xs
											${nvkpi.trangthai == 'inactive' ? 'btn-danger active' : 'btn-default' }
											"> <input
                                                        onchange="inactive(${nvkpi.id });"
                                                        type="radio" name="options" id="option2${nvkpi.id }" value="0"/>
                                                    <span
                                                            class="glyphicon glyphicon-remove"></span>
                                                </label>
                                                </div>
                                            </td>
                                        </c:if>
                                        <c:if test="${nvkpi.trangthai == 'deleted' }">
                                            <td>Đã Xóa</td>
                                        </c:if>
                                    </security:authorize>

                                        <%-- <td>${nvkpi.mucdohoanthanh}</td>
                                        <td>${nvkpi.mota}</td> --%>
                                    <td><a
                                            href="${contextPath }/admin/nhanvienkpi/${nvkpi.id}"> <i
                                            style="color: blue;" class="fa fa-pencil fa-lg"
                                            aria-hidden="true" title="Sửa"> </i>
                                    </a> <c:if test="${nvkpi.trangthai != 'deleted' }">
                                        <a onclick="deleteOne(${nvkpi.id});" href="#"
                                           data-toggle="modal" data-target="#myModal"
                                           style="color: red; margin-left: 10px;"> <i
                                                class="fa fa-trash-o fa-lg" aria-hidden="true" title="Xóa"></i></a>
                                    </c:if></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>Nhân Viên</th>
                                <th>KPI</th>
                                <th>Chỉ Tiêu Đăng Ký</th>
                                <th>Ngày Đăng Ký</th>
                                <th>Ngày Hoàn Thành</th>
                                <security:authorize access="hasAnyRole('ADMIN')">
                                    <th>Trạng Thái</th>
                                </security:authorize>
                                <!-- <th>Mức Độ Hoàn Thành</th>
                                <th>Mô Tả</th> -->

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
                           value="${_csrf.token}"/>
                    <input value="" id="arrayId" hidden="" name="arrayId"/>
                    <button class="btn btn-danger" style="float: left;">Xóa</button>
                </form:form>


                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>