$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	$("#formHoaDon").validate({
		rules : {
			manhanvien : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			tennhanvien : {
				required : true,
				
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			socmnd : {
				required : true
				
			},
			noicap : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}
				
			},
			ngaycap : {
				required : true
				
			},
			diachi : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}
				
			},
			
			sodienthoai : {
				required : true
				
			}
			,
			ngayvaolam : {
				required : true
				
			}

		},
		messages : {
			manhanvien : {
				required : "* Vui Lòng Nhập Mã Nhân Viên"
				

			},
			tennhanvien : {
				required : "* Vui Lòng Nhập Tên Nhân Viên"
				

			},
			socmnd : {
				required : "* Vui Lòng Nhập Số CMND"
				
			},
			noicap : {
				required : "* Vui Lòng Nhập Nơi Cấp"
				
				
			},
			ngaycap : {
				required : "* Vui Lòng Nhập Ngày Cấp"
				
			},
			diachi : {
				required : "* Vui Lòng Nhập Địa Chỉ"
				
				
			},
			
			sodienthoai : {
				required : "* Vui Lòng Nhập Số Điện Thoại"
				
			}
			,
			ngayvaolam : {
				required : "* Vui Lòng Nhập Ngày Vào Làm"
				
			}

		}
	});
});

var timeout = null;

function getHangHoaById() {
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var id = $('#hanghoa :selected').val();
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
		
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/gethanghoabyid",
			data: id,
			success : function(result) {
				
				//Get the reference of the Table's TBODY element.
				var tBody = $("#example1 > TBODY")[0];
				 
			    //Add Row.
			    row = tBody.insertRow(-1);

			    //Add Name cell.
			    var cell = $(row.insertCell(-1));
			    	
			    	var soluong = $('#soluong').val();
			    	var giaban = parseFloat(result.giaban);
			    	var tongtien = soluong * giaban;
			    	cell.html(result.tenhang);
			    	cell = $(row.insertCell(-1));
			    	cell.html('<input type="number" value="'+giaban+'" >');
			    	cell = $(row.insertCell(-1));
			    	cell.html('<input type="number" value="'+soluong+'" >');
			    	cell = $(row.insertCell(-1));
			    	cell.html('<input type="number" value="'+tongtien+'" >');
			    	cell = $(row.insertCell(-1));
			    	cell.html('<a href="#"> <i style="color: red;" class="fa fa-close" aria-hidden="true" title="Sửa"> </i></a>');
			    
				
			},
			error : function(e) {

			}
		});
	}, 100);
};

$('#btn-thhvcthd').click(function (){
	
	if($('#soluong').val() > 0){
		$('#_hanghoa-error').css("display", "none");
		$('#_hanghoa-error').text("");
		getHangHoaById();
	}else {
		
		$('#_hanghoa-error').css("display", "block");
		$('#_hanghoa-error').text("* Vui Lòng Nhập Số Lượng");

	}
	
	
    
});

function kiemtramanhanvien() {
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var nhanvien = {};
		nhanvien["manhanvien"] = $('#manhanvien').val().trim();
		
		nhanvien["id"] = $("#id").val().trim();
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});

		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/kiemtramanhanvien",
			data : JSON.stringify(nhanvien),
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				console.log(result);
				if (result == "error") {
					$('#_manhanvien-error').css("display", "block");
					$('#_manhanvien-error').text("* Mã Nhân Viên Đã Tồn Tại");

					$('#btn-submit').attr('type', 'button');
				} 

				else {
					$('#_manhanvien-error').css("display", "none");
					$('#_manhanvien-error').text("");
					$('#btn-submit').attr('type', 'submit');
				}

			},
			error : function(e) {

			}
		});
	}, 100);
};
$('#manhanvien').on('keyup keypress keydown', function(event) {

	if ($('#manhanvien').val().trim() != '') {

		kiemtramanhanvien();

	} else {
		$('#_manhanvien-error').css("display", "none");
		$('#_manhanvien-error').text("");
	}

});

