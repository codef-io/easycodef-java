package io.codef.api;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 코드에프 응답 결과 클래스
 */
public class EasyCodefResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -4106296996913677632L;

    private HashMap<String, Object> result;
    private Object data;

    /**
     * EasyCodefResponse 생성자
     */
    protected EasyCodefResponse() {
        result = new HashMap<String, Object>();
        data = new HashMap<String, Object>();

        this.put(EasyCodefConstant.RESULT, result);
        this.put(EasyCodefConstant.DATA, data);

        this.setResultMessage(EasyCodefMessageConstant.OK.getCode(), EasyCodefMessageConstant.OK.getMessage(), "");
    }

    /**
     * EasyCodefResponse 생성자
     */
    @SuppressWarnings("unchecked")
    protected EasyCodefResponse(HashMap<String, Object> map) {
        Iterator<String> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            if (EasyCodefConstant.RESULT.equals(key)) {    // 결과 코드 정보
                result = (HashMap<String, Object>) map.get(EasyCodefConstant.RESULT);
                this.put(EasyCodefConstant.RESULT, result);
            } else if (EasyCodefConstant.RESULT.equals(key)) { //결과 데이터 정보
                try {
                    data = map.get(EasyCodefConstant.DATA);
                } catch (ClassCastException e) {
                    data = map.get(EasyCodefConstant.DATA);
                }
                this.put(EasyCodefConstant.DATA, data);
            } else {
                this.put(key, map.get(key));    // 사용자 정의 파라미터가 존재하는 경우 응답부에 추가 설정
            }
        }


    }

    /**
     * EasyCodefResponse 생성자
     */
    protected EasyCodefResponse(EasyCodefMessageConstant message) {
        result = new HashMap<String, Object>();
        data = new HashMap<String, Object>();

        this.put(EasyCodefConstant.RESULT, result);
        this.put(EasyCodefConstant.DATA, data);

        this.setResultMessage(message.getCode(), message.getMessage(), "");
    }

    /**
     * EasyCodefResponse 생성자
     */
    protected EasyCodefResponse(EasyCodefMessageConstant message, String extraMessage) {
        result = new HashMap<String, Object>();
        data = new HashMap<String, Object>();

        this.put(EasyCodefConstant.RESULT, result);
        this.put(EasyCodefConstant.DATA, data);

        this.setResultMessage(message.getCode(), message.getMessage(), extraMessage);
    }


    /**
     * 요청 수행 결과 코드 설정
     */
    protected void setResultMessage(String errCode, String errMsg, String extraMsg) {
        this.result.put(EasyCodefConstant.CODE, errCode);
        this.result.put(EasyCodefConstant.MESSAGE, errMsg);
        this.result.put(EasyCodefConstant.EXTRA_MESSAGE, extraMsg);
    }

    /**
     * 요청 수행 결과 코드 설정
     */
    protected void setResultMessage(EasyCodefMessageConstant message) {
        this.result.put(EasyCodefConstant.CODE, message.getCode());
        this.result.put(EasyCodefConstant.MESSAGE, message.getMessage());
        this.result.put(EasyCodefConstant.EXTRA_MESSAGE, message.getExtraMessage());
    }


    /**
     * Override toString
     */
    @Override
    public String toString() {
        return "EasyCodefResponse [result=" + result + ", data=" + data + "]";
    }
}
