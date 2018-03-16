(function($, undefined) {

    "use strict";

    // When ready.
    $(function() {

        var $form = $( "#formHoaDon" );
        var $input = $form.find( "input[name*='_money']" );

        $input.on( "keyup", function( event ) {


            // When user select text in the document, also abort.
            var selection = window.getSelection().toString();
            if ( selection !== '' ) {
                return;
            }

            // When the arrow keys are pressed, abort.
            if ( $.inArray( event.keyCode, [38,40,37,39] ) !== -1 ) {
                return;
            }


            var $this = $( this );

            // Get the value.
            var input = $this.val();

            var input = input.replace(/[\D\s\._\-]+/g, "");
            input = input ? parseInt( input, 10 ) : 0;

            $this.val( function() {
                // return ( input === 0 ) ? "" : input.toLocaleString( "en-US" );
                return ( input < 0 ) ? "" : input.toLocaleString( "it-IT" );
            } );
        } );

        /**
         * ==================================
         * When Form Submitted
         * ==================================
         */
        /*$form.on( "submit", function( event ) {

            var $this = $( this );
            var arr = $this.serializeArray();

            for (var i = 0; i < arr.length; i++) {
                arr[i].value = arr[i].value.replace(/[($)\s\._\-]+/g, ''); // Sanitize the values.
            };

            console.log( arr );

            event.preventDefault();
        });*/

    });
})(jQuery);



$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
    $("#formHoaDon").validate({
        rules : {
            nhanvienbanhang : {
                required : true
            },
            nhanvienchamsoc : {
                required : true
            },
            nhanviengiaohang : {
                required : true
            },
            khachhang : {
                required : true
            },
            sohoadon : {
                required : true,
                normalizer : function(value) {

                    return $.trim(value);
                }

            },
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
                required : true,
                min : 0
            },
            tiendatra: {
                required : true,
                min : 0
            },
            congno : {
                required : true,
                min : 0
            },
            diachigiaohang : {
                required : true
            },
            sodienthoai : {
                required : true
            }
        },
        messages : {
            nhanvienbanhang : {
                required :  '* Vui Lòng Chọn Nhân Viên Bán Hàng'
            },
            nhanvienchamsoc : {
                required :  '* Vui Lòng Chọn Nhân Viên Chăm Sóc'
            },
            nhanviengiaohang : {
                required :  '* Vui Lòng Chọn Nhân Viên Giao Hàng'
            },
            khachhang : {
                required :  '* Vui Lòng Chọn Khách Hàng'
            },
            sohoadon : {
                required :  '* Vui Lòng Nhập Số Hóa Đơn'

            },
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
                required : '* Vui Lòng Nhập Tổng Tiền',
                min : "* Vui Lòng Nhập Tổng Tiền Lớn Hơn 0"
            },
            tiendatra: {
                required : '* Vui Lòng Tiền Đã Trả',
                min : "* Vui Lòng Nhập Tiền Đã Trả Lớn Hơn 0"
            },
            congno : {
                required : '* Vui Lòng Nhập Công Nợ',
                min : "* Vui Lòng Nhập Tiền Còn Nợ Lớn Hơn 0"
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
                        var giabanhhold = parseFloat($('#giabanhh'+id).val().replace(/\./g,""));

                        var thanhtiennew =soluongmoi * giabanhhold;
                        $('#thanhtienhh'+id).val(changeNumberToString(thanhtiennew));
			    	}else {
			    		console.log("else");
			    		//Get the reference of the Table's TBODY element.
						var tBody = $("#tblcthd > TBODY")[0];
						 
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
                        cell.html('<input onkeyup="showNumberToString();" onchange="capnhatthanhtien('+id+');kiemTraGiaBan('+id+');"   name="giabanhh" id="giabanhh'+id+'" type="text" value="'+changeNumberToString(giaban)+'" >');

                        cell = $(row.insertCell(-1));
                        cell.html('<input min="0" onchange="capnhatthanhtien('+id+');"  name="soluonghh" id="soluonghh'+id+'" type="number" value="'+soluong+'" >');

                        cell = $(row.insertCell(-1));
                        cell.html('<input onkeyup="showNumberToString();"  name="thanhtienhh" id="thanhtienhh'+id+'" type="text" value="'+changeNumberToString(thanhtien)+'" >');

                        cell = $(row.insertCell(-1));
				    	cell.html('<a onclick="Remove(this,'+id+',0);" href="javascript:void(0);"> <i style="color: red;" class="fa fa-close" aria-hidden="true" title="Sửa"> </i></a>');
				    
			    	}
			    	settongtien();
	                setcongno();
				
			},
			error : function(e) {

			}
		});
	}, 100);
};

