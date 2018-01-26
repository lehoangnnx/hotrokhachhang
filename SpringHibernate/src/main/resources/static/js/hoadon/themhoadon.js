$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	$("#formHoaDon").validate({
		rules : {
			ngaylap : {
				required : true
			},
            ngayxuat : {
                required : true
            },
            ngaythanhtoan : {
                required : true
            },
            tongtien : {
                required : true
            },
            tiendatra: {
                required : true
            },
            congno : {
                required : true
            },
            diachigiaohang : {
                required : true
            },
            sodienthoai : {
                required : true
            }
		},
		messages : {
            ngaylap : {
                required : '* Vui Lòng Nhập Ngày Lập'
            },
            ngayxuat : {
                required : '* Vui Lòng Nhập Ngày Xuất'
            },
            ngaythanhtoan : {
                required : '* Vui Lòng Nhập Ngày Thanh Toán'
            },
            tongtien : {
                required : '* Vui Lòng Nhập Tổng Tiền'
            },
            tiendatra: {
                required : '* Vui Lòng Tiền Đã Trả'
            },
            congno : {
                required : '* Vui Lòng Nhập Công Nợ'
            },
            diachigiaohang : {
                required : '* Vui Lòng Nhập Địa Chỉ Giao Hàng'
            },
            sodienthoai : {
                required :'* Vui Lòng Nhập Số Điện Thoại'
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
						var tBody = $("#tblcthd > TBODY")[0];
						 
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
				    	cell.html('<input min="0" onchange="capnhatthanhtien('+id+');"   name="giabanhh" id="giabanhh'+id+'" type="number" value="'+giaban+'" >');

				    	cell = $(row.insertCell(-1));
				    	cell.html('<input min="0" onchange="capnhatthanhtien('+id+');"  name="soluonghh" id="soluonghh'+id+'" type="number" value="'+soluong+'" >');
				    	
				    	cell = $(row.insertCell(-1));
				    	cell.html('<input min="0" name="thanhtienhh" id="thanhtienhh'+id+'" type="number" value="'+thanhtien+'" >');
				    	
				    	cell = $(row.insertCell(-1));
				    	cell.html('<a onclick="Remove(this,'+id+');" href="javascript:void(0);"> <i style="color: red;" class="fa fa-close" aria-hidden="true" title="Sửa"> </i></a>');
				    
			    	}
                settongtien();
                setcongno();

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
        $('#tongtien').val(parseInt($('#tongtien').val()) - parseInt($('#thanhtienhh'+id).val()));

        //Get the reference of the Table.
        var table = $("#tblcthd")[0];

        //Delete the Table row using it's Index.
        table.deleteRow(row[0].rowIndex);

        setcongno();
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

$('#tblcthd').change(function () {
    settongtien();
    setcongno();
});
function settongtien(){
    var getthanhtienhh = document.getElementsByName('thanhtienhh');
    var tongtien = 0;
    for (var i = 0; i < getthanhtienhh.length; i++) {
        tongtien += parseInt(getthanhtienhh.item(i).value);

    }
    $('#tongtien').val(tongtien);
};
function capnhatthanhtien(id){
	var giaban = $('#giabanhh'+id).val();
	var soluong = $('#soluonghh'+id).val();

	$('#thanhtienhh'+id).val(parseFloat(giaban) * parseInt(soluong));
}
$('#formHoaDon').change(function () {
    setcongno();
   
});
function setcongno() {
    $('#congno').val(parseInt($('#tongtien').val()) - parseInt($('#tiendatra').val()));
};
$(document).ready(function(){
	var dateObj = new Date();
	var month = dateObj.getUTCMonth() + 1;
	var day = dateObj.getUTCDate();
	var year = dateObj.getUTCFullYear();
	
	$('#ngaylap').val(day+'/'+month+'/'+year);
	$('#ngayxuat').val(day+'/'+month+'/'+year);
	$('#ngaythanhtoan').val(day+'/'+month+'/'+year);
	
});
$('#btn-submit').click(function(){
	 var tbody = $("#tblcthd tbody");

	    if (tbody.children().length == 0) {
	    	$('#soluong').focus();
	    	$('#btn-submit').attr('type', 'button');
	    	$('#_hanghoa-error').css("display", "block");
			$('#_hanghoa-error').text("* Vui Lòng Chọn Hàng Hóa");
	    }else {
	    	$('#_hanghoa-error').css("display", "none");
			$('#_hanghoa-error').text("");
	    	$('#btn-submit').attr('type', 'submit');
	    }
})
	
	

