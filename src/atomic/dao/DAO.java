package atomic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import atomic.exception.DBAccessException;
import base.common.Constant;


/**
 * 操作数据库工具类
 * 
 * @author 王丹
 * @version  2014-3-19
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@SuppressWarnings({"rawtypes"})
@Repository("dao")
public class DAO extends DaoAbstract implements IDAO {
	
	@Resource(name="dataSource")
	private DataSource dataSource = null;
	
	@PostConstruct
	protected void setInjectDataSource() {
		super.setDataSources(dataSource);
	}
	
	/**
	 * 根据传入的SQL语句及参数列表操作数据库,支持insert、update、delete等语句
	 * 
	 * @param sql  需要传入到SQL语句
	 * @param args 需要传入到SQL语句中的参数列表,如果没有参数则传入null,
	 * 有参数则需要跟SQL语句中的"?"所对应
	 * @return Integer执行操作语句后影响的记录数
	 */
	public int executeUpdate(String sql, Object[] args) {
		return this.pExecuteUpdate(null, sql, args);
	}
	
	/**
	 * 根据传入的SQL语句及参数列表操作数据库,支持insert、update、delete等语句
	 * 
	 * @param con  需要传入的Connection,处理审核单据的时候使用
	 * @param sql  需要传入到SQL语句
	 * @param args 需要传入到SQL语句中的参数列表,如果没有参数则传入null,
	 * 有参数则需要跟SQL语句中的"?"所对应
	 * @return Integer执行操作语句后影响的记录数
	 */
	public int executeUpdate(Connection con, String sql, Object[] args) {
		return this.pExecuteUpdate(con , sql , args);
	}
	/**
	 * 批量处理数据
	 * @param sql  更新或修改的sql
	 * @param list<Object[]> 要更新或修改的数据集合
	 *         list 中 为Object[] 数组
	 *         
	 *         String sql = "insert into tabName values(?,?)";
	 *         List<Object[]> list = new ArrayList<Object[]>();
	 *         Object[] param = null;
	 *         for(int i=0; i<3; i++){
	 *             param =  new Object[2];
	 *             param[0] = "a_"+(i+1);
	 *             param[1] = "b_"+(i+1);
	 *             list.add(param);
	 *         }
	 */
	public int[] batchUpdate(String sql,final List<Object[]> list) {
		 try{
			 //定义spring批量更新数据对象
			 BatchPreparedStatementSetter setter = null;
			 setter =  new BatchPreparedStatementSetter() {
				 
				 //用来返回批次的大小
				 public int getBatchSize() {
					 return list.size();
				 }
				 
				 //用来为PreparedStatement设值
				 //参数说明：
				 // ps：我们将要设值的PreparedStatement
				 // i：在这个批次中，正在执行操作的索引，从0算起。
				 public void setValues(PreparedStatement ps, int i) throws SQLException {
					 
					 //绑定参数到PreparedStatement对象中
					 setPreparedStatement(ps,list.get(i));
				 }
			 };
			return this.getJdbcTemplate().batchUpdate(sql,setter);
		}catch(Exception e){
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * 根据传入的SQL语句及参数列表,查询出记录集的List
	 * 
	 * @param sql  需要传入到SQL语句
	 * @param args 需要传入到SQL语句中的参数列表,如果没有参数则传入null,
	 * 有参数则需要跟SQL语句中的"?"所对应
	 * @return Object对象的List集合,即结果集
	 */
	public List executeQuery(String sql, Object[] args) {
		return this.pExecuteQuery(sql, args);
	}
	
	/**
	 * 根据传入的SQL语句及参数列表,查询单个Map对象的集合
	 * 只取出该结果集的第一行数据
	 * 
	 * @param sql  需要传入到SQL语句
	 * @param args 需要传入到SQL语句中的参数列表,如果没有参数则传入null,
	 * 有参数则需要跟SQL语句中的"?"所对应
	 * @return Map对象的集合,即结果集
	 */
	public Map queryMap(String sql, Object[] args) {
		List l = this.pExecuteQuery(sql, args);
		if(l != null && l.size()>0)
		{
			return (Map)l.get(0);
		} else {
			return new HashMap();
		}
	}
	
	/**
	 * 根据传入的SQL语句查询单个整数值
	 * 
	 * @param sql  需要传入到SQL语句
	 * @param args 需要传入到SQL语句中的参数列表,如果没有参数则传入null,
	 * 有参数则需要跟SQL语句中的"?"所对应
	 * @return 返回值类型为Integer
	 */
	public Integer queryInt(String sql, Object[] args) {
		Number n = this.queryValue(sql, args, new Integer(1)) ;
		return n == null ? 0 : n.intValue();
	}
	
	/**
	 * 根据传入的SQL语句查询单个长整数值
	 * 
	 * @param sql  需要传入到SQL语句
	 * @param args 需要传入到SQL语句中的参数列表,如果没有参数则传入null,
	 * 有参数则需要跟SQL语句中的"?"所对应
	 * @return 返回值类型为long
	 */
	public long queryLong(String sql, Object[] args) {
		Number n = this.queryValue(sql, args, new Long(1)) ;
		return n == null ? 0 : n.longValue();
	}
	
	/**
	 * 根据传入的SQL语句查询单个字符串对象
	 * 
	 * @param sql  需要传入到SQL语句
	 * @param args 需要传入到SQL语句中的参数列表,如果没有参数则传入null,
	 * 有参数则需要跟SQL语句中的"?"所对应
	 * @return 返回值类型为String
	 */
	public String queryString(String sql, Object[] args) {
		String str = this.queryValue(sql, args, new String());
		return str == null ? "" : str;
		
	}
	
	/**
	 * 根据传入的SQL语句查询单个Object对象
	 * 
	 * @param sql  需要传入到SQL语句
	 * @param args 需要传入到SQL语句中的参数列表,如果没有参数则传入null,
	 * 有参数则需要跟SQL语句中的"?"所对应
	 * @param clas 需要转换后的数据类型
	 * @return 返回值类型为Object
	 */
	public Object queryObject(String sql, Object[] args, Class cla) {
		try {
			
			if (cla.equals(Integer.class))
			{
				return this.queryInt(sql, args);
			}
			if (cla.equals(Long.class))
			{
				return this.queryLong(sql, args);
			}
			
			return this.queryValue(sql, args, cla.newInstance());
		} catch (Exception e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * 分页方法专用
	 * 
	 * @param start 开始记录数
	 * @param rows  每页行数
	 * @param sql	查询语句
	 * @param args  参数列表 
	 * @return Map对象,新分页SQL,新分页参数
	 */	   
	public Map fenyeSql(int start, int rows, String sql, Object[] args) {

			//result.put("newSql", newSql);
			//result.put("newArgs", newArgs);
		return null;
	}
	
	/**
	 * 将分页查询的结果放入到map中并且同时放入总行数
	 *
	 * @param start	开始记录数
	 * @param rows  每页行数
	 * @param sql   查询语句
	 * @param args  参数列表 
	 * @return Map  注意其中的key在此方法中是写死的
	 * 		key: fenyeList-----------------------分页查询的结果集
	 * 		key: recordSize----------------------查询的总记录数
	 */
	public Map fenyeMap(int start, int rows, String sql, Object[] args) {

		/*	
			result.put("fenyeList", fenyeList);
			result.put("recordSize", recordSize);
			result.put("newSql", newSql);
			result.put("newArgs", newArgs);
			result.put("oldSql", sql);
		} catch(Exception e) {
			result.put("fenyeList", new ArrayList(0));
			result.put("recordSize", new Long(0));
		}*/
		return null;
	}
	
	/**
	 * 根据传入的SQL语句,取得该查询语句所查询出的记录个数
	 *
	 * @param sql  需要传入到SQL语句
	 * @param args 需要传入到SQL语句中的参数列表,如果没有参数则传入null,
	 * 有参数则需要跟SQL语句中的"?"所对应
	 * @return 返回值类型为long
	 */
	public long recordSize(String sql, Object[] args) {
		try {
			return this.queryLong(" select count(1) from ("+sql+")", args);
		} catch(Exception e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * 判断一条记录是否存在
	 * 
	 * @param sql  查询语句
	 * @param args 参数列表
	 * @return 返回值类型为boolean
	 */
	public boolean isRecordExist(String sql, Object[] args) {
		try {
			return recordSize(sql, args) > 0 ? true : false;
		} catch(Exception e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * 根据传入的SQL语句获得序列的下一个值
	 * 
	 * @param sequenceName 序列名称
	 * @return 返回值类型为long
	 */
	public int getSequenceNextVal(String sequenceName) {
		String sql = new StringBuffer("SELECT ").append(sequenceName.toUpperCase()).append(".NEXTVAL FROM DUAL").toString();
		try {
			Number n = this.queryValue(sql, null, new Long(0), false);
			return n.intValue();
		} catch(Exception e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * 调用存储过程,无返回值
	 * 
	 * @param procedureName 存储过程名称
	 * @param args 存储过程参数数组
	 */
	public void callProcedure(String procedureName, Object[] args) {
		this.pCallProcedure(null, procedureName, args);
	}
	
	

	/**
	 * 数据校验
	 * 
	 * @param tableName 数据库表名
	 * @param whereMap 查询条件=>whereMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值
	 * @return 校验结果=>
	 *                数据存在：true
	 *                数据不存在：false
	 */
	public boolean check(String tableName, Map<String, Object> whereMap) {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(1) FROM ").append(tableName).append(getWhereStr(whereMap));
		
			return isRecordExist(sql.toString(), null);
		} catch(Exception e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * 保存数据
	 * 
	 * @param tableName 数据库表名
	 * @param columnMap 查询条件=>columnMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值
	 * @return 执行结果 =>
	 *                大于0：执行成功
	 *                小于等于：执行失败
	 */
	public int save(String tableName, Map<String, Object> columnMap) {
		return save(null , tableName , columnMap);
	}
	
	/**
	 * 保存数据
	 * 
	 * @param con  需要传入的Connection,处理审核单据的时候使用
	 * @param tableName 数据库表名
	 * @param columnMap 查询条件=>columnMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值
	 * @return 执行结果 =>
	 *                大于0：执行成功
	 *                小于等于：执行失败
	 */
	public int save(Connection con, String tableName, Map<String, Object> columnMap) {
		try {
			return this.pExecuteUpdate(con, saveSql(tableName, columnMap), null);
		} catch(Exception e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * 保存数据SQL语句
	 * 
	 * @param tableName 数据库表名
	 * @param columnMap 查询条件=>columnMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值
	 * @return 返回SQL语句
	 */
	public String saveSql(String tableName, Map<String, Object> columnMap) {
		try {
			StringBuffer sql = new StringBuffer();
			StringBuffer column = new StringBuffer();
			StringBuffer val = new StringBuffer();
			if(columnMap != null && columnMap.size() > 0) {
				for(String key : columnMap.keySet()) {
					column.append(key).append(", ");
					val.append(getColumnVal(key, columnMap.get(key))).append(", ");
				}
			}
			
			String columnStr = column.toString().substring(0, column.toString().lastIndexOf(", "));
			String valStr = val.toString().substring(0, val.toString().lastIndexOf(", "));
			sql.append("INSERT INTO ").append(tableName).append(" (").append(columnStr).append(")").append(" VALUES (").append(valStr).append(")");
			
			return sql.toString();
		} catch(Exception e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * 删除数据
	 * 
	 * @param tableName 数据库表名
	 * @param whereMap 查询条件=>whereMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值
	 * @return 执行结果 =>
	 *                大于0：执行成功
	 *                小于等于：执行失败
	 */
	public int delete(String tableName, Map<String, Object> whereMap) {
		return delete(null ,tableName , whereMap );
	}
	
	/**
	 * 删除数据
	 * 
	 * @param con  需要传入的Connection,处理审核单据的时候使用
	 * @param tableName 数据库表名
	 * @param whereMap 查询条件=>whereMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值
	 * @return 执行结果 =>
	 *                大于0：执行成功
	 *                小于等于：执行失败
	 */
	public int delete(Connection con, String tableName, Map<String, Object> whereMap) {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE ").append(tableName).append(" SET I_DEL = ").append(Constant.STATUS_STOP).append(getWhereStr(whereMap));
			return executeUpdate(con, sql.toString(), null);
		} catch(Exception e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * 恢复数据
	 * 
	 * @param tableName 数据库表名
	 * @param whereMap 查询条件=>whereMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值
	 * @return 执行结果 =>
	 *                大于0：执行成功
	 *                小于等于：执行失败
	 */
	public int regain(String tableName, Map<String, Object> whereMap) {
		return regain(null , tableName , whereMap);
	}
	
	/**
	 * 恢复数据
	 * 
	 * @param con  需要传入的Connection,处理审核单据的时候使用
	 * @param tableName 数据库表名
	 * @param whereMap 查询条件=>whereMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值
	 * @return 执行结果 =>
	 *                大于0：执行成功
	 *                小于等于：执行失败
	 */
	public int regain(Connection con, String tableName, Map<String, Object> whereMap) {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE ").append(tableName).append(" SET I_DEL = ").append(Constant.STATUS_START).append(getWhereStr(whereMap));
		
			return executeUpdate(sql.toString(), null);
		} catch(Exception e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * 修改数据
	 * 
	 * @param tableName 数据库表名
	 * @param columnMap 查询条件=>columnMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值
	 * @param whereMap 查询条件=>whereMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值                         
	 *                         
	 * @return 执行结果 =>
	 *                大于0：执行成功
	 *                小于等于：执行失败
	 */
	public int update(String tableName, Map<String, Object> columnMap, Map<String, Object> whereMap) {
		return update(null , tableName , columnMap , whereMap);
	}
	
	/**
	 * 修改数据
	 * 
	 * @param con  需要传入的Connection,处理审核单据的时候使用
	 * @param tableName 数据库表名
	 * @param columnMap 查询条件=>columnMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值
	 * @param whereMap 查询条件=>whereMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值                         
	 *                         
	 * @return 执行结果 =>
	 *                大于0：执行成功
	 *                小于等于：执行失败
	 */
	public int update(Connection con, String tableName, Map<String, Object> columnMap, Map<String, Object> whereMap) {
		try {
			
			return executeUpdate(con, updateSql(tableName , columnMap , whereMap), null);
		} catch(Exception e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * 修改数据SQL语句
	 * 
	 * @param tableName 数据库表名
	 * @param columnMap 查询条件=>columnMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值
	 * @param whereMap 查询条件=>whereMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值                         
	 *                         
	 * @return 返回SQL语句
	 */
	public String updateSql(String tableName, Map<String, Object> columnMap, Map<String, Object> whereMap) {
		try {
			StringBuffer sql = new StringBuffer();
			StringBuffer column = new StringBuffer();
			if(columnMap != null && columnMap.size() > 0) {
				for(String key : columnMap.keySet()) {
					column.append(key).append(" = ").append(getColumnVal(key, columnMap.get(key))).append(", ");
				}
			}
			
			String columnStr = column.toString().substring(0, column.toString().lastIndexOf(", "));
			sql.append("UPDATE ").append(tableName).append(" SET ").append(columnStr).append(getWhereStr(whereMap));
			
			return sql.toString();
		} catch(Exception e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * 查询数据（单行记录）
	 * 
	 * @param tableName 数据库表名
	 * @param columnMap 查询条件=>columnMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值
	 * @param whereMap 查询条件=>whereMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值                         
	 *                         
	 * @return 返回结果集
	 */
	
	public Map get(String tableName, Map<String, Object> columnMap, Map<String, Object> whereMap) {
		try {
			StringBuffer sql = new StringBuffer();
			StringBuffer column = new StringBuffer();
			if(columnMap != null && columnMap.size() > 0) {
				for(String key : columnMap.keySet()) {
					column.append(getColumnVal(key)).append(", ");
				}
			}
			
			String columnStr = column.toString().substring(0, column.toString().lastIndexOf(", "));
			sql.append("SELECT ").append(columnStr).append(" FROM ").append(tableName).append(getWhereStr(whereMap));
			
			return queryMap(sql.toString(), null);
		} catch(Exception e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * 查询数据（多行记录）
	 * 
	 * @param tableName 数据库表名
	 * @param columnMap 查询条件=>columnMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值
	 * @param whereMap 查询条件=>whereMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值                         
	 *                         
	 * @return 返回结果集
	 */
	
	public List find(String tableName, Map<String, Object> columnMap, Map<String, Object> whereMap) {
		try {
			StringBuffer sql = new StringBuffer();
			StringBuffer column = new StringBuffer();
			if(columnMap != null && columnMap.size() > 0) {
				for(String key : columnMap.keySet()) {
					column.append(getColumnVal(key)).append(", ");
				}
			}
			
			String columnStr = column.toString().substring(0, column.toString().lastIndexOf(", "));
			sql.append("SELECT ").append(columnStr).append(" FROM ").append(tableName).append(getWhereStr(whereMap));
			
			return executeQuery(sql.toString(), null);
		} catch(Exception e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * 获得查询条件SQL语句
	 * 
	 * @param whereMap 查询条件=>whereMap
	 *                         key：对应表的字段名
	 *                         value：对应字段值
	 * @return 查询条件SQL语句
	 */
	private String getWhereStr(Map<String, Object> whereMap) {
		StringBuffer sql = new StringBuffer();
		if(whereMap != null && whereMap.size() > 0) {
			int i = 0;
			for(String key : whereMap.keySet()) {
				sql.append(getWhereVal(i, key, whereMap.get(key)));
				i++;
			}
		} else {
			sql.append("");
		}
		
		return sql.toString();
	}
	
	/**
	 * 获得查询条件
	 * 
	 * @param i
	 * @param key
	 * @param value
	 * @return
	 */
	private String getWhereVal(int i, String key, Object value) {
		StringBuffer val = new StringBuffer();
		if(i == 0) {
			val.append(" WHERE ");
		} else {
			val.append(" AND ");
		}
		
		if(key.toLowerCase().startsWith("d_")) {
			val.append("TRUNC(").append(key).append(")").append(" = ").append("TO_DATE('").append(value).append("','yyyy-mm-dd')");
		} else {
			if(key.toLowerCase().equals("id")) {
				val.append(key).append(" = ").append(value);
			} else if(key.toLowerCase().startsWith("i_")) {
				val.append(key).append(" = ").append(value);
			} else {
				val.append(key).append(" = ").append("'").append(value).append("'");
			}
		}
		
		return val.toString();
	}
	
	/**
	 * 获得列值(insert,update)
	 * 
	 * @param key
	 * @param value
	 * @param isWhere
	 * 
	 * @return
	 */
	private String getColumnVal(String key, Object value) {
		StringBuffer val = new StringBuffer();
		if(key.toLowerCase().startsWith("d_")) {
			val.append("TO_DATE('").append(value).append("','yyyy-mm-dd hh24:mi:ss')");
		} else {
			if(key.toLowerCase().equals("id")) {
				val.append(value);
			} else if(key.toLowerCase().startsWith("i_")) {
				val.append(value);
			} else {
				val.append("'").append(value).append("'");
			}
		}
		
		return val.toString();
	}
	
	/**
	 * 获得列值(select)
	 * 
	 * @param key
	 * @return
	 */
	private String getColumnVal(String key) {
		StringBuffer val = new StringBuffer();
		if(key.toLowerCase().startsWith("d_")) {
			val.append("TO_CHAR(").append(key).append(",'yyyy-mm-dd') ").append(key);
		} else {
			val.append(key);
		}
		
		return val.toString();
	}
}
