package pl.edu.s34784.s34784bank.model;

import java.math.BigDecimal;

public class TransactionResponse {

    private TransactionStatus status;
    private BigDecimal balance;

    public TransactionResponse(TransactionStatus status, BigDecimal balance) {
        this.status = status;
        this.balance = balance;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
