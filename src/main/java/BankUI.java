import java.util.ArrayList;
import java.util.Arrays;

public class BankUI {
    public void greet() {
        System.out.println("Welcome to AwesomeGIC Bank! What would you like to do?");
        this.printOptions();
    }

    public void printOptions() {
        System.out.println("[D]eposit\n"
                + "[W]ithdraw\n"
                + "[P]rint statement\n"
                + "[Q]uit");
    }

    public void promptDeposit() {
        System.out.println("Please enter the amount to deposit:");
    }

    public void deposit(Money m) {
        System.out.printf("Thank you. %s has been deposited to your account.\n",
                m);
        this.askAnythingElse();
    }

    private void askAnythingElse() {
        System.out.println("Is there anything else you'd like to do?");
        this.printOptions();
    }

    public void withdraw(Money m) {
        System.out.printf("Thank you. %s has been withdrawn.\n",
                m);
        this.askAnythingElse();
    }

    public void promptWithdraw() {
        System.out.println("Please enter the amount to withdraw:");
    }

    public void printStatement(ArrayList<ArrayList<String>> rows) {
        int[] colSizes = new int[rows.get(0).size()];
        for (int i = 0; i < colSizes.length; i++) {
            int finalI = i;
            colSizes[i] = rows.stream().mapToInt(e -> e.get(finalI).length())
                    .max().getAsInt();
        }
        String fmt = String.join(" | ",
                Arrays.stream(colSizes).<CharSequence>mapToObj(i -> "%-" + i + "s")::iterator);
        for (ArrayList<String> row : rows) {
            System.out.printf(fmt + "\n", (Object[]) row.toArray(String[]::new));
        }
    }

    public void error(ParseException e) {
        System.out.println("Error: " + e.getMessage());
    }

    public void quit() {
        System.out.println("Thank you for banking with AwesomeGIC Bank.");
        System.out.println("Have a nice day!");
    }
}
