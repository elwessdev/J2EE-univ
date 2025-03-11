package bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Admin {
    private String name, password;
    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean checkAuth(){
        if (!Objects.equals(name, "admin") || !Objects.equals(password, "admin")) {
            System.out.println("Name or Password are not correct");
            return false;
        }
        System.out.println("Hi Admin");
        return true;
    }

    public void allAccounts() throws SQLException {
        if(!checkAuth()){
            return;
        }
        String sql = "SELECT * FROM bank_accounts";
        try (
            Connection connection = PG_Connection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)
        ){
            ResultSet rs = stmt.executeQuery();
            int cnt = 1;
            while (rs.next()) {
                boolean isSaving = rs.getString("acc_type").equals("saving");
                System.out.println("\n---- ACCOUNT "+cnt+" ----");
                System.out.println("Account Number: " + rs.getInt("id"));
                System.out.println("Account Holder: " + rs.getString("name"));
                if (isSaving) {
                    System.out.println("Balance without Interest: " +
                            (rs.getDouble("balance")-rs.getDouble("interest_amount"))
                    );
                    System.out.println("Interest (3%): " + rs.getDouble("interest_amount"));
                }
                System.out.println("Account Balance: " +
                        (isSaving
                            ?(rs.getDouble("balance")+rs.getDouble("interest_amount"))
                            :rs.getDouble("balance")
                        )
                );
                System.out.println("-----------------");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
