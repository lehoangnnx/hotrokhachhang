$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	$("#formHangHoa").validate({
		rules : {
			mahang : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			tenhang : {
				required : true,
				
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			gianhap : {
				required : true,
				
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			giaban : {
				required : true,
				
				normalizer : function(value) {

					return $.trim(value);
				}

			}/*,
			giakhuyenmai : {
				required : true,
				
				normalizer : function(value) {

					return $.trim(value);
				}

			}*/,
			donvitinh : {
				required : true,
				
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			nhomhang : {
				required : true
			}
			/*,
			mota : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}
			}*/

		},
		messages : {
			mahang : {
				required : "* Vui Lòng Nhập Mã Hàng"
				
			},
			tenhang : {
				required : "* Vui Lòng Nhập Tên Hàng"
				
				

			},
			gianhap : {
				required : "* Vui Lòng Nhập Giá Nhập"
				
				

			},
			giaban : {
				required : "* Vui Lòng Nhập Giá Bán"
				

			}/*,
			giakhuyenmai : {
				required : "* Vui Lòng Nhập Giá Khuyến Mãi"
				
				

			}*/,
			donvitinh : {
				required : "* Vui Lòng Nhập Đơn Vị Tính"
				
				

			},
			nhomhang : {
				required: " * Vui Lòng Chọn Nhóm Hàng Hóa"
			}
			/*,
			mota : {
				required : "* Vui Lòng Nhập Mô Tả"
			}*/

		}
	});
});
$("input").focusout(function(){
    
    
    if(parseFloat($("#giaban").val()) < parseFloat($("#gianhap").val()))
    {
        $("#_giaban-error").css("display","block");
        $('#_giaban-error').text("* Giá Bán Phải Lớn Hơn Giá Nhập");
		$('#btn-submit').attr('type','button');
    }else {

        $("#_giaban-error").css("display","none");
        $('#_giaban-error').text("");
    }
    if($("#giakhuyenmai").val() != '' ){
	    if(parseFloat($("#giakhuyenmai").val()) < parseFloat($("#gianhap").val()))
	    {
	        $("#_giakhuyenmai-error").css("display","block");
	        $('#_giakhuyenmai-error').text("* Giá Khuyến Mãi Phải Lớn Hơn Giá Nhập");
			$('#btn-submit').attr('type','button');
	    }else {
	    	 $("#_giakhuyenmai-error").css("display","none");
	         $('#_giakhuyenmai-error').text("");
	         $('#btn-submit').attr('type','submit');
	    }
    }else {
    	$("#_giakhuyenmai-error").css("display","none");
        $('#_giakhuyenmai-error').text("");
        $('#btn-submit').attr('type','submit');
    }
    
    
    if(parseFloat($("#giaban").val()) > parseFloat($("#gianhap").val()) &&
    		parseFloat($("#giakhuyenmai").val()) > parseFloat($("#gianhap").val())) {
    	
 		$('#btn-submit').attr('type','submit'); 
    }
});
var timeout = null;
function kiemtramahang(){
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var hanghoa = {};
		hanghoa["mahang"] = $('#mahang').val().trim();
		hanghoa["id"] = $("#id").val().trim();
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/kiemtramahang",
			data : JSON.stringify(hanghoa),
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				console.log(result);
				if(result == "error"){
					$('#error').css("display", "block");
					$('#error').text("* Mã Hàng Đã Tồn Tại");
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
$('#mahang').on('keyup keypress keydown', function(event){
	
	if($('#mahang').val().trim() != ''){
		
		kiemtramahang();
		
	}else {
		$('#error').css("display", "none");
		$('#error').text("");
	}
	
})