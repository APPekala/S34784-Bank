package pl.edu.s34784.s34784bank.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.s34784.s34784bank.model.*;
import pl.edu.s34784.s34784bank.service.BankService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/register")
    public Client register(@RequestParam BigDecimal balance) {
        return bankService.registerClient(balance);
    }

    @PostMapping("/deposit")
    public TransactionResponse deposit(
            @RequestParam Long clientId,
            @RequestParam BigDecimal amount) {
        return bankService.deposit(clientId, amount);
    }

    @PostMapping("/transfer")
    public TransactionResponse transfer(
            @RequestParam Long clientId,
            @RequestParam BigDecimal amount) {
        return bankService.transfer(clientId, amount);
    }

    @GetMapping("/client/{id}")
    public Client getClient(@PathVariable Long id) {
        return bankService.getClient(id);
    }
}
