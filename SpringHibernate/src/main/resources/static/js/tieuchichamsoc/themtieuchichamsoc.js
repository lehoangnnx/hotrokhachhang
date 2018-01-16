$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	$("#formTieuChiChamSoc").validate({
		rules : {
			tentieuchi : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}

			}

		},
		messages : {
			tentieuchi : {
				required : "* Vui Lòng Nhập Tên Tiêu Chí"
				

			}

		}
	});
});

var timeout = null;
function kiemtratentieuchi(){
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var tieuchikhachhang = {};
		tieuchikhachhang["tentieuchi"] = $('#tentieuchi').val().trim();
		tieuchikhachhang["id"] = $("#id").val().trim();
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/kiemtratentieuchi",
			data : JSON.stringify(tieuchikhachhang),
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				
				if(result == "error"){
					$('#error').css("display", "block");
					$('#error').text("* Tên Tiêu Chí Đã Tồn Tại");
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
$('#tentieuchi').on('keyup keypress keydown', function(event){
	
	if($('#tentieuchi').val().trim() != ''){
		
		kiemtratentieuchi();
		
	}else {
		$('#error').css("display", "none");
		$('#error').text("");
		
	}
	
});

