package mvc.domain;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Transacctions {

    @NotNull
    private LocalDate date;

    @NotNull
    private double amount;

    @NotNull
    private int accountNumber;

    public Transacctions(LocalDate date, double amount, int accountNumber ) {
        this.date = date;
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
