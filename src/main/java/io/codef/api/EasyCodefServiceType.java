package io.codef.api;

/**
 * CODEF 서비스 타입 enum 클래스
 */
public enum EasyCodefServiceType {
    SANDBOX(2),
    DEMO(1),
    API(0);

    private final int serviceType;

    EasyCodefServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    int getServiceType() {
        return serviceType;
    }

}
