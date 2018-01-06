	
	var timeout = null;
	function login() { 
			
			
		clearTimeout(timeout);
		timeout = setTimeout(
				function() {

					var token = $("meta[name='_csrf']").attr("content");
					var header = $("meta[name='_csrf_header']").attr("content");
					$(document).ajaxSend(function(e, xhr, options) {
						xhr.setRequestHeader(header, token);
					});
					$.ajax({

								type : "POST",
								//contentType : "application/json",
								url : contextPath + "/login",
								data : $('#login-form').serialize(),
								//dataType: 'json',
								// timeout: 600000,
								success : function(result) {
								
									alert(result);
									if(result == 'Bad credentials'){
										$('#msgerror').text('* Sai Tên Đăng Nhập Hoặc Mật Khẩu');
									}else if(result == 'User account is locked'){
										$('#msgerror').text('* Tài Khoản Đã Bị Khóa');
									}else if(result == 'success'){
										$('#msgerror').text('success');
										
										window.location.href = contextPath;
										
									}
									
								},
								error : function(e) {
									alert("Lỗi ! Vui Lòng Kiểm Tra Lại");
								}
							});
				}, 1000); 
	 };
	

			
			$('#btn-login').click(function (){
				
					var userName = $('#username').val();
					var password = $('#password').val();
					if(userName == '' && password == ''  ){
							$('#msgerror').text('* Vui Lòng Nhập Đủ Thông Tin');
					}else if(userName ==''){ 
						$('#msgerror').text('* Vui Lòng Nhập UserName');
					}else if(password ==''){
						$('#msgerror').text('* Vui Lòng Nhập Mật Khẩu');
					}else {
						
						$('#msgerror').text('');
						
						login();
					}
		}); 
