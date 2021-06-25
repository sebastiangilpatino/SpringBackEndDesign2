package mvc.data;

import mvc.domain.Account;
import mvc.domain.Transacctions;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BankRepository {
    private Map<Integer, Account> bank = new HashMap<Integer, Account>();

    public void save(Account account) {
        this.bank.put(account.getAccountNumber(), account);
    }

    public Account getBank(Integer accountNumber) {
        return bank.get(accountNumber);
    }

    public void delete(Integer accountNumber) {
        bank.remove(accountNumber);
    }

    public void deposit(Transacctions transacctions) {
        bank.get(transacctions.getAccountNumber()).addTransaction(transacctions);
        bank.get(transacctions.getAccountNumber()).setBalance(
                bank.get(transacctions.getAccountNumber()).getBalance() + transacctions.getAmount()
        );
    }

    public void withdraw(Transacctions transacctions) {
        bank.get(transacctions.getAccountNumber()).addTransaction(transacctions);
        bank.get(transacctions.getAccountNumber()).setBalance(
                bank.get(transacctions.getAccountNumber()).getBalance() - transacctions.getAmount()
        );
    }

    public Collection<Account> findAll() {
        return bank.values();
    }
}
