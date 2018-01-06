$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	$("#formNhomHang").validate({
		rules : {
			manhom : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			tennhom : {
				required : true,
				
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			mota : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}
			}

		},
		messages : {
			manhom : {
				required : "* Vui Lòng Nhập Mã Nhóm Hàng"
				
			},
			tennhom : {
				required : "* Vui Lòng Nhập Tên Nhóm Hàng"
				

			},
			
			mota : {
				required : "* Vui Lòng Nhập Mô Tả"
			}

		}
	});
});

var timeout = null;
function kiemtratennhomhang(){
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var nhomhang = {};
		nhomhang["manhom"] = $('#manhom').val().trim();
		nhomhang["id"] = $("#id").val().trim();
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/kiemtramanhomhang",
			data : JSON.stringify(nhomhang),
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				console.log(result);
				if(result == "error"){
					$('#error').css("display", "block");
					$('#error').text("* Tên Mã Nhóm Đã Tồn Tại");
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
$('#manhom').on('keyup keypress keydown', function(event){
	
	if($('#manhom').val().trim() != ''){
		
		kiemtratennhomhang();
		
	}else {
		$('#error').css("display", "none");
		$('#error').text("");
	}
	
})