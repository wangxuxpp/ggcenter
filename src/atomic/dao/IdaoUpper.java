package atomic.dao;

import java.util.List;

public interface IdaoUpper {
	
	@SuppressWarnings("rawtypes")
	List executeQuery(String sql, Object[] args);
}
