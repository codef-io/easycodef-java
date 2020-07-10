package io.codef.api;

import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodefTokenTest.java
 * </pre>
 * 
 * Desc : EasyCodef 토큰 발급 사용예시
 * @Company : ©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:42:31 PM
 * @Version : 1.0.1
 */
public class EasyCodefTokenTest {

	@Test
	public void usageExample() throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		/**	
		 * #1.쉬운 코드에프 객체 생성
		 */
		EasyCodef codef = new EasyCodef();

		/**	
		 * #2.데모 클라이언트 정보 설정	
		 * - 데모 서비스 가입 후 코드에프 홈페이지에 확인 가능(https://codef.io/#/account/keys)
		 * - 데모 서비스로 상품 조회 요청시 필수 입력 항목
		 */
		codef.setClientInfoForDemo(EasyCodefClientInfo.DEMO_CLIENT_ID, EasyCodefClientInfo.DEMO_CLIENT_SECRET);
		
		/**	
		 * #3.정식 클라이언트 정보 설정
		 * - 정식 서비스 가입 후 코드에프 홈페이지에 확인 가능(https://codef.io/#/account/keys)
		 * - 정식 서비스로 상품 조회 요청시 필수 입력 항목
		 */
		codef.setClientInfo(EasyCodefClientInfo.CLIENT_ID, EasyCodefClientInfo.CLIENT_SECRET);
		
		/**	
		 * #4.RSA암호화를 위한 퍼블릭키 설정
		 * - 데모/정식 서비스 가입 후 코드에프 홈페이지에 확인 가능(https://codef.io/#/account/keys)
		 * - 암호화가 필요한 필드에 사용 - encryptValue(String plainText);
		 */
		codef.setPublicKey(EasyCodefClientInfo.PUBLIC_KEY);
		
		/**	
		 * #5.코드에프 토큰 발급 요청
		 * - 서비스타입(API:정식, DEMO:데모, SANDBOX:샌드박스)
		 */
		String accessToken = codef.requestToken(EasyCodefServiceType.SANDBOX);
		System.out.println(accessToken);
		 
		
		assertNotNull("엑세스 토큰 발급 실패(클라이언트 정보(EasyCodefClientInfo)가 올바르게 설정되었는지 확인 필요)", accessToken);
	}
}
