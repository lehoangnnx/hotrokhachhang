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


    $('input[name*="hh"]').each(function () {
        var input = $(this).val().replace(/\.|,|\s/g,'.');

        $(this).val(input);
    });

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
                var kieugia = $('#kieugia').val();

                if(result.giakhuyenmai == null && kieugia == 'giakhuyenmai'){
                    $('#_hanghoa-error').css("display", "block");
                    $('#_hanghoa-error').text("* Hàng Hóa Này Không Có Giá Khuyến Mãi");
                }else {
                    var giaban = 0;
                    if (kieugia == 'giabansi') {
                        giaban = Number(result.giaban);
                    } else if (kieugia == 'giabanle') {
                        giaban = Number(result.giabanle);
                    } else if (kieugia == 'giakhuyenmai') {
                        giaban = Number(result.giakhuyenmai);
                    }
                    var id = result.id;
                    var soluong = Number($('#soluong').val());

                    var thanhtien = soluong * giaban;
                    if ($('#idhh' + id).text() != '') {
                        var soluongcu = Number($('#soluonghh' + id).val());
                        $('#soluonghh' + id).val(soluongcu + soluong);

                        var soluongmoi = Number($('#soluonghh' + id).val());
                       /* var giabanhhold = Number($('#giabanhh' + id).val().replace(/\.|,|\s/g, ''));
                        var thanhtiennew = soluongmoi * giabanhhold;*/
                        var thanhtiennew =soluongmoi * giaban;
                        $('#giabanhh'+id).val(changeNumberToString(giaban));
                        $('#thanhtienhh' + id).val(changeNumberToString(thanhtiennew));
                    } else {
                        //console.log("else");
                        //Get the reference of the Table's TBODY element.
                        var tBody = $("#tblcthd > TBODY")[0];

                        //Add Row.
                        row = tBody.insertRow(-1);

                        //Add Name cell.
                        var cell = $(row.insertCell(-1));
                        cell.html('<input hidden value="0" name="idcthd" ><input hidden value="' + id + '" name="idhh" ><span id="idhh' + id + '">' + id + '</span>');

                        cell = $(row.insertCell(-1));
                        cell.html('<span id="mahanghh' + id + '">' + result.mahang + '</span>');

                        cell = $(row.insertCell(-1));
                        cell.html('<span id="tenhanghh' + id + '">' + result.tenhang + '</span>');

                        cell = $(row.insertCell(-1));
                        cell.html('<input onkeyup="showNumberToString();" onchange="capnhatthanhtien(' + id + ');kiemTraGiaBan(' + id + ');"   name="giabanhh" id="giabanhh' + id + '" type="text" value="' + changeNumberToString(giaban) + '" >');

                        cell = $(row.insertCell(-1));
                        cell.html('<input min="0" onchange="capnhatthanhtien(' + id + ');"  name="soluonghh" id="soluonghh' + id + '" type="number" value="' + soluong + '" >');

                        cell = $(row.insertCell(-1));
                        cell.html('<input onkeyup="showNumberToString();"  name="thanhtienhh" id="thanhtienhh' + id + '" type="text" value="' + changeNumberToString(thanhtien) + '" >');

                        cell = $(row.insertCell(-1));
                        cell.html('<a onclick="Remove(this,' + id + ',0);" href="javascript:void(0);"> <i style="color: red;" class="fa fa-close" aria-hidden="true" title="Sửa"> </i></a>');

                    }
                    settongtien();
                    setcongno();
                }
			},
			error : function(e) {

			}
		});
	}, 100);
};


