function deleteOne(id) {
	$("#arrayId").val(id);
	$("#command").attr("action", contextPath + "/admin/quyen");
};
$(document).ready(function() {
	var msg = $("#msg").val();
	if (msg != "") {
		alert(msg);
	}

});