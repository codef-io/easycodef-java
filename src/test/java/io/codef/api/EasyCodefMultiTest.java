package io.codef.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;


/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodefMultiTest.java
 * </pre>
 * <p>
 * Desc : EasyCodef를 사용한 다건 요청 예시
 * - 건강보험공단 상품 3개 ( 건강검진결과, 진료받은 내용, 진료 및 투약정보 )
 *
 * @Company : ©CODEF corp.
 * @Author : vbbbv@codef.io
 * @Date : Jun 30, 2022
 * @Version : 1.0.1
 */
public class EasyCodefMultiTest {

    /* 다건인증을 진행시 동일 계정으로 판별하기 위한 id값 */
    private static final String id = "example_codef_id_1234";


    /**
     * 1차 요청
     * - 멀티 스레드를 이용한 다건 요청 처리
     * - 1번 상품 1차 요청에 대한 응답 코드 CF-03002(추가인증 요청) 확인 필요 -> 간편 인증 후 2차 요청 진행
     */
    @Test
    public void usageExample1() throws InterruptedException {

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
         * - 각 상품별 파라미터를 설정(https://developer.codef.io/products)
         * - (하단 상품별 요청 파라미터 설정 부분 참고)
         */
        List<HashMap<String, Object>> list = new ArrayList<>();

        list.add(Test_KR_PB_PP_004());
        list.add(TEST_KR_PB_PP_019());
        list.add(Test_KR_PB_PP_018());


        /**
         * #6.코드에프 정보 조회 1차 요청
         * - 서비스타입(API:정식, DEMO:데모, SANDBOX:샌드박스)
         */
        for (HashMap<String, Object> bodyMap : list) {

            // 다건요청을 진행시 0.5 ~ 1초의 딜레이 필요. 동시에 요청이 진행될 경우 첫번째 세션이 생성되기 전에 여러개의 요청이 동시에 생성되어 별도의 세션으로 처리됨
            Thread.sleep(700);

            new Thread(() -> {
                try {
                    /* API 요청 */
                    String result = codef.requestProduct(bodyMap.get("urlPath").toString(), EasyCodefServiceType.API, bodyMap);

                    /* 응답 결과 확인
                     * - 1번 상품 1차 요청에 대한 추가 인증 요청 출력 ( code : CF-03002 )
                     * - 후에 2차 요청 성공시 1번 상품을 제외한 나머지 상품 (2번, 3번) 조회 결과 값 출력 부분
                     */
                    System.out.println(result);
                } catch (UnsupportedEncodingException | JsonProcessingException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        //junit으로 테스트시 바로 종료처리 되기 때문에 슬립 처리
        Thread.sleep(300000);
    }


    /**
     * 2차 요청 - 간편 인증 (ex.카카오 인증) 후 2차 요청 진행 (2차 요청 전 twoWayInfoMap 변경 필요)
     */
    @Test
    public void usageExample2() throws IOException, InterruptedException {

        /**
         *  쉬운 코드이프 객체 생성 및 정보 설정 (#1~#4)
         */
        // #1
        EasyCodef codef = new EasyCodef();
        // #2
        codef.setClientInfoForDemo(EasyCodefClientInfo.CLIENT_ID, EasyCodefClientInfo.CLIENT_SECRET);
        // #3
        codef.setClientInfo(EasyCodefClientInfo.CLIENT_ID, EasyCodefClientInfo.CLIENT_SECRET);
        // #4
        codef.setPublicKey(EasyCodefClientInfo.PUBLIC_KEY);

        /**
         *  추가 인증 요청 파라미터 설정
         */
        // 1차 입력부 포함 (기본 파라미터)
        HashMap<String, Object> bodyMap = Test_KR_PB_PP_004();

        bodyMap.put("is2Way", true);
        bodyMap.put("simpleAuth", "1");

        /* 1차 요청에서 받은 응답 값으로 설정 - 값 설정 후 2차 요청 진행 */
        HashMap<String, Object> twoWayInfoMap = new HashMap<String, Object>();
        twoWayInfoMap.put("jobIndex", 0);
        twoWayInfoMap.put("threadIndex", 0);
        twoWayInfoMap.put("jti", "62bd44fbec829c545056f59f");
        twoWayInfoMap.put("twoWayTimestamp", 1656571132897L);

        bodyMap.put("twoWayInfo", twoWayInfoMap);
        // 요청 파라미터 설정 종료

        /* API 요청 */
        String result = codef.requestCertification(bodyMap.get("urlPath").toString(), EasyCodefServiceType.API, bodyMap);

        /* 응답결과 확인
         *  - 1번 상품의 2차 요청 결과 값 출력 부분
         */
        System.out.println(result);
    }


    /**
     * 각 상품별 요청 파라미터 설정 부분 =======================================================================================
     */

    /* 1번 상품 건강보험 건강검진결과 */
    private HashMap<String, Object> Test_KR_PB_PP_004() {
        // 요청 파라미터 설정 시작
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();

        bodyMap.put("id", id);

        bodyMap.put("organization", "0002");
        bodyMap.put("identity", "yyyyMMdd");        //생년월일 yyyyMMdd
        bodyMap.put("userName", "");                //이름
        bodyMap.put("phoneNo", "");                 //휴대폰번호
        bodyMap.put("loginTypeLevel", "1");         //카카오인증
        bodyMap.put("loginType", "5");              //간편인증

        bodyMap.put("inquiryType", "0");
        bodyMap.put("searchStartYear", "2021");
        bodyMap.put("type", "1");
        bodyMap.put("searchEndYear", "2022");
        bodyMap.put("urlPath", "/v1/kr/public/pp/nhis-health-checkup/result");
        return bodyMap;

        // 요청 파라미터 설정 종료
    }

    /* 2번 상품 건강보험공단 진료받은 내용 */
    private HashMap<String, Object> TEST_KR_PB_PP_019() {
        // 요청 파라미터 설정 시작
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();

        bodyMap.put("id", id);

        bodyMap.put("organization", "0002");
        bodyMap.put("identity", "yyyyMMdd");        //생년월일 yyyyMMdd
        bodyMap.put("userName", "");                //이름
        bodyMap.put("phoneNo", "");                 //휴대폰번호
        bodyMap.put("loginTypeLevel", "1");         //카카오인증
        bodyMap.put("loginType", "5");              //간편인증

        bodyMap.put("startDate", "20220101");
        bodyMap.put("type", "1");
        bodyMap.put("endDate", "20220401");
        bodyMap.put("urlPath", "/v1/kr/public/pp/nhis-list/medical-history");
        return bodyMap;

        // 요청 파라미터 설정 종료
    }

    /* 3번 상품 건강보험공단 진료 및 투약정보 */
    private HashMap<String, Object> Test_KR_PB_PP_018() {
        // 요청 파라미터 설정 시작
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();

        bodyMap.put("id", id);

        bodyMap.put("organization", "0002");
        bodyMap.put("identity", "yyyyMMdd");        //생년월일 yyyyMMdd
        bodyMap.put("userName", "");                //이름
        bodyMap.put("phoneNo", "");                 //휴대폰번호
        bodyMap.put("loginTypeLevel", "1");         //카카오인증
        bodyMap.put("loginType", "5");              //간편인증

        bodyMap.put("startDate", "20220101");
        bodyMap.put("type", "1");
        bodyMap.put("endDate", "20220401");
        bodyMap.put("urlPath", "/v1/kr/public/pp/nhis-treatment/information");
        return bodyMap;

        // 요청 파라미터 설정 종료
    }
}
