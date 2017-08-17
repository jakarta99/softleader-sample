$(function() {
			dialog2 = $("#leaveQuestion").dialog({
				autoOpen : false,
				height : 400,
				width : 800,
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

			form = dialog2.find("form").on("submit", function(event) {
				event.preventDefault();
				//submit;
			});

			$("#online").button().on("click", function() {
				dialog2.dialog("open");
			});
			
			
			dialog1 = $("#loginWindow").dialog({
				autoOpen : false,
				height : 250,
				width : 250,
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

			form = dialog1.find("form").on("submit", function(event) {
				event.preventDefault();
				//submit;
			});

			$("#login").button().on("click", function() {
				dialog1.dialog("open");
			});

		});