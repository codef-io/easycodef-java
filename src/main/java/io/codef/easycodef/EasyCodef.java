package io.codef.easycodef;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * FileName : EasyCodef.java
 * Comment  : 코드에프의 쉬운 사용을 위한 유틸 라이브러리 클래스 
 * @version : 1.0.1
 * @author  : notfound404
 * @date    : Mar 27, 2020
 */
public class EasyCodef {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	/**	EasyCodef 사용을 위한 변수 설정 오브젝트	*/
	private EasyCodefProperties properties = new EasyCodefProperties();

	/**
	 * Comment  : 정식서버 사용을 위한 클라이언트 정보 설정
	 * @version : 1.0.1
	 * @tags    : @param realClientId
	 * @tags    : @param realClientSecret
	 * @date    : Mar 27, 2020
	 */
	public void setClientInfo(String clientId, String clientSecret) {
		properties.setClientInfo(clientId, clientSecret);
	}
	
	/**
	 * Comment  : 데모서버 사용을 위한 클라이언트 정보 설정
	 * @version : 1.0.1
	 * @tags    : @param demoClientId
	 * @tags    : @param demoClientSecret
	 * @date    : Mar 27, 2020
	 */
	public void setClientInfoForDemo(String demoClientId, String demoClientSecret) {
		properties.setClientInfoForDemo(demoClientId, demoClientSecret);
	}
	
	/**
	 * Comment  : RSA암호화를 위한 퍼블릭키 설정 
	 * @version : 1.0.1
	 * @tags    : @param publicKey
	 * @date    : Mar 27, 2020
	 */
	public void setPublicKey(String publicKey) {
		properties.setPublicKey(publicKey);
	}
	
	/**
	 * Comment  : RSA암호화를 위한 퍼블릭키 반환
	 * @version : 1.0.1
	 * @tags    : @return
	 * @date    : May 21, 2020
	 */
	public String getPublicKey() {
		return properties.getPublicKey();
	}
	
	/**
	 * Comment  : 상품 요청 
	 * @version : 1.0.1
	 * @throws JsonProcessingException 
	 * @throws UnsupportedEncodingException 
	 * @throws InterruptedException 
	 * @tags    : @param serviceType
	 * @tags    : @return
	 * @date    : Mar 27, 2020
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
	 * Comment  : 상품 추가인증 요청 
	 * @version : 1.0.1
	 * @tags    : @param productUrl
	 * @tags    : @param serviceType
	 * @tags    : @param parameterMap
	 * @tags    : @return
	 * @tags    : @throws UnsupportedEncodingException
	 * @tags    : @throws JsonProcessingException
	 * @tags    : @throws InterruptedException
	 * @date    : Apr 7, 2020
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
	 * Comment  : 서비스 타입에 따른 클라이언트 정보 설정 확인
	 * @version : 1.0.1
	 * @param serviceType 
	 * @tags    : @return
	 * @date    : Mar 27, 2020
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
	 * Comment  : 퍼블릭키 정보 설정 확인
	 * @version : 1.0.1
	 * @tags    : @return
	 * @date    : Mar 27, 2020
	 */
	private boolean checkPublicKey() {
		if(properties.getPublicKey() == null || "".equals(properties.getPublicKey().trim())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Comment  : 추가인증 파라미터 설정 확인
	 * @version : 1.0.1
	 * @tags    : @return
	 * @date    : Apr 7, 2020
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
	 * Comment  : 추가인증 키워드 확인
	 * @version : 1.0.1
	 * @tags    : @param parameterMap
	 * @tags    : @return
	 * @date    : Apr 9, 2020
	 */
	private boolean checkTwoWayKeyword(HashMap<String, Object> parameterMap) {
		if(parameterMap.containsKey("is2Way") || parameterMap.containsKey("twoWayInfo")) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Comment  : connectedId 발급을 위한 계정 등록
	 * @version : 1.0
	 * @throws JsonProcessingException 
	 * @throws UnsupportedEncodingException 
	 * @throws InterruptedException 
	 * @tags    : @param accountList
	 * @tags    : @return
	 * @date    : Mar 27, 2020
	 */
	public String createAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.CREATE_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Comment  : 계정 정보 추가
	 * @version : 1.0
	 * @throws InterruptedException 
	 * @tags    : @param accountList
	 * @tags    : @return
	 * @date    : Mar 27, 2020
	 */
	public String addAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.ADD_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Comment  : 계정 정보 수정
	 * @version : 1.0
	 * @throws InterruptedException 
	 * @tags    : @param accountList
	 * @tags    : @return
	 * @date    : Mar 27, 2020
	 */
	public String updateAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.UPDATE_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Comment  : 계정 정보 삭제
	 * @version : 1.0
	 * @throws InterruptedException 
	 * @tags    : @param accountList
	 * @tags    : @return
	 * @date    : Mar 27, 2020
	 */
	public String deleteAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.DELETE_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Comment  : connectedId로 등록된 계정 목록 조회
	 * @version : 1.0
	 * @throws InterruptedException 
	 * @tags    : @param accountList
	 * @tags    : @return
	 * @date    : Mar 27, 2020
	 */
	public String getAccountList(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.GET_ACCOUNT_LIST, serviceType, parameterMap);
	}
	
	/**
	 * Comment  : 클라이언트 정보로 등록된 모든 connectedId 목록 조회
	 * @version : 1.0
	 * @throws InterruptedException 
	 * @tags    : @param accountList
	 * @tags    : @return
	 * @date    : Mar 27, 2020
	 */
	public String getConnectedIdList(EasyCodefServiceType serviceType) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.GET_CID_LIST, serviceType, null);
	}
	
	/**
	 * Comment  : 토큰 발급 테스트 
	 * @version : 1.0.1
	 * @tags    : @param serviceType
	 * @tags    : @return
	 * @date    : Apr 9, 2020
	 */
	public HashMap<String, Object> requestToken(EasyCodefServiceType serviceType){
		if(serviceType.getServiceType() == 0) {
			return EasyCodefConnector.requestToken(properties.getClientId(), properties.getClientSecret());
		} else if(serviceType.getServiceType() == 1) {
			return EasyCodefConnector.requestToken(properties.getDemoClientId(), properties.getDemoClientSecret());
		} else {
			return EasyCodefConnector.requestToken(EasyCodefConstant.SANDBOX_CLIENT_ID, EasyCodefConstant.SANDBOX_CLIENT_SECRET);
		}
	}
}
