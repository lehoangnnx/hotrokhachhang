$(document).ready(function() {
	$('#dkieutieuchi').hide();
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	$("#formChamSoc").validate({
		rules : {
			nhanvienbanhang : {
				required : true
			},
			
			nhanviengiaohang : {
				required : true
			},
			khachhang : {
				required : true
			},
			lan : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			noidung : {
				required : true,
				
				normalizer : function(value) {

					return $.trim(value);
				}

			}

		},
		messages : {
			nhanvienbanhang : {
				required :  '* Vui Lòng Chọn Nhân Viên Bán Hàng'
			},
			
			nhanviengiaohang : {
				required :  '* Vui Lòng Chọn Nhân Viên Giao Hàng'
			},
			khachhang : {
				required :  '* Vui Lòng Chọn Khách Hàng'
			},
			lan : {
				required : "* Vui Lòng Nhập Lần Chăm Sóc"
				

			},
			noidung : {
				required : "* Vui Lòng Nhập Nội Dung"
				

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
				
				
				$('.odd').remove();
				var id = result.id;
				var tentieuchi = result.tentieuchi;
		    	var kieutieuchitccs = $('#kieutieuchitccs').val();
		    	if(kieutieuchitccs == 'true'){
		    		kieutieuchitccs = 'Tốt';
		    	}
		    	if(kieutieuchitccs == 'false'){
		    		kieutieuchitccs = 'Chưa Tốt';
		    	}
			    	if($('#idtccs'+id).text() != ''){
			    		
			    		$('#kieutieuchitccs'+id).text(kieutieuchitccs);
			    	}else {
			    		//Get the reference of the Table's TBODY element.
						var tBody = $("#tblctcs > TBODY")[0];
						 
					    //Add Row.
					    row = tBody.insertRow(-1);

					    //Add Name cell.
					    var cell = $(row.insertCell(-1));
			    		cell.html('<input hidden value="'+id+'" name="idtccs" ><span id="idtccs'+id+'">'+id+'</span>');
				    	
			    		cell = $(row.insertCell(-1));
				    	cell.html('<span id="tentieuchitccs'+id+'">'+tentieuchi+'</span>');
			    		
				    	
				    	cell = $(row.insertCell(-1));
				    	cell.html('<input hidden value="'+$('#kieutieuchitccs').val()+'" name="kieutieuchitccs" ><span  id="kieutieuchitccs'+id+'"   >'+kieutieuchitccs+'</span>');
				    	
				    	
				    	
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
        var table = $("#tblctcs")[0];

        //Delete the Table row using it's Index.
        table.deleteRow(row[0].rowIndex);
    }
};
function checkGiaTriTieuChiCHamSoc(){
	var kieutieuchitccs = $('#kieutieuchitccs').val();
	console.log(kieutieuchitccs);
	if( kieutieuchitccs > 0){

		$('#_diem-error').css("display", "none");
		$('#_diem-error').text("");
		getTieuChiChamSocId();
	}else {
		
		$('#_diem-error').css("display", "block");
		$('#_diem-error').text("* Vui Lòng Nhập Lớn Hơn 0");

	}
	
	//getTieuChiChamSocId();
	//console.log("â");
};

function getKieuTieuChi() {
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
			url : contextPath + "/admin/getkieutieuchi",
			data: id,
			success : function(result) {
				var kieutieuchi = result.kieutieuchi;
			   // console.log(kieutieuchi);
			    if(kieutieuchi == 'so'   ){
                    $('#dkieutieuchi').show();
			    	var html = '<input type="number" id="kieutieuchitccs" class="form-control" value="0" ' +
						 ' placeholder="Nhập Điểm"> <span class="input-group-btn"> ' +
						' <button id="btn-ttccsvctcs" type="button" onclick="checkGiaTriTieuChiCHamSoc();"  '+
							'class="btn btn-info btn-flat">Thêm</button>'+
					'</span>';
			    	$('#dkieutieuchi').html(html);
			    }else if(kieutieuchi == 'tien'){
                    $('#dkieutieuchi').show();
                    var html = '<input name="kieutieuchitccs_tien" type="text" id="kieutieuchitccs" class="form-control" value="0" ' +
                        ' onkeypress="showNumberToString();" placeholder="Nhập Số Tiền"> <span class="input-group-btn"> ' +
                        ' <button id="btn-ttccsvctcs" type="button" onclick="checkGiaTriTieuChiCHamSoc();"  '+
                        'class="btn btn-info btn-flat">Thêm</button>'+
                        '</span>';
                    $('#dkieutieuchi').html(html);
				}else if(kieutieuchi == 'cokhong') {
                    $('#dkieutieuchi').show();
			    	var html = '<select class="form-control select2" name="" id="kieutieuchitccs"'+
						'style="width: 100%;">'+
				'	<option value="true" selected="selected">Tốt</option>'+		
					'<option value="false">Chưa Tốt</option>'+
				'	</select> <span class="input-group-btn">'+
				'	<button id="btn-ttccsvctcs" type="button" onclick="getTieuChiChamSocId();"'+
					'	class="btn btn-info btn-flat">Thêm</button>'+
					'	</span>';
		    	$('#dkieutieuchi').html(html);
			    }else {
                    $('#dkieutieuchi').hide();
				}
			    	
				
			},
			error : function(e) {

			}
		});
	}, 100);
};

function showNumberToString() {

    $("input[name='kieutieuchitccs_tien']").on( "keyup", function( event ) {


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
$('#tieuchichamsoc').change(function(){
	
	getKieuTieuChi();
});
function addDays(dateObj, numDays) {
	   dateObj.setDate(dateObj.getDate() + numDays);
	   return dateObj;
};
$(document).ready(function(){
	var dateObj = new Date();
	var month = dateObj.getUTCMonth() + 1;
	var day = dateObj.getUTCDate();
	var year = dateObj.getUTCFullYear();
	var nextDay = addDays(dateObj , 7); // Cộng 7 ngày vào ngày hiện tại
	
	 // Báo kết quả
	$('#ngay').val(day+'/'+month+'/'+year);
	$('#ngaycstiep').val(nextDay.getUTCDate()+'/'+ (nextDay.getUTCMonth() + 1) +'/'+nextDay.getUTCFullYear());
});

function getSoLanChamSocVaDamPhanKhachHang() {
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var id = $('#khachhang :selected').val();
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
		
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/getsolanchamsocvadamphankhachhang",
			data: id ,
			success : function(result) {
				
				$('#spsolanchamsoc').text(result.solanchamsoc);
				$('#spsolandamphan').text(result.solandamphan);
			    $('#lan').val(parseInt(result.solanchamsoc) + parseInt(result.solandamphan) + 1);
				
			},
			error : function(e) {

			}
		});
	}, 100);
};
$('#khachhang').change(function(){

	getSoLanChamSocVaDamPhanKhachHang();
});

$('#khachhang').change(function(){
	
	gethoadontheokhachhang()
});

function gethoadontheokhachhang() {
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var id = $('#khachhang :selected').val();
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
		
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/gethoadontheokhachhang",
			data: id ,
			success : function(result) {
				var html ='';
				for(var i = 0; i< Object.keys(result).length ; i++){
					html += '<option value="'+result[i].id+'">'+result[i].sohoadon+'</option>';
				}
				html+='<option value="0">Không</option>';
				$('#hoadon').html(html);
				
			},
			error : function(e) {

			}
		});
	}, 100);
};
