package io.codef.api;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * EasyCodef 토큰 재사용 테스트
 */
public class EasyCodefTokenReuseTest {

    @Test
    public void reuse() throws InterruptedException, IOException {

        String accessToken1 = usageExample(EasyCodefServiceType.SANDBOX);
        String accessToken2 = usageExample(EasyCodefServiceType.SANDBOX);
        assertEquals("토큰 재사용 실패", accessToken1, accessToken2);

        accessToken1 = usageExample(EasyCodefServiceType.DEMO);
        accessToken2 = usageExample(EasyCodefServiceType.DEMO);
        assertEquals("토큰 재사용 실패", accessToken1, accessToken2);

        accessToken1 = usageExample(EasyCodefServiceType.API);
        accessToken2 = usageExample(EasyCodefServiceType.API);
        assertEquals("토큰 재사용 실패", accessToken1, accessToken2);
    }

    public String usageExample(EasyCodefServiceType type) throws InterruptedException, IOException {
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
        String accessToken = codef.requestToken(type);
        System.out.println(accessToken);

        return accessToken;
    }
}
