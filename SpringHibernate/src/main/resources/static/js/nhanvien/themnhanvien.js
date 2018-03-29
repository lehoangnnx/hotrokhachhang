(function($, undefined) {

    "use strict";

    // When ready.
    $(function() {

        var $form = $( "#formNhanVien" );
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
	$("#formNhanVien").validate({
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
				
			},
			luong : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}
				
			},
			
			chietkhau : {
				required : true,

				normalizer : function(value) {

					return $.trim(value);
				}
				
			}, 
			bophan : {
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
			,
			luong : {
				required : "* Vui Lòng Nhập Lương"
				
			}
			,
			chietkhau : {
				required : "* Vui Lòng Nhập Chiết Khấu",
				// min : "* Chiết Khấu Phải Lớn Hơn 0"
				
			}, 
			bophan : {
				required : "* Vui Lòng Chọn Bộ Phận"
			}

		}
	});
});

var timeout = null;
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

