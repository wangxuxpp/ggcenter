package atomic.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;



@SuppressWarnings({"rawtypes"})
public interface IDAO extends IdaoUpper{
    
	int executeUpdate(String sql, Object[] args);
	
	int executeUpdate(Connection con, String sql, Object[] args);
	
	
	List executeQuery(String sql, Object[] args);
	
	
	Map queryMap(String sql, Object[] args);
	
	Integer queryInt(String sql, Object[] args);
	
	long queryLong(String sql, Object[] args);
	
	String queryString(String sql, Object[] args);
	
	
	Object queryObject(String sql, Object[] args, Class clas);
	
	
	Map fenyeSql(int start, int rows, String sql, Object[] args);
	
	
	Map fenyeMap(int start, int rows, String sql, Object[] args);
	
	long recordSize(String sql, Object[] args);
	
	boolean isRecordExist(String sql, Object[] args);
	
	int getSequenceNextVal(String sequenceName);
	
	void callProcedure(String procedureName, Object[] args);
	
	boolean check(String tableName, Map<String, Object> whereMap);
	
	int save(String tableName, Map<String, Object> columnMap);
	
	int save(Connection con, String tableName, Map<String, Object> columnMap);
	
	String saveSql(String tableName, Map<String, Object> columnMap);
	
	int delete(String tableName, Map<String, Object> whereMap);
	
	int delete(Connection con, String tableName, Map<String, Object> whereMap);
	
	int regain(String tableName, Map<String, Object> whereMap);
	
	int regain(Connection con, String tableName, Map<String, Object> whereMap);
	
	int update(String tableName, Map<String, Object> columnMap, Map<String, Object> whereMap);
	
	int update(Connection con, String tableName, Map<String, Object> columnMap, Map<String, Object> whereMap);
	
	String updateSql(String tableName, Map<String, Object> columnMap, Map<String, Object> whereMap);
	
	
	Map get(String tableName, Map<String, Object> columnMap, Map<String, Object> whereMap);
	
	
	List find(String tableName, Map<String, Object> columnMap, Map<String, Object> whereMap);
	
	Connection openConnection();
	
	void closeConnection(Connection con);
	
	int[] batchUpdate(String sql,final List<Object[]> list);
}
