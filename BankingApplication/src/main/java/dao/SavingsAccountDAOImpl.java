package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Account;
import models.SavingsAccount;
import util.JDBCConnection;

public class SavingsAccountDAOImpl extends AccountDAOImpl implements SavingsAccountDAO {

	public static Connection conn = JDBCConnection.getConnection();

	public boolean createAccount(Account a) {
		try {

			String sql = "CALL create_savings_account(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, Double.toString(a.getBalance()));
			cs.setString(2, Integer.toString(a.getUserId()));
			cs.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public Account viewAccount(int id) {
		try {

			String sql = "SELECT * FROM accounts WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Account a = new SavingsAccount();
				a.setId(rs.getInt("ID"));
				a.setBalance(rs.getDouble("BALANCE"));
				a.setUserId(rs.getInt("USER_ID"));

				return a;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Account> viewAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();

		try {

			String sql = "SELECT * FROM accounts WHERE type = 'Savings'";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Account a = new SavingsAccount();
				a.setId(rs.getInt("ID"));
				a.setBalance(rs.getDouble("BALANCE"));
				a.setUserId(rs.getInt("USER_ID"));

				accounts.add(a);

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

			String sql = "SELECT * FROM accounts WHERE type = 'Savings' AND user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(userId));;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Account a = new SavingsAccount();
				a.setId(rs.getInt("ID"));
				a.setBalance(rs.getDouble("BALANCE"));
				a.setUserId(rs.getInt("USER_ID"));

				accounts.add(a);

			}

			return accounts;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}