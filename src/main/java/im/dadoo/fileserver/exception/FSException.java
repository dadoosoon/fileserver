package im.dadoo.fileserver.exception;

import im.dadoo.fileserver.dto.DTException;

public class FSException extends RuntimeException {

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */
	private static final long serialVersionUID = -425857491062725880L;
	
	private String url;
	private String method;
	private Integer status;
	private Integer code;
	
	public FSException(Integer status, Integer code, String url, String method, String message) {
		super(message);
		this.status = status;
		this.code = code;
		this.url = url;
		this.method = method;
	}

	public FSException(Integer status, Integer code, String message) {
		super(message);
		this.status = status;
		this.code = code;
		this.url = null;
		this.method = null;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public String getMethod() {
		return method;
	}

	public Integer getStatus() {
		return status;
	}
	
	public Integer getCode() {
		return code;
	}

	public DTException toDTO() {
		return new DTException(this.status, this.code, this.url, this.method, this.getMessage());
	}
}
