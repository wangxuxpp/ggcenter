package base.tableMap;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import atomic.dao.IDAO;
import atomic.exception.DBAccessException;
import base.tableMap.ConstType.ResultMapSql;
import base.util.SessionUser;


/**
 * 数据库表Map映射工具类接口
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
public interface ITableMap {
	/**
	 * 清空信息
	 * 1.清空MAP中所有元素
	 * 2.清空表名和表别名
	 */
	 void clear();
	/**
	 * 清空Map中的值
	 */
	void clearMapValue();
	/**
	 * 设置Map中指定Key的Value
	 * @param key
	 * @param value
	 */
	void setMapValue(String key , Object value);
	/**
	 * 获取Map中的值
	 * @param key
	 * @return
	 */
	Object GetMapValue(String key);
	/**
	 * 整理Map中的数据
	 * 1.处理日期型数据
	 * 2.处理字符串等
	 */
	void PrepareValue();
			/**
	 * @return the fTableName
	 */
	String getFTableName() ;
			/**
	 * 设置Tablename 字段并根据该字段生成Map
	 * @param tableName the fTableName to set
	 * @throws SQLException 
	 */
	void setFTableName(String tableName , IDAO fdao) throws DBAccessException;
			/**
	 * @return the fAlias
	 */
	String getFAlias();
	/**
	 * @param alias the fAlias to set
	 */
	void setFAlias(String alias);
	/**
	 * @return the fMap
	 */
	Map<String, Object> getFMap();
	/**
	 * 准备插入语句值自动增加部分值
	 * @param logUser 操作员
	 * @param fdao 数据库操作对象
	 */
	void prepareInsertSqlValue(SessionUser logUser , IDAO fdao);
	 /**
	 * 准备修改语句自动赋部分值
	 * @param logUser
	 */
	void prepareUpdateSqlValue(SessionUser logUser);
	/**
	 * 生成插入Sql语句
	 * @param logUser
	 * @param fdao
	 * @return
	 */
	ResultMapSql reInsertValue(SessionUser logUser , IDAO fdao);
	/**
	 * 生成Sql语句Update
	 * @param logUser
	 * @return
	 */
	ResultMapSql reUpdateSqlValue(SessionUser logUser , boolean isContainEmpty);
	void synchronizeMap(Map<String , Object> value);
	void synchronizeEqualMap(Map<String , Object> value);
	
	/**
	 * 获取HttpServletRequest中和自己相同的值并赋值
	 * @param pRequest
	 */
	void synchronizeRequestValue(HttpServletRequest pRequest);
	/**
	 * 获取ModelMap中和自己相同的值并赋值
	 * @param pModel
	 */
	void synchronizeModelMapValue(ModelMap pModel);

	
	void ReadInfoFromId(String pId , IDAO pdao);
}
