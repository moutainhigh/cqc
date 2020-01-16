package com.cqc.common.exception;

import com.cqc.common.api.IErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 *基础异常类
 * @author dragon
 *
 */
@Getter
@Setter
public class BaseException extends RuntimeException{
	
private static final long serialVersionUID = 8601220581897145467L;

    IErrorCode<?> errorMsg;

	public BaseException(IErrorCode<?> errorMsg) {
		super(errorMsg.getMessage());
		this.errorMsg=errorMsg;
	}

    public BaseException(String code, String msg) {
	    super((msg));
        this.errorMsg = new IErrorCode() {
            @Override
            public String getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return msg;
            }
        };
    }
}
