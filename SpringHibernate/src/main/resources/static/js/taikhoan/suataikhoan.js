$(document).ready(function() {
	// Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	$("#formTaiKhoan").validate({
		rules : {
			username : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}

			},
			email : {
				required : true,
				email : true,
				normalizer : function(value) {

					return $.trim(value);
				}

			}/*,
			matkhau : {
				required : true,
				normalizer : function(value) {

					return $.trim(value);
				}
			}*/

		},
		messages : {
			username : {
				required : "* Vui Lòng Nhập User Name"

			},
			email : {
				required : "* Vui Lòng Nhập Email",
				email : "* Vui Lòng Nhập Đúng Email"

			}/*,

			matkhau : {
				required : "* Vui Lòng Nhập Mật Khẩu"
			}*/

		}
	});
});

var timeout = null;
function kiemtrausernamevaemailtaikhoan() {
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		var taikhoan = {};
		taikhoan["username"] = $('#username').val().trim();
		taikhoan["email"] = $('#email').val().trim();
		taikhoan["id"] = $("#id").val().trim();
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});

		$.ajax({

			type : "POST",
			contentType : "application/json",
			url : contextPath + "/admin/kiemtrausernamevaemailtaikhoan",
			data : JSON.stringify(taikhoan),
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				console.log(result);
				if (result == "error") {
					$('#_username-error').css("display", "block");
					$('#_username-error').text("* User Name Đã Tồn Tại");

					$('#_email-error').css("display", "block");
					$('#_email-error').text("* Email Đã Tồn Tại");

					$('#btn-submit').attr('type', 'button');
				} else if (result == "errorusername") {
					$('#_username-error').css("display", "block");
					$('#_username-error').text("* User Name Đã Tồn Tại");
					$('#_email-error').css("display", "none");
					$('#_email-error').text("");
					$('#btn-submit').attr('type', 'button');
				} else if (result == "erroremail") {
					$('#_email-error').css("display", "block");
					$('#_email-error').text("* Email Đã Tồn Tại");
					$('#_username-error').css("display", "none");
					$('#_username-error').text("");
					$('#btn-submit').attr('type', 'button');
				}

				else {
					$('#_username-error').css("display", "none");
					$('#_username-error').text("");
					$('#_email-error').css("display", "none");
					$('#_email-error').text("");
					$('#btn-submit').attr('type', 'submit');
				}

			},
			error : function(e) {

			}
		});
	}, 100);
};
$('#username').on('keyup keypress keydown', function(event) {

	if ($('#username').val().trim() != '') {

		kiemtrausernamevaemailtaikhoan();

	} else {
		$('#_username-error').css("display", "none");
		$('#_username-error').text("");
	}

});

$('#email').on('keyup keypress keydown', function(event) {

	if ($('#email').val().trim() != '') {

		kiemtrausernamevaemailtaikhoan();

	} else {
		$('#_email-error').css("display", "none");
		$('#_email-error').text("");
	}

});