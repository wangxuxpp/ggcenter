/******************************************************************
 * 显示上传图径
 */
function previewImage(file){
	var fileField = file.files;
	var pos = fileField[0].name.lastIndexOf(".");
	var lastname = fileField[0].name.substring(pos, fileField[0].name.length);
	var imgSize = parseInt(fileField[0].size, 10);
	var divName = $("#divName").val();
	if (lastname.toLowerCase() != ".jpg") {
		$("#"+divName).addClass("bdts tscw");
		$("#"+divName).html("您上传的文件类型为 " + lastname + " ，图片必须为 JPG 类型");
	    return;
	}else if(imgSize/1024/1024 > 2) {
		$("#"+divName).addClass("bdts tscw");
		$("#"+divName).html("您上传的文件大小为 " + imgSize + "K，大小必须小于 2MK");
	    return;
	}else{
		$("#"+divName).addClass("bdts");
		$("#"+divName).html("件格式 JPG 文件大小2MK以内");
	}
	upload();
}

/******************************************************************
 * 显示上传图径
 */
function showUpload(pathName, imgId, divId){
	$("#pathName").val(pathName);
	$("#imgName").val(imgId);
	$("#divName").val(divId);
	document.getElementById("fileField").click();
}

/******************************************************************
 * 保存
 */
function upload(){
	var form = document.getElementById("formName");
	Util.waitStart();
	$("#formName").ajaxSubmit({
		url: form.action,
		dataType: "json",
		success: function(result){
			Util.waitEnd();
			var imghead = $("#imgName").val();
			var pathName = $("#pathName").val();
			$("#"+imghead).html('');
			$("#"+imghead).html('<img id="'+pathName+'" style="max-height: 360px;">');
			var img = document.getElementById(pathName);
			var random = Math.random();
			img.src = "file/factory/"+result.imgSrc+"?"+Math.floor(random*100+1);
		}
	});
}

/******************************************************************
 * 
 */
function previewImage11(file){
	var preview = $("#divName").val();
	var imghead = $("#imgName").val(); 
	var MAXWIDTH = 420;
	var MAXHEIGHT = 360;
	var div = document.getElementById(preview);
	if(file.files && file.files[0]){
		div.innerHTML = '<img id="'+imghead+'">';
		var img = document.getElementById(imghead);
		img.onload=function(){
			var rect = clacImgZoomParam(MAXWIDTH,MAXHEIGHT,img.offsetWidth,img.offsetHeight);
			img.width = rect.width;
			img.height = rect.height;
			img.style.marginLeft = '0px';
			img.style.marginTop = '0px';
		}
		var reader = new FileReader();
		reader.onload = function(evt){img.src = evt.target.result;}
		reader.readAsDataURL(file.files[0]);
	}else{
		var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
		file.select();
		var src = document.selection.createRange().text;
		div.innerHTML = '<img id="'+imghead+'">';
		var img = document.getElementById(imghead);
		img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
		var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
		status = ('rect:0, 0, '+rect.width+', '+rect.height);
		div.innerHTML = '<div id=divhead style="width:'+rect.width+'px; height:'+rect.height+'px; margin-top:0px; '+sFilter+src+'\'"></div>';
	}
	upload();
}

/******************************************************************
 * 
 */
function clacImgZoomParam(maxWidth, maxHeight, width, height){
	var param = {top:0, left:0, width:width, height:height};
	if(width > maxWidth || height > maxHeight){
		rateWidth = width / maxWidth;
		rateHeight = height / maxHeight;
		if(rateWidth > rateHeight){
			param.width = maxWidth;
			param.height = Math.round(height / rateWidth);
		}else{
			param.width = Math.round(width / rateHeight);
			param.height = maxHeight;
		}
	}
	param.left = Math.round((maxWidth-param.width) / 2);
	param.top = Math.round((maxHeight-param.height) / 2);
	return param;
}
