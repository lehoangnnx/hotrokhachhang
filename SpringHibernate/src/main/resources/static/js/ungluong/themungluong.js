(function($, undefined) {

    "use strict";

    // When ready.
    $(function() {

        var $form = $( "#formUngLuong" );
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
	$("#formUngLuong").validate({
		rules : {
			nhanvien : {
				required : true
			}
			
		},
		messages : {
			nhanvien : {
				required : "* Vui Lòng Chọn Nhân Viên"
			}

		}
	});
});


$(document).ready(function(){
    var dateObj = new Date();
    var month = dateObj.getUTCMonth() + 1;
    var day = dateObj.getUTCDate();
    var year = dateObj.getUTCFullYear();

    $('#ngayung').val(day+'/'+month+'/'+year);

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