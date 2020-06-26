package io.codef.easycodef;

import java.util.HashMap;
import java.util.List;

/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodefResponse.java
 * </pre>
 * 
 * Desc : 코드에프 응답 결과 클래스
 * @Company : ©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:38:30 PM
 * @Version : 1.0.1
 */
public class EasyCodefResponse extends HashMap<String, Object>{
	
	private static final long serialVersionUID = -4106296996913677632L;
	
	private HashMap<String,Object> result; 
	private Object data;
	
	/**
	 * Desc : EasyCodefResponse 생성자
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:38:37 PM
	 * @Version : 1.0.1
	 */
	protected EasyCodefResponse() {
		result = new HashMap<String,Object>();
		data = new HashMap<String, Object>();
		
		this.put(EasyCodefConstant.RESULT, result);
		this.put(EasyCodefConstant.DATA, data);
		
		this.setResultMessage(EasyCodefMessageConstant.OK.getCode(), EasyCodefMessageConstant.OK.getMessage(), "");
	}
	
	/**
	 * Desc : EasyCodefResponse 생성자
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:39:55 PM
	 * @Version : 1.0.1
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	protected EasyCodefResponse(HashMap<String, Object> map) {
		result = (HashMap<String, Object>) map.get(EasyCodefConstant.RESULT);
		try {
			data = (HashMap<String, Object>) map.get(EasyCodefConstant.DATA);
		} catch (ClassCastException e) {
			data = (List<HashMap<String, Object>>) map.get(EasyCodefConstant.DATA);
		}
		
		this.put(EasyCodefConstant.RESULT, result);
		this.put(EasyCodefConstant.DATA, data);
	}
	
	/**
	 * Desc : EasyCodefResponse 생성자
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:40:00 PM
	 * @Version : 1.0.1
	 * @param message
	 */
	protected EasyCodefResponse(EasyCodefMessageConstant message) {
		result = new HashMap<String,Object>();
		data = new HashMap<String, Object>();
		
		this.put(EasyCodefConstant.RESULT, result);
		this.put(EasyCodefConstant.DATA, data);
		
		this.setResultMessage(message.getCode(), message.getMessage(), "");
	}
	
	/**
	 * Desc : EasyCodefResponse 생성자
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:40:06 PM
	 * @Version : 1.0.1
	 * @param message
	 * @param extraMessage
	 */
	protected EasyCodefResponse(EasyCodefMessageConstant message, String extraMessage) {
		result = new HashMap<String,Object>();
		data = new HashMap<String, Object>();
		
		this.put(EasyCodefConstant.RESULT, result);
		this.put(EasyCodefConstant.DATA, data);
		
		this.setResultMessage(message.getCode(), message.getMessage(), extraMessage);
	}

	
	/**
	 * Desc : 요청 수행 결과 코드 설정 
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:40:12 PM
	 * @Version : 1.0.1
	 * @param errCode
	 * @param errMsg
	 * @param extraMsg
	 */
	protected void setResultMessage(String errCode, String errMsg, String extraMsg) {
		this.result.put(EasyCodefConstant.CODE, errCode);
		this.result.put(EasyCodefConstant.MESSAGE, errMsg);
		this.result.put(EasyCodefConstant.EXTRA_MESSAGE, extraMsg);
	}
	
	/**
	 * Desc : 요청 수행 결과 코드 설정 
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:40:18 PM
	 * @Version : 1.0.1
	 * @param message
	 */
	protected void setResultMessage(EasyCodefMessageConstant message) {
		this.result.put(EasyCodefConstant.CODE, message.getCode());
		this.result.put(EasyCodefConstant.MESSAGE, message.getMessage());
		this.result.put(EasyCodefConstant.EXTRA_MESSAGE, message.getExtraMessage());
	}
	
	

	/**
	 * Desc : Override toString 
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:40:26 PM
	 * @Version : 1.0.1
	 * @return
	 */
	@Override
	public String toString() {
		return "EasyCodefResponse [result=" + result + ", data=" + data + "]";
	}
}
