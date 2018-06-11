package atomic.exception;

/**
 * 系统异常类
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
public class SystemException extends RuntimeException {

	public SystemException() {
		super();
	}
	
	public SystemException(String message) {
		super(message);
	}

	public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
        
    public SystemException(Throwable cause) {
        super(cause);
    }

}
