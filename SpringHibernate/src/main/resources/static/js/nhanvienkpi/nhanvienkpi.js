function deleteOne(id) {
	$("#arrayId").val(id);
	$("#command").attr("action", contextPath + "/admin/nhanvienkpi");
};
$(document).ready(function() {
	var msg = $("#msg").val();
	if (msg != "") {
		alert(msg);
	}

});

var timeout = null;
function updatetrangthainhanvienkpi(id, trangthai){
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
			url : contextPath + "/admin/updatetrangthainhanvienkpi",
			data : {id , trangthai},
			// dataType: 'json',
			// timeout: 600000,
			success : function(result) {
				if(result === 'active'){
					$('#lbloption1'+id+'').removeClass('btn-default').addClass('btn-success');
				    $('#lbloption2'+id+'').removeClass('btn-danger').addClass('btn-default');
				}else if (result === 'inactive'){
					$('#lbloption2'+id+'').removeClass('btn-default').addClass('btn-danger');
					$('#lbloption1'+id+'').removeClass('btn-success').addClass('btn-default');
				}
			},
			error : function(e) {

			}
		});
	}, 100);
};
function active(id){
	
	updatetrangthainhanvienkpi(id, 'active');
    
   // $('input[id=option1'+id+']').parent().toggleClass('btn-default').toggleClass('btn-success');
   // $('[id=option2'+id+']').parent().toggleClass('btn-default').toggleClass('btn-danger');    

}
function inactive(id){
	updatetrangthainhanvienkpi(id, 'inactive');
	
    
   // $('input[id=option2'+id+']').parent().toggleClass('btn-default').toggleClass('btn-danger');
   // $('[id=option1'+id+']').parent().toggleClass('btn-default').toggleClass('btn-success');

}