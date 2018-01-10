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
				
				
				
				var id = result.id;
		    	var soluong = parseInt($('#soluong').val());
		    	var giaban = parseFloat(result.giaban);
		    	
		    	var thanhtien = soluong * giaban;
		    	
		    	
		    	
				
			    	if($('#idhh'+id).text() != ''){
			    		var soluongcu = parseInt($('#soluonghh'+id).val());
			    		$('#soluonghh'+id).val(soluongcu + soluong);
			    		
			    		var soluongmoi = parseInt($('#soluonghh'+id).val());
			    		$('#thanhtienhh'+id).val(soluongmoi * giaban);
			    	}else {
			    		//Get the reference of the Table's TBODY element.
						var tBody = $("#example1 > TBODY")[0];
						 
					    //Add Row.
					    row = tBody.insertRow(-1);

					    //Add Name cell.
					    var cell = $(row.insertCell(-1));
			    		cell.html('<input hidden value="'+id+'" name="idhh" ><span id="idhh'+id+'">'+id+'</span>');
				    	
			    		cell = $(row.insertCell(-1));
				    	cell.html('<span id="mahanghh'+id+'">'+result.mahang+'</span>');
			    		
				    	cell = $(row.insertCell(-1));
				    	cell.html('<span id="tenhanghh'+id+'">'+result.tenhang+'</span>');
				    	
				    	cell = $(row.insertCell(-1));
				    	cell.html('<input name="giabanhh" id="giabanhh'+id+'" type="number" value="'+giaban+'" >');
				    	
				    	cell = $(row.insertCell(-1));
				    	cell.html('<input name="soluonghh" id="soluonghh'+id+'" type="number" value="'+soluong+'" >');
				    	
				    	cell = $(row.insertCell(-1));
				    	cell.html('<input name="thanhtienhh" id="thanhtienhh'+id+'" type="number" value="'+thanhtien+'" >');
				    	
				    	cell = $(row.insertCell(-1));
				    	cell.html('<a onclick="Remove(this,'+id+');" href="javascript:void(0);"> <i style="color: red;" class="fa fa-close" aria-hidden="true" title="Sửa"> </i></a>');
				    
			    	}
			    	
				
			},
			error : function(e) {

			}
		});
	}, 100);
};
function Remove(button,id) {
    //Determine the reference of the Row using the Button.
    var row = $(button).closest("TR");
    var tenhang = $('#tenhanghh'+id).text();
    var mahang = $('#mahanghh'+id).text();
    if (confirm("Bạn Muốn Xóa : " + mahang +' - '+ tenhang)) {

        //Get the reference of the Table.
        var table = $("#example1")[0];

        //Delete the Table row using it's Index.
        table.deleteRow(row[0].rowIndex);
    }
};
$('#btn-thhvcthd').click(function (){
	
	if($('#soluong').val() > 0){
		$('.odd').remove();
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

$(document).ready(function(){
	var dateObj = new Date();
	var month = dateObj.getUTCMonth() + 1;
	var day = dateObj.getUTCDate();
	var year = dateObj.getUTCFullYear();
	var minutes = dateObj.getUTCMinutes();
	var seconds = dateObj.getUTCSeconds();
	var milliseconds = dateObj.getUTCMilliseconds();
	var newdate ='HD'+ year  + month + day  + minutes  + seconds  + milliseconds;
	$('#sohoadon').val(newdate);
});

