package com.devslopsleon.orders.services.auth;

public final class TenantContext {

    private static final ThreadLocal<Long> COMPANY_PK = new ThreadLocal<>();

    public static void setCompanyPk(Long pk) {
        COMPANY_PK.set(pk);
    }
    public static Long getCompanyPk() {
        return COMPANY_PK.get();
    }
    public static void clear() {
        COMPANY_PK.remove();
    }
}
