package bank;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n--- 🏦 BANK MENU ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Delete Account");
            System.out.println("6. Show all accounts (admin only)");
            System.out.println("7. Exit");
            System.out.print("-> Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("=> 1. Normal Account (without interest)");
                    System.out.println("=> 2. Saving Account (with interest)");
                    while(true){
                        System.out.print("=> Choose an option: ");
                        int createOption = scanner.nextInt();
                        if(createOption==1||createOption==2){
                            System.out.print("==> Account Holder Name: ");
                            scanner.nextLine();
                            String name = scanner.nextLine();
                            System.out.print("==> Initial Deposit: ");
                            double initialDeposit = scanner.nextDouble();
                            if(createOption==1){
                                bank.createAccount("normal", name, initialDeposit);
                            }
                            if(createOption==2){
                                bank.createAccount("saving", name, initialDeposit);
                            }
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.print("=> Enter Account Number: ");
                    int accNumDep = scanner.nextInt();
                    Account accountDep = bank.getAccount(accNumDep);
                    if (accountDep != null) {
                        //System.out.println("\uD83D\uDC4B Hi "+accountDep.getHolder());
                        System.out.print("Enter Deposit Amount: ");
                        double depAmount = scanner.nextDouble();
                        accountDep.deposit(depAmount);
                    } else {
                        System.out.println("\uD83D\uDED1 Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("=> Enter Account Number: ");
                    int accNumWith = scanner.nextInt();
                    Account accountWith = bank.getAccount(accNumWith);
                    if (accountWith != null) {
                        //System.out.println("\uD83D\uDC4B Hi "+accountWith.getHolder());
                        System.out.print("Enter Withdrawal Amount: ");
                        double withAmount = scanner.nextDouble();
                        accountWith.withdraw(withAmount);
//                        try {
//                            accountWith.withdraw(accountWith.getBalance(),withAmount);
//                        } catch (InsufficientBalanceException e) {
//                            System.out.println(e.getMessage());
//                        }
                    } else {
                        System.out.println("\uD83D\uDED1 Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("=> Enter Account Number: ");
                    int accNumBal = scanner.nextInt();
                    Account accountBal = bank.getAccount(accNumBal);
                    if (accountBal != null) {
                        System.out.println("\n---- \uD83D\uDCB8 BALANCE ----");
                        System.out.println("Holder: " + accountBal.getHolder());
                        if(accountBal.getType().equals("saving")){
                            System.out.println("Interest (3%): " + ((SavingsAccount)accountBal).getInterest());
                            System.out.println("Balance without Interest: "
                                    +
                                    (accountBal.getBalance()-((SavingsAccount)accountBal).getInterest())
                            );
                            System.out.println("Balance with Interest: " + accountBal.getBalance());
                        } else {
                            System.out.println("Balance: " + accountBal.getBalance());
                        }
                        System.out.println("------------------------------");
                    } else {
                        System.out.println("\uD83D\uDED1 Account not found.");
                    }
                    break;
                case 5:
                    System.out.print("=> Enter Account Number: ");
                    int del_account_num = scanner.nextInt();
                    boolean del_account = bank.deleteAccount(del_account_num);
                    if(del_account) {
                        System.out.println("\uD83D\uDC4D Account deleted");
                    } else {
                        System.out.println("\uD83D\uDED1 Account not found");
                    }
                    break;
                case 6:
                    System.out.print("Enter Admin username: ");
                    scanner.nextLine();
                    String adminName = scanner.nextLine();
                    System.out.print("Enter Admin Password: ");
                    String adminPass = scanner.nextLine();
                    Admin admin = new Admin(adminName,adminPass);
                    admin.allAccounts();
                    break;
                case 7:
                    System.out.println("\uD83D\uDED1 Exiting Bank System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("\uD83D\uDED1 Invalid option. Please try again.");
            }
        }
    }
}
