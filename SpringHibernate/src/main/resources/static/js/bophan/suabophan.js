$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	$("#formBoPhan").validate({
		rules : {
			tenbophan : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			vitri : {
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
				required : "* Vui Lòng Nhập Tên Bộ Phận"
				

			},
			vitri : {
				required : "* Vui Lòng Nhập Vị Trí"
				

			}/*,
			mota : {
				required : "* Vui Lòng Nhập Mô Tả"
			}*/

		}
	});
});

var timeout = null;
function kiemtratenbophan(){
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var bophan = {};
		bophan["tenbophan"] = $('#tenbophan').val().trim();
		bophan["id"] = $("#id").val().trim();
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/kiemtratenbophan",
			data : JSON.stringify(bophan),
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				console.log(result);
				if(result == "error"){
					$('#error').css("display", "block");
					$('#error').text("* Tên Bộ Phận Đã Tồn Tại");
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
$('#tenbophan').on('keyup keypress keydown', function(event){
	
	if($('#tenbophan').val().trim() != ''){
		
		kiemtratenbophan();
		
	}else {
		$('#error').css("display", "none");
		$('#error').text("");
		
	}
	
});

