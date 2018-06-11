package business.center.leaguer.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import atomic.exception.SystemException;
import base.baseinterface.BaseService;
import base.baseinterface.IBaseService;
import base.common.Constant;
import base.util.SessionUser;
import base.util.Util;
import business.common.gridData.GridDataStatic;

/**
 * 用户业务层
 * 
 * @author 王丹
 * @version  2014-3-21
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@Service("userService")//确定业务逻辑器Id，让系统知道如何可以访问到这个业务类
@SuppressWarnings({"rawtypes" , "unchecked"})
public class UserService extends BaseService implements IBaseService {

	/**
	 * grid列表查询
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap list(ModelMap requestModel) {
		ModelMap m = new ModelMap();
		SessionUser user = (SessionUser)requestModel.get(Constant.SYS_SESSION);
		String sql = " SELECT T.ID, T.I_SYS_FACTORY_ID, T.I_SYS_ROLE_ID, T.C_LOGINNAME, T.C_PASSWORD, T.C_NAME, " +
					 "        T.C_MAIL, T.C_MEMO, T.I_SUPER, T.C_ALIASCODE, T1.C_NAME C_SYS_ROLE_NAME " +
					 "   FROM sys_loginuser T left join SYS_ROLE t1 on t.I_SYS_ROLE_ID = t1.id " +
					 "  where t.I_SYS_ROLE_ID != 0 AND t.I_SYS_FACTORY_ID = "+user.getFactoryId();
		int start = Util.objInt(requestModel.get("start"));
		int limit = Util.objInt(requestModel.get("limit"));
 		m.putAll(GridDataStatic.getGrid(dao, sql, start, limit));
		return m;
	}

	/**
	 * 选择grid列表查询
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap selectList(ModelMap requestModel) {
		ModelMap m = new ModelMap();
		SessionUser user = (SessionUser)requestModel.get(Constant.SYS_SESSION);
		String sql = " SELECT T.ID, T.I_SYS_FACTORY_ID, T.I_SYS_ROLE_ID, T.C_LOGINNAME, T.C_PASSWORD, T.C_NAME, " +
					 "        T.C_MAIL, T.C_MEMO, T.I_SUPER, T.C_ALIASCODE, T1.C_NAME C_SYS_ROLE_NAME " +
					 "   FROM sys_loginuser T left join SYS_ROLE t1 on t.I_SYS_ROLE_ID = t1.id " +
					 "  where t.I_SYS_ROLE_ID != 0 AND t.I_SYS_FACTORY_ID = "+user.getFactoryId();
		int start = Util.objInt(requestModel.get("start"));
		int limit = Util.objInt(requestModel.get("limit"));
 		m.putAll(GridDataStatic.getGrid(dao, sql, start, limit));
		return m;
	}
	
	/**
	 * 得到角色列表
	 * 
	 * @param request void
	 * 
	 * history
	 * 
	 */
	public List getRoleList(ModelMap requestModel){
		SessionUser user = (SessionUser)requestModel.get(Constant.SYS_SESSION);
		String sql = "select id, i_sys_factory_id, c_name, c_memo from sys_role where i_sys_factory_id = "+user.getFactoryId();
		return dao.executeQuery(sql, null);
	}

	/**
	 * 得到一条数据
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap get(ModelMap requestModel) {
		ModelMap map = new ModelMap();
		String sql = " SELECT T.ID, T.I_SYS_FACTORY_ID, T.I_SYS_ROLE_ID, T.C_LOGINNAME, T.C_PASSWORD, T.C_NAME, " +
					 "        T.C_MAIL, T.C_MEMO, T.I_SUPER, T.C_ALIASCODE, T1.C_NAME C_SYS_ROLE_NAME " +
					 "   FROM sys_loginuser T left join SYS_ROLE t1 on t.I_SYS_ROLE_ID = t1.id " +
					 "  where T.ID = ?";
		map.putAll(dao.queryMap(sql, new Object[]{Util.objInt(requestModel.get("ID"))}));
		return map;
	}

	/**
	 * 新增
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap save(ModelMap requestModel) {
		this.saveCheck(requestModel);
		SessionUser user = (SessionUser)requestModel.get(Constant.SYS_SESSION);
//		try{
//			requestModel.put("C_PASSWORD", Util.getPassword(dao, requestModel.get("C_PASSWORD").toString()));
//		}catch (Exception e){
//			e.printStackTrace();
//			log.equals(e);
//		}
		requestModel.put("I_SYS_FACTORY_ID", user.getFactoryId());
		this.pInsert(requestModel, (SessionUser)requestModel.get(Constant.SYS_SESSION), "SYS_LOGINUSER", false, null);
		return null;
	}

	/**
	 * 新增前验证
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap saveCheck(ModelMap requestModel) {
		SessionUser user = (SessionUser)requestModel.get(Constant.SYS_SESSION);
		String sql = "SELECT 1 FROM SYS_LOGINUSER where C_NAME = ? and I_SYS_FACTORY_ID = "+user.getFactoryId();
		if(dao.isRecordExist(sql, new Object[]{requestModel.get("C_NAME")})){
			throw new SystemException("用户名称重复，不能保存，请重新填写！");
		}
		return null;
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
		requestModel.remove("C_PASSWORD");
		this.pUpdate(requestModel, (SessionUser)requestModel.get(Constant.SYS_SESSION), "SYS_LOGINUSER", null);
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
		SessionUser user = (SessionUser)requestModel.get(Constant.SYS_SESSION);
		String sql = "SELECT 1 FROM SYS_LOGINUSER where C_NAME = ? and id != ? and I_SYS_FACTORY_ID = "+user.getFactoryId();
		if(dao.isRecordExist(sql, new Object[]{requestModel.get("C_NAME"), requestModel.get("ID")})){
			throw new SystemException("用户名称重复，不能保存，请重新填写！");
		}
		return null;
	}

	/**
	 * 修改密码
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap updatePassword(ModelMap requestModel) {
		SessionUser user = (SessionUser)requestModel.get(Constant.SYS_SESSION);
		this.updatePasswordCheck(requestModel);
		String sql = "update SYS_LOGINUSER set C_PASSWORD = fun_password('"+requestModel.get("C_NEW_PASSWORD").toString()+"') where id = ? " +
				" and I_SYS_FACTORY_ID = "+user.getFactoryId();
		dao.executeUpdate(sql, new Object[]{requestModel.get("ID")});
		return null;
	}

	/**
	 * 修改密码前验证
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap updatePasswordCheck(ModelMap requestModel) {
		SessionUser user = (SessionUser)requestModel.get(Constant.SYS_SESSION);
		String sql = "SELECT 1 FROM SYS_LOGINUSER where C_PASSWORD = ? and id = ? and I_SYS_FACTORY_ID = "+user.getFactoryId();
		if(!dao.isRecordExist(sql, new Object[]{requestModel.get("C_OLD_PASSWORD"), requestModel.get("ID")})){
			throw new SystemException("原密码不正确，请重新填写！");
		}
		return null;
	}

	/**
	 * 删除
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap delete(ModelMap requestModel) {
		this.deleteCheck(requestModel);
		this.pDeletePhysical(requestModel, "SYS_LOGINUSER");
		return null;
	}

	/**
	 * 删除前验证
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
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
	
	/**
	 * 重置密码
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap reversePassword(ModelMap requestModel) {
		this.reversePasswordCheck(requestModel);
		String sql = "update SYS_LOGINUSER set C_PASSWORD = '123456' where id = ?";
		dao.executeUpdate(sql, new Object[]{requestModel.get("ID")});
		requestModel.put("password", "123456");
		return null;
	}

	/**
	 * 重置密码前验证
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap reversePasswordCheck(ModelMap requestModel) {
		String sql = "SELECT C_NAME FROM SYS_LOGINUSER where id != ? ";
		Map map = dao.queryMap(sql, new Object[]{requestModel.get("ID")});
		if(map.isEmpty()){
			throw new SystemException("用户不存在，请重新选择！");
		}
		requestModel.putAll(map);
		return null;
	}

}
