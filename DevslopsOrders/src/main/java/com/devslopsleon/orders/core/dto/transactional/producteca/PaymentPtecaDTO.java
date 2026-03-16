package com.devslopsleon.orders.core.dto.transactional.producteca;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentPtecaDTO implements Serializable {
    private LocalDateTime Date;
    private BigDecimal Amount;
    private String Status;
    private String Method;
    private BigDecimal TransactionFee;
    private String IntegrationId;

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMethod() {
        return Method;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public BigDecimal getTransactionFee() {
        return TransactionFee;
    }

    public void setTransactionFee(BigDecimal transactionFee) {
        TransactionFee = transactionFee;
    }

    public String getIntegrationId() {
        return IntegrationId;
    }

    public void setIntegrationId(String integrationId) {
        IntegrationId = integrationId;
    }
}
