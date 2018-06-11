package base.baseinterface;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import atomic.dao.IDAO;
import atomic.exception.SystemException;
import base.tableMap.ITableMap;
import base.tableMap.ConstType.IServiceOperationDel;
import base.tableMap.ConstType.IServiceOperationInsert;
import base.tableMap.ConstType.IServiceOperationReverseDel;
import base.tableMap.ConstType.IServiceOperationUpdate;
import base.tableMap.ConstType.ResultMapSql;
import base.util.FactoryStatic;
import base.util.SessionUser;
import base.util.Util;


/**
 * 系统中基础的业务类,为其他业务类提供基础方法
 * 
 * @author wx
 * @version  2014-6-20
 * history:
 *
 */
@SuppressWarnings({"rawtypes" , "unchecked"})
public abstract class BaseService implements IBaseService {
	
	/**
	 * DAO对象,数据库操作对象,通过Spring启动时注入
	 */
	@Resource(name="dao")
	protected IDAO dao;
	
	public IDAO getDao()
	{
		return dao;
	}
	
	
	protected String winUrl = "";
	/**
	 * Log4j对象,可在派生类中直接使用
	 */
	protected Logger log = Logger.getLogger(this.getClass());

	/**
	 * 
	 * 物理删除
	 * 
	 * @param sendMap
	 * @param tableName void
	 * 
	 * history
	 *
	 */
	protected void pDeletePhysical(Map sendMap , String tableName)
	{
		String str = "delete from "+tableName+" where id="+sendMap.get("ID").toString();
		dao.executeUpdate(str, null);
	}
	
	/**
	 * 
	 * TODO
	 * 逻辑删除
	 * @param sendMap
	 * @param user
	 * @param tableName
	 * @param invoke 
	 * @param delCheck 删除审核状态
	 * history
	 *
	 */
	protected void pSingleDelete(Map sendMap ,SessionUser user ,String tableName , IServiceOperationDel invoke)
	{
		ITableMap m = FactoryStatic.getTableMap(tableName, dao);
		String id = sendMap.get("ID").toString();
		m.setMapValue("ID", id);
		m.setMapValue("I_DEL", "1");
		dao.executeUpdate( m.reUpdateSqlValue(user, false).sqlInsert, null);
		if (invoke != null)
		{
			try
			{
				invoke.delBackInvoke(tableName, sendMap.get("ID").toString(), user, sendMap, null);
			}
			catch(Exception e)
			{
				throw new SystemException(e.getMessage());
			}
			
		}
	}
	/**
	 * 
	 * TODO
	 * 恢复删除
	 * @param sendMap
	 * @param user
	 * @param tableName
	 * @param invoke
	 * @param reserveDelCheck 是否执行恢复审核状态
	 * 
	 * history
	 *
	 */
	protected void pReverseDelete(Map sendMap ,SessionUser user ,String tableName,IServiceOperationReverseDel invoke)
	{
		ITableMap m = FactoryStatic.getTableMap(tableName, dao);
		String id = sendMap.get("ID").toString();
		m.setMapValue("ID", id);
		m.setMapValue("I_DEL", "0");

		try
		{	
			dao.executeUpdate(m.reUpdateSqlValue(user, false).sqlInsert, null);
			if (invoke!=null)
			{
				invoke.reverseDelBackInvoke(tableName, sendMap.get("ID").toString(), user, sendMap, null);
			}
		}
		catch(Exception er)
		{
			throw new SystemException(er.getMessage());
		}

	}
	
	/**
	 * 
	 * TODO
	 * 插入记录
	 * @param sendMap 请求SPRINGMAP
	 * @param user 用户
	 * @param tableName 表名
	 * @param isGenerateCode 是否生产C_CODE
	 * @param invoke		插入回调方法
	 * @param performCommit 是否执行提交操作
	 * @return ResultMapSql
	 * 
	 * history
	 *
	 */
	protected ResultMapSql pInsert(Map sendMap ,SessionUser user ,String tableName , boolean isGenerateCode, IServiceOperationInsert invoke , boolean... isFactoryId)
	{
		ITableMap m = FactoryStatic.getTableMap(tableName, dao);
		m.synchronizeMap(sendMap);
		
		if (isGenerateCode)
		{
			m.setMapValue("C_CODE", Util.getBusinessCode(dao, tableName));
		}

		if (isFactoryId.length == 0)
		{
			m.setMapValue("I_SYS_FACTORY_ID", user.getFactoryId());
		}
		
		ResultMapSql s = m.reInsertValue(user , dao);
		try{
			dao.executeUpdate(s.sqlInsert, null);
			
			if (invoke != null)
			{
				invoke.insertBackInvoke(tableName, s.id, user, sendMap, null);
			}
		}
		catch(Exception er)
		{
			throw new SystemException(er.getMessage());
		}
		return s;
	}

	/**
	 * 
	 * 修改记录
	 * 
	 * @param sendMap
	 * @param user
	 * @param tableName
	 * @return ResultMapSql
	 * 
	 * history
	 *
	 */
	protected ResultMapSql pUpdate(Map sendMap ,SessionUser user ,String tableName , IServiceOperationUpdate invoke)
	{
		ITableMap m = FactoryStatic.getTableMap(tableName, dao);

		m.synchronizeEqualMap(sendMap);
		ResultMapSql s = m.reUpdateSqlValue(user , true);
		if (invoke != null)
		{
			try
			{
				invoke.updateBackInvoke(tableName, s.id, user, sendMap, null);
			} catch(Exception e)
			{
				throw new SystemException(e.getMessage());
			}
		}
		dao.executeUpdate(s.sqlInsert, null);
		return s;
	}
}
