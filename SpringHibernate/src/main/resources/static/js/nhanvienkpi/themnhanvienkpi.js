$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	$("#formNhanVienKpi").validate({
		rules : {
			nhanvien : {
				required : true
			},
			kpi : {
				required : true
			},
			ngayhoanthanh: {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}

			}

		},
		messages : {
			nhanvien : {
				required : "* Vui Lòng Chọn Nhân Viên"
			},
			kpi : {
				required : "* Vui Lòng Chọn KPI"
			},
            ngayhoanthanh : {
                required : "* Vui Lòng Nhập Ngày Hoàn Thành"
			}

		}
	});
});

var timeout = null;
function kiemtratenkpi(){
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var kpi = {};
		kpi["ten"] = $('#ten').val().trim();
		kpi["id"] = $("#id").val().trim();
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/kiemtratenkpi",
			data : JSON.stringify(kpi),
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				
				if(result == "error"){
					$('#error').css("display", "block");
					$('#error').text("* Tên Đã Tồn Tại");
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
$('#ten').on('keyup keypress keydown', function(event){
	
	if($('#ten').val().trim() != ''){
		
		kiemtratenkpi();
		
	}else {
		$('#error').css("display", "none");
		$('#error').text("");
	}
	
});

$(document).ready(function(){
	var date = new Date();
	
	var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
    var month = date.getUTCMonth() + 1;
	$('#ngayhoanthanh').val(lastDay.getDate()+'/'+ month +'/'+date.getUTCFullYear());
	
});

function getKPI() {
    clearTimeout(timeout);
    timeout = setTimeout(function() {
        var id = $('#kpi :selected').val();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

        $.ajax({

            type : "POST",
            contentType : "application/json",
            url : contextPath + "/admin/getkpi",
            data: id,
            success : function(result) {
                var kieukpi= result.kieukpi;
                // console.log(kieutieuchi);
                if(kieukpi == 'so'   ){
					$('#chitieudangky').attr('type', 'number');
                    $('#chitieudangky').removeAttr('onkeypress');
					$('#addon').text('SỐ');

                }else if(kieukpi == 'phamtram'){
                    $('#chitieudangky').attr('type', 'number');
                    $('#chitieudangky').removeAttr('onkeypress');
                    $('#addon').text('%');
                }else if(kieukpi == 'tien') {
                    $('#chitieudangky').attr('type', 'text');
                    $('#chitieudangky').attr('onkeyup', 'showNumberToString();');
                    $('#addon').text('VNĐ');
                }


            },
            error : function(e) {

            }
        });
    }, 100);
};
$('#kpi').change(function () {
	getKPI();
});

function checkchitieudangky() {
    var input = $('#chitieudangky').val().replace(/\./g, "");
    console.log(input);
    if(input <= 0){
        $('#btn-submit').attr('type','button');
        $('#chitieudangky-error').css("display", "block");
        $('#chitieudangky-error').text("* Chỉ Tiêu Đăng Ký Phải Lớn Hơn 0");
    }else {
        $('#btn-submit').attr('type','submit');
        $('#chitieudangky-error').css("display", "none");
        $('#chitieudangky-error').text("");
    }
}
function showNumberToString() {
    checkchitieudangky();
    $("input[name='_chitieudangky']").on( "keyup", function( event ) {


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