<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Default index page</h2>
<section class="container">
	<div class="row">
		<c:forEach var="item" items="${lsttaikhoan }">
			<div class="col-md-3 col-sm-6">
				<c:out value="${item.getUsername() }"></c:out>
				<c:out value="${item.getEmail() }"></c:out>
			</div>
		</c:forEach>
	</div>
</section>