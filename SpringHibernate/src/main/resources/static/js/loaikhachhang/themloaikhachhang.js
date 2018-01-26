$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	$("#formLoaiKhachHang").validate({
		rules : {
			tenloai : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}

			}/*,
			mota : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}
			}*/

		},
		messages : {
			tenloai : {
				required : "Vui Lòng Nhập Tên Loại Khách Hàng"
				

			}/*,
			mota : {
				required : "Vui Lòng Nhập Mô Tả"
			}*/

		}
	});
});

var timeout = null;
function kiemtratenloaikhachhang(){
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var loaikhachhang = {};
		loaikhachhang["tenloai"] = $('#tenloai').val().trim();
		loaikhachhang["id"] = $("#id").val().trim();
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/kiemtratenloaikhachhang",
			data : JSON.stringify(loaikhachhang),
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				console.log(result);
				if(result == "error"){
					$('#error').css("display", "block");
					$('#error').text("* Tên Loại Đã Tồn Tại");
					$('#btn-submit').attr('type','button');
				}else {
					$('#error').css("display", "none");
					$('#error').text("");
					$('#btn-submit').attr('type','submit');
				}

			},
			error : function(e) {

			}
		});
	}, 100);
};
$('#tenloai').on('keyup keypress keydown', function(event){
	
	if($('#tenloai').val().trim() != ''){
		
		kiemtratenloaikhachhang();
		
	}else {
		$('#error').css("display", "none");
		$('#error').text("");
		
	}
	
});

