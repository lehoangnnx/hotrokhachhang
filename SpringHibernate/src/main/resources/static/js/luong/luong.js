function deleteOne(id) {
	$("#arrayId").val(id);
	$("#command").attr("action", contextPath + "/admin/luong");
};