function kiemTraGiaBan(id) {
   /* $('input[name="giabanhh"]').each(function(){
        var input = $(this); // This is the jquery object of the input, do what you will\
        console.log(input.val());
    });*/

    clearTimeout(timeout);
    timeout = setTimeout(function() {
        var hanghoa = {};
        hanghoa["giaban"] = $('#giabanhh'+id).val().replace(/\./g,"");
        hanghoa["id"] = id;
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

        $.ajax({

            type : "POST",
            contentType : "application/json",
            url : contextPath + "/admin/kiemtragiaban",
            data:JSON.stringify(hanghoa),
            success : function(result) {
                if(result == 'success'){
                    $('#giabanhh'+id).focus();
                    $('#btn-thhvcthd').hide();
                    $('#btn-submit').hide();
                    $('#giabanhh'+id).css('border', '1px solid red');
                    $('#_giaban-error').css("display", "block");
                    $('#_giaban-error').text("* Giá Bán Hiện Tại Nhỏ Hơn Giá Nhập");
                }else {
                    $('#btn-thhvcthd').show();
                    $('#btn-submit').show();
                    $('#giabanhh'+id).css('border', '');
                    $('#_giaban-error').css("display", "none");
                    $('#_giaban-error').text("");
                }
            },
            error : function(e) {

            }
        });
    }, 100);

};


function changeNumberToString(__input) {


    var output = __input.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.")
    return output;
};

function showNumberToString() {

    $("input[name*='hh']").on( "keyup", function( event ) {


        // When user select text in the document, also abort.
        var selection = window.getSelection().toString();
        if ( selection !== '' ) {
            return;
        }

        // When the arrow keys are pressed, abort.
        if ( $.inArray( event.keyCode, [38,40,37,39] ) !== -1 ) {
            return;
        }


        var $this = $( this );

        // Get the value.
        var input = $this.val();

        var input = input.replace(/[\D\s\._\-]+/g, "");
        input = input ? parseInt( input, 10 ) : 0;

        $this.val( function() {
            // return ( input === 0 ) ? "" : input.toLocaleString( "en-US" );
            return ( input < 0 ) ? "" : input.toLocaleString( "it-IT" );
        } );
    } );
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
		//$('.odd').remove();
		$('#_hanghoa-error').css("display", "none");
		$('#_hanghoa-error').text("");
		getHangHoaById();
	}else {
		
		$('#_hanghoa-error').css("display", "block");
		$('#_hanghoa-error').text("* Vui Lòng Nhập Số Lượng");

	}
	
	
    
});


$('#tblcthd').change(function () {
    settongtien();
    setcongno();
});

function settongtien(){
    var getthanhtienhh = document.getElementsByName('thanhtienhh');
    var tongtien = 0;
    for (var i = 0; i < getthanhtienhh.length; i++) {
        tongtien += parseFloat(getthanhtienhh.item(i).value.replace(/\./g,""));

    }
    $('#tongtien').val(changeNumberToString(tongtien));
};
function capnhatthanhtien(id){
    var giaban = parseFloat($('#giabanhh'+id).val().replace(/\./g,""));
    var soluong = parseInt($('#soluonghh'+id).val().replace(/\./g,""));
    var thanhtien = giaban * soluong;
    $('#thanhtienhh'+id).val(changeNumberToString(thanhtien));
}
/*$('#formHoaDon').change(function () {
    setcongno();

});*/
$('#tongtien').change(function () {

    setcongno();
});
$('#tiendatra').change(function () {

    setcongno();
});
function setcongno() {
    var tongtien = $('#tongtien').val().replace(/\./g,"");
    var tiendatra = $('#tiendatra').val().replace(/\./g,"");
    var congno = tongtien - tiendatra;

    $('#congno').val(changeNumberToString(congno));
};
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
