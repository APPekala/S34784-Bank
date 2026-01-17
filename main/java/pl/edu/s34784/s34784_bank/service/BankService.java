package pl.edu.s34784.s34784bank.service;

import org.springframework.stereotype.Service;
import pl.edu.s34784.s34784bank.model.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BankService {

    private final Map<Long, Client> clients = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Client registerClient(BigDecimal initialBalance) {
        Long id = idGenerator.getAndIncrement();
        Client client = new Client(id, initialBalance);
        clients.put(id, client);
        return client;
    }

    public TransactionResponse deposit(Long clientId, BigDecimal amount) {
        Client client = clients.get(clientId);

        if (client == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return new TransactionResponse(TransactionStatus.DECLINED, null);
        }

        client.setBalance(client.getBalance().add(amount));
        return new TransactionResponse(TransactionStatus.ACCEPTED, client.getBalance());
    }

    public TransactionResponse transfer(Long clientId, BigDecimal amount) {
        Client client = clients.get(clientId);

        if (client == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return new TransactionResponse(TransactionStatus.DECLINED, null);
        }

        if (client.getBalance().compareTo(amount) < 0) {
            return new TransactionResponse(TransactionStatus.DECLINED, client.getBalance());
        }

        client.setBalance(client.getBalance().subtract(amount));
        return new TransactionResponse(TransactionStatus.ACCEPTED, client.getBalance());
    }

    public Client getClient(Long id) {
        return clients.get(id);
    }
}
