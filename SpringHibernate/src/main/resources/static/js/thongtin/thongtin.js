

var timeout = null;
function thaydoimatkhautaikhoan() {
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
			data : {},
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				

			},
			error : function(e) {

			}
		});
	}, 100);
};