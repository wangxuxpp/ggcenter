 package business.center.login.service;

import java.util.Map;

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
 * 登陆实现类
 * 
 * @author 王丹
 * @version  2014-3-19
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@Service("loginService")//确定业务逻辑器Id，让系统知道如何可以访问到这个业务类
@SuppressWarnings({"rawtypes"})
public class LoginService extends BaseService implements IBaseService {
	
	/**
	 * 登录验证
	 * 
	 * @param loginInfo
	 * @param request
	 * @return boolean
	 * 
	 * history
	 * 
	 */
	
	public boolean validateLogin(Map loginInfo, HttpServletRequest request){

		String factoryName = Util.objStr(loginInfo.get("factoryName"));
		String loginName = Util.objStr(loginInfo.get("loginName"));
		String password = Util.objStr(loginInfo.get("password"));
		String sCaptcha = Util.objStr(request.getSession().getAttribute(Constant.CAPCHA_SESSION_KEY));
		String captcha = Util.objStr(loginInfo.get("captcha")).toUpperCase();
		
		if(!Util.strIsEmpty(captcha)){
			if(!captcha.equals(sCaptcha)){
				throw new SystemException("验证码不符，请重新填写！");
			}
		}
		
		Map factoryMap = dao.queryMap("SELECT T.ID, T.C_NAME, T.C_STATUS FROM SYS_FACTORY T WHERE T.C_CODE = ?", new Object[]{factoryName});
		if(factoryMap.isEmpty()){
			factoryMap = dao.queryMap("SELECT T.ID, T.C_NAME, T.C_STATUS FROM SYS_FACTORY T WHERE T.C_NAME = ?", new Object[]{factoryName});
			if(factoryMap.isEmpty()){
				return false;
			}
		}
		
		String cStatus = Util.objStr(factoryMap.get("C_STATUS"));
		if("无效".equals(cStatus)){
			throw new SystemException("账户还未激活，不能登录，请激活后重新试一下！");
		}
		
		Map userMap = dao.queryMap("SELECT T.ID, T.C_NAME, T.I_SYS_ROLE_ID FROM SYS_LOGINUSER T WHERE T.C_LOGINNAME = ? and t.I_SYS_FACTORY_ID = ?", 
				new Object[]{loginName, factoryMap.get("ID")});
		if(userMap.isEmpty()){
			return false;
		}
		if(!dao.isRecordExist("select 1 from SYS_LOGINUSER where C_PASSWORD = '"+password+"' and id = ? ", new Object[]{userMap.get("ID")})){
			return false;
		}
		
		SessionUser user = new SessionUser();
		user.setFactoryId(Util.objInt(factoryMap.get("ID")));
		user.setFactoryCode(factoryName);
		user.setFactoryName(Util.objStr(userMap.get("C_NAME")));
		user.setUserId(Util.objInt(userMap.get("ID")));
		user.setUserName(Util.objStr(userMap.get("C_NAME")));
		user.setUserRole(Util.objInt(userMap.get("I_SYS_ROLE_ID")));
		user.setDateTime(DateUtil.getDate());
		request.getSession().setAttribute(Constant.SYS_SESSION, user);
		return true;
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
