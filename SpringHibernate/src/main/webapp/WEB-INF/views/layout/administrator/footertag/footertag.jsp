<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script>var contextPath = "${pageContext.request.contextPath}"</script>
<!-- jQuery 2.2.3 -->
<script src="${contexPath }/js/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${contexPath }/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${contexPath }/js/app.min.js"></script>

<!-- iCheck 1.0.1 -->
<script src="${contexPath }/js/plugins/iCheck/icheck.min.js"></script>
<!-- Select2 -->
<script src="${contexPath }/js/plugins/select2/select2.full.min.js"></script>
<!-- InputMask -->
<script src="${contexPath }/js/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${contexPath }/js/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${contexPath }/js/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<!-- date-range-picker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="${contexPath }/js/plugins/daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="${contexPath }/js/plugins/datepicker/bootstrap-datepicker.js"></script>


<!-- bootstrap time picker -->
<script src="${contexPath }/js/plugins/timepicker/bootstrap-timepicker.min.js"></script>




<script src="${contextPath }/js/jquery.dataTables.min.js"></script>
<script src="${contextPath }/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${contextPath }/js/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${contextPath }/js/fastclick.js"></script>
<script src="${contextPath }/js/demo.js"></script>


 <script src="${contexPath }/js/jquery.validate.min.js"></script>
<script type="text/javascript"
		src="${contextPath}/js/additional-methods.min.js"></script> 
		
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
<script>
  $(function () {
    //Initialize Select2 Elements
    $(".select2").select2();

    //Datemask dd/mm/yyyy
    $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
    //Datemask2 mm/dd/yyyy
    $("#datemask2").inputmask("mm/dd/yyyy", {"placeholder": "mm/dd/yyyy"});
    //Money Euro
    $("[data-mask]").inputmask();

    //Date range picker
    $('#reservation').daterangepicker();
    //Date range picker with time picker
    $('#reservationtime').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A'});
    //Date range as a button
    $('#daterange-btn').daterangepicker(
        {
          ranges: {
            'Today': [moment(), moment()],
            'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
            'Last 7 Days': [moment().subtract(6, 'days'), moment()],
            'Last 30 Days': [moment().subtract(29, 'days'), moment()],
            'This Month': [moment().startOf('month'), moment().endOf('month')],
            'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
          },
          startDate: moment().subtract(29, 'days'),
          endDate: moment()
        },
        function (start, end) {
          $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
        }
    );

    //Date picker
    $('#datepicker').datepicker({
      autoclose: true
    });

    //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass: 'iradio_minimal-blue'
    });
    //Red color scheme for iCheck
    $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
      checkboxClass: 'icheckbox_minimal-red',
      radioClass: 'iradio_minimal-red'
    });
    
    //Flat red color scheme for iCheck
    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
      checkboxClass: 'icheckbox_flat-green',
      radioClass: 'iradio_flat-green'
    });

    

    //Timepicker
    $(".timepicker").timepicker({
      showInputs: false
    });
  });
</script>
<!-- <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/additional-methods.min.js"></script> -->
