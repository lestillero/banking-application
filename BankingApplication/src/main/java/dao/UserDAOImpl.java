package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.SuperUser;
import models.User;
import util.JDBCConnection;

public class UserDAOImpl implements UserDAO { // NEED TO HANDLE EXCEPTIONS (e.g. user error, putting in incorrect
												// username)

	public static Connection conn = JDBCConnection.getConnection();

	public boolean createUser(User u) {
		try {

			String sql = "CALL create_user(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, u.getUsername());
			cs.setString(2, u.getPassword());
			cs.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public User viewUser(int id) {
		try {

			String sql = "SELECT * FROM users WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				User u = new User();
				u.setId(rs.getInt("ID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));

				return u;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public User viewUser(String username) {
		try {

			String sql = "SELECT * FROM users WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				if (rs.getInt("ID") % 2 == 1) {

					SuperUser s = new SuperUser();
					s.setId(rs.getInt("ID"));
					s.setUsername(rs.getString("USERNAME"));
					s.setPassword(rs.getString("PASSWORD"));
					
					return s;

				} else {

					User u = new User();
					u.setId(rs.getInt("ID"));
					u.setUsername(rs.getString("USERNAME"));
					u.setPassword(rs.getString("PASSWORD"));

					return u;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<User> viewAllUsers() {
		List<User> users = new ArrayList<User>();

		try {

			String sql = "SELECT * FROM users";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				if (rs.getInt("ID") % 2 == 1) {

					SuperUser s = new SuperUser();
					s.setId(rs.getInt("ID"));
					s.setUsername(rs.getString("USERNAME"));
					s.setPassword(rs.getString("PASSWORD"));

					users.add(s);

				} else {

					User u = new User();
					u.setId(rs.getInt("ID"));
					u.setUsername(rs.getString("USERNAME"));
					u.setPassword(rs.getString("PASSWORD"));

					users.add(u);
				}

			}

			return users;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Integer> viewAllUserIds() {
		List<Integer> userIds = new ArrayList<Integer>();

		try {

			String sql = "SELECT id FROM users";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Integer i = rs.getInt("ID");
				userIds.add(i);

			}

			return userIds;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<String> viewAllUsernames() {
		List<String> usernames = new ArrayList<String>();

		try {

			String sql = "SELECT username FROM users";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String u = rs.getString("USERNAME");
				usernames.add(u);

			}

			return usernames;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean updateUser(User u) {
		try {

			String sql = "UPDATE users SET username = ?, password = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, Integer.toString(u.getId()));

			ps.executeQuery();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteUser(int id) {
		try {

			String sql = "DELETE users WHERE id = ?";
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