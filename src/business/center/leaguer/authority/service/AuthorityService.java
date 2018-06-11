package business.center.leaguer.authority.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import base.baseinterface.BaseService;
import base.baseinterface.IBaseService;
import business.common.authorityMem.web.WebRoleMem;

/**
 * 获取权限
 * 
 * @author 王丹
 * @version  1.0  2015-6-29
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@Service("authorityService")
@SuppressWarnings({"rawtypes"})
public class AuthorityService extends BaseService implements IBaseService {

	public WebRoleMem webRoleMem;

	/**
	 * 得到页面信息
	 * 
	 * @param factoryId
	 * @param roleId
	 * @return List
	 * 
	 * history
	 * 
	 */
	public List getPage(int factoryId, int roleId) {
		if (webRoleMem == null){
			webRoleMem = WebRoleMem.getFMem();
		}
		return webRoleMem.getPage(factoryId, roleId);
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
