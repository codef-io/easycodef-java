package io.codef.api.account;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.codef.api.EasyCodef;
import io.codef.api.EasyCodefClientInfo;
import io.codef.api.EasyCodefServiceType;
import io.codef.api.EasyCodefUtil;

/**
 * FileName : EasyCodefTest.java
 * Comment  : 쉬운 코드에프 라이브러리 사용 예제 - 사용자 계정 관리
 * @version : 1.0.1
 * @author  : notfound404
 * @date    : Mar 30, 2020
 */
public class UpdateAccountTest {
	
	/**
	 * Comment  : 쉬운 코드에프 계정 수정 샘플
	 * @version : 1.0.1
	 * @throws InterruptedException 
	 * @tags    : @throws IOException
	 * @date    : Mar 31, 2020
	 */
	@Test
	public void updateAccount() throws IOException, InterruptedException {
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
		 * #5.요청 파라미터 설정
		 * - 계정관리 파라미터를 설정(https://developer.codef.io/cert/account/cid-overview)	
		 */
		List<HashMap<String, Object>>  accountList = new ArrayList<HashMap<String, Object>> ();
		
		HashMap<String, Object> accountMap = new HashMap<String, Object>();
		accountMap.put("countryCode",	"KR");
		accountMap.put("businessType",	"CD");
		accountMap.put("clientType",  	"P");
		accountMap.put("organization",	"0309");
		accountMap.put("loginType",  	"1");
		accountMap.put("id",  			"user_id");
		
		try {
			accountMap.put("password",  	EasyCodefUtil.encryptRSA("user_password", codef.getPublicKey())); // RSA암호화가 필요한 필드는 encryptRSA(String plainText, String publicKey) 메서드를 이용해 암호화
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		accountList.add(accountMap);
		
		HashMap<String, Object> parameterMap = new HashMap<String, Object> ();
		parameterMap.put("accountList", accountList);
		parameterMap.put("connectedId", "sandbox_connectedId_01");
		
		/**	#6.요청	*/
		String result = codef.updateAccount(EasyCodefServiceType.SANDBOX, parameterMap);
		
		/**	#7.결과 확인	*/
		System.out.println(result);

		HashMap<String, Object> responseMap = new ObjectMapper().readValue(result, HashMap.class);
		HashMap<String, Object> resultMap = (HashMap<String, Object>)responseMap.get("result");
		
		assertEquals("코드에프 상품 요청 결과 실패(반환된 코드와 메시지 확인 필요)", "CF-00000", (String)resultMap.get("code"));
	}
}
