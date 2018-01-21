

var timeout = null;
function updateThongBaoChamSoc(id){
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
		$.ajax({

			type : "POST",
			//contentType : "application/json",
			url : contextPath + "/admin/updatethongbaochamsoc",
			data : {id},
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				
				if(result != "error"){
					window.location=contextPath+"/admin/chamsoc/"+result;
				}

			},
			error : function(e) {

			}
		});
	}, 100);
};

function updateThongBaoKhachHang(id){
	clearTimeout(timeout);
	timeout = setTimeout(function() {
		
		var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
		$.ajax({

			type : "POST",
			//contentType : "application/json",
			url : contextPath + "/admin/updatethongbaokhachhang",
			data : {id},
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				
				if(result == "success"){
					window.location=contextPath+"/admin/khachhang/"+id;
				}

			},
			error : function(e) {

			}
		});
	}, 100);
};
