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
<style>
    .changetext {
        font-weight: bold;
        font-size: 15px;
        color: green;
    }
</style>
<security:authorize access="hasAnyRole('BANHANG') && !hasAnyRole('ADMIN')">
    <section class="content-header">
        <form:form id="formThongKe" action="${contextPath }/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1"
                   method="get">
            <input hidden name="trangthai" value="${param.trangthai}"/>
            <input hidden name="limit" value="${param.limit}"/>
            <input hidden name="page" value="${param.page}"/>
            <div class="col-md-12">
                <div class="box box-primary">

                    <div class="box-body">
                        <div class="box-header with-border text-center">
                            <h3 class="box-title">Thống Kê Hóa Đơn Theo Ngày Thanh Toán</h3>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Từ Ngày</label>

                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input name="tungay" id="tungay" onchange="checkNgay();"
                                           value="<fmt:formatDate value="${tungay }"
                                                pattern="dd-MM-yyyy" />"
                                           type="text" class="form-control"
                                           data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
                                </div>
                                <label id="tungay-error" class="error" for="tungay"></label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Đến Ngày</label>

                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input name="denngay" id="denngay" onchange="checkNgay();"
                                           value="<fmt:formatDate
                                                pattern="dd-MM-yyyy" value="${denngay }"/>"
                                           type="text" class="form-control"
                                           data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
                                </div>
                                <label id="denngay-error" class="error" for="denngay"></label>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <button style="margin-top: 25px;" type="submit" id="btn-submit"
                                    class="btn btn-block btn-success btn-flat">Thống Kê
                            </button>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">

                                <label>
                                    <input ${hthd != null ? 'checked' : ''} type="checkbox" class="minimal" name="hthd">
                                    Hiển Thị Hóa Đơn Theo Thống Kê
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
        <section class="col-md-12">
            <div class="box box-primary">
                <c:set var="tongtien" value="0"/>
                <c:set var="tiendatra" value="0"/>
                <c:set var="congno" value="0"/>
                <c:forEach var="hd" items="${listHoadonThongKe }">
                    <c:set var="tongtien" value="${tongtien + hd.tongtien }"/>
                    <c:set var="tiendatra" value="${tiendatra + hd.tiendatra }"/>
                    <c:set var="congno" value="${congno + hd.congno }"/>
                </c:forEach>
                <div class="box-body">
                    <div class="col-md-12 margin" style="border-bottom: 1px solid;">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Tổng Hóa Đơn : <span>${fn:length(listHoadonThongKe) }</span>
                                </label>

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <c:set var="hddtt" value="0"/>
                                <c:forEach var="hd" items="${listHoadonThongKe }">
                                    <c:if test="${hd.tongtien == hd.tiendatra && hd.congno == 0 }">
                                        <c:set var="hddtt" value="${hddtt + 1 }"/>
                                    </c:if>

                                </c:forEach>
                                <label>Hóa Đơn Đã Thanh Toán : <span>${hddtt }</span>
                                </label>

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <c:set var="hdctt" value="0"/>
                                <c:forEach var="hd" items="${listHoadonThongKe }">
                                    <c:if test="${hd.tongtien > hd.tiendatra && hd.congno > 0 }">
                                        <c:set var="hdctt" value="${hdctt + 1 }"/>
                                    </c:if>

                                </c:forEach>
                                <label>Hóa Đơn Chưa Thanh Toán : <span>${hdctt }</span>
                                </label>

                            </div>
                        </div>

                    </div>
                    <div class="col-md-12 margin">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="changetext" for="tongtien">Tổng Tiền : <span><fmt:formatNumber
                                        type="number" pattern="###,###" value="${tongtien }"/></span>
                                    &#8363;
                                </label>

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="changetext" for="tiendatra">Tiền Đã Trả : <span><fmt:formatNumber
                                        type="number" pattern="###,###" value="${tiendatra }"/></span>
                                    &#8363;
                                </label>

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="changetext text-red" for="congno">Đang Nợ : <span><fmt:formatNumber
                                        type="number" pattern="###,###" value="${congno }"/></span>
                                    &#8363;
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="col-md-12">
            <div class="box box-primary">
                <div class="box-body">
                    <div class="col-xs-12 table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>Tên KPI</th>
                                <th>Chỉ Tiêu Đăng Ký</th>
                                <th style="text-align: center;">Mức Độ Hoàn Thành</th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="tiendatra_kpi" value="0"/>
                            <c:set var="khachhangmoi" value="0"/>
                            <c:set var="khachhangtailap" value="0"/>
                            <c:forEach var="hd" items="${listHoadonKpi }">
                                <c:set var="tiendatra_kpi" value="${tiendatra_kpi + hd.tiendatra }"/>
                                <c:choose>
                                    <c:when test="${hd.hoadondautien == true}">
                                        <c:set var="khachhangmoi" value="${khachhangmoi + 1}"/>
                                    </c:when>
                                    <c:when test="${hd.hoadondautien == false}">
                                        <c:set var="khachhangtailap" value="${khachhangtailap + 1}"/>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                            <c:forEach var="nvk" items="${listNhanvienkpi}">
                                <tr>
                                    <td>${nvk.kpi.ten}</td>
                                    <c:choose>
                                        <c:when test="${nvk.kpi.kieukpi == 'tien'}">
                                            <td><fmt:formatNumber type="number" pattern="###,###"
                                                                  value="${nvk.chitieudangky}"/> &#8363;
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>${nvk.chitieudangky}</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${nvk.kpi.id == 1}">
                                            <td><fmt:formatNumber type="number" pattern="###,###"
                                                                  value="${tiendatra_kpi}"/>/
                                                <fmt:formatNumber type="number" pattern="###,###"
                                                                  value="${nvk.chitieudangky}"/>
                                                 &#8363;
                                            </td>
                                        </c:when>
                                        <c:when test="${nvk.kpi.id == 2}">
                                            <td>${khachhangmoi}/${nvk.chitieudangky}
                                            </td>
                                        </c:when>
                                        <c:when test="${nvk.kpi.id == 3}">
                                            <td>${khachhangtailap}/${nvk.chitieudangky}
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>Không Xác Định</td>
                                        </c:otherwise>
                                    </c:choose>


                                </tr>
                            </c:forEach>
                            <c:if test="${empty listNhanvienkpi }">
                                <tr>
                                    <td valign="top" colspan="8" class="dataTables_empty">Không Có KPI</td>
                                </tr>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.col -->
                </div>
            </div>
        </section>
    </section>
