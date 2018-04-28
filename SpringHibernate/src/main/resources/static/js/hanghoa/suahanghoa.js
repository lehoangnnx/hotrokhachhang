(function($, undefined) {

    "use strict";

    // When ready.
    $(function() {

        var $form = $( "#formHangHoa" );
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

function showNumberToString() {

    $("input[name*='giakhuyenmai']").on( "keyup", function( event ) {


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
        input = input ? parseInt( input, 10 ) : "";

        $this.val( function() {
            // return ( input === 0 ) ? "" : input.toLocaleString( "en-US" );
            return ( input < 0 ) ? "" : input.toLocaleString( "it-IT" );
        } );
    } );
};
$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
    $("#formHangHoa").validate({
        rules : {
            mahang : {
                required : true,
                normalizer : function(value) {

                    return $.trim(value);
                }

            },
            tenhang : {
                required : true,

                normalizer : function(value) {

                    return $.trim(value);
                }

            },
            gianhap : {
                required : true,

                normalizer : function(value) {

                    return $.trim(value);
                }

            },
            giaban : {
                required : true,

                normalizer : function(value) {

                    return $.trim(value);
                }

            }/*,
			giakhuyenmai : {
				required : true,
				
				normalizer : function(value) {

					return $.trim(value);
				}

			}*/,
            giabanle : {
                required : true,

                normalizer : function(value) {

                    return $.trim(value);
                }

            },
            donvitinh : {
                required : true,

                normalizer : function(value) {

                    return $.trim(value);
                }

            },
            nhomhang : {
                required : true
            }
            /*,
            mota : {
                required : true,
                normalizer : function(value) {

                    return $.trim(value);
                }
            }*/

        },
        messages : {
            mahang : {
                required : "* Vui Lòng Nhập Mã Hàng"

            },
            tenhang : {
                required : "* Vui Lòng Nhập Tên Hàng"



            },
            gianhap : {
                required : "* Vui Lòng Nhập Giá Nhập"



            },
            giaban : {
                required : "* Vui Lòng Nhập Giá Bán Sỉ"


            }/*,
			giakhuyenmai : {
				required : "* Vui Lòng Nhập Giá Khuyến Mãi"



			}*/,
            giaban : {
                required : "* Vui Lòng Nhập Giá Bán Lẻ"


            },
            donvitinh : {
                required : "* Vui Lòng Nhập Đơn Vị Tính"



            },
            nhomhang : {
                required: " * Vui Lòng Chọn Nhóm Hàng Hóa"
            }
            /*,
            mota : {
                required : "* Vui Lòng Nhập Mô Tả"
            }*/

        }
    });
});
$("input").focusout(function(){
	console.log("s");
    kiemTraGia();
});

function kiemTraGia() {
    var giaban = Number($("#giaban").val().replace(/\.|,|\s/g,''));
    var giabanle = Number($("#giabanle").val().replace(/\.|,|\s/g,''));
    var gianhap = Number($("#gianhap").val().replace(/\.|,|\s/g,''));
    var giakhuyenmai =Number($("#giakhuyenmai").val().replace(/\.|,|\s/g,''));
    if(giaban < gianhap)
    {
        $("#_giaban-error").css("display","block");
        $('#_giaban-error').text("* Giá Bán Sỉ Phải Lớn Hơn Giá Nhập");
        $('#btn-submit').attr('type','button');
    }else {

        $("#_giaban-error").css("display","none");
        $('#_giaban-error').text("");
    }
    if(giabanle < gianhap)
    {
        $("#_giabanle-error").css("display","block");
        $('#_giabanle-error').text("* Giá Bán Lẻ Phải Lớn Hơn Giá Nhập");
        $('#btn-submit').attr('type','button');
    }else {

        $("#_giabanle-error").css("display","none");
        $('#_giabanle-error').text("");
    }
    if(giakhuyenmai != '' ){
        if(giakhuyenmai < gianhap)
        {
            $("#_giakhuyenmai-error").css("display","block");
            $('#_giakhuyenmai-error').text("* Giá Khuyến Mãi Phải Lớn Hơn Giá Nhập");
            $('#btn-submit').attr('type','button');
        }else {
            $("#_giakhuyenmai-error").css("display","none");
            $('#_giakhuyenmai-error').text("");
            /*$('#btn-submit').attr('type','submit');*/
        }
    }else {
        $("#_giakhuyenmai-error").css("display","none");
        $('#_giakhuyenmai-error').text("");

    }

    if(giakhuyenmai != '' ){
        if(giaban > gianhap && giakhuyenmai >= gianhap && giabanle > gianhap) {

            $('#btn-submit').attr('type','submit');
        }
    }else {
        if(giaban > gianhap  && giabanle > gianhap) {

            $('#btn-submit').attr('type','submit');
        }
    }

}
var timeout = null;
function kiemtramahang(){
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var hanghoa = {};
		hanghoa["mahang"] = $('#mahang').val().trim();
		hanghoa["id"] = $("#id").val().trim();
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/kiemtramahang",
			data : JSON.stringify(hanghoa),
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				console.log(result);
				if(result == "error"){
					$('#error').css("display", "block");
					$('#error').text("* Mã Hàng Đã Tồn Tại");
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
$('#mahang').on('keyup keypress keydown', function(event){
	
	if($('#mahang').val().trim() != ''){
		
		kiemtramahang();
		
	}else {
		$('#error').css("display", "none");
		$('#error').text("");
	}
	
})