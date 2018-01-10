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
			    		console.log("if");
			    		var soluongcu = parseInt($('#soluonghh'+id).val());
			    		$('#soluonghh'+id).val(soluongcu + soluong);
			    		
			    		var soluongmoi = parseInt($('#soluonghh'+id).val());
			    		$('#thanhtienhh'+id).val(soluongmoi * giaban);
			    	}else {
			    		console.log("else");
			    		//Get the reference of the Table's TBODY element.
						var tBody = $("#example1 > TBODY")[0];
						 
					    //Add Row.
					    row = tBody.insertRow(-1);

					    //Add Name cell.
					    var cell = $(row.insertCell(-1));
			    		cell.html('<input hidden value="0" name="idcthd" ><input hidden value="'+id+'" name="idhh" ><span id="idhh'+id+'">'+id+'</span>');
				    	
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
				    	cell.html('<a onclick="Remove(this,'+id+',0);" href="javascript:void(0);"> <i style="color: red;" class="fa fa-close" aria-hidden="true" title="Sửa"> </i></a>');
				    
			    	}
			    	
				
			},
			error : function(e) {

			}
		});
	}, 100);
};
function updateChiTietHoaDonById(id) {
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var chitiethoadon = {};
		chitiethoadon["id"] = id;
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
		
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/updatechitiethoadon",
			data:  JSON.stringify(chitiethoadon),
			success : function(result) {
				
			},
			error : function(e) {

			}
		});
	}, 100);
};
function Remove(button,id,idcthd) {
    //Determine the reference of the Row using the Button.
    var row = $(button).closest("TR");
    var tenhang = $('#tenhanghh'+id).text();
    var mahang = $('#mahanghh'+id).text();
   
    if (confirm("Bạn Muốn Xóa : " + mahang +' - '+ tenhang)) {
    	if(idcthd != 0){
    		updateChiTietHoaDonById(idcthd);
    	}
    	
        //Get the reference of the Table.
        var table = $("#example1")[0];

        //Delete the Table row using it's Index.
        table.deleteRow(row[0].rowIndex);
    }
};
$('#btn-thhvcthd').click(function (){
	
	if($('#soluong').val() > 0){
		//$('.odd').remove();
		$('#_hanghoa-error').css("display", "none");
		$('#_hanghoa-error').text("");
		getHangHoaById();
	}else {
		
		$('#_hanghoa-error').css("display", "block");
		$('#_hanghoa-error').text("* Vui Lòng Nhập Số Lượng");

	}
	
	
    
});



