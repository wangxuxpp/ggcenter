package atomic.exception;

/**
 * 数据库访问异常类
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
@SuppressWarnings("serial")
public class DBAccessException extends RuntimeException {

	public DBAccessException() {
    	super();
    }
       
    public DBAccessException(String message) {
    	super(message);
    }
    
    public DBAccessException(String message, Throwable cause) {
        super(message, cause);
    }
        
    public DBAccessException(Throwable cause) {
        super(cause);
    }
    
}
