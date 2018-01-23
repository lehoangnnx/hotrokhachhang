<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<section class="content">
	<!-- Small boxes (Stat box) -->
	<div class="row">
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-aqua">
				<div class="inner">
					<h3>${fn:length(hoadons) }</h3>

					<p>Hóa Đơn</p>
				</div>
				<div class="icon">
					<i class="ion ion-bag"></i>
				</div>
				<!-- <a href="#" class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a> -->
			</div>
		</div>
		<!-- ./col -->
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-green">
				<div class="inner">
					<h3>
						${fn:length(chamsocs) }
					</h3>

					<p>Lần Chăm Sóc</p>
				</div>
				<div class="icon">
					<i class="ion ion-stats-bars"></i>
				</div>
				<!-- <a href="#" class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a> -->
			</div>
		</div>
		<!-- ./col -->
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-yellow">
				<div class="inner">
					<h3>${fn:length(khachhangs) }</h3>

					<p>Khách Hàng</p>
				</div>
				<div class="icon">
					<i class="ion ion-person-add"></i>
				</div>
				<!-- <a href="#" class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a> -->
			</div>
		</div>
		<!-- ./col -->
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-red">
				<div class="inner">
					<h3>${fn:length(nhanviens) }</h3>

					<p>Nhân Viên</p>
				</div>
				<div class="icon">
					<i class="ion ion-pie-graph"></i>
				</div>
				<!-- <a href="#" class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a> -->
			</div>
		</div>


		<!-- ./col -->
		




		<!-- /.box-body -->
	</div>
	<!-- /.row -->
	<!-- Main row -->

	<!-- /.row (main row) -->

</section>