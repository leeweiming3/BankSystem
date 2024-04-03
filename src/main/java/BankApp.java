import java.util.Scanner;

public class BankApp {
    Parser parser;
    Bank bank;
    BankUI bankUi;

    public BankApp() {
        this.bank = new Bank();
        this.parser = new Parser();
        this.bankUi = new BankUI();
    }

    public static void main(String[] args) {
        BankApp b = new BankApp();
        b.run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        bankUi.greet();
        while (parser.isRunning()) {
            try {
                parser.exec(sc.nextLine(), bankUi, bank);
            } catch (ParseException e) {
                bankUi.error(e);
            }
        }
        sc.close();
    }
}