package pl.edu.s34784.s34784bank.model;

import java.math.BigDecimal;

public class Client {

    private Long id;
    private BigDecimal balance;

    public Client(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
