package base.tableMap;

import java.sql.Connection;
import java.util.Map;

import base.util.SessionUser;


/**
 * 公用类
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
public interface ConstType {


	/**
	 * 返回SQL语句类
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
	public class ResultMapSql
	{
		public String sqlInsert;
		public String id;
		
		public ResultMapSql()
		{
			sqlInsert ="";
			id="";
		}
	}
	
	/**
	 * 删除方法回调
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
	public interface IServiceOperationDel{
		
		void delBackInvoke(String tableName , String Id , SessionUser user , Map m , Connection cnn) throws Exception;
	}
	
	/**
	 * 插入方法回调
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
	public interface IServiceOperationInsert{
		
		void insertBackInvoke(String tableName , String Id , SessionUser user , Map m , Connection cnn) throws Exception;
	}
	
	/**
	 * 
	 * 修改方法回调
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
	public interface IServiceOperationUpdate{
		
		void updateBackInvoke(String tableName , String Id , SessionUser user , Map m , Connection cnn) throws Exception;
	}
	
	/**
	 * 恢复删除方法回调
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
	public interface IServiceOperationReverseDel{
		
		void reverseDelBackInvoke(String tableName , String Id , SessionUser user , Map m , Connection cnn) throws Exception;
	}
	

}