</security:authorize>
<%--
<section class="content-header">
<h1>
    Hóa Đơn
    &lt;%&ndash; <small><a href="${contextPath }/admin/roles?status=active">Danh Sách Hóa Đơn</a></small>
    <small><a class="btn btn-success" href="${contextPath }/admin/hoadon/add">Thêm mới</a></small> &ndash;%&gt;

</h1>
&lt;%&ndash;<ol class="breadcrumb">

    &lt;%&ndash;  <li><a href="${contextPath }/"><i class="fa fa-dashboard"></i> Home</a></li>
    <li><a href="${contextPath }/roles">Quyền</a></li>
    <li class="active">Quản lý quyền</li> &ndash;%&gt;
</ol>&ndash;%&gt;
</section>--%>

<!-- Main content -->
<section class="content">
    <div class="row">

        <div class="col-xs-12">
            <ul class="nav nav-tabs">
                <li
                        class="tablinks ${param.trangthai == 'dathanhtoan' ? 'active' : '' }">
                    <a
                            href="${contextPath }/admin/hoadon?trangthai=dathanhtoan&limit=100&page=1&hthd=on">Đã
                        Thanh Toán</a>
                </li>
                <li
                        class="tablinks ${param.trangthai == 'chuathanhtoan' ? 'active' : '' }">
                    <a
                            href="${contextPath }/admin/hoadon?trangthai=chuathanhtoan&limit=100&page=1&hthd=on">Chưa
                        Thanh Toán</a>
                </li>
                <%-- <li class="tablinks ${param.trangthai == 'dangno' ? 'active' : '' }">
                    <a
                    href="${contextPath }/admin/hoadon?trangthai=dangno&limit=100&page=1">Đang
                        Nợ</a>
                </li> --%>
                <li class="tablinks ${param.trangthai == 'deleted' ? 'active' : '' }">
                    <a href="${contextPath }/admin/hoadon?trangthai=deleted&limit=100&page=1&hthd=on">Đã Xóa</a>
                </li>

                <security:authorize access="hasAnyRole('BANHANG')">
                    <li class="tablinks ${param.trangthai == 'hoadonnhanviencapduoi' ? 'active' : '' }">
                        <a href="${contextPath }/admin/hoadon?trangthai=hoadonnhanviencapduoi&limit=100&page=1&hthd=on">Hóa Đơn Nhân Viên Cấp Dưới</a>
                    </li>
                </security:authorize>

            </ul>


            <div class="box">

                <div class="box-header">
                    <h3 class="box-title">Danh Sách Hóa Đơn</h3>
                    <security:authorize access="hasAnyRole('ADMIN','BANHANG')">
                        <a class="btn btn-success pull-right" href="${contextPath }/admin/hoadon/add">Thêm
                            mới</a>
                    </security:authorize>
                </div>

                <!-- /.box-header -->
                <div class="box-body">
                    <div class="table-responsive">
                        <table id="example2" class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Số Hóa Đơn</th>
                                <th>Khách Hàng</th>
                                <th>Nhân Viên Bán Hàng</th>
                                <th>Tổng Tiền</th>
                                <th>Tiền Đã Trả</th>
                                <th>Tiền Còn Nợ</th>
                                <th>Ngày Lập</th>
                                <th>Ngày Thanh Toán</th>
                                <th>Trạng Thái</th>
                                <th>Thao Tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="hd" items="${listHoadon }">
                                <tr>
                                    <td ><a class="${hd.tiendatra < hd.tongtien && hd.congno > 0 ? 'text-red' : '' } " href="${contextPath}/admin/hoadon/${hd.id}">${hd.sohoadon }</a></td>
                                    <td>
                                        <a href="${contextPath}/admin/khachhang/${hd.khachhang.id}">${hd.khachhang.makh }- ${hd.khachhang.ten }</a>
                                    </td>
                                    <td><a href="${contextPath}/admin/nhanvien/${hd.nhanvienByIdnhanvienban.id}">${hd.nhanvienByIdnhanvienban.manhanvien }-
                                            ${hd.nhanvienByIdnhanvienban.tennhanvien }</a></td>
                                    <td class="changetext"><fmt:formatNumber type="number" pattern="###,###"
                                                                             value="${hd.tongtien }" /> &#8363;</td>
                                    <td class="changetext"><fmt:formatNumber type="number" pattern="###,###"
                                                                             value="${hd.tiendatra }" /> &#8363;</td>
                                    <td class="changetext text-red"><fmt:formatNumber type="number" pattern="###,###"
                                                                                      value="${hd.congno }" /> &#8363;</td>
                                    <td><fmt:formatDate pattern="dd-MM-yyyy"
                                                        value="${hd.ngaylap }"/></td>
                                    <td><fmt:formatDate pattern="dd-MM-yyyy"
                                                        value="${hd.ngaythanhtoan }"/></td>
                                    <c:if test="${hd.trangthai == 'dagiaohang' }">
                                        <td>Đã Giao Hàng</td>
                                    </c:if>
                                    <c:if test="${hd.trangthai == 'chuagiaohang' }">
                                        <td >Chưa Giao Hàng</td>
                                    </c:if>
                                    <c:if test="${hd.trangthai == 'deleted' }">
                                        <td>Đã Xóa</td>
                                    </c:if>
                                    <td><a href="${contextPath }/admin/hoadon/${hd.id}"> <i
                                            style="color: blue;" class="fa fa-pencil fa-lg"
                                            aria-hidden="true" title="Sửa"> </i>
                                    </a>
                                        <security:authorize access="hasAnyRole('ADMIN','BANHANG')">
                                            <c:if test="${hd.trangthai != 'deleted' && param.trangthai != 'hoadonnhanviencapduoi' }">
                                                <a onclick="deleteOne(${hd.id});" href="#" data-toggle="modal"
                                                   data-target="#myModal" style="color: red; margin-left: 10px;">
                                                    <i class="fa fa-trash-o fa-lg" aria-hidden="true" title="Xóa"></i>
                                                </a>
                                            </c:if>
                                            <c:if test="${hd.trangthai == 'deleted' && param.trangthai != 'hoadonnhanviencapduoi' }">
                                                <a onclick="deletesOne(${hd.id});" href="#" data-toggle="modal"
                                                   data-target="#myModal_d" style="color: red; margin-left: 10px;">
                                                    <i class="fa fa-trash-o fa-lg" aria-hidden="true" title="Xóa"></i>
                                                </a>
                                            </c:if>
                                        </security:authorize>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>Số Hóa Đơn</th>
                                <th>Khách Hàng</th>
                                <th>Nhân Viên Bán Hàng</th>
                                <th>Tổng Tiền</th>
                                <th>Tiền Đã Trả</th>
                                <th>Tiền Còn Nợ</th>

                                <th>Ngày Lập</th>
                                <th>Ngày Thanh Toán</th>
                                <th>Trạng Thái</th>
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
                                                 href="${contextPath }/admin/hoadon?trangthai=${param.trangthai }&limit=${param.limit }&page=${currentpage -1 }">Previous</a>
                        </li>

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
                                                     href="${contextPath }/admin/hoadon?trangthai=${param.trangthai }&limit=${param.limit }&page=${status.index }">${status.index }</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${currentpage < pagecount }">
                        <li class="page-item"><a class="page-link"
                                                 href="${contextPath }/admin/hoadon?trangthai=${param.trangthai }&limit=${param.limit }&page=${currentpage + 1 }">Next</a>
                        </li>

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
                <h4 id="titlemodel" style="text-align: center; font-weight: bold; color: red;"
                    class="modal-title">Bạn Muốn Xóa ?</h4>
            </div>

            <div class="modal-footer">
                <form:form action="" method="delete">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <input value="" id="arrayId" hidden="" name="arrayId"/>
                    <button id="btn-deleteds" class="btn btn-danger" style="float: left;">Xóa</button>
                </form:form>


                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>
<div class="modal fade" id="myModal_d" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4  style="text-align: center; font-weight: bold; color: red;"
                    class="modal-title">Bạn Muốn Xóa Vĩnh Viễn ?</h4>
            </div>

            <div class="modal-footer">
                <form:form id="command1" action="${contextPath }/admin/xoavinhvienhoadon" method="patch">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <input value="" id="idds" hidden="" name="idds"/>
                    <button name="deleted" class="btn btn-danger" style="float: left;">Xóa</button>
                </form:form>


                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>