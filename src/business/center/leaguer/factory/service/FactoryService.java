package business.center.leaguer.factory.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import atomic.exception.SystemException;
import base.baseinterface.BaseService;
import base.baseinterface.IBaseService;
import base.common.Constant;
import base.util.SessionUser;

/**
 * 企业信息
 * 
 * @author 王宇
 * @version 2015-7-01
 * 
 * history:
 *
 */
@Service("factoryService")
@SuppressWarnings({"unchecked"})
public class FactoryService extends BaseService  implements IBaseService {

	/**
	 * 得到一条数据
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap get(ModelMap requestModel){
		ModelMap m = new ModelMap();
		SessionUser user = (SessionUser)requestModel.get(Constant.SYS_SESSION);
		String sql = "select T.ID, T1.ID I_FACTORY_ID, T1.C_CODE, T.C_LOGINNAME, T1.C_NAME C_FACTORY_NAME, T1.C_PRO, T1.C_CITY, " +
					 "       T1.C_ADDRESS, T1.C_RUNAREA, T1.C_TEL, T1.C_INFO, T.C_NAME, T.C_MAIL, T.I_SYS_ROLE_ID " +
					 "  from SYS_LOGINUSER T JOIN SYS_FACTORY T1 ON T.I_SYS_FACTORY_ID = T1.ID WHERE T.ID = ? ";
		m.putAll(dao.queryMap(sql, new Object[]{user.getUserId()}));
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
	public ModelMap update(ModelMap requestModel){
		updateCheck(requestModel);
		this.pUpdate(requestModel, (SessionUser)requestModel.get(Constant.SYS_SESSION), "SYS_LOGINUSER", null);
		requestModel.put("ID",requestModel.get("I_FACTORY_ID"));
		requestModel.put("C_NAME",requestModel.get("C_FACTORY_NAME"));
		this.pUpdate(requestModel, (SessionUser)requestModel.get(Constant.SYS_SESSION), "SYS_FACTORY", null);
		return null;
	}
	
	/**
	 * 修改校验
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap updateCheck(ModelMap requestModel){
		String sql = "select 1 from SYS_FACTORY where C_NAME = ? and ID != ?";
		if(this.dao.isRecordExist(sql, new Object[]{requestModel.get("C_FACTORY_NAME"),requestModel.get("I_FACTORY_ID")})){
			throw new SystemException("C_FACTORY_NAME");
		}
		return null;
	}

	public ModelMap list(ModelMap requestModel){
		return null;
	}

	public ModelMap save(ModelMap requestModel){
		return null;
	}
	public ModelMap saveCheck(ModelMap requestModel){
		return null;
	}

	public ModelMap delete(ModelMap requestModel){
		return null;
	}
	public ModelMap deleteCheck(ModelMap requestModel){
		return null;
	}

	public ModelMap regain(ModelMap requestModel){
		return null;
	}
	public ModelMap regainCheck(ModelMap requestModel){
		return null;
	}

	public ModelMap nextItem(ModelMap requestModel){
		return null;
	}
	public ModelMap precedeItem(ModelMap requestModel){
		return null;
	}

}
