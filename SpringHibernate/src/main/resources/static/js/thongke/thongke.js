$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	$("#formThongKe").validate({
		rules : {
			tungay : {
				required : true

			},
			denngay : {
				required : true

			}

		},
		messages : {
			tungay : {
				required : "* Vui Lòng Nhập Từ Ngày"

			},
			denngay : {
				required : "* Vui Lòng Nhập Đến Ngày"

			}

		}
	});
});
/*
 * var timeout = null; function updateThongBaoChamSoc(id){
 * clearTimeout(timeout); timeout = setTimeout(function() {
 * 
 * var token = $("meta[name='_csrf']").attr("content"); var header =
 * $("meta[name='_csrf_header']").attr("content"); $(document).ajaxSend(function
 * (e, xhr, options) { xhr.setRequestHeader(header, token); });
 * 
 * $.ajax({
 * 
 * type : "POST", //contentType : "application/json", url : contextPath +
 * "/admin/updatethongbaochamsoc", data : {id}, // dataType: 'json', // timeout:
 * 600000, success : function(result) {
 * 
 * if(result != "error"){ window.location=contextPath+"/admin/chamsoc/"+result; }
 *  }, error : function(e) {
 *  } }); }, 100); };
 * 
 * function updateThongBaoKhachHang(id){ clearTimeout(timeout); timeout =
 * setTimeout(function() {
 * 
 * var token = $("meta[name='_csrf']").attr("content"); var header =
 * $("meta[name='_csrf_header']").attr("content"); $(document).ajaxSend(function
 * (e, xhr, options) { xhr.setRequestHeader(header, token); });
 * 
 * $.ajax({
 * 
 * type : "POST", //contentType : "application/json", url : contextPath +
 * "/admin/updatethongbaokhachhang", data : {id}, // dataType: 'json', //
 * timeout: 600000, success : function(result) {
 * 
 * if(result == "success"){ window.location=contextPath+"/admin/khachhang/"+id; }
 *  }, error : function(e) {
 *  } }); }, 100); }; $(document).ready(function(){ var dateObj = new Date();
 * var month = dateObj.getUTCMonth() + 1; var day = dateObj.getUTCDate(); var
 * year = dateObj.getUTCFullYear();
 *  // Báo kết quả $('#tungay').val(01+'/'+month+'/'+year);
 * $('#denngay').val(day+'/'+month+'/'+year);
 * 
 * 
 * });
 */
// Chuyển chuỗi kí tự (string) sang đối tượng Date()
function parseDate(str) {
	var mdy = str.split('/');
	return new Date(mdy[2], mdy[1], mdy[0]);
};
function checkNgay() {
	var tungay = parseDate($('#tungay').val()).getTime();
	var denngay = parseDate($('#denngay').val()).getTime();

	if (denngay < tungay) {

		$('#denngay-error').css("display", "block");
		$('#denngay-error').text("* Ngày Kết Thúc Lớn Hơn Ngày Bắt Đầu");
		$('#btn-submit').attr('type', 'button');
	} else {
		$('#denngay-error').css("display", "none");
		$('#denngay-error').text("");
		$('#btn-submit').attr('type', 'submit');
	}
}
