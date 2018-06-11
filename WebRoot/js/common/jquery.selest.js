/*  搜索类型选择JS  */
$(function(){ 

	$("#searchSelected").click(function(){ 
		$("#searchTab").show();
		$(this).addClass("searchOpen");
	}); 

	$("#searchTab li").hover(function(){
		$(this).addClass("selected");
	},function(){
		$(this).removeClass("selected");
	});
	 
	$("#searchTab li").click(function(){
		$("#searchSelected").html($(this).html());
		$("#searchTab").hide();
		$("#searchSelected").removeClass("searchOpen");
	});
	
});