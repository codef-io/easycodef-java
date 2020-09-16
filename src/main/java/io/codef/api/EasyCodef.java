package io.codef.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodef.java
 * </pre>
 * 
 * Desc : 코드에프의 쉬운 사용을 위한 유틸 라이브러리 클래스 
 * @Company : ©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:28:31 PM
 */
public class EasyCodef {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * EasyCodef 사용을 위한 변수 설정 오브젝트
	 */
	private EasyCodefProperties properties = new EasyCodefProperties();

	/**
	 * Desc : 정식서버 사용을 위한 클라이언트 정보 설정
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:30:59 PM
	 * @param clientId
	 * @param clientSecret
	 */
	public void setClientInfo(String clientId, String clientSecret) {
		properties.setClientInfo(clientId, clientSecret);
	}
	
	/**
	 * Desc : 데모서버 사용을 위한 클라이언트 정보 설정
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:31:12 PM
	 * @param demoClientId
	 * @param demoClientSecret
	 */
	public void setClientInfoForDemo(String demoClientId, String demoClientSecret) {
		properties.setClientInfoForDemo(demoClientId, demoClientSecret);
	}
	
	/**
	 * Desc : RSA암호화를 위한 퍼블릭키 설정
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:31:24 PM
	 * @param publicKey
	 */
	public void setPublicKey(String publicKey) {
		properties.setPublicKey(publicKey);
	}
	
	/**
	 * Desc : RSA암호화를 위한 퍼블릭키 반환
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:32:25 PM
	 * @return
	 */
	public String getPublicKey() {
		return properties.getPublicKey();
	}
	
