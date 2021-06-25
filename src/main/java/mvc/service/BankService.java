package mvc.service;

import mvc.data.BankRepository;
import mvc.domain.Account;
import mvc.domain.Transacctions;
import mvc.integration.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    @Autowired
    EmailSender emailSender;

    public void save(Account account) {
        bankRepository.save(account);
        emailSender.sendEmail(account.getAccountHolder(), "Welcome");
    }

    public Account getAccount(int accountNumber) {
        return bankRepository.getBank(accountNumber);
    }

    public void delete(int accountNumber) {
        bankRepository.delete(accountNumber);
    }

    public Collection<Account> findAll() {
        return bankRepository.findAll();
    }

    public void deposit(Transacctions transacctions) {
        bankRepository.deposit(transacctions);
    }

    public void withdraw(Transacctions transacctions) {
        bankRepository.withdraw(transacctions);
    }
}
