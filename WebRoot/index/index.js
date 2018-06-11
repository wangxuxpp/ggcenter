function doLogin(){
	ajaxRequest("login.hm?method=doClose", {
		success:function(result){
			window.location.href = "index.jsp";
		}
	});
}
