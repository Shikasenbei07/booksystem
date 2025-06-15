package model;

public class AccountBL {
	public Account login(String email) {
		Account ac = null;

		AccountDAO dao = new AccountDAO();
		ac = dao.findAccountByKey(email);

		return ac;
	}

	public boolean insertAccount(Account ac) {
		boolean result = false;

		AccountDAO dao = new AccountDAO();
		result = dao.insertAccount(ac);

		return  result;
	}
}
