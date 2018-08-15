fviewSetting = function(lvl, id) {
	var id_ = id.replace("h", "");
	id_ = id_.replace("e", "");
	$.get("/content_sections/section/" + lvl + "/" + id_, function(data) {
		$("#sections_content").html(data);
	});
}

fnAplicarCambios = function() {

	$.post("/content_sections/aplicar_cambios", {
		"idrevisionValidacion" : $("#txtrevisionid").val(),
		"revision" : $("#txtrevision").val(),
		"detalle" : $('#txtdetalle').summernote('code'),
		"ayuda" : $('#txtayuda').summernote('code'),
		"descripcion": $($('#txtdetalle').summernote('code')).text(),
		"idsubelemento.idsubelemento" : $("#txtidsubelemento").val()
	}, function(data) {
		$("#myModal").modal("hide");
		$.get("/content_sections/listar_detalle/"
				+ $("#txtidsubelemento").val(), function(data) {
			$("#content-info").html(data);
		});
	});

	$("#txtrevisionid").val(0);
}

fnLoadSection = function(id) {

	if (id == 0) {
		return;
	}
//	$("#txtdetalle").summernote("disable");
//	$("#txtayuda").summernote("disable");
	$("#txtrevisionid").val(id);
	$.ajax({
		type:"GET",
		dataType : "json",
		url : "/content_sections/section_load/" + id,
		data : $(this).serialize(),
		success : function(data){
			$("#txtrevision").val(data.revision);
			$("#txtdetalle").summernote('code',data.detalle);
			$("#txtayuda").summernote('code',data.ayuda);
			
		}
	});

	$("#myModal").modal("show");

}
