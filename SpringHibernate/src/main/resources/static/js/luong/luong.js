function deleteOne(id) {
	$("#arrayId").val(id);
	$("#command").attr("action", contextPath + "/admin/luong");
};
$(document).ready(function() {
	var msg = $("#msg").val();
	if (msg != "") {
		alert(msg);
	}

});