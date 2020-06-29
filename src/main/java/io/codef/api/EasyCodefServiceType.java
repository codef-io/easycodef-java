package io.codef.api;

/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodefServiceType.java
 * </pre>
 * 
 * Desc : CODEF 서비스 타입 enum 클래스
 * @Company : ©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:40:36 PM
 */
public enum EasyCodefServiceType {
	SANDBOX(2),
	DEMO(1),
	API(0);
	
	private int serviceType;
	
	private EasyCodefServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	protected int getServiceType() {
		return serviceType;
	}
	
}
