 package business.center.system.page.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import atomic.exception.SystemException;
import base.baseinterface.BaseService;
import base.baseinterface.IBaseService;
import base.tableMap.ConstType.ResultMapSql;
import base.util.FactoryStatic;
import base.util.SessionUser;
import base.util.Util;

/**
 * 系统页面
 * 
 * @author 王丹
 * @version  1.0  2015-6-27
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@Service("pageService")//确定业务逻辑器Id，让系统知道如何可以访问到这个业务类
@SuppressWarnings({"unchecked"})
public class PageService extends BaseService implements IBaseService {
	
	/**
	 * 得到列表数据
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap list(ModelMap requestModel) {
		ModelMap m = new ModelMap();
		String sql = "select T.ID, T.C_NAME, T.C_URL, T.C_IMAGENAME, T.C_MEMO from SYS_PAGE T";
		m.putAll(FactoryStatic.getGrid(requestModel, sql, "", dao));
		return m;
	}
	
	/**
	 * 得到一条记录
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap get(ModelMap requestModel) {
		ModelMap m = new ModelMap();
		int id = Util.objInt(requestModel.get("ID"));
		String sql = "select T.ID, T.C_NAME, T.C_URL, T.C_IMAGENAME, T.C_MEMO from SYS_PAGE T where T.ID = "+id;
		m.putAll(dao.queryMap(sql, new Object[]{id}));
		String C_IMAGENAME = Util.objStr(m.get("C_IMAGENAME")).substring(13);
		m.put("C_IMAGENAME", C_IMAGENAME);
		return m;
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
		SessionUser user = new SessionUser();
		saveCheck(requestModel);
		requestModel.put("C_IMAGENAME", "images/model/"+requestModel.get("C_IMAGENAME"));
		this.pInsert(requestModel, user, "SYS_PAGE", false, null, false);
		return null;
	}
	
	/**
	 * 新增验证
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap saveCheck(ModelMap requestModel) {
		String C_NAME = Util.objStr(requestModel.get("C_NAME"));
		if(Util.strIsEmpty(C_NAME)){
			throw new SystemException("名称不能为空");
		}
		
		String sql = "SELECT 1 FROM SYS_PAGE where C_NAME = ? ";
		if(dao.isRecordExist(sql, new Object[]{requestModel.get("C_NAME")})){
			throw new SystemException("名称重复，不能保存，请重新填写！");
		}
		
		String C_URL = Util.objStr(requestModel.get("C_URL"));
		if(Util.strIsEmpty(C_URL)){
			throw new SystemException("路径不能为空");
		}
		
		sql = "SELECT 1 FROM SYS_PAGE where C_URL = ?";
		if(dao.isRecordExist(sql, new Object[]{requestModel.get("C_URL")})){
			throw new SystemException("路径重复，不能保存，请重新填写！");
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
		SessionUser user = new SessionUser();
		updateCheck(requestModel);
		requestModel.put("C_IMAGENAME", "images/model/"+requestModel.get("C_IMAGENAME"));
		this.pUpdate(requestModel, user, "SYS_PAGE", null);
		return null;
	}
	
	/**
	 * 修改验证
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap updateCheck(ModelMap requestModel) {
		String C_NAME = Util.objStr(requestModel.get("C_NAME"));
		if(Util.strIsEmpty(C_NAME)){
			throw new SystemException("名称不能为空");
		}
		
		String sql = "SELECT 1 FROM SYS_PAGE where C_NAME = ? and id != ?";
		if(dao.isRecordExist(sql, new Object[]{C_NAME, requestModel.get("ID")})){
			throw new SystemException("名称重复，不能修改，请重新填写！");
		}
		
		String C_URL = Util.objStr(requestModel.get("C_URL"));
		if(Util.strIsEmpty(C_URL)){
			throw new SystemException("路径不能为空");
		}
		
		sql = "SELECT 1 FROM SYS_PAGE where C_URL = ? and id != ?";
		if(dao.isRecordExist(sql, new Object[]{C_URL, requestModel.get("ID")})){
			throw new SystemException("路径重复，不能修改，请重新填写！");
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
		this.pDeletePhysical(requestModel, "SYS_PAGE");
		return null;
	}
	
	/**
	 * 删除验证
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap deleteCheck(ModelMap requestModel) {
		String sql = "SELECT 1 FROM SYS_PAGESUB where I_SYS_PAGE_ID = ?";
		if(dao.isRecordExist(sql, new Object[]{requestModel.get("ID")})){
			throw new SystemException("子表中有信息，不能删除！");
		}
		return null;
	}
	
	/**
	 * 恢复
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap regain(ModelMap requestModel) {
		return null;
	}
	
	/**
	 * 恢复验证
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap regainCheck(ModelMap requestModel) {
		return null;
	}
	
	public ModelMap precedeItem(ModelMap requestModel) {
		return null;
	}
	public ModelMap nextItem(ModelMap requestModel) {
		return null;
	}
	
	/**
	 * 得到子列表数据
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap listSub(ModelMap requestModel) {
		ModelMap m = new ModelMap();
		int I_SYS_PAGE_ID = Util.objInt(requestModel.get("I_SYS_PAGE_ID"));
		String sql = " SELECT T.ID, T.I_SYS_PAGE_ID, T.C_NAME, T.C_URL,T .C_IMAGENAME, T.C_MEMO" +
					 " FROM SYS_PAGESUB T WHERE T.I_SYS_PAGE_ID = " + I_SYS_PAGE_ID;
		m.putAll(FactoryStatic.getGrid(requestModel, sql, "", dao));
		return m;
	}
	
	/**
	 * 子表得到一条记录
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap getSub(ModelMap requestModel) {
		ModelMap m = new ModelMap();
		int id = Util.objInt(requestModel.get("ID"));
		String sql = " SELECT T.ID, T.I_SYS_PAGE_ID, T.C_NAME, T.C_URL,T .C_IMAGENAME, T.C_MEMO" +
					 " FROM SYS_PAGESUB T WHERE T.ID = " + id;
		m.putAll(dao.queryMap(sql, null));
		String C_IMAGENAME = Util.objStr(m.get("C_IMAGENAME")).substring(13);
		m.put("C_IMAGENAME", C_IMAGENAME);
		return m;
	}
	
	/**
	 * 子表新增
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap saveSub(ModelMap requestModel) {
		SessionUser user = new SessionUser();
		saveCheck(requestModel);
		requestModel.put("C_IMAGENAME", "images/model/"+requestModel.get("C_IMAGENAME"));
		ResultMapSql map =this.pInsert(requestModel, user, "SYS_PAGESUB", false, null, false);
		requestModel.put("I_SYS_ROLE_ID", 0);
		requestModel.put("I_SYS_PAGESUB_ID", Util.objInt(map.id));
		this.pInsert(requestModel, user, "SYS_AUTHORITY", false, null, false);
		return null;
	}
	
	/**
	 * 子表新增验证
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap saveSubCheck(ModelMap requestModel) {
		String C_NAME = Util.objStr(requestModel.get("C_NAME"));
		if(Util.strIsEmpty(C_NAME)){
			throw new SystemException("名称不能为空");
		}
		
		String sql = "SELECT 1 FROM SYS_PAGESUB where C_NAME = ? ";
		if(dao.isRecordExist(sql, new Object[]{requestModel.get("C_NAME")})){
			throw new SystemException("名称重复，不能保存，请重新填写！");
		}
		
		String C_URL = Util.objStr(requestModel.get("C_URL"));
		if(Util.strIsEmpty(C_URL)){
			throw new SystemException("路径不能为空");
		}
		
		sql = "SELECT 1 FROM SYS_PAGESUB where C_URL = ?";
		if(dao.isRecordExist(sql, new Object[]{requestModel.get("C_URL")})){
			throw new SystemException("路径重复，不能保存，请重新填写！");
		}
		return null;
	}
	
	/**
	 * 子表修改
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap updateSub(ModelMap requestModel) {
		SessionUser user = new SessionUser();
		updateCheck(requestModel);
		requestModel.put("C_IMAGENAME", "images/model/"+requestModel.get("C_IMAGENAME"));
		this.pUpdate(requestModel, user, "SYS_PAGESUB", null);
		return null;
	}
	
	/**
	 * 子表修改验证
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap updateSubCheck(ModelMap requestModel) {
		String C_NAME = Util.objStr(requestModel.get("C_NAME"));
		if(Util.strIsEmpty(C_NAME)){
			throw new SystemException("名称不能为空");
		}
		
		String sql = "SELECT 1 FROM SYS_PAGESUB where C_NAME = ? and id != ?";
		if(dao.isRecordExist(sql, new Object[]{C_NAME, requestModel.get("ID")})){
			throw new SystemException("名称重复，不能修改，请重新填写！");
		}
		
		String C_URL = Util.objStr(requestModel.get("C_URL"));
		if(Util.strIsEmpty(C_URL)){
			throw new SystemException("路径不能为空");
		}
		
		sql = "SELECT 1 FROM SYS_PAGESUB where C_URL = ? and id != ?";
		if(dao.isRecordExist(sql, new Object[]{C_URL, requestModel.get("ID")})){
			throw new SystemException("路径重复，不能修改，请重新填写！");
		}
		return null;
	}
	
	/**
	 * 子表删除
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap deleteSub(ModelMap requestModel) {
		deleteCheck(requestModel);
		this.pDeletePhysical(requestModel, "SYS_PAGESUB");
		return null;
	}
	
	/**
	 * 子表删除验证
	 * 
	 * @param requestModel
	 * @return 
	 * 
	 * history
	 * 
	 */
	public ModelMap deleteSubCheck(ModelMap requestModel) {
		return null;
	}
	
}
