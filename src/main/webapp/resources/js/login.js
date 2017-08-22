$(function() {
	// 線上客服視窗
	dialog2 = $("#leaveQuestion").dialog({
		autoOpen : false,
		height : 400,
		width : 800,
		modal : true,
		close : function() {
			form[0].reset();
		},
		show : {
			effect : "blind",
			duration : 800
		},
		hide : {
			effect : "blind",
			duration : 800
		}
	});

	form = dialog2.find("form").on("submit", function(event) {
		event.preventDefault();
		// submit;
	});

	$("#online").button().on("click", function() {
		dialog2.dialog("open");
	});

	// 登入視窗
	dialog1 = $("#loginWindow").dialog({
		autoOpen : false,
		height : 250,
		width : 250,
		modal : true,
		close : function() {
			form[0].reset();
		},
		show : {
			effect : "blind",
			duration : 800
		},
		hide : {
			effect : "blind",
			duration : 800
		}
	});

	form = dialog1.find("form").on("submit", function(event) {
		event.preventDefault();
		// submit;
	});

	$("#login").button().on("click", function() {
		dialog1.dialog("open");
	});

	$("#loginBtn").on("click", function() {
		var email = $("#inputEmail").val();
		var password = $("#inputPassword").val();
		var json = {
			"email" : email,
			"password" : password
		};

		if (email != null && email != "") {
			if (password != null && password != "") {
				$.post("login/check", json, function(datas) {
					if (datas != "/index") {
						alert(datas + "歡迎回來～轉載中~");
						window.location.href = "LoginPage.jsp";
					} else {
						alert("Email／密碼錯誤，請重新輸入～");
					}
				})
			} else {
				alert("密碼不可空");
			}
		} else {
			alert("Email不可空");
		}
	})

	// 註冊視窗
	dialog3 = $("#registerWindow").dialog({
		autoOpen : false,
		height : 450,
		width : 250,
		modal : true,
		close : function() {
			form[0].reset();
		},
		show : {
			effect : "blind",
			duration : 800
		},
		hide : {
			effect : "blind",
			duration : 800
		}
	});

	form = dialog3.find("form").on("submit", function(event) {
		event.preventDefault();
		// submit;
	});

	$("#registerBtn").button().on("click", function() {
		dialog3.dialog("open");
		dialog1.dialog("close");
	});

	$("#registerNow").on("click", function() {
		var email = $("#addEmail").val();
		var password = $("#addPassword").val();
		var name = $("#addName").val();
		var phone = $("#addPhone").val();
		var address = $("#addAddress").val();
		var gender = $("#addGender").val();
		var json = {
			"email" : email,
			"password" : password,
			"name" : name,
			"phone" : phone,
			"address" : address,
			"gender" : gender
		};
		$.post("login/register", json, function(datas) {
			if (datas != "/index") {
				alert(datas + "恭喜您註冊成功～轉載中～");
				window.location.href = "LoginPage.jsp";
			} else {
				alert("註冊失敗，請聯絡客服(09-59487-520)");
			}
		})
	})
});
