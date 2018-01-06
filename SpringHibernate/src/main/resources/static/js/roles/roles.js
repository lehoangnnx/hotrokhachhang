function deleteOne(arrayId){
			$("#arrayId").val(arrayId);
			$("#command").attr("action", contextPath+"/admin/roles");
		}
	function deleteAll(){
			$("#command").attr("action", contextPath+"/admin/roles");
	}

$(document).ready(function() {
	var msg = $("#msg").val();
	if(msg != "" ){
		alert(msg);
	}
	
});