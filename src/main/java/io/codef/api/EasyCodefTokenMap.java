package io.codef.api;

import java.util.HashMap;

/**
 * 쉬운 코드에프 이용을 위한 토큰 관리 클래스
 */
public class EasyCodefTokenMap {

    /** 쉬운 코드에프 이용을 위한 토큰 저장 맵 */
    private static final HashMap<String, String> ACCESS_TOKEN_MAP = new HashMap<String, String>();

    /**
     * 토큰 저장
     */
    public static void setToken(String clientId, String accessToken) {
        ACCESS_TOKEN_MAP.put(clientId, accessToken);
    }

    /**
     * 토큰 반환
     */
    public static String getToken(String clientId) {
        return ACCESS_TOKEN_MAP.get(clientId);
    }
}
