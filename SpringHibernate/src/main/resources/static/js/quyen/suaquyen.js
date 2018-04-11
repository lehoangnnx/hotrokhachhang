$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	$("#formQuyen").validate({
		rules : {
			maquyen : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			tenquyen : {
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
			maquyen : {
				required : "* Vui Lòng Nhập Mã Quyền"
				
			},
			tenquyen : {
				required : "* Vui Lòng Nhập Tên Quyền"
				

			}/*,
			
			mota : {
				required : "* Vui Lòng Nhập Mô Tả"
			}*/

		}
	});
});

var timeout = null;
function kiemtramaquyen(){
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var quyen = {};
		quyen["maquyen"] = $('#maquyen').val().trim();
		quyen["id"] = $("#id").val().trim();
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/kiemtramaquyen",
			data : JSON.stringify(quyen),
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				console.log(result);
				if(result == "error"){
					$('#error').css("display", "block");
					$('#error').text("* Tên Mã Quyền Đã Tồn Tại");
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
$('#maquyen').on('keyup keypress keydown', function(event){
	
	if($('#maquyen').val().trim() != ''){
		var a = $("#maquyen").val().toUpperCase();
        $("#maquyen").val(a);
		if(a.substr(0, 5) === "ROLE_"){
            $("#error").text("");
            kiemtramaquyen();
		}else{
			$('#error').css("display", "block");
            $("#error").text("Mã Quyền Phải Bắt Đầu Bằng ROLE_");
		}
		
		
	}else {
		$('#error').css("display", "none");
		$('#error').text("");
	}
	
})