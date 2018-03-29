<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<style>
    .lb-flat-red {
        padding-left: 10px;
        padding-right: 20px;
    }
</style>
<style>
    table {
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
        border: 1px solid #ddd;
    }

    th, td {
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2
    }
    dt {
        margin-bottom: 5px;
    }
    dd {
        margin-bottom: 20px;
    }
</style>
<section class="content">

    <div class="row">
        <!-- left column -->
        <div class="col-md-12">
            <div class="box box-solid">
                <div class="box-header with-border">
                    <i class="fa fa-commenting-o"></i>
                    <h3 class="box-title">Chăm Sóc</h3>
                    <button class="btn btn-info pull-right" type="button" onclick="goBack();">Quay Lại</button>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <dl class="col-md-4">
                        <dt>Nhân Viên Chăm Sóc</dt>
                        <dd>${map.nhanvienchamsoc}</dd>

                        <dt>Khách Hàng</dt>
                        <dd>${chamsoc.khachhang.makh} - ${chamsoc.khachhang.ten}</dd>

                        <dt>Ngày Chăm Sóc</dt>
                        <dd><fmt:formatDate pattern="dd-MM-yyyy" value="${chamsoc.ngay}"/></dd>
                        <dt>Số Lần Đã Chăm Sóc</dt>
                        <dd>${chamsoc.khachhang.solanchamsoc }</dd>





                    </dl>
                    <dl class="col-md-4">
                        <dt>Nhân Viên Bán Hàng</dt>
                        <dd>${map.nhanvienbanhang}</dd>
                        <dt>Hóa Đơn</dt>
                        <dd>${map.hoadon}</dd>
                        <dt>Ngày Chăm Sóc Tiếp Theo</dt>
                        <dd><fmt:formatDate pattern="dd-MM-yyyy" value="${chamsoc.ngaycstiep}"/></dd>
                        <dt>Số Lần Đã Đàm Phán</dt>
                        <dd>${chamsoc.khachhang.solandamphan }</dd>
                    </dl>
                    <dl class="col-md-4">
                        <dt>Nhân Viên Giao Hàng</dt>
                        <dd>${map.nhanviengiaohang}</dd>
                        <dt>Lần Chăm Sóc</dt>
                        <dd>${chamsoc.lan}</dd>
                        <dt>Nội Dung Chăm Sóc</dt>
                        <dd>${chamsoc.noidung}</dd>
                        <dt>Ghi Chú</dt>
                        <dd>${chamsoc.ghichu}</dd>
                    </dl>
                    <dl class="col-md-12 text-center">
                        <dt>Tên Tiêu Chí Chăm Sóc - Nội Dung Chăm Sóc</dt>
                        <c:forEach var="ctcs" items="${listChitietchamsoc }">

                            <dd>${ctcs.tieuchichamsoc.tentieuchi} - <c:if
                                    test="${ctcs.tieuchichamsoc.kieutieuchi == 'so' }">
                                <input hidden value="${ctcs.diem}"
                                       id="ikieutieuchitccs${ctcs.tieuchichamsoc.id}"
                                       name="kieutieuchitccs">
                                <span id="kieutieuchitccs${ctcs.tieuchichamsoc.id}">
                                        ${ctcs.diem} </span>
                            </c:if> <c:if
                                    test="${ctcs.tieuchichamsoc.kieutieuchi == 'cokhong' }">
                                <input hidden value="${ctcs.cokhong}"
                                       id="ikieutieuchitccs${ctcs.tieuchichamsoc.id}"
                                       name="kieutieuchitccs">
                                <span id="kieutieuchitccs${ctcs.tieuchichamsoc.id}">

                                        ${ctcs.cokhong == 'true' ? 'Tốt' : 'Chưa Tốt'} </span>
                            </c:if> <c:if test="${ctcs.tieuchichamsoc.kieutieuchi == 'tien' }">
                                <input hidden value="${ctcs.tienchamsoc}"
                                       id="ikieutieuchitccs${ctcs.tieuchichamsoc.id}"
                                       name="kieutieuchitccs">
                                <span id="kieutieuchitccs${ctcs.tieuchichamsoc.id}">
														<fmt:formatNumber type="number" pattern="###,###"
                                                                          value="${ctcs.tienchamsoc}"/>
														&#8363;</span>
                            </c:if></dd>
                        </c:forEach>
                    </dl>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>
    <!-- /.row -->
</section>

