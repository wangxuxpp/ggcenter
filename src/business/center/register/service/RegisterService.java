package business.center.register.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import atomic.exception.SystemException;
import base.baseinterface.BaseService;
import base.baseinterface.IBaseService;
import base.common.Constant;
import base.util.DateUtil;
import base.util.SessionUser;
import base.util.Util;

/**
 * 注册页
 * 
 * @author 王宇
 * @version  2015-6-23
 * 
 * history:
 *
 */
@Service("registerService")

public class RegisterService extends BaseService implements IBaseService  {
	
	/**
	 * 新增
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap doRegister(ModelMap requestModel, HttpServletRequest request) {

		String sCaptcha = Util.objStr(request.getSession().getAttribute(Constant.CAPCHA_SESSION_KEY));
		String captcha = Util.objStr(requestModel.get("CAPTCHA")).toUpperCase();
		if(!Util.strIsEmpty(captcha)){
			if(!captcha.equals(sCaptcha)){
				throw new SystemException("CAPTCHA");
			}
		}
		
		String C_FACTORY_NAME = Util.objStr(requestModel.get("C_FACTORY_NAME"));
		String sql = "select 1 from SYS_FACTORY where C_NAME = ?";
		if(this.dao.isRecordExist(sql, new Object[]{C_FACTORY_NAME})){
			throw new SystemException("C_FACTORY_NAME");
		}
		
		SessionUser user = Util.getUser();
		String wheresql = " and C_PRO = '"+Util.objStr(requestModel.get("C_PRO"))+"' and C_CITY = '"+Util.objStr(requestModel.get("C_CITY"))+"'";
		String codeNum = Util.getfactoryCode(dao, "SYS_FACTORY", "C_CODE", wheresql);
		String C_CODE = Util.objStr(requestModel.get("C_PRO_ABBR"))+Util.objStr(requestModel.get("C_CITY_ABBR"))+codeNum;
		requestModel.put("C_CODE", C_CODE);
		String C_NAME = Util.objStr(requestModel.get("C_NAME"));
		requestModel.put("C_NAME", requestModel.get("C_FACTORY_NAME"));
		String factoryId = this.pInsert(requestModel, user, "SYS_FACTORY", false, null, false).id;
		
		requestModel.put("I_SYS_FACTORY_ID", factoryId);
		requestModel.put("I_SYS_ROLE_ID", 0);
		requestModel.put("C_NAME", C_NAME);
		String userId = this.pInsert(requestModel, user, "SYS_LOGINUSER", false, null, false).id;
		
		requestModel.put("C_ERPURL", "lockhost");
		requestModel.put("I_PORT", 8080);
		requestModel.put("C_PROJECT", "easypc");
		requestModel.put("C_USER", requestModel.get("C_LOGINNAME"));
		requestModel.put("C_PASSWORD", requestModel.get("C_PASSWORD"));
		requestModel.put("C_ERPUSER", "超级系统管理员");
		requestModel.put("C_ERPPASSWORD", "easypc123456");
		this.pInsert(requestModel, user, "SYS_ERPFACTORY", false, null, false);
		
		
		user.setFactoryId(Util.objInt(factoryId));
		user.setFactoryCode(C_CODE);
		user.setFactoryName(Util.objStr(requestModel.get("C_FACTORY_NAME")));
		user.setUserId(Util.objInt(userId));
		user.setUserName(Util.objStr(requestModel.get("C_NAME")));
		user.setUserRole(0);
		user.setDateTime(DateUtil.getDate());
		request.getSession().setAttribute(Constant.SYS_SESSION, user);
		
		ModelMap m = new ModelMap();
		m.put("C_CODE", C_CODE);
		m.put("C_MAIL", requestModel.get("C_MAIL"));
		return m;
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
