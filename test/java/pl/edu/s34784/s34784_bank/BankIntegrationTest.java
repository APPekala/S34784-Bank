package pl.edu.s34784.s34784bank;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.s34784.s34784bank.service.BankService;
import pl.edu.s34784.s34784bank.model.*;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BankIntegrationTest {

    private final BankService service = new BankService();

    @Test
    void shouldHandleFullBankScenario() {

        Client client = service.registerClient(new BigDecimal("100"));
        assertThat(client.getBalance()).isEqualByComparingTo("100");

        TransactionResponse deposit = service.deposit(client.getId(), new BigDecimal("50"));
        assertThat(deposit.getStatus()).isEqualTo(TransactionStatus.ACCEPTED);

        TransactionResponse transfer = service.transfer(client.getId(), new BigDecimal("30"));
        assertThat(transfer.getBalance()).isEqualByComparingTo("120");

        TransactionResponse failed = service.transfer(client.getId(), new BigDecimal("500"));
        assertThat(failed.getStatus()).isEqualTo(TransactionStatus.DECLINED);
    }
}
