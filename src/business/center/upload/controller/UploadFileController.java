package business.center.upload.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import base.baseinterface.BaseController;
import base.util.JSONResult;
import business.center.upload.service.UploadFileService;

/**
 * 上传文件
 * 
 * @author 王丹
 * @version  1.0  2015-7-4
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@Controller 
@RequestMapping("/uploadFile.do")
public class UploadFileController extends BaseController {

	@Resource(name="uploadFileService")
	public UploadFileService service;

	/**
	 * 上传
	 * 
	 * @param request
	 * @param response
	 * @param map void
	 * 
	 * history
	 * 
	 */
	@RequestMapping(params="method=upload")
	public void upload(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		JSONResult result = new JSONResult();
		try{
			map.put("REQUEST", request);
			result.setData("imgSrc", service.upload(map));
			result.setSuccessType("");	
		}catch(Exception e){
			log.error(e);
			result.setFailure(e.getMessage());
		}
		result.write(response);
	}
	
}
