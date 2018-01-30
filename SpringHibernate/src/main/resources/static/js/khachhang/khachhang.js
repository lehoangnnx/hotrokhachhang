function deleteOne(id) {
	$("#arrayId").val(id);
	$("#command").attr("action", contextPath + "/admin/khachhang");
};

var timeout = null;
function updateuutienkhachhang(id, uutien){
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
			url : contextPath + "/admin/updateuutienkhachhang",
			data : {id , uutien},
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				if(result === 'co'){
					$('#lbloption1'+id+'').removeClass('btn-default').addClass('btn-success');
				    $('#lbloption2'+id+'').removeClass('btn-danger').addClass('btn-default');
				}else if (result === 'khong'){
					$('#lbloption2'+id+'').removeClass('btn-default').addClass('btn-danger');
					$('#lbloption1'+id+'').removeClass('btn-success').addClass('btn-default');
				}
			},
			error : function(e) {

			}
		});
	}, 100);
};
function onUuTien(id){
	
	updateuutienkhachhang(id, 'co');
    
   // $('input[id=option1'+id+']').parent().toggleClass('btn-default').toggleClass('btn-success');
   // $('[id=option2'+id+']').parent().toggleClass('btn-default').toggleClass('btn-danger');    

}
function offUuTien(id){
	updateuutienkhachhang(id, 'khong');
	
    
   // $('input[id=option2'+id+']').parent().toggleClass('btn-default').toggleClass('btn-danger');
   // $('[id=option1'+id+']').parent().toggleClass('btn-default').toggleClass('btn-success');

}


