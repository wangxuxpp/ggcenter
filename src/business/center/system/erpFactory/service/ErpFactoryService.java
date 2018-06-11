package business.center.system.erpFactory.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import base.baseinterface.BaseService;
import base.baseinterface.IBaseService;
import base.common.Constant;
import base.util.SessionUser;

/**
 * 平台用户连接
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
@Service("erpFactoryService")
@SuppressWarnings({"unchecked"})
public class ErpFactoryService extends BaseService  implements IBaseService {

	/**
	 * grid查询列表
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap get(ModelMap requestModel) {
		ModelMap m = new ModelMap();
		String sql = "select ID, C_ERPURL, I_PORT, C_PROJECT, C_USER, C_PASSWORD, C_ERPUSER, C_ERPPASSWORD from SYS_ERPFACTORY ";
		m.putAll(dao.queryMap(sql, new Object[]{}));
		return m;
	}

	/**
	 * 修改
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap update(ModelMap requestModel) {
		this.updateCheck(requestModel);
		this.pUpdate(requestModel, (SessionUser)requestModel.get(Constant.SYS_SESSION), "SYS_ERPFACTORY", null);
		return null;
	}

	/**
	 * 修改前验证
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap updateCheck(ModelMap requestModel) {
		return null;
	}

	public ModelMap list(ModelMap requestModel) {
		return null;
	}
	
	public ModelMap save(ModelMap requestModel) {
		return null;
	}
	public ModelMap saveCheck(ModelMap requestModel) {
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
