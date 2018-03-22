<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<style>
    dt {
        margin-bottom: 5px;
    }
    dd {
        margin-bottom: 20px;
    }
</style>
<section class="content">
    <!-- Small boxes (Stat box) -->
    <div class="row">
        <form:form id="formSaoKeHoaDon" action="${contextPath }/admin/saokehoadon"
                   method="get">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border text-center">
                        <h3 class="box-title">Sao Kê Hóa Đơn Theo Ngày Thanh Toán</h3>
                    </div>
                    <div class="box-body">
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
                            <div class="form-group">
                                <label>Nhân Viên Bán Hàng</label> <select
                                    class="form-control select2" name="nhanvienbanhang"
                                    style="width: 100%;">

                                <c:forEach var="nv" items="${listNhanvien }">

                                    <option ${param.nhanvienbanhang == nv.id ? 'selected' : '' }
                                            value="${nv.id }">${nv.manhanvien} - ${nv.tennhanvien }</option>
                                </c:forEach>

                            </select>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <button style="margin-top: 25px;" type="submit" id="btn-submit"
                                    class="btn btn-block btn-success btn-flat">Sao Kê
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>

    </div>


    <div class="row">

        <style>

        </style>
        <c:forEach var="l" items="${listMap}">
            <div class="col-md-12">
                <div class="box box-solid">
                    <div class="box-header with-border">
                        <i class="fa fa-calendar"></i>

                        <h3 class="box-title"><fmt:formatNumber minIntegerDigits="2" value="${l.thang}" /> - ${l.nam}</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <dl class="dl-horizontal col-md-4">
                            <dt>Tổng Hóa Đơn</dt>
                            <dd>${l.tonghoadon}</dd>
                            <dt>Tổng Tiền</dt>
                            <dd><fmt:formatNumber type="number" pattern="###,###" value="${l.tongtien}" />  &#8363;</dd>
                            <dt>Hóa Đơn Đã Thanh Toán</dt>
                            <dd>${l.hoadondathanhtoan}</dd>
                            <dt>Tiền Đã Thanh Toán</dt>
                            <dd><fmt:formatNumber type="number" pattern="###,###" value="${l.tiendatra}" />  &#8363;</dd>
                            <dt>Hóa Đơn Chưa Thanh Toán</dt>
                            <dd>${l.hoadonchuathanhtoan}</dd>
                            <dt>Tiền Còn Nợ</dt>
                            <dd><fmt:formatNumber type="number" pattern="###,###" value="${l.congno}" />  &#8363;</dd>

                        </dl>
                        <dl class="dl-horizontal col-md-4">
                            <dt>Khách Hàng Mới</dt>
                            <dd>${l.khachhangmoi}</dd>
                            <dt>Khách Hàng Tái Lập</dt>
                            <dd>${l.khachhangtailap}</dd>
                        </dl>
                        <dl class="dl-horizontal col-md-4">
                            <dt>Lương</dt>
                            <dd><fmt:formatNumber type="number" pattern="###,###" value="${l.luong}" />  &#8363;</dd>
                            <dt>Thưởng</dt>
                            <dd><fmt:formatNumber type="number" pattern="###,###" value="${l.thuong}" />  &#8363;</dd>
                            <dt>Thưởng Hóa Đơn</dt>
                            <dd><fmt:formatNumber type="number" pattern="###,###" value="${l.thuonghoadon}" />  &#8363;</dd>
                            <dt>Tiền Ứng</dt>
                            <dd><fmt:formatNumber type="number" pattern="###,###" value="${l.tienung}" />  &#8363;</dd>
                        </dl>

                        <dl class="dl-horizontal col-md-12" >
                            <c:if test="${not empty l.nhanvienkpi}">
                                <dt>Tên KPI/Chỉ Tiêu Đăng Ký</dt>
                            <c:forEach var="nvk" items="${l.nhanvienkpi}">

                                <dd>${nvk.tenkpi} /


                                <c:choose>
                                    <c:when test="${nvk.kieukpi == 'tien'}">
                                        <fmt:formatNumber type="number" pattern="###,###" value="${nvk.chitieudangky}" />  &#8363;
                                    </c:when>
                                    <c:otherwise>
                                        ${nvk.chitieudangky}
                                    </c:otherwise>
                                </c:choose>

                                </dd>
                            </c:forEach>
                            </c:if>
                        </dl>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>


</section>