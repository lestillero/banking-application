package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Account;
import models.CheckingAccount;
import models.LoanAccount;
import models.SavingsAccount;
import util.JDBCConnection;

public class AccountDAOImpl implements AccountDAO {

	public static Connection conn = JDBCConnection.getConnection();

	public Account viewAccount(int id) {
		try {

			String sql = "SELECT * FROM accounts WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				if (rs.getString("TYPE").equals("Savings")) {

					Account sa = new SavingsAccount();
					sa.setId(rs.getInt("ID"));
					sa.setBalance(rs.getDouble("BALANCE"));
					sa.setUserId(rs.getInt("USER_ID"));

					return sa;

				} else if (rs.getString("TYPE").equals("Checking")) {

					Account ca = new CheckingAccount();
					ca.setId(rs.getInt("ID"));
					ca.setBalance(rs.getDouble("BALANCE"));
					ca.setUserId(rs.getInt("USER_ID"));

					return ca;

				} else if (rs.getString("TYPE").equals("Loan")) {

					Account la = new LoanAccount();
					la.setId(rs.getInt("ID"));
					la.setBalance(rs.getDouble("BALANCE"));
					la.setUserId(rs.getInt("USER_ID"));

					return la;

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Account> viewAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();

		try {

			String sql = "SELECT * FROM accounts";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				if (rs.getString("TYPE").equals("Savings")) {

					Account sa = new SavingsAccount();
					sa.setId(rs.getInt("ID"));
					sa.setBalance(rs.getDouble("BALANCE"));
					sa.setUserId(rs.getInt("USER_ID"));

					accounts.add(sa);

				} else if (rs.getString("TYPE").equals("Checking")) {

					Account ca = new CheckingAccount();
					ca.setId(rs.getInt("ID"));
					ca.setBalance(rs.getDouble("BALANCE"));
					ca.setUserId(rs.getInt("USER_ID"));

					accounts.add(ca);

				} else if (rs.getString("TYPE").equals("Loan")) {

					Account la = new LoanAccount();
					la.setId(rs.getInt("ID"));
					la.setBalance(rs.getDouble("BALANCE"));
					la.setUserId(rs.getInt("USER_ID"));

					accounts.add(la);

				}

			}

			return accounts;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Account> viewTheirAccounts(int userId) {
		List<Account> accounts = new ArrayList<Account>();

		try {

			String sql = "SELECT * FROM accounts WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(userId));;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				if (rs.getString("TYPE").equals("Savings")) {

					Account sa = new SavingsAccount();
					sa.setId(rs.getInt("ID"));
					sa.setBalance(rs.getDouble("BALANCE"));
					sa.setUserId(rs.getInt("USER_ID"));

					accounts.add(sa);

				} else if (rs.getString("TYPE").equals("Checking")) {

					Account ca = new CheckingAccount();
					ca.setId(rs.getInt("ID"));
					ca.setBalance(rs.getDouble("BALANCE"));
					ca.setUserId(rs.getInt("USER_ID"));

					accounts.add(ca);

				} else if (rs.getString("TYPE").equals("Loan")) {

					Account la = new LoanAccount();
					la.setId(rs.getInt("ID"));
					la.setBalance(rs.getDouble("BALANCE"));
					la.setUserId(rs.getInt("USER_ID"));

					accounts.add(la);

				}

			}

			return accounts;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public List<Integer> viewTheirAccountIds(int userId) {
		
		List<Integer> accountIds = new ArrayList<Integer>();

		try {

			String sql = "SELECT id FROM accounts WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(userId));;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				Integer accountId = rs.getInt("ID");
				accountIds.add(accountId);

			}

			return accountIds;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean updateAccount(Account a) {
		try {

			String sql = "UPDATE accounts SET balance = ?, user_id = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Double.toString(a.getBalance()));
			ps.setString(2, Integer.toString(a.getUserId()));
			ps.setString(3, Integer.toString(a.getId()));

			ps.executeQuery();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteAccount(int id) {
		try {

			String sql = "DELETE accounts WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(id));

			ps.executeQuery();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
