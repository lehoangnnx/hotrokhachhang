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

function getTieuChiChamSocId() {
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var id = $('#tieuchichamsoc :selected').val();
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
		
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/gettieuchichamsocid",
			data: id,
			success : function(result) {
				
				
				
				var id = result.id;
				var tentieuchi = result.tentieuchi;
		    	var diem = parseInt($('#diem').val());
		    	
		    	
		    	
		    	
				
			    	if($('#idtccs'+id).text() != ''){
			    		var diemcu = parseInt($('#diemtccs'+id).val());
			    		$('#diemtccs'+id).val(diem + diemcu);
			    		
			    		
			    	}else {
			    		//Get the reference of the Table's TBODY element.
						var tBody = $("#example1 > TBODY")[0];
						 
					    //Add Row.
					    row = tBody.insertRow(-1);

					    //Add Name cell.
					    var cell = $(row.insertCell(-1));
			    		cell.html('<input hidden value="'+id+'" name="idtccs" ><span id="idtccs'+id+'">'+id+'</span>');
				    	
			    		cell = $(row.insertCell(-1));
				    	cell.html('<span id="tentieuchitccs'+id+'">'+tentieuchi+'</span>');
			    		
				    	
				    	cell = $(row.insertCell(-1));
				    	cell.html('<input name="diemtccs" id="diemtccs'+id+'" type="number" value="'+diem+'" >');
				    	
				    	
				    	
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
    var tentieuchi = $('#tentieuchitccs'+id).text();
   
    if (confirm("Bạn Muốn Xóa : " + tentieuchi)) {

        //Get the reference of the Table.
        var table = $("#example1")[0];

        //Delete the Table row using it's Index.
        table.deleteRow(row[0].rowIndex);
    }
};
$('#btn-ttccsvctcs').click(function (){
	
	if($('#diem').val() > 0){
		$('.odd').remove();
		$('#_diem-error').css("display", "none");
		$('#_diem-error').text("");
		getTieuChiChamSocId();
	}else {
		
		$('#_diem-error').css("display", "block");
		$('#_diem-error').text("* Vui Lòng Nhập Điểm");

	}
	
	
    
});


