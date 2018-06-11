<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="display: none;">
<form action="uploadFile.do?method=upload" id="formName" name="formName" method="post" enctype="multipart/form-data">
	<input type="file" name="fileField" id="fileField" onchange="previewImage(this)" value=""/>
	<input type="hidden" id="pathName" name="pathName" value="">
	<input type="hidden" id="imgName" name="imgName" value="">
	<input type="hidden" id="divName" name="divName" value="">
	<input type="hidden" id="showName" name="showName" value="">
</form>
</div>