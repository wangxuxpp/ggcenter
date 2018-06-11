package business.common.authorityMem.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atomic.dao.IDAO;
import atomic.shareMem.BaseMemory;
import base.util.FactoryStatic;

/**
 * web 角色权限缓存处理类
 * 角色->一级菜单【模块类别】->二级菜单【功能模块】->三级【操作功能】 
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
public class WebRoleMem extends BaseMemory {

	private Map<String , List> fMainWinMap = new HashMap<String, List>();
	private static WebRoleMem fMem = null;
	private String key = "";
	
	private WebRoleMem(){
		this.shareMemKey = "WebRoleMem";
	}

	public static WebRoleMem getFMem(){
		if (fMem == null){
			fMem = new WebRoleMem(); 
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
		if(fMainWinMap.size() <= 0 && get() != null){
			fMainWinMap = (Map)get();
			r = fMainWinMap.get(key);
		}
		if(r == null || get() == null){
			r = getModel(factoryId, RoleId);
			for (int i=0 ; i<r.size() ; i++){
				Map classMap = (Map)r.get(i);
				String pageId = classMap.get("I_SYS_PAGE_ID").toString();
				List pageSub = getSubModel(RoleId, pageId);;
				classMap.put("subList", pageSub);
			}
			fMainWinMap.put(key, r);
			put(fMainWinMap);
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
		fMainWinMap.put(this.key, null);
		this.put(fMainWinMap);
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
					"  FROM SYS_AUTHORITY T "+
					" inner join SYS_PAGESUB T1 on t.i_sys_pagesub_id = t1.id "+
					" inner join SYS_PAGE T2 on t1.i_sys_page_id = t2.id "+
					" inner join sys_pageinvalid t3 on t2.id = t3.i_sys_page_id "+
					" WHERE T.I_SYS_PAGESUB_ID = T1.ID "+
					"   AND T1.I_SYS_PAGE_ID = T2.ID "+
					"   AND T.I_SYS_ROLE_ID = ? "+
					"   AND T3.I_SYS_FACTORY_ID = ? "+
					" group BY T2.C_NAME, T2.C_URL, T2.C_IMAGENAME, T2.C_MEMO, T1.I_SYS_PAGE_ID "+
					" order by T1.I_SYS_PAGE_ID ";
		return getDao().executeQuery(str, new Object[]{roleId, factoryId});
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
