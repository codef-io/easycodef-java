package io.codef.easycodef;

import java.util.HashMap;
import java.util.List;

/**
 * FileName : EasyCodefResponse.java
 * Comment  : 코드에프 응답 결과 클래스
 * @version : 1.0.1
 * @author  : notfound404
 * @date    : Mar 27, 2020
 */
public class EasyCodefResponse extends HashMap<String, Object>{
	
	private static final long serialVersionUID = -4106296996913677632L;
	
	private HashMap<String,Object> result; 
	private Object data;
	
	/**
	 * Comment  : EasyCodefResponse 생성자
	 * @version : 1.0.1
	 * @tags    : 
	 * @date    : Mar 27, 2020
	 */
	protected EasyCodefResponse() {
		result = new HashMap<String,Object>();
		data = new HashMap<String, Object>();
		
		this.put(EasyCodefConstant.RESULT, result);
		this.put(EasyCodefConstant.DATA, data);
		
		this.setResultMessage(EasyCodefMessageConstant.OK.getCode(), EasyCodefMessageConstant.OK.getMessage(), "");
	}
	
	/**
	 * Comment  : EasyCodefResponse 생성자
	 * @version : 1.0.1
	 * @tags    : 
	 * @date    : Mar 27, 2020
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
	 * Comment  : EasyCodefResponse 생성자
	 * @version : 1.0.1
	 * @tags    : @param message
	 * @date    : Mar 27, 2020
	 */
	protected EasyCodefResponse(EasyCodefMessageConstant message) {
		result = new HashMap<String,Object>();
		data = new HashMap<String, Object>();
		
		this.put(EasyCodefConstant.RESULT, result);
		this.put(EasyCodefConstant.DATA, data);
		
		this.setResultMessage(message.getCode(), message.getMessage(), "");
	}
	
	/**
	 * Comment  : EasyCodefResponse 생성자
	 * @version : 1.0.1
	 * @tags    : @param message
	 * @tags    : @param extraMessage
	 * @date    : Mar 27, 2020
	 */
	protected EasyCodefResponse(EasyCodefMessageConstant message, String extraMessage) {
		result = new HashMap<String,Object>();
		data = new HashMap<String, Object>();
		
		this.put(EasyCodefConstant.RESULT, result);
		this.put(EasyCodefConstant.DATA, data);
		
		this.setResultMessage(message.getCode(), message.getMessage(), extraMessage);
	}

	
	/**
	 * Comment  : 요청 수행 결과 코드 설정 
	 * @version : 1.0.1
	 * @tags    : @param errCode
	 * @tags    : @param errMsg
	 * @tags    : @param extraMsg
	 * @date    : Mar 27, 2020
	 */
	protected void setResultMessage(String errCode, String errMsg, String extraMsg) {
		this.result.put(EasyCodefConstant.CODE, errCode);
		this.result.put(EasyCodefConstant.MESSAGE, errMsg);
		this.result.put(EasyCodefConstant.EXTRA_MESSAGE, extraMsg);
	}
	
	/**
	 * Comment  : 요청 수행 결과 코드 설정 
	 * @version : 1.0.1
	 * @tags    : @param message
	 * @date    : Mar 27, 2020
	 */
	protected void setResultMessage(EasyCodefMessageConstant message) {
		this.result.put(EasyCodefConstant.CODE, message.getCode());
		this.result.put(EasyCodefConstant.MESSAGE, message.getMessage());
		this.result.put(EasyCodefConstant.EXTRA_MESSAGE, message.getExtraMessage());
	}
	
	

	/**
	 * Comment  : Override toString 
	 * @version : 1.0.1
	 * @tags    : @return
	 * @date    : Mar 27, 2020
	 */
	@Override
	public String toString() {
		return "EasyCodefResponse [result=" + result + ", data=" + data + "]";
	}
}
