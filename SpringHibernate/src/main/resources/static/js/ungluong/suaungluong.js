(function($, undefined) {

    "use strict";

    // When ready.
    $(function() {

        var $form = $( "#formLuong" );
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
    $("#formLuong").validate({
        rules : {
            nhanvien : {
                required : true
            },
            luong : {
                required : true,
                min : 0,
                normalizer : function(value) {

                    return $.trim(value);
                }

            },
            thuong : {
                required : true,
                min : 0,
                normalizer : function(value) {

                    return $.trim(value);
                }

            }

        },
        messages : {
            nhanvien : {
                required : "* Vui Lòng Chọn Nhân Viên"
            },
            luong : {
                required : "* Vui Lòng Nhập Lương",
                min : "* Lương Phải lớn Hơn Hoặc Bằng 0"


            },
            thuong : {
                required : "* Vui Lòng Nhập Thưởng",
                min : "* Thưởng Phải lớn Hơn Hoặc Bằng 0"


            }

        }
    });
});

var timeout = null;
function kiemtraluong(){
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		
		var thang = $('#thang :selected').val();
		var nam = $('#nam :selected').val();
		
		var id = $("#id").val().trim();
		
		
		nhanvien=$('#nhanvien :selected').val();
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
		$.ajax({

			type : "POST",
			//contentType : "application/json",
			url : contextPath + "/admin/kiemtraluong",
			data : {id,thang,nam,nhanvien},
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				
				if(result == "error"){
					$('#error').css("display", "block");
					$('#error').text("* Lương Của Nhân Viên Đã Tồn Tại");
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
$('#formLuong').change(function(){
	kiemtraluong();
});
$('#btn-submit').click(function (event) {
    var sotienung = Number($('#sotienung').val().replace(/\.|,|\s/g,''));
    if(sotienung <= 0){
        $('#sotienung').focus();
        $('#_sotienung-error').css("display", "block");
        $('#_sotienung-error').text(" * Vui Lòng Nhập Số Tiền Ứng Lớn Hơn 0");
        event.preventDefault();
    }else {
        $('#_sotienung-error').css("display", "none");
        $('#_sotienung-error').text("");
    }
});