$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	$("#formLuong").validate({
		rules : {
			nhanvien : {
				required : true
			},
			luong : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			thuong : {
				required : true,
				
				normalizer : function(value) {

					return $.trim(value);
				}

			}
			
		},
		messages : {
			nhanvien : {
				required : "* Vui Lòng Chọn Nhân Viên"
			},
			luong : {
				required : "* Vui Lòng Nhập Lương"
				

			},
			thuong : {
				required : "* Vui Lòng Nhập Thưởng"
				

			}

		}
	});
});

var timeout = null;
function kiemtraluong(){
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		
		var thang = $('#thang :selected').val();
		var nam = $('#nam :selected').val();
		
		var id = $("#id").val().trim();
		
		
		nhanvien=$('#nhanvien :selected').val();
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
		$.ajax({

			type : "POST",
			//contentType : "application/json",
			url : contextPath + "/admin/kiemtraluong",
			data : {id,thang,nam,nhanvien},
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				
				if(result == "error"){
					$('#error').css("display", "block");
					$('#error').text("* Lương Của Nhân Viên Đã Tồn Tại");
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
$('#formLuong').change(function(){
	kiemtraluong();
});