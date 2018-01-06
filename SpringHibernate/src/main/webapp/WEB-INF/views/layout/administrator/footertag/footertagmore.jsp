<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- DataTables -->
<script src="${contextPath }/js/jquery.dataTables.min.js"></script>
<script src="${contextPath }/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${contextPath }/js/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${contextPath }/js/fastclick.js"></script>
<script src="${contextPath }/js/demo.js"></script>

<!-- page script -->
<script>
  $(function () {
    $("#example1").DataTable();
    $('#example2').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false
    });
  });
</script>
