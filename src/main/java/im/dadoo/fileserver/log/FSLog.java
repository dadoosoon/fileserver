package im.dadoo.fileserver.log;

import java.util.Map;

public class FSLog {

	public static final String SERVICE_NAME = "fileserver";
	
	public static final String TYPE_FUN = "function";
	
	public static final String TYPE_SIGN = "save";
	
	private String serviceName;
	private String type;
	private Long createDatetime;
	private Map<String, Object> content;
	
	public FSLog(Map<String, Object> content, String type) {
		this.serviceName = SERVICE_NAME;
		this.type = type;
		this.content = content;
		this.createDatetime = System.currentTimeMillis();
	}
	
	public FSLog(Map<String, Object> content, String type, Long createDatetime) {
		this.serviceName = SERVICE_NAME;
		this.type = type;
		this.content = content;
		this.createDatetime = createDatetime;
	}
	
	
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Long getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Long createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Map<String, Object> getContent() {
		return content;
	}

	public void setContent(Map<String, Object> content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
