package bank;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bank {
//    private final List<Account> accounts = new ArrayList<>();

//    public void createAccount(String type, String holder, double deposit) {
//        if(type.equals("saving")) {
//            String sql = "INSERT INTO accounts (name, balance, interest_amount) VALUES (?, ?, ?)";
//            SavingsAccount newAccount = new SavingsAccount(holder, deposit);
//            accounts.add(newAccount);
//            System.out.println("\n---- \uD83D\uDCB8 NEW ACCOUNT CREATED ----");
//            System.out.println("Account Number: " + newAccount.getANumber());
//            System.out.println("Account Name: " + newAccount.getHolder());
//            System.out.println("Account Deposit: " + deposit);
//            //System.out.println("Interest rate : "+newAccount.getInterestRate());
//            System.out.println("Interest (3%): " + newAccount.getInterest());
//            System.out.println("Account Balance: " + newAccount.getBalance());
//            System.out.println("-----------------------------");
//        } else {
//            Account newAccount = new Account(holder, deposit);
//            accounts.add(newAccount);
//            System.out.println("\n---- \uD83D\uDCB8 NEW ACCOUNT CREATED ----");
//            System.out.println("Account Number: " + newAccount.getANumber());
//            System.out.println("Account Name: " + newAccount.getHolder());
//            System.out.println("Account Balance: " + newAccount.getBalance());
//            System.out.println("-----------------------------");
//
//        }
//    }

    //==> Create Account
    public void createAccount(String type, String holder, double deposit) {
        String sql;
        boolean isSaving = type.equals("saving");

        if (isSaving) {
            sql = "INSERT INTO bank_accounts (name, balance, acc_type, interest_amount) VALUES (?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO bank_accounts (name, balance, acc_type) VALUES (?, ?, ?)";
        }

        try (
            Connection connection = PG_Connection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            stmt.setString(1, holder);
            stmt.setDouble(2, isSaving ?deposit+(deposit*0.03) :deposit);
            stmt.setString(3, type);
            if (isSaving) {
                stmt.setDouble(4, deposit*0.03);
            }
            int affRows = stmt.executeUpdate();
            if (affRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int accountNumber = generatedKeys.getInt(1);
                    System.out.println("\n---- 🏦 NEW ACCOUNT CREATED ----");
                    System.out.println("Account Number: " + accountNumber);
                    System.out.println("Account Name: " + holder);
                    if (isSaving) {
                        System.out.println("Interest (3%): " + deposit*0.03);
                    }
                    System.out.println("Account Balance: " + (isSaving ?deposit+(deposit*0.03) :deposit));
                    System.out.println("-----------------------------");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    //==> Get Account Info
//    public Account getAccount(int number) {
//        for (Account account : accounts) {
//            if (account.getANumber() == number) {
//                return account;
//            }
//        }
//        return null;
//    }
    public Account getAccount(int number) {
        String sql = "SELECT id, name, balance, acc_type, interest_amount FROM bank_accounts WHERE id = ?";

        try (
            Connection connection = PG_Connection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, number);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String accType = rs.getString("acc_type");
                if("saving".equals(accType)) {
                    return new SavingsAccount(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("balance"),
                        rs.getString("acc_type"),
                        rs.getDouble("interest_amount")
                    );
                } else {
                    return new Account(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("balance"),
                        rs.getString("acc_type")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

    //==> Delete Account
//    public Boolean deleteAccount(int number) {
//        for (Account account : accounts) {
//            if (account.getANumber() == number) {
//                accounts.remove(account);
//                return true;
//            }
//        }
//        return false;
//    }
    public Boolean deleteAccount(int AccNumber) {
        String sql = "DELETE FROM bank_accounts WHERE id = ?";
        try (
            Connection connection = PG_Connection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setInt(1, AccNumber);
            int affRows = stmt.executeUpdate();
            if (affRows > 0) {
                return true;
            }
        } catch(SQLException e){
            System.err.println("Error: " + e.getMessage());
        }
        return false;
    }
}
