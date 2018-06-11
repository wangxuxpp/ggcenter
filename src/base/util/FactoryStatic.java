package base.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import atomic.dao.IDAO;
import atomic.exception.SystemException;
import atomic.shareMem.ShareMem;
import base.common.Constant;
import base.tableMap.ITableMap;
import base.tableMap.TableMap;
import base.util.fileOperator.FileStreamService;
import base.util.pojo.ProjectPatch;
import base.web.WebEnvironment;
import business.common.shareMem.MemObject;
import jGrid.JqueryGrid;



/**
 * 工厂类提供创建对象实例
 * 
 * @author wx
 * @version  2014-3-20
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@SuppressWarnings({"rawtypes"})
public class FactoryStatic {

	public static IDAO getDao()
	{
		return (IDAO)WebEnvironment.getBean("dao");
	}
    public static ProjectPatch getProjectPatch()
    {
    	return (ProjectPatch)WebEnvironment.getBean("projectPatch");
    }
	public static ShareMem getShareMem()
	{
		ShareMem f= (ShareMem)WebEnvironment.getBean("shareMem");
		return f;
	}
	public static MemObject getMemObject()
	{
		MemObject f= (MemObject)WebEnvironment.getBean("memObject");
		return f;
	}
	public static final WebApplicationContext getWebAppContext()
	{
		return WebEnvironment.getWebApplicationContext();
	}
	
	/**
	 * 
	 * 释放对象
	 * 
	 * @param obj 需要释放的对象
	 * 
	 * history
	 *
	 */
    public static void freeAndNull(Object obj)
    {
    	Object op = obj;
    	obj = null;
    	if (op == null)
    	{
    		return ;
    	}
    	
		try {
			if (op instanceof Statement)
			{
				((Statement)op).close();
			}
			if (op instanceof PreparedStatement)
			{
				((PreparedStatement)op).close();
			}
			if (op instanceof ResultSet)
			{
				((ResultSet)op).close();
			}
		} catch (SQLException e) {
			throw new SystemException(e.getMessage());
		}
		finally
		{
			op = null;
		}
    }
    
    public static ITableMap getTableMap(String tableName , IDAO fDao)
    {
    	return TableMap.instance(tableName, fDao);
    }
    public static SessionUser getSessionUser(ModelMap map)
    {
    	SessionUser u = null;
    	try{
    		u = (SessionUser)map.get(Constant.SYS_SESSION);	
    	}catch(Exception e)
    	{
    	}
    	return u;
    }
	
	public static Map getGrid(ModelMap model , String sqlStr ,String emptyStr , IDAO fdao ) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ("+sqlStr+") where 1=1 " );

		if (model.get("PROPERTY") != null)
		{
			sql.append(model.get("PROPERTY").toString()) ;
		}
	
		JqueryGrid jqGrid = new JqueryGrid("");
		String param = jqGrid.getGrid().setParameter(model);
		if(jqGrid.getGrid().isSearch()) {
			sql.append(param); 
		} else{
			sql.append(emptyStr);
		}
		//数据排序规则(根据实际情况选择)
		if(!Util.strIsEmpty( jqGrid.getGrid().getSort()) && !Util.strIsEmpty(jqGrid.getGrid().getOrder())) {
			sql.append(" order by ").append(jqGrid.getGrid().getSort()).append(" ").append(jqGrid.getGrid().getOrder());
		}
		Map fenyeMap = jqGrid.getDao().fenyeMap(fdao ,jqGrid.getGrid().getStart(), jqGrid.getGrid().getRows(), sql.toString(), null);
		return jqGrid.getGrid().getParameter(fenyeMap);
	}
    public static String ToDBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) 
		{
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} 
			else if (c[i] > '\uFF00' && c[i] < '\uFF5F') 
			{
				c[i] = (char) (c[i] - 65248);
			}
		}
		String returnString = new String(c);
		return returnString;
    }
    
    private static FileStreamService fop = null;
	public static FileStreamService getFileStream()
	{
		if (fop == null)
		{
			fop = new FileStreamService();
		}
		return fop;
	}
}
