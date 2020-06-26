package io.codef.easycodef;

/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodefClientInfo.java
 * </pre>
 * 
 * Desc : EasyCodef 발급 클라이언트 정보 설정 클래스
 * @Company : ©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:42:11 PM
 * @Version : 1.0.1
 */
public class EasyCodefClientInfo {

	/**	
	 * TODO :	사용자는 코드에프 가입을 통해 발급 받은 클라이언트 정보와 RSA 공개키 정보를 설정해야 함.
	 * 			설정하지 않은 상태에서는 SANDBOX 테스트만 사용 가능.
	 */
	public static final String DEMO_CLIENT_ID = "코드에프 데모 서비스 신청 후 발급 받은 데모버전 클라이언트 아이디 설정";	
	public static final String DEMO_CLIENT_SECRET = "코드에프 데모 서비스 신청 후 발급 받은 데모버전 클라이언트 시크릿 설정";
	
	public static final String CLIENT_ID = "코드에프 정식 서비스 신청 후 발급 받은 정식버전 클라이언트 아이디 설정";
	public static final String CLIENT_SECRET = "코드에프 정식 서비스 신청 후 발급 받은 정식버전 클라이언트 아이디 설정";
	
	public static final String PUBLIC_KEY = "코드에프 가입을 통해 발급 받은 RSA 공개키 정보 설정";
	
}
