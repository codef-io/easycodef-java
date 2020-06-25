package io.codef.easycodef;

/**
 * FileName : EasyCodefProperties.java
 * Comment  : 코드에프의 쉬운 사용을 위한 프로퍼티 클래스 
 * @version : 1.0.1
 * @author  : notfound404
 * @date    : May 21, 2020
 */
public class EasyCodefProperties {
	
	//	데모 엑세스 토큰 발급을 위한 클라이언트 아이디
	private String demoClientId 	= "";
	
	//	데모 엑세스 토큰 발급을 위한 클라이언트 시크릿
	private String demoClientSecret 	= "";	
	
	//	OAUTH2.0 데모 토큰
	private String demoAccessToken = "";
	
	//	정식 엑세스 토큰 발급을 위한 클라이언트 아이디
	private String clientId 	= "";
	
	//	정식 엑세스 토큰 발급을 위한 클라이언트 시크릿
	private String clientSecret 	= "";	
	
	//	OAUTH2.0 토큰
	private String accessToken = "";
	
	//	RSA암호화를 위한 퍼블릭키
	private String publicKey 	= "";

	
	/**
	 * Comment  : 정식서버 사용을 위한 클라이언트 정보 설정
	 * @version : 1.0.1
	 * @tags    : @param realClientId
	 * @tags    : @param realClientSecret
	 * @date    : Mar 27, 2020
	 */
	public void setClientInfo(String clientId, String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}
	
	/**
	 * Comment  : 데모서버 사용을 위한 클라이언트 정보 설정
	 * @version : 1.0.1
	 * @tags    : @param demoClientId
	 * @tags    : @param demoClientSecret
	 * @date    : Mar 27, 2020
	 */
	public void setClientInfoForDemo(String demoClientId, String demoClientSecret) {
		this.demoClientId = demoClientId;
		this.demoClientSecret = demoClientSecret;
	}
	
	/**
	 * Comment  : 데모 클라이언트 아이디 반환
	 * @version : 1.0.1
	 * @tags    : @return
	 * @date    : May 21, 2020
	 */
	public String getDemoClientId() {
		return demoClientId;
	}

	/**
	 * Comment  : 데모 클라이언트 시크릿 반환
	 * @version : 1.0.1
	 * @tags    : @return
	 * @date    : May 21, 2020
	 */
	public String getDemoClientSecret() {
		return demoClientSecret;
	}

	/**
	 * Comment  : 데모 접속 토큰 반환
	 * @version : 1.0.1
	 * @tags    : @return
	 * @date    : May 21, 2020
	 */
	public String getDemoAccessToken() {
		return demoAccessToken;
	}

	/**
	 * Comment  : 데모 클라이언트 시크릿 반환
	 * @version : 1.0.1
	 * @tags    : @return
	 * @date    : May 21, 2020
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * Comment  : API 클라이언트 시크릿 반환
	 * @version : 1.0.1
	 * @tags    : @return
	 * @date    : May 21, 2020
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * Comment  : API 접속 토큰 반환
	 * @version : 1.0.1
	 * @tags    : @return
	 * @date    : May 21, 2020
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Comment  : RSA암호화를 위한 퍼블릭키 반환
	 * @version : 1.0.1
	 * @tags    : @return
	 * @date    : May 21, 2020
	 */
	public String getPublicKey() {
		return publicKey;
	}

	/**
	 * Comment  : RSA암호화를 위한 퍼블릭키 설정 
	 * @version : 1.0.1
	 * @tags    : @param publicKey
	 * @date    : Mar 27, 2020
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * Comment  : 데모 접속 토큰 설정
	 * @version : 1.0.1
	 * @tags    : @return
	 * @date    : May 21, 2020
	 */
	public void setDemoAccessToken(String demoAccessToken) {
		this.demoAccessToken = demoAccessToken;
	}

	/**
	 * Comment  : API 접속 토큰 설정
	 * @version : 1.0.1
	 * @tags    : @return
	 * @date    : May 21, 2020
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}
