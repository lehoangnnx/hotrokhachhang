

var timeout = null;
function thaydoimatkhautaikhoan(matkhau) {
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});

		$.ajax({

			type : "POST",
			//contentType : "application/json",
			url : contextPath + "/admin/thaydoimatkhautaikhoan",
			data : {matkhau},
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				

			},
			error : function(e) {

			}
		});
	}, 100);
};

$('#btn-doimatkhau').click(function(){
	var pwd = $('#pwd').val().trim();
	var rpwd = $('#rpwd').val().trim();
	if(pwd == ''){
		$('#pwd').focus();
		$('#error').text('* Vui Lòng Nhập Mật Khẩu Mới');
	}else if(rpwd == ''){
		$('#rpwd').focus();
		$('#error').text('* Vui Lòng Nhập Lại Mật Khẩu Mới');
	}else {
		if(pwd.length < 6 || pwd.length > 16){
			$('#pwd').focus();
			$('#error').text('* Mật Khẩu Mới Từ 6 Đến 16 Ký Tự');
		} else if (!rpwd.match(pwd)) {
			$('#rpwd').focus();
			$('#error').text('* Nhập Lại Mật Khẩu Không Khớp');
		}else {
			thaydoimatkhautaikhoan(rpwd);
		}
	}
	
});