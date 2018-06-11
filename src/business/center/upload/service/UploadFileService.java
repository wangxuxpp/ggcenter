package business.center.upload.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import atomic.exception.SystemException;
import base.baseinterface.BaseService;
import base.baseinterface.IBaseService;
import base.common.Constant;
import base.util.FactoryStatic;
import base.util.SessionUser;
import base.util.Util;
import base.util.fileOperator.FileStreamService;
import base.util.pojo.FilePatch;

/**
 * 上传文件
 * 
 * @author 王丹
 * @version  1.0  2015-6-5
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@Service("uploadFileService")

public class UploadFileService extends BaseService  implements IBaseService {

	/**
	 * 图片上传截取
	 * 
	 * @param requestModel void
	 * 
	 * history
	 * 
	 */
	public String upload(ModelMap requestModel){
		SessionUser user = (SessionUser)requestModel.get(Constant.SYS_SESSION);
		if(!requestModel.containsKey("REQUEST")){
			throw new SystemException("请传入REQUEST对象");
		}
		String pathName = Util.objStr(requestModel.get("pathName"));
		HttpServletRequest request = (HttpServletRequest)requestModel.get("REQUEST");
		FileStreamService fileOp = FactoryStatic.getFileStream();
		String fileSqlPatch = FilePatch.getFactoryPatch()+pathName+File.separator;
		fileOp.fileUpLoad(request, "fileField", fileSqlPatch, Util.objStr(user.getFactoryId()));
		//String filePatch = fileSqlPatch+File.separator+user.getFactoryId()+".jpg";
		//fileSqlPatch = fileSqlPatch+File.separator+user.getFactoryId()+"_1.jpg";
		//ImgDispatch.imgTranslate(fileSqlPatch, filePatch, Util.objStr(user.getFactoryId()));
		//fileOp.fileDel(fileSqlPatch);
		return pathName+File.separator+user.getFactoryId()+".jpg";
	}
	
	public ModelMap list(ModelMap requestModel) {
		return null;
	}
	
	public ModelMap get(ModelMap requestModel) {
		return null;
	}

	public ModelMap save(ModelMap requestModel) {
		return null;
	}
	public ModelMap saveCheck(ModelMap requestModel) {
		return null;
	}

	public ModelMap update(ModelMap requestModel) {
		return null;
	}
	public ModelMap updateCheck(ModelMap requestModel) {
		return null;
	}

	public ModelMap delete(ModelMap requestModel) {
		return null;
	}
	public ModelMap deleteCheck(ModelMap requestModel) {
		return null;
	}

	public ModelMap regain(ModelMap requestModel) {
		return null;
	}
	public ModelMap regainCheck(ModelMap requestModel) {
		return null;
	}
	
	public ModelMap nextItem(ModelMap requestModel) {
		return null;
	}
	public ModelMap precedeItem(ModelMap requestModel) {
		return null;
	}

}
