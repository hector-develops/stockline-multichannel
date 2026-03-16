package com.devslopsleon.orders.dexeptions;

public class CompanyDuplicateFoundException extends RuntimeException {
    public CompanyDuplicateFoundException(String message) {
        super(message);
    }
}
