package bank;

public class SavingsAccount extends Account {
    private final double interestRate = 0.03;
    private double interest;
    public SavingsAccount(int a_number, String holder, double deposit, String type, double interest) {
        super(a_number,holder,deposit,type);
        this.interest = interest;
//        this.interest = deposit*interestRate;
//        this.setBalance(deposit+interest);
    }
    public double getInterest() {
        return interest;
    }
    public double getInterestRate() {
        return interestRate;
    }

//    public double getRealBalance(){
//        return getBalance()-interest;
//    }

//    public void setSavings(double savings) {
//        this.interest = interest;
//    }
//    public void updateSavings(double savings) {
//        this.savings += savings;
//    }
}
