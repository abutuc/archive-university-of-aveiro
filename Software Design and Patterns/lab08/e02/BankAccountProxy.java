
public class BankAccountProxy implements BankAccount {
	private BankAccountImpl bankAccount;

	public BankAccountProxy(String bank, double initialDeposit) {
		bankAccount = new BankAccountImpl(bank, initialDeposit);
	}

	public String getBank() {
		return bankAccount.getBank();
	}

	@Override
	public void deposit(double amount) {
		bankAccount.deposit(amount);
	}

	@Override
	public boolean withdraw(double amount) {
		if (Company.user == User.COMPANY)
			throw new UnsupportedOperationException();
		return bankAccount.withdraw(amount);
	}

	@Override
	public double balance() {
		if (Company.user == User.COMPANY)
			throw new UnsupportedOperationException();
		return bankAccount.balance();
	}
}
