import java.util.ArrayList;
import java.util.Arrays;

public class Bank {
    private final Money initialBalance;
    private final Money balance;
    private final ArrayList<Transaction> txns;

    public Bank(Money balance, ArrayList<Transaction> txns) {
        this.balance = balance;
        this.initialBalance = balance;
        this.txns = txns;
    }

    public Bank() {
        this(Money.of(0), new ArrayList<>());
    }

    public void deposit(Money m) {
        Transaction newTransaction = Transaction.makeDeposit(m);
        txns.add(newTransaction);
        this.balance.add(newTransaction.getChange());
    }

    public void withdraw(Money m) {
        Transaction newTransaction = Transaction.makeWithdrawal(m);
        txns.add(newTransaction);
        this.balance.add(newTransaction.getChange());
    }

    public ArrayList<ArrayList<String>> getTable() {
        ArrayList<ArrayList<String>> rows = new ArrayList<>();
        rows.add(new ArrayList<>(Arrays.asList("Date", "Amount", "Balance")));

        Money currBalance = initialBalance;
        for (Transaction t : txns) {
            currBalance = currBalance.add(t.getChange());
            ArrayList<String> row = t.asRow();
            row.add(currBalance.withoutUnit());
            rows.add(row);
        }
        return rows;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-22s| %-8s| %s\n", "Date", "Amount", "Balance"));
        Money currBalance = initialBalance;
        for (Transaction t : txns) {
            currBalance = currBalance.add(t.getChange());
            sb.append(String.format("%-22s| %-8s| %s\n", t.getTime().toString(),
                    t.getChange().withoutUnit(), currBalance.withoutUnit()));
        }
        return sb.toString();
    }
}
