package business.common.authorityMem.movable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atomic.dao.IDAO;
import atomic.shareMem.BaseMemory;
import base.util.FactoryStatic;

/**
 * 移动设备角色权限缓存处理类
 * 角色->一级菜单【模块类别】->二级菜单【功能模块】->三级【操作功能】 
 * 
 * @author wx
 * @version  2014-4-3
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@SuppressWarnings({"rawtypes" , "unchecked"})
public class MovableRoleMem extends BaseMemory {

	private Map<String , List> fMovableWinMap = new HashMap<String , List>();
	private static MovableRoleMem fMem = null;
	private String key = null;
	
	private MovableRoleMem(){
		this.shareMemKey = "WebRoleMem";
	}

	public static MovableRoleMem getFMem(){
		if (fMem == null){
			fMem = new MovableRoleMem(); 
		}
		return fMem;
	}
	
	/**
	 * 获取角色对应的企业页面信息
	 * 
	 * list是反回的页面权限列表，其中函有子表列表的key是subList
	 * 
	 * @param RoleId 用户角色ID
	 * @return List  模块类别列表 
	 * history
	 *
	 */
	public synchronized List getPage(Integer factoryId, Integer RoleId){
		List r = null;
		this.key = factoryId+"_"+RoleId;
		if(fMovableWinMap.size() <= 0 && get() != null){
			fMovableWinMap = (Map)get();
			r = fMovableWinMap.get(key);
		}
		if(r == null || get() == null){
			r = getModel(factoryId, RoleId);
			for (int i=0 ; i<r.size() ; i++){
				Map classMap = (Map)r.get(i);
				String pageId = classMap.get("I_SYS_PAGE_ID").toString();
				List pageSub = getSubModel(RoleId, pageId);;
				classMap.put("subList", pageSub);
			}
			fMovableWinMap.put(key, r);
			put(fMovableWinMap);
		}
		return r;
	}
	
	/**
	 * 给缓存key赋值
	 * 
	 * @param factoryId void
	 * 
	 * history
	 * 
	 */
	public void setMemKey(Integer factoryId, Integer id){
		this.key = factoryId+"_"+id.toString();
	}
	
	/**
	 * 清空缓存
	 *  
	 * 
	 * history
	 * 
	 */
	public void pClear(){
		fMovableWinMap.put(this.key, null);
		this.put(fMovableWinMap);
	}

	/**
	 * 得到dao
	 * 
	 * @return IDAO
	 * 
	 * history
	 * 
	 */
	private static IDAO getDao(){
		return FactoryStatic.getDao();
	}
	
	/**
	 * 得到一级页面
	 * 
	 * @param roleId
	 * @return List
	 * 
	 * history
	 * 
	 */
	private List getModel(Integer factoryId, Integer roleId){
		String str ="SELECT T2.C_NAME, T2.C_URL, T2.C_IMAGENAME, T2.C_MEMO, T1.I_SYS_PAGE_ID "+
					"  FROM SYS_AUTHORITY T, SYS_PAGESUB T1, SYS_PAGE T2 "+
					" WHERE T.I_SYS_PAGESUB_ID = T1.ID AND T1.I_SYS_PAGE_ID = T2.ID "+
					"   AND T.I_SYS_ROLE_ID = ? ORDER BY T2.C_NAME";
		return getDao().executeQuery(str, new Object[]{roleId});
	}
	
	/**
	 * 得到二级页面
	 * 
	 * @param roleId
	 * @param pageId
	 * @return List
	 * 
	 * history
	 * 
	 */
	private List getSubModel(Integer roleId, String pageId){
		String str ="SELECT T1.C_NAME, T1.C_URL, T1.C_IMAGENAME, T1.C_MEMO "+
					"  FROM SYS_AUTHORITY T, SYS_PAGESUB T1 "+
					" WHERE T.I_SYS_PAGESUB_ID = T1.ID "+
					"   AND T.I_SYS_ROLE_ID = ? " +
					"   AND T1.I_SYS_PAGE_ID = ? ";
		return getDao().executeQuery(str, new Object[]{roleId, pageId});
	}

}
