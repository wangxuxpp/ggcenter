package atomic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.JdbcUtils;

import atomic.exception.DBAccessException;

@SuppressWarnings({"rawtypes" , "unchecked"})
public abstract class DaoAbstract extends JdbcDaoSupport{
	
	protected void setDataSources(DataSource dataSource)
	{
		super.setDataSource(dataSource);
	}

	/**
	 * 绑定参数到PreparedStatement对象中
	 *
	 * @param ps
	 * @param args
	 * @throws SQLException 
	 */
	protected void setPreparedStatement(PreparedStatement ps, Object[] args) throws SQLException {
		if(args != null && args.length > 0) {	
			for(int i = 0; i < args.length; i++) {
				Object arg = args[i];
				if(arg instanceof SqlParameterValue) {
					SqlParameterValue paramValue = (SqlParameterValue)arg;
					StatementCreatorUtils.setParameterValue(ps, i + 1, paramValue, paramValue.getValue());
				} else {
					StatementCreatorUtils.setParameterValue(ps, i + 1, SqlTypeValue.TYPE_UNKNOWN, arg);
				}
			}
		}
	}
	/**
	 * 获取JDBC结果集的列名
	 * 
	 * @param rs JDBC结果集
	 * @return 结果集中所有的字段名
	 * @throws SQLException 
	 */
	protected String[] getColumnName(ResultSet rs) throws SQLException {
		ResultSetMetaData meta = rs.getMetaData();
		int colCount = meta.getColumnCount();
		String[] colName = new String[colCount];
		for(int i = 0; i < colCount; i++) {
			colName[i] = meta.getColumnName(i + 1);
		}			
		return colName;	
	}
	/**
	 * 获取行记录的结果集
	 * 
	 * @param rs
	 * @param colName
	 * @return Object对象的结果集
	 * @throws SQLException
	 */
	protected Object getRowRecord(ResultSet rs, String[] colName) throws SQLException {
		Map<String, Object> rowRecord = new HashMap<String, Object>();
		for(int i = 1; i <= colName.length; i++) {
			String key = colName[i - 1];
			Object obj = JdbcUtils.getResultSetValue(rs, i);
			if(obj == null) {
				obj = "";
			}
			rowRecord.put(key, obj);
		}
		return rowRecord;
	}
	/**
	 * 关闭PreparedStatement
	 *
	 * @param ps
	 */
	protected void closeStatement(Statement ps) {
		try {
			Statement obj = ps;
			if(obj != null) {
				obj.close();
			}
			ps = null;
			obj = null;
		} catch(Exception e) {
			throw new DBAccessException(e);
		}
	}
	/**
	 * 关闭ResultSet 
	 * 
	 * @param rs
	 */
	protected void closeResultSet(ResultSet rs) {
		try {
			ResultSet obj = rs;
			if(obj != null) {
				obj.close();
			}
			rs = null;
			obj = null;
		} catch(Exception e) {
			throw new DBAccessException(e);
		}
	}
	/**
	 * 获得调用存储工程参数字符串
	 * 
	 * @param procedureName 存储过程名称
	 * @param args 存储过程参数数组
	 * @return String参数字符串
	 */
	private String getProcedureCall(String procedureName, Object[] args) {
		StringBuffer procedureCallString = new StringBuffer();
		if(args != null && args.length > 0) {
			StringBuffer callsb = new StringBuffer();
			for(int i = 0; i < args.length; i++) {
				callsb.append("?,");
			}
			String callString = callsb.toString();		
			if(callString.endsWith(",")) {
				callString = callString.substring(0, callString.lastIndexOf(","));
			}
			procedureCallString.append("{call ").append(procedureName).append("(").append(callString).append(")").append("}");
		} else {
			procedureCallString.append("{call ").append(procedureName).append("}");
		}		
		
		return procedureCallString.toString();
	}
	
//---------------------------------------------------------------------------------------------------------------------------------------	
	/**
	 * 打开Connection
	 * 
	 * @return
	 */
	public final Connection openConnection() {
		try {
			return getConnection();
		} catch(Exception e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * 关闭Connection 
	 *
	 * @param con
	 */
	public  final void closeConnection(Connection con) {
		try {
			if(con != null) {
				releaseConnection(con);
			}
			con = null;
		} catch(Exception e) {
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
	
	public final  List pExecuteQuery(String sql, Object[] args) {
		List<Object> result = new ArrayList<Object>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = this.openConnection();
			ps = con.prepareStatement(sql);
			if(sql.indexOf("?") >= 0) {
				setPreparedStatement(ps, args);
			}
			rs = ps.executeQuery();
			String[] colName = getColumnName(rs);
			while(rs.next()) {
				result.add(getRowRecord(rs, colName));
			}
			return result;
		} catch(Exception e) {
			throw new DBAccessException(e);
		} finally {
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(con);

		}
	}

	/**
	 * 根据传入的SQL语句及参数列表操作数据库,支持insert、update、delete等语句
	 * 
	 * @param sql  需要传入到SQL语句
	 * @param args 需要传入到SQL语句中的参数列表,如果没有参数则传入null,
	 * 有参数则需要跟SQL语句中的"?"所对应
	 * @return Integer执行操作语句后影响的记录数
	 */
	public  final int pExecuteUpdate(Connection cnn , String sql, Object[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		
		if (cnn == null)
		{
			con = this.openConnection();
		}else{
			con = cnn;
		}
		
		try {
			ps = con.prepareStatement(sql);
			if(sql.indexOf("?") >= 0) {
				setPreparedStatement(ps, args);
			}
			return ps.executeUpdate();
		} catch(Exception e) {
			throw new DBAccessException(e);
		} finally {
			closeStatement(ps);
			if (cnn == null)
			{
				closeConnection(con);
			}
		}
	}
	

	
	
	public final <T> T queryValue(String sql, Object[] args, T clas ) {
		return queryValue(sql , args , clas , true);
	}

	/**
	 * 
	 * TODO
	 * 
	 * @param <T>
	 * @param sql 查询SQL语句
	 * @param args 语句参数
	 * @param clas 返回值实例
	 * @param isRownumModle 是否使用ROWNUM 作为查询条件
	 * @return T
	 * 
	 * history
	 *
	 */
	
	public final <T> T queryValue(String sql, Object[] args, T clas , Boolean isRownumModle) {
		try {
			
			StringBuffer str = new StringBuffer();
			
			if (isRownumModle)
			{
				str.append("SELECT * FROM (").append(sql).append(") WHERE ROWNUM = 1");
			} else {
				str.append(sql);
			}
			
			Object obj = null;
			try
			{
				obj= getJdbcTemplate().queryForObject(str.toString(), args, Object.class);
			}catch (Exception e )
			{
				if (e instanceof  EmptyResultDataAccessException && ((EmptyResultDataAccessException)e).getActualSize() == 0 ) 
				{
					return null;
				}
				throw e;
			}
			
			try
			{
				if (clas instanceof Number)
				{
					return (T)Number.class.cast(obj);
				}
				return (T) clas.getClass().cast(obj);
			}
			catch(Exception e)
			{
				return null;
			}
			
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
	public  final void pCallProcedure(Connection cnn ,String procedureName, Object[] args) {
		Connection con = null;
		CallableStatement cs = null;
		
		if (cnn == null)
		{
			con = this.openConnection();
		}else{
			con = cnn;
		}
		
		try {
			String procedureCall = getProcedureCall(procedureName, args);
			cs = con.prepareCall(procedureCall);
			if(procedureCall.indexOf("?") >= 0) {
				setPreparedStatement(cs, args);
			}
			cs.execute();
		} catch(Exception e) {
			throw new DBAccessException(e);
		} finally {
			closeStatement(cs);
			if (cnn == null)
			{
				closeConnection(con);
			}
		}
	}

}
