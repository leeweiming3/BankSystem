import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Money {
    private final BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value;
    }

    public static Money of(long value) {
        return new Money(BigDecimal.valueOf(value));
    }

    public String withoutUnit() {
        return new DecimalFormat("0.00").format(value);
    }

    public Money negate() {
        return new Money(value.negate());
    }

    public Money add(Money other) {
        return new Money(value.add(other.value));
    }

    @Override
    public String toString() {
        return NumberFormat.getCurrencyInstance(Locale.US).format(value);

    }
}
