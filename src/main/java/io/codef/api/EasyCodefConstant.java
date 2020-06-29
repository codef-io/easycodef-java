package io.codef.api;

/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodefConstant.java
 * </pre>
 * 
 * Desc : EasyCodef를 사용하기 위해 필요한 상품 요청 관련 정보 클래스
 * @Company : ©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:36:32 PM
 */
public class EasyCodefConstant {
	
	/**	OAUTH 서버 도메인	*/
	protected static final String OAUTH_DOMAIN = "https://oauth.codef.io";
	
	/**	OAUTH 엑세스 토큰 발급 URL PATH	*/
	protected static final String GET_TOKEN = "/oauth/token";
	
	
	/**	샌드박스 서버 도메인	*/
	protected static final String SANDBOX_DOMAIN = "https://sandbox.codef.io";
	
	/**	샌드박스 엑세스 토큰 발급을 위한 클라이언트 아이디	*/
	protected static final String SANDBOX_CLIENT_ID 	= "ef27cfaa-10c1-4470-adac-60ba476273f9";
	
	/**	샌드박스 엑세스 토큰 발급을 위한 클라이언트 시크릿	*/
	protected static final String SANDBOX_CLIENT_SECRET 	= "83160c33-9045-4915-86d8-809473cdf5c3";
	
	
	/**	데모 서버 도메인	*/
	protected static final String DEMO_DOMAIN = "https://development.codef.io";
	
	/**	정식 서버 도메인	*/
	protected static final String API_DOMAIN = "https://api.codef.io";
	
	
	/** 응답부 수행 결과 키워드	*/
	protected static final String RESULT = "result";
	
	/** 응답부 수행 결과 메시지 코드 키워드	*/
	protected static final String CODE = "code";

	/** 응답부 수행 결과 메시지 키워드	*/
	protected static final String MESSAGE = "message";
	
	/** 응답부 수행 결과 추가 메시지 키워드	*/
	protected static final String EXTRA_MESSAGE = "extraMessage";
	
	/**	응답부 수행 결과 데이터 키워드	*/
	protected static final String DATA = "data";
	
	/** 계정 목록  키워드	*/
	protected static final String ACCOUNT_LIST = "accountList";
	
	protected static final String CONNECTED_ID = "connectedId";
	
	
	/**	엑세스 토큰 거절 사유1	*/
	protected static String INVALID_TOKEN = "invalid_token";
	
	/**	엑세스 토큰 거절 사유2	*/
	protected static String ACCESS_DENIED = "access_denied";
	
	/**	계정 등록 URL	*/
	protected static final String CREATE_ACCOUNT = "/v1/account/create";
	
	/**	계정 추가 URL	*/
	protected static final String ADD_ACCOUNT = "/v1/account/add";
	
	/**	계정 수정 URL	*/
	protected static final String UPDATE_ACCOUNT = "/v1/account/update";
	
	/**	계정 삭제 URL	*/
	protected static final String DELETE_ACCOUNT = "/v1/account/delete";
	
	/**	계정 목록 조회 URL	*/
	protected static final String GET_ACCOUNT_LIST = "/v1/account/list";
	
	/**	커넥티드 아이디 목록 조회 URL	*/
	protected static final String GET_CID_LIST = "/v1/account/connectedId-list"; 
	
}
