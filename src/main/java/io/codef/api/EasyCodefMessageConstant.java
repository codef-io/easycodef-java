package io.codef.api;

/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodefMessageConstant.java
 * </pre>
 * 
 * Desc : EasyCodef에서 사용되는 메시지 코드 클래스
 * @Company : ©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:36:41 PM
 */
public enum EasyCodefMessageConstant {

	OK("CF-00000", "성공"),
	
	INVALID_JSON("CF-00002", "json형식이 올바르지 않습니다."),
	INVALID_PARAMETER("CF-00007", "요청 파라미터가 올바르지 않습니다."),
	UNSUPPORTED_ENCODING("CF-00009", "지원하지 않는 형식으로 인코딩된 문자열입니다."),
	
	EMPTY_CLIENT_INFO("CF-00014", "상품 요청을 위해서는 클라이언트 정보가 필요합니다. 클라이언트 아이디와 시크릿 정보를 설정하세요."),
	EMPTY_PUBLIC_KEY("CF-00015", "상품 요청을 위해서는 퍼블릭키가 필요합니다. 퍼블릭키 정보를 설정하세요."),
	
	INVALID_2WAY_INFO("CF-03003", "2WAY 요청 처리를 위한 정보가 올바르지 않습니다. 응답으로 받은 항목을 그대로 2way요청 항목에 포함해야 합니다."),
	INVALID_2WAY_KEYWORD("CF-03004", "추가 인증(2Way)을 위한 요청은 requestCertification메서드를 사용해야 합니다."),
	
	BAD_REQUEST("CF-00400", "클라이언트 요청 오류로 인해 요청을 처리 할 수 ​​없습니다."),
	UNAUTHORIZED("CF-00401", "요청 권한이 없습니다."),
	FORBIDDEN("CF-00403", "잘못된 요청입니다."),
	NOT_FOUND("CF-00404", "요청하신 페이지(Resource)를 찾을 수 없습니다."),
	METHOD_NOT_ALLOWED("CF-00405", "요청하신 방법(Method)이 잘못되었습니다."),
	
	
	LIBRARY_SENDER_ERROR("CF-09980", "통신 요청에 실패했습니다. 응답정보를 확인하시고 올바른 요청을 시도하세요."),
	SERVER_ERROR("CF-09999", "서버 처리중 에러가 발생 했습니다. 관리자에게 문의하세요."),
	
	;
	
	
	
	private String code;
	private String message;
	private String extraMessage;
	
	private EasyCodefMessageConstant(String code, String message) {
		this.code = code;
		this.message = message;
	}

	protected String getCode() {
		return code;
	}

	protected String getMessage() {
		return message;
	}

	protected void setExtraMessage(String extraMessage) {
		this.extraMessage = extraMessage;
	}

	protected String getExtraMessage() {
		return extraMessage;
	}
}
