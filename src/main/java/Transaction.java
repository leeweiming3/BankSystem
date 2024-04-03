import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Transaction {
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm:ssa");
    private final LocalDateTime time;
    private final Money change;

    public Transaction(LocalDateTime time, Money change) {
        this.time = time;
        this.change = change;
    }

    public static Transaction makeDeposit(Money m) {
        return new Transaction(LocalDateTime.now(), m);
    }

    public static Transaction makeWithdrawal(Money m) {
        return new Transaction(LocalDateTime.now(), m.negate());
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Money getChange() {
        return change;
    }

    public ArrayList<String> asRow() {
        ArrayList<String> ar = new ArrayList<>();
        ar.add(time.format(df));
        ar.add(change.withoutUnit());
        return ar;
    }

}