var trangthai  = [];
function kiemTraGiaBan(id) {
   /*$('input[name="giabanhh"]').each(function(){
        var input = $(this); // This is the jquery object of the input, do what you will\
        console.log(input.val());
    });*/

    clearTimeout(timeout);
    timeout = setTimeout(function() {
        var hanghoa = {};
        hanghoa["giaban"] = $('#giabanhh'+id).val().replace(/\.|,|\s/g,'');
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
                    trangthai.push(id);

                    for(var i = 0 ; i < trangthai.length ; i++){
                      var _id=trangthai[i];
                        $('#giabanhh'+_id).focus();
                        $('#btn-thhvcthd').hide();
                        $('#btn-submit').hide();
                        $('#giabanhh'+_id).css('border', '1px solid red');
                        $('#_giaban-error').css("display", "block");
                        $('#_giaban-error').text("* Giá Bán Hiện Tại Nhỏ Hơn Giá Nhập");
                    }

                }else {

                    var index =trangthai.indexOf(id);
                    if (index !== -1){
                        trangthai.splice(index, 1);

                    }
                    $('#giabanhh' + id).css('border', '');
                    if(trangthai.length == 0) {

                            $('#btn-thhvcthd').show();
                            $('#btn-submit').show();

                            $('#_giaban-error').css("display", "none");
                            $('#_giaban-error').text("");

                    }

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

        var tongtien = $('#tongtien').val().replace(/\.|,|\s/g,'');
        var thanhtienhh = $('#thanhtienhh'+id).val().replace(/\.|,|\s/g,'');
        var _tongtien = Number(tongtien) - Number(thanhtienhh);
        var output = changeNumberToString(_tongtien);
        $('#tongtien').val(output);


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
        tongtien += Number(getthanhtienhh.item(i).value.replace(/\.|,|\s/g,''));

    }
    $('#tongtien').val(changeNumberToString(tongtien));
};
function capnhatthanhtien(id){
    var giaban = Number($('#giabanhh'+id).val().replace(/\.|,|\s/g,''));
    var soluong = Number($('#soluonghh'+id).val().replace(/\.|,|\s/g,''));
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
    var tongtien = Number($('#tongtien').val().replace(/\.|,|\s/g,''));
    var tiendatra = Number($('#tiendatra').val().replace(/\.|,|\s/g,''));
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
});

function parseDate(str) {
    var mdy = str.split('/');
    return new Date(mdy[2], mdy[1], mdy[0]);
};
function checkngay(event) {
    var dateObj = new Date();
    var month = dateObj.getUTCMonth() + 1;
    var day = dateObj.getUTCDate();
    var year = dateObj.getUTCFullYear();

    var ngaylap = parseDate($('#ngaylap').val()).getTime();
    var ngayxuat = parseDate($('#ngayxuat').val()).getTime();
    var ngayhientai = parseDate(day+'/'+month+'/'+year).getTime();

    if(ngaylap > ngayhientai){

        event.preventDefault();

        $('#ngaylap-error').css("display", "block");
        $('#ngaylap-error').text("* Ngày Lập Không Lớn Hơn Ngày Hiện Tại");
        return;
    }else if(ngayxuat > ngayhientai){
        event.preventDefault();

        $('#ngayxuat-error').css("display", "block");
        $('#ngayxuat-error').text("* Ngày Xuất Không Lớn Hơn Ngày Hiện Tại");
        return;
    }else {
        $('#ngaylap-error').css("display", "none");
        $('#ngaylap-error').text("");
        $('#ngayxuat-error').css("display", "none");
        $('#ngayxuat-error').text("");
        $('#ngaythanhtoan-error').css("display", "none");
        $('#ngaythanhtoan-error').text("");
        return;
    }
};


$('#khachhang').change(function(){
    var idKhachHang = $(this).val();
    clearTimeout(timeout);
    timeout = setTimeout(function() {
        var id = idKhachHang;
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

        $.ajax({

            type : "POST",
            contentType : "application/json",
            url : contextPath + "/admin/getthongtinkhachhang",
            data: id ,
            success : function(result) {
                $('#diachigiaohang').val(result.diachi);
                $('#sodienthoai').val(result.sodienthoai);
            },
            error : function(e) {

            }
        });
    }, 100);
});
