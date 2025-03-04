package bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
//    private static int counter = 1;
    private final int A_number;
    private String holder;
    private String type;
    private double balance;

    public Account(int a_number, String holder, double balance, String type) {
        this.holder = holder;
        this.balance = balance;
        this.type = type;
//        this.A_number = counter++;
        this.A_number = a_number;
    }

    public int getANumber() {
        return A_number;
    }

    public String getHolder() {
        return holder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    //==> Deposit
//    public void deposit(double balance,double amount) {
//        if (amount > 0) {
//        	balance += amount;
//            System.out.println("\n---- \uD83D\uDCB8 DEPOSIT ----");
//            System.out.println("Holder: " + holder);
//            System.out.println("Balance: " + balance);
//            System.out.println("Deposited: " + amount);
//            System.out.println("New Balance: " + balance);
//            System.out.println("-----------------");
//        } else {
//            System.out.println("Invalid.");
//        }
//    }
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }

        String sql = "UPDATE bank_accounts SET balance = balance + ? WHERE id = ?";

        try (
            Connection connection = PG_Connection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, this.A_number);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                this.balance += amount;
                System.out.println("\n---- 💸 DEPOSIT ----");
                System.out.println("Holder: " + this.holder);
                System.out.println("Current Balance: " + (this.balance-amount));
                System.out.println("Deposited: " + amount);
                System.out.println("New Balance: " + this.balance);
                System.out.println("-----------------");

            } else {
                System.out.println("\uD83D\uDED1 Deposit failed. Account not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    //==> Withdraw
//    public void withdraw(double balance, double amount) throws InsufficientBalanceException {
//    	try {
//    		if(amount < 0 || amount > deposit) {
//    			throw new InsufficientBalanceException("Insufficient balance");
//    		}
//    		deposit -= amount;
//            System.out.println("\n---- \uD83D\uDCB8 WITHDRAW ----");
//            System.out.println("Holder: " + holder);
//            System.out.println("Balance: " + balance);
//            System.out.println("Withdrawn: " + amount);
//            System.out.println("New Balance: " + deposit);
//            System.out.println("-----------------");
//    	} catch(InsufficientBalanceException err) {
//    		throw new InsufficientBalanceException("Insufficient balance to withdraw: " + amount);
//    	}
//    }
    public void withdraw(double amount) {
        if(amount <= 0) {
            System.out.println("Invalid withdraw amount.");
        }
        // Check Balance
        String sqlBalance = "SELECT balance FROM bank_accounts WHERE id = ?";
        try(
                Connection connection = PG_Connection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sqlBalance)
        ) {
            stmt.setInt(1, this.A_number);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if(amount > rs.getDouble("balance")) {
                    throw new InsufficientBalanceException("\uD83D\uDED1 Insufficient balance.");
                }
            }
        } catch (SQLException | InsufficientBalanceException e) {
            System.err.println(e.getMessage());
        }

        // Withdraw
        String sqlWithdraw = "UPDATE bank_accounts SET balance = balance - ? WHERE id = ?";
        try(
                Connection connection2 = PG_Connection.getConnection();
                PreparedStatement stmtWithdraw = connection2.prepareStatement(sqlWithdraw)
        ) {
            stmtWithdraw.setDouble(1, amount);
            stmtWithdraw.setInt(2, this.A_number);
            int affectedRows = stmtWithdraw.executeUpdate();
            if (affectedRows > 0) {
                this.balance -= amount;
                System.out.println("\n---- \uD83D\uDCB8 WITHDRAW ----");
                System.out.println("Holder: " + holder);
                System.out.println("Balance: " + (balance+amount));
                System.out.println("Withdrawn: " + amount);
                System.out.println("New Balance: " + balance);
                System.out.println("-----------------");
            }
    	} catch(SQLException e) {
            System.err.println(e.getMessage());
    	}
    }
}
