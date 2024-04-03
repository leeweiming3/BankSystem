import java.math.BigDecimal;

public class Parser {
    private boolean hasQuit;
    private ParserState state;

    public Parser() {
        this.state = ParserState.NORMAL;
        this.hasQuit = false;
    }

    public void exec(String s, BankUI bankUi, Bank bank) throws ParseException {
        switch (state) {
        case DEPOSITING:
            Money depositValue = this.readMoney(s);
            bank.deposit(depositValue);
            bankUi.deposit(depositValue);
            this.state = ParserState.NORMAL;
            break;
        case WITHDRAWING:
            Money withdrawValue = this.readMoney(s);
            bank.withdraw(withdrawValue);
            bankUi.withdraw(withdrawValue);
            this.state = ParserState.NORMAL;
            break;
        case NORMAL:
            switch (s) {
            case "D":
            case "d":
                bankUi.promptDeposit();
                this.state = ParserState.DEPOSITING;
                break;
            case "W":
            case "w":
                bankUi.promptWithdraw();
                this.state = ParserState.WITHDRAWING;
                break;
            case "p":
                bankUi.printStatement(bank.getTable());
                break;
            case "q":
                bankUi.quit();
                this.hasQuit = true;
                break;
            default:
                throw new ParseException("Invalid command: " + s);
            }
        }
    }

    public boolean isRunning() {
        return !hasQuit;
    }

    private Money readMoney(String s) throws ParseException {
        try {
            return new Money(new BigDecimal(s));
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid number: " + s);
        }
    }

    enum ParserState {
        NORMAL, DEPOSITING, WITHDRAWING
    }
}