	/**
	 * Desc : 상품 요청 
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:32:31 PM
	 * @param productUrl
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String requestProduct(String productUrl, EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		boolean validationFlag = true;
		
		/**	#1.필수 항목 체크 - 클라이언트 정보	*/
		validationFlag = checkClientInfo(serviceType.getServiceType());
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.EMPTY_CLIENT_INFO);
			return mapper.writeValueAsString(response);
		}
		
		/**	#2.필수 항목 체크 - 퍼블릭 키	*/
		validationFlag = checkPublicKey();
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.EMPTY_PUBLIC_KEY);
			return mapper.writeValueAsString(response);
		}
		
		/**	#3.추가인증 키워드 체크	*/
		validationFlag = checkTwoWayKeyword(parameterMap);
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.INVALID_2WAY_KEYWORD);
			return mapper.writeValueAsString(response);
		}
		
		/**	#4.상품 조회 요청	*/
		EasyCodefResponse response = EasyCodefConnector.execute(productUrl, serviceType.getServiceType(), parameterMap, properties);
		
		/**	#5.결과 반환	*/
		return mapper.writeValueAsString(response);
	}
	
	/**
	 * Desc : 상품 추가인증 요청
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:32:41 PM
	 * @param productUrl
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String requestCertification(String productUrl, EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		boolean validationFlag = true;
		
		/**	#1.필수 항목 체크 - 클라이언트 정보	*/
		validationFlag = checkClientInfo(serviceType.getServiceType());
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.EMPTY_CLIENT_INFO);
			return mapper.writeValueAsString(response);
		}
		
		/**	#2.필수 항목 체크 - 퍼블릭 키	*/
		validationFlag = checkPublicKey();
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.EMPTY_PUBLIC_KEY);
			return mapper.writeValueAsString(response);
		}
		
		/**	#3.추가인증 파라미터 필수 입력 체크	*/
		validationFlag = checkTwoWayInfo(parameterMap);
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.INVALID_2WAY_INFO);
			return mapper.writeValueAsString(response);
		}
		
		/**	#4.상품 조회 요청	*/
		EasyCodefResponse response = EasyCodefConnector.execute(productUrl, serviceType.getServiceType(), parameterMap, properties);
		
		/**	#5.결과 반환	*/
		return mapper.writeValueAsString(response);
	}
	
	
	/**
	 * Desc : 서비스 타입에 따른 클라이언트 정보 설정 확인
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:33:23 PM
	 * @param serviceType
	 * @return
	 */
	private boolean checkClientInfo(int serviceType) {
		if(serviceType == 0) {
			if(properties.getClientId() == null || "".equals(properties.getClientId().trim())) {
				return false;
			}
			if(properties.getClientSecret() == null || "".equals(properties.getClientSecret().trim())) {
				return false;
			}
		} else if(serviceType == 1) {
			if(properties.getDemoClientId() == null || "".equals(properties.getDemoClientId().trim())) {
				return false;
			}
			if(properties.getDemoClientSecret() == null || "".equals(properties.getDemoClientSecret().trim())) {
				return false;
			}
		} else {
			if(EasyCodefConstant.SANDBOX_CLIENT_ID == null || "".equals(EasyCodefConstant.SANDBOX_CLIENT_ID.trim())) {
				return false;
			}
			if(EasyCodefConstant.SANDBOX_CLIENT_SECRET == null || "".equals(EasyCodefConstant.SANDBOX_CLIENT_SECRET.trim())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Desc : 퍼블릭키 정보 설정 확인
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:33:31 PM
	 * @return
	 */
	private boolean checkPublicKey() {
		if(properties.getPublicKey() == null || "".equals(properties.getPublicKey().trim())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Desc : 추가인증 파라미터 설정 확인
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:33:39 PM
	 * @param parameterMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean checkTwoWayInfo(HashMap<String, Object> parameterMap) {
		if(!parameterMap.containsKey("is2Way") || !(parameterMap.get("is2Way") instanceof Boolean) || !(boolean)parameterMap.get("is2Way")){
			return false;
		}
		
		if(!parameterMap.containsKey("twoWayInfo")) {
			return false;
		}
		
		HashMap<String, Object> twoWayInfoMap = (HashMap<String, Object>)parameterMap.get("twoWayInfo");
		if(!twoWayInfoMap.containsKey("jobIndex") || !twoWayInfoMap.containsKey("threadIndex") || !twoWayInfoMap.containsKey("jti") || !twoWayInfoMap.containsKey("twoWayTimestamp")){
			return false;
		}
		
		return true;
	}
	
	/**
	 * Desc : 추가인증 키워드 확인
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:33:45 PM
	 * @param parameterMap
	 * @return
	 */
	private boolean checkTwoWayKeyword(HashMap<String, Object> parameterMap) {
		if(parameterMap != null && (parameterMap.containsKey("is2Way") || parameterMap.containsKey("twoWayInfo"))) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Desc : connectedId 발급을 위한 계정 등록
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:02 PM
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String createAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.CREATE_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Desc : 계정 정보 추가
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:11 PM
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String addAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.ADD_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Desc : 계정 정보 수정 
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:21 PM
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String updateAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.UPDATE_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Desc : 계정 정보 삭제
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:30 PM
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String deleteAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.DELETE_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Desc : connectedId로 등록된 계정 목록 조회
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:37 PM
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String getAccountList(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.GET_ACCOUNT_LIST, serviceType, parameterMap);
	}
	
	/**
	 * Desc : 클라이언트 정보로 등록된 모든 connectedId 목록 조회
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:44 PM
	 * @param serviceType
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String getConnectedIdList(EasyCodefServiceType serviceType) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.GET_CID_LIST, serviceType, null);
	}
	
	/**
	 * Desc : 토큰 반환 요청 - 보유 중인 유효한 토큰이 있는 경우 반환, 없는 경우 신규 발급 후 반환
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:35:03 PM
	 * @param serviceType
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public String requestToken(EasyCodefServiceType serviceType) throws JsonParseException, JsonMappingException, IOException {
		String clientId = null;
		String clientSecret = null;
		
		if(serviceType.getServiceType() == 0) {
			clientId = properties.getClientId();
			clientSecret = properties.getClientSecret();
		} else if(serviceType.getServiceType() == 1) {
			clientId = properties.getDemoClientId();
			clientSecret = properties.getDemoClientSecret();
		} else {
			clientId = EasyCodefConstant.SANDBOX_CLIENT_ID;
			clientSecret = EasyCodefConstant.SANDBOX_CLIENT_SECRET;
		}
		
		String accessToken = EasyCodefTokenMap.getToken(clientId); // 보유 중인 토큰이 있는 경우 반환
		if(accessToken != null) {
			HashMap<String, Object> tokenMap = EasyCodefUtil.getTokenMap(accessToken);
			if(EasyCodefUtil.checkValidity((int)(tokenMap.get("exp")))) {	// 토큰의 유효 기간 확인
				return accessToken;	// 정상 토큰인 경우 반환
			}
		}
		
		HashMap<String, Object> tokenMap = EasyCodefConnector.publishToken(clientId, clientSecret);	// 보유 중인 토큰이 없거나 신규 발급 조건에 해당하는 경우 발급 후 반환(만료일시를 지났거나 한시간 이내로 도래한 경우 신규 발급)
		if(tokenMap != null) {
			accessToken = (String)tokenMap.get("access_token");
			EasyCodefTokenMap.setToken(clientId, accessToken);	// 발급 토큰 저장
			return accessToken;
		} else {
			return null;
		}
	}
	
	/**
	 * Desc : 토큰 신규 발급 후 반환(코드에프 이용 중 추가 업무 사용을 하는 등 토큰 권한 변경이 필요하거나 신규 토큰이 필요한 경우시 사용)
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Sep 16, 2020 11:58:32 AM
	 * @param serviceType
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String requestNewToken(EasyCodefServiceType serviceType) throws JsonParseException, JsonMappingException, IOException {
		String clientId = null;
		String clientSecret = null;
		
		if(serviceType.getServiceType() == 0) {
			clientId = properties.getClientId();
			clientSecret = properties.getClientSecret();
		} else if(serviceType.getServiceType() == 1) {
			clientId = properties.getDemoClientId();
			clientSecret = properties.getDemoClientSecret();
		} else {
			clientId = EasyCodefConstant.SANDBOX_CLIENT_ID;
			clientSecret = EasyCodefConstant.SANDBOX_CLIENT_SECRET;
		}
		
		String accessToken = null;
		HashMap<String, Object> tokenMap = EasyCodefConnector.publishToken(clientId, clientSecret);	// 토큰 신규 발급
		if(tokenMap != null) {
			accessToken = (String)tokenMap.get("access_token");
			EasyCodefTokenMap.setToken(clientId, accessToken);	// 발급 토큰 저장
			return accessToken;
		} else {
			return null;
		}
	}
}
