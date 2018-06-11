package business.center.leaguer.permit.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import base.baseinterface.BaseService;
import base.baseinterface.IBaseService;
import base.common.Constant;
import base.util.DateUtil;
import base.util.SessionUser;

/**
 * 企业信息
 * 
 * @author 王宇
 * @version  2015-7-01
 * 
 * history:
 *
 */
@Service("permitService")
@SuppressWarnings({"rawtypes" , "unchecked"})
public class PermitService extends BaseService  implements IBaseService {

	/**
	 * 得到施工用户登记状态
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public Map getStatus(ModelMap requestModel) {
		SessionUser user = (SessionUser)requestModel.get(Constant.SYS_SESSION);
		String sql = "  select T.C_STATUS from SYS_CONSTRUCTIONPERMIT t WHERE T.I_SYS_FACTORY_ID = ? ";
		return dao.queryMap(sql, new Object[]{user.getFactoryId()});
	}
	
	/**
	 * 得到企业信息
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap get(ModelMap requestModel) {
		ModelMap m = new ModelMap();
		SessionUser user = (SessionUser)requestModel.get(Constant.SYS_SESSION);
		String sql = "SELECT T.ID, T.C_LEGALPERSON, T.C_NUMBER, T.C_CAPITAL, T.C_RUNAREA, T.C_PRO, T.C_CITY, T.C_TEL, " +
					 "       T.C_CODE, T.C_ADDRESS, T.C_RUNAREA, T.C_INFO, T.C_PHONE, T.C_NAME, T1.C_MEMO, T1.C_STATUS, " +
					 "       TO_CHAR(T.D_DATE, 'YYYY-MM-DD') C_DATE, TO_CHAR(T.D_INSERT_DATE, 'YYYY-MM-DD') C_INSERT_DATE, " +
					 "       TO_CHAR(T1.D_ENABLE_DATE, 'YYYY-MM-DD') C_ENABLE_DATE, " + //生效日期
					 "       TO_CHAR(T1.D_REGISTER_DATE, 'YYYY-MM-DD') C_REGISTER_DATE " + //注册日期
					 "  FROM SYS_FACTORY T LEFT JOIN SYS_CONSTRUCTIONPERMIT T1 ON T.ID = T1.I_SYS_FACTORY_ID " +
					 " WHERE T.ID = ? ";
		m.putAll(dao.queryMap(sql, new Object[]{user.getFactoryId()}));
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
		SessionUser user = (SessionUser)requestModel.get(Constant.SYS_SESSION);
		requestModel.put("ID", user.getFactoryId());
		this.pUpdate(requestModel, user, "SYS_FACTORY", null);
		
		//保存施工用户登记
		requestModel.put("I_SYS_FACTORY_ID", requestModel.get("ID"));
		requestModel.put("C_MEMO", "施工用户激活！");
		requestModel.put("D_REGISTER_DATE", requestModel.get("D_INSERT_DATE"));
		requestModel.put("D_ENABLE_DATE", DateUtil.getDate());
		this.pInsert(requestModel, user, "SYS_CONSTRUCTIONPERMIT", false, null);
		return null;
	}
	
	/**w
	 * 修改校验
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
