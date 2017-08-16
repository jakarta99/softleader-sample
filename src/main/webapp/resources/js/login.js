$(function() {
			dialog = $("#loginWindow").dialog({
				autoOpen : false,
				height : 400,
				width : 350,
				modal : true,
				close : function() {form[0].reset();},
				show: {
					effect: "blind",
					duration: 800
				},
				hide: {
					effect: "blind",
					duration: 800
				}
			});

			form = dialog.find("form").on("submit", function(event) {
				event.preventDefault();
				//submit;
			});

			$("#login").button().on("click", function() {
				dialog.dialog("open");
			});

		});
		