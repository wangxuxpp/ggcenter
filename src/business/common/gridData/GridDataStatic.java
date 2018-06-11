package business.common.gridData;

import org.springframework.ui.ModelMap;

import atomic.dao.IDAO;
import base.util.Util;

/**
 * 查询grid
 * 
 * @author 王丹
 * @version  1.0  2015-7-7
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
public class GridDataStatic {

	/**
	 * 得到分页列表
	 * 
	 * @param dao
	 * @param sql
	 * @param start
	 * @param limit
	 * @return Map
	 * 
	 * history
	 * 
	 */
	public static ModelMap getGrid(IDAO dao, String sql, int start, int limit){
		ModelMap map = new ModelMap();
		int totalNum = Util.objInt(dao.recordSize(sql, null));
		map.put("totalNum", totalNum);
		if(limit > 0){
			sql = String.format("SELECT * FROM (SELECT TABLE_ALIAS_.*, ROWNUM ROWNO FROM (%s) TABLE_ALIAS_ WHERE ROWNUM <= ?) WHERE ROWNO >= ?", sql);
			map.put("dataList", dao.executeQuery(sql, new Object[]{start+limit, start+1}));
			int totalPage = totalNum%limit == 0 ? totalNum/limit : totalNum/limit+1;
			map.put("totalPage", totalPage);
		}else{
			sql = String.format("SELECT TABLE_ALIAS_.*, ROWNUM ROWNO FROM (%s) TABLE_ALIAS_ ", sql);
			map.put("dataList", dao.executeQuery(sql, null));
		}
		return map;
	}
	
}
