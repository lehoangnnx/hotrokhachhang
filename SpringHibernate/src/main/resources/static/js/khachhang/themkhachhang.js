(function($, undefined) {

    "use strict";

    // When ready.
    $(function() {

        var $form = $( "#formKhachHang" );
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
	$("#formKhachHang").validate({
		rules : {
			loaikhachhang : {
				required : true
			},
			nhomkhachhang : {
				required : true
			},
			makh : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			ten : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}
			},
			sotienchamsoc : {
				min : 0
			},
            sotiendachamsoc : {
                min : 0
            },
            diem : {
                min : 0
            },
            solanchamsoc : {
                min : 0
            },
            solandamphan : {
                min : 0
            },
            diemtiemnang : {
                min : 0
            }

		},
		messages : {
			loaikhachhang : {
				required : "* Vui Lòng Chọn Loại Khách Hàng"
			},
			nhomkhachhang : {
				required : "* Vui Lòng Chọn Nhóm Khách Hàng"
			},
			makh : {
				required : "* Vui Lòng Nhập Mã Khách Hàng"
				

			},
			ten : {
				required : "* Vui Lòng Nhập Tên Khách Hàng"
			},
            sotienchamsoc : {
                min : "* Số Tiền Chăm Sóc Phải Lớn Hơn Hoặc Bằng 0"
            },
            sotiendachamsoc : {
                min : "* Số Tiền Đã Chăm Sóc Phải Lớn Hơn Hoặc Bằng 0"
            },
            diem : {
                min : "* Điểm Phải Lớn Hơn Hoặc Bằng 0"
            },
            solanchamsoc : {
                min : "* Số Lần Chăm Sóc Phải Lớn Hơn Hoặc Bằng 0"
            },
            solandamphan : {
                min : "* Số Lần Đàm Phám Phải Lớn Hơn Hoặc Bằng 0"
            },
            diemtiemnang : {
                min : "* Điểm Tiềm Năng Phải Lớn Hơn Hoặc Bằng 0"
            }

		}
	});
});

var timeout = null;
function kiemtramakhachhang(){
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var khachhang = {};
		khachhang["makh"] = $('#makh').val().trim();
		khachhang["id"] = $("#id").val().trim();
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/kiemtramakhachhang",
			data : JSON.stringify(khachhang),
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				console.log(result);
				if(result == "error"){
					$('#error').css("display", "block");
					$('#error').text("* Mã Khách Hàng Đã Tồn Tại");
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
$('#makh').on('keyup keypress keydown', function(event){
	
	if($('#makh').val().trim() != ''){
		
		kiemtramakhachhang();
		
	}else {
		$('#error').css("display", "none");
		$('#error').text("");
		
	}
	
});

