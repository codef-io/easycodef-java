package io.codef.easycodef;

import java.util.HashMap;

/**
 * FileName : EasyCodefTokenMap.java
 * Comment  : 쉬운 코드에프 이용을 위한 토큰 관리 클래스
 * @version : 1.0.1
 * @author  : notfound404
 * @date    : May 21, 2020
 */
public class EasyCodefTokenMap {
	
	/**	쉬운 코드에프 이용을 위한 토큰 저장 맵	*/
	private static HashMap<String, String> ACCESS_TOKEN_MAP = new HashMap<String, String>();
	
	/**
	 * Comment  : 토큰 저장 
	 * @version : 1.0.1
	 * @tags    : @param clientId
	 * @tags    : @param accessToken
	 * @date    : May 21, 2020
	 */
	public static void setToken(String clientId, String accessToken) {
		ACCESS_TOKEN_MAP.put(clientId, accessToken);
	}
	
	/**
	 * Comment  : 토큰 반환
	 * @version : 1.0.1
	 * @tags    : @param clientId
	 * @tags    : @return
	 * @date    : May 21, 2020
	 */
	public static String getToken(String clientId) {
		return ACCESS_TOKEN_MAP.get(clientId);
	}
}
