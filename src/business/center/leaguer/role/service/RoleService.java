package business.center.leaguer.role.service;

import java.util.HashMap;
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
 * 角色表
 * 
 * @author 王宇
 * @version  2015-6-29
 * 
 * history:
 *
 */
@Service("roleService")
@SuppressWarnings({"rawtypes" , "unchecked"})
public class RoleService extends BaseService  implements IBaseService {

	/**
	 * 查询
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
		String sql = "select T.ID, T.I_SYS_FACTORY_ID, T.C_NAME, T.C_MEMO, T.C_ALIASCODE from SYS_ROLE T " +
					 " where t.I_SYS_FACTORY_ID = "+user.getFactoryId();
		int start = Util.objInt(requestModel.get("start"));
		int limit = Util.objInt(requestModel.get("limit"));
		m.putAll(GridDataStatic.getGrid(dao, sql, start, limit));
		return m;
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
		int id = Util.objInt(requestModel.get("ID"));
		String sql = "select T.ID, T.I_SYS_FACTORY_ID ,T.C_NAME, T.C_MEMO, T.C_ALIASCODE from SYS_ROLE T where T.ID = ?";
		map.putAll(dao.queryMap(sql, new Object[]{id}));
		return map;
	}
	
	/**
	 * 得到已选权限
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public Map getAuthorityMap(ModelMap requestModel) {
		String sql = "SELECT I_SYS_PAGESUB_ID FROM SYS_AUTHORITY WHERE I_SYS_ROLE_ID = ? ORDER BY I_SYS_PAGESUB_ID ";
		Map map = new HashMap();
		List list = dao.executeQuery(sql, new Object[]{requestModel.get("ID")});
		for(int i = 0 , n = list.size() ; i < n ; i = i + 1){
			Map temp = (Map)list.get(i);
			map.put(temp.get("I_SYS_PAGESUB_ID"), temp.get("I_SYS_PAGESUB_ID"));
		}
		return map;
	}
	
	/**
	 * 得到所有模块权限
	 * 
	 * @param map
	 * @return List
	 * 
	 * history
	 * 
	 */
	public List getAuthorityAllList(ModelMap requestModel) {
//		SessionUser user = (SessionUser)requestModel.get(Constant.SYS_SESSION);
//		List list = webRoleMem.getFactoryPage(user.getFactoryId());

		String sql = "SELECT ID, C_NAME, C_URL, C_IMAGENAME, C_MEMO FROM SYS_PAGE ORDER BY ID ";
		String sql2 = "SELECT ID, C_NAME, C_URL, C_IMAGENAME, C_MEMO FROM SYS_PAGESUB WHERE I_SYS_PAGE_ID = ? ORDER BY ID ";
		List list = dao.executeQuery(sql, null);
			for(int j = 0 , m = list.size() ; j < m ; j = j + 1){
				Map temp2 = (Map)list.get(j);
				List list2 = dao.executeQuery(sql2, new Object[]{temp2.get("ID")});
				temp2.put("subList", (list2.size() > 0 ? list2 : null));
			}
		return list;
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
		saveCheck(requestModel);
		this.pInsert(requestModel, (SessionUser)requestModel.get(Constant.SYS_SESSION), "SYS_ROLE", false, null);
		return null;
	}
	
	/**
	 * 新增校验
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap saveCheck(ModelMap requestModel) {
		String sql = "SELECT 1 FROM SYS_ROLE where C_NAME = ? ";
		if(dao.isRecordExist(sql, new Object[]{requestModel.get("C_NAME")})){
			throw new SystemException("角色名称重复，不能保存，请重新填写！");
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
		updateCheck(requestModel);
		this.pUpdate(requestModel, (SessionUser)requestModel.get(Constant.SYS_SESSION), "SYS_ROLE", null);
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
	public ModelMap updateCheck(ModelMap requestModel) {
		String sql = "SELECT 1 FROM SYS_ROLE where C_NAME = ? and id != ?";
		if(dao.isRecordExist(sql, new Object[]{requestModel.get("C_NAME"), requestModel.get("ID")})){
			throw new SystemException("角色名称重复，不能修改，请重新填写！");
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
		deleteCheck(requestModel);
		this.pDeletePhysical(requestModel, "SYS_ROLE");
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
	 * 保存角色权限
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public Map authoritySave(ModelMap requestModel) {
		dao.executeUpdate("delete from SYS_AUTHORITY where I_SYS_ROLE_ID = ? ", new Object[]{requestModel.get("ID")});
		String sql = "INSERT INTO SYS_AUTHORITY " +
				"SELECT ? I_SYS_ROLE_ID, ID I_SYS_PAGESUB_ID FROM SYS_PAGESUB WHERE ID IN (0";
		String[] ids = (String[])requestModel.get("I_SYS_PAGESUB_ID");
		for(int i = 0 ; i < ids.length ; i = i + 1){
			if("".equals(ids[i])||ids[i]==null) continue;
			sql = sql + ", " + ids[i];
		}
		sql += ")";
		dao.executeUpdate(sql, new Object[]{requestModel.get("ID")});
		return null;
	}

}
