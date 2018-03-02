$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	$("#formNhanVienKpi").validate({
		rules : {
			nhanvien : {
				required : true
			},
			kpi : {
				required : true
			},
			so: {
				required : true,
				min : 0,
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			ngayhoanthanh: {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}

			}, mucdohoanthanh : {
				min : 0
			}

		},
		messages : {
			nhanvien : {
				required : "* Vui Lòng Chọn Nhân Viên"
			},
			kpi : {
				required : "* Vui Lòng Chọn KPI"
			},
			so : {
				required : "* Vui Lòng Nhập Số",
				min : "* Số Phải Lớn Hơn Hoặc Bằng 0"

			},
			ngayhoanthanh : {
				required : "* Vui Lòng Nhập Ngày Hoàn Thành "
				

			},
			mucdohoanthanh : {
				min : "* Mức Độ Hoàn Thanh Phải Lớn Hơn Hoặc Bằng 0"
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
	
});

$(document).ready(function(){
	var date = new Date();
	
	var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
    var month = date.getUTCMonth() + 1;
	$('#ngayhoanthanh').val(lastDay.getDate()+'/'+ month +'/'+date.getUTCFullYear());
	
});