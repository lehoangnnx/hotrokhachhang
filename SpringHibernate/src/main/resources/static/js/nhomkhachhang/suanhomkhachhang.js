$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
    $("#formNhomKhachHang").validate({
        rules : {
            tennhom : {
                required : true,
                normalizer : function(value) {

                    return $.trim(value);
                }

            },
            sodiemtrentien : {
                required : true,
                min : 0,
                normalizer : function(value) {

                    return $.trim(value);
                }

            },
            sotientrendiem : {
                required : true,
                min : 0,
                normalizer : function(value) {

                    return $.trim(value);
                }

            },
            phantramtien : {
                required : true,
                min : 0,
                normalizer : function(value) {

                    return $.trim(value);
                }

            },
            diem : {
                required : true,
                min : 0,
                normalizer : function(value) {

                    return $.trim(value);
                }

            },
            phantram : {
                required : true,
                min : 0,
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
            tennhom : {
                required : "* Vui Lòng Nhập Tên Nhóm Khách Hàng"


            },
            sodiemtrentien : {
                required :  "* Vui Lòng Nhập Số Điểm Trên Tiền",
                min : "* Số Điểm Phải Lớn Hơn Hoặc Bằng 0"


            },
            sotientrendiem : {
                required :  "* Vui Lòng Nhập Số Tiền Trên Điểm",
                min : "* Số Tiền Phải Lớn Hơn Hoặc Bằng 0"


            },
            phantramtien : {
                required :  "* Vui Lòng Nhập Phần Trăm Trên Hóa Đơn",
                min : "* Phần Trăm Trên Hóa Đơn Phải Lớn Hơn Hoặc Bằng 0"

            },
            diem : {
                required : "* Vui Lòng Nhập Điểm",
                min : "* Điểm Phải Lớn Hơn Hoặc Bằng 0"

            },
            phantram : {
                required : "* Vui Lòng Nhập Phần Trăm",
                min : "* Phần Trăm Phải Lớn Hơn Hoặc Bằng 0"
            }/*,
			mota : {
				required : "* Vui Lòng Nhập Mô Tả"
			}*/

        }
    });
});

var timeout = null;
function kiemtratennhomkhachhang(){
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var nhomkhachhang = {};
		nhomkhachhang["tennhom"] = $('#tennhom').val().trim();
		nhomkhachhang["id"] = $("#id").val().trim();
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/kiemtratennhomkhachhang",
			data : JSON.stringify(nhomkhachhang),
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				console.log(result);
				if(result == "error"){
					$('#error').css("display", "block");
					$('#error').text("* Tên Nhóm Đã Tồn Tại");
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
$('#tennhom').on('keyup keypress keydown', function(event){
	
	if($('#tennhom').val().trim() != ''){
		
		kiemtratennhomkhachhang();
		
	}else {
		$('#error').css("display", "none");
		$('#error').text("");
	}
	
})