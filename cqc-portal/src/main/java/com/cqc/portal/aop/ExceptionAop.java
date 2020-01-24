package com.cqc.portal.aop;

import com.cqc.common.api.Result;
import com.cqc.common.enums.BaseErrorMsg;
import com.cqc.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * 统一异常处理
 * @author Dragon
 *
 */
@Slf4j
@ControllerAdvice
public class ExceptionAop {
	
	
	/*
	 * @ExceptionHandler(value = CustomMsgException.class)
	 * 
	 * @ResponseBody public <T> Result<T>
	 * customMsgExceptionHandle(CustomMsgException e) { log.error("自定义异常：{}",
	 * e.getMessage()); return
	 * Result.getMessageInstance(BaseErrorMsg.BUSINESS_ERROR,e.getMessage()); }
	 */
	
	@ExceptionHandler(value = BaseException.class)
	@ResponseBody
	public <T> Result<T> baseExceptionHandle(BaseException e) {
		log.error("自定义异常：{}", e.getMessage());
        return Result.failed(e.getErrorMsg());
	}

	/**
	 * 400 - Bad Request
	 */
	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public  <T> Result<T> handleValidationException(ValidationException e) {
		log.warn("参数验证失败", e);
		return  Result.getInstance(BaseErrorMsg.MISS_REQUEST_PARAM);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public  <T> Result<T> handleAuthenticationException(AuthenticationException e) {
		log.warn("security 验证出错", e);
		Result<T> instance =  Result.failed();
		instance.setMessage(e.getMessage());
		return instance;
	}

	/*@ExceptionHandler(OAuth2Exception.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public <T> Result<T> handleAuthenticationException(OAuth2Exception e) {
		log.warn("OAuth2 验证出错", e);
		Result<T> instance = Result.failed();
		instance.setMessage(e.getMessage());
		return instance;
	}
	*/
	

	/**
	 * 400 - Bad Request
	 */
	//@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public  <T> Result<T> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		log.warn("缺少请求参数", e);
		return  Result.getInstance(BaseErrorMsg.MISS_REQUEST_PARAM);
	}

	/**
	 * 400 - Bad Request
	 */
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public  <T> Result<T> handleBindException(BindException e) {
		log.error("参数绑定失败");
		BindingResult bindingResult = e.getBindingResult();
		FieldError error = bindingResult.getFieldError();
		String defaultMessage = error.getDefaultMessage();
		Result<T> result =  Result.getInstance(BaseErrorMsg.FAILED, null);
		result.setMessage(defaultMessage);
		return result;
	}

	/**
	 * 405 - Method Not Allowed
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public  <T> Result<T> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		log.error("不支持当前请求方法:{}",e);
		return  Result.getInstance(BaseErrorMsg.REQUEST_METHOD_NOT_SUPPORT);
	}
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(value=MethodArgumentNotValidException.class)  
	@ResponseBody
	public <T> Result<T>  methodArgumentNotValidException( MethodArgumentNotValidException exception) {
		log.info("请求参数错误1：{}", exception.getMessage());
		  String msg = "";
		for (FieldError error : exception.getBindingResult().getFieldErrors()) {
			 msg +=error.getDefaultMessage();
		}
		return (Result<T>)  Result.getMessageInstance(BaseErrorMsg.FAILED,msg);
	}

	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(value=ConstraintViolationException.class)  
	@ResponseBody
	public <T> Result<T> constraintViolationException(ConstraintViolationException exception){
		  Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
		  String msg = "";
		  for (ConstraintViolation<?> constraintViolation : violations) {
			  msg +=constraintViolation.getMessage();
			}
		log.info("请求参数错误2：{}", exception.getMessage());
		return (Result<T>)  Result.getMessageInstance(BaseErrorMsg.FAILED, msg);
	}

	@ExceptionHandler(value=AssertionError.class)
	@ResponseBody
	public <T> Result<T> assertionErrorException(AssertionError exception){
		String msg = "";
		msg = exception.getMessage();
		log.info("AssertionError：{}", exception.getMessage());
		return (Result<T>)  Result.getMessageInstance(BaseErrorMsg.FAILED, msg);
	}

	/*@ExceptionHandler(value=IllegalArgumentException.class)
	@ResponseBody
	public <T> Result<T> illegalArgumentException(IllegalArgumentException exception){
		String msg = "";
		msg = exception.getMessage();
		log.info("请求参数错误3：{}", exception.getMessage());
		return (Result<T>)  Result.getMessageInstance(BaseErrorMsg.FAILED, msg);
	}*/

	@ExceptionHandler(value = Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public <T> Result<T> HandleException(Throwable e) {
		log.error("系统异常:", e);
		return  Result.getInstance(BaseErrorMsg.UNKNOW_ERROR);
	}

}
