$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	$("#formKpi").validate({
		rules : {
			ten: {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			so : {
				required : true,
				
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			phantram : {
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
			ten : {
				required : "* Vui Lòng Nhập Tên "
				

			},
			so : {
				required : "* Vui Lòng Nhập Số"
				

			},
			phantram : {
				required : "* Vui Lòng Nhập Phần Trăm"
				
			},
			mota : {
				required : "* Vui Lòng Nhập Mô Tả"
			}

		}
	});
});

var timeout = null;
function kiemtratenkpi(){
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var kpi = {};
		kpi["ten"] = $('#ten').val().trim();
		kpi["id"] = $("#id").val().trim();
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/kiemtratenkpi",
			data : JSON.stringify(kpi),
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				
				if(result == "error"){
					$('#error').css("display", "block");
					$('#error').text("* Tên Đã Tồn Tại");
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
$('#ten').on('keyup keypress keydown', function(event){
	
	if($('#ten').val().trim() != ''){
		
		kiemtratenkpi();
		
	}else {
		$('#error').css("display", "none");
		$('#error').text("");
	}
	
})