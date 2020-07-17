package io.codef.api;

import java.util.HashMap;
import java.util.Iterator;
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
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	protected EasyCodefResponse(HashMap<String, Object> map) {
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			if(EasyCodefConstant.RESULT.equals(key)) {	// 결과 코드 정보
				result = (HashMap<String, Object>) map.get(EasyCodefConstant.RESULT);
				this.put(EasyCodefConstant.RESULT, result);
			} else if(EasyCodefConstant.RESULT.equals(key)) { //결과 데이터 정보
				try {
					data = (HashMap<String, Object>) map.get(EasyCodefConstant.DATA);
				} catch (ClassCastException e) {
					data = (List<HashMap<String, Object>>) map.get(EasyCodefConstant.DATA);
				}
				this.put(EasyCodefConstant.DATA, data);
			}else {
				this.put(key, map.get(key));	// 사용자 정의 파라미터가 존재하는 경우 응답부에 추가 설정
			}
		}
		
		
	}
	
	/**
	 * Desc : EasyCodefResponse 생성자
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:40:00 PM
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
	 * @return
	 */
	@Override
	public String toString() {
		return "EasyCodefResponse [result=" + result + ", data=" + data + "]";
	}
}
