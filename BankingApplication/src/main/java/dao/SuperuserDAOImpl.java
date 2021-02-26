package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.SuperUser;
import util.JDBCConnection;

public class SuperUserDAOImpl implements SuperUserDAO {

	public static Connection conn = JDBCConnection.getConnection();

	public boolean createSuperUser(SuperUser s) {
		try {

			String sql = "CALL create_superuser(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, s.getUsername());
			cs.setString(2, s.getPassword());
			cs.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public SuperUser viewSuperUser(int id) {
		try {

			String sql = "SELECT * FROM users WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				SuperUser s = new SuperUser();
				s.setId(rs.getInt("ID"));
				s.setUsername(rs.getString("USERNAME"));
				s.setPassword(rs.getString("PASSWORD"));

				return s;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public SuperUser viewSuperUser(String username) {
		try {

			String sql = "SELECT * FROM users WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				SuperUser s = new SuperUser();
				s.setId(rs.getInt("ID"));
				s.setUsername(rs.getString("USERNAME"));
				s.setPassword(rs.getString("PASSWORD"));

				return s;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<SuperUser> viewAllSuperUsers() {
		List<SuperUser> superUsers = new ArrayList<SuperUser>();

		try {

			String sql = "SELECT * FROM users WHERE mod(id,2) = 1";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				SuperUser s = new SuperUser();
				s.setId(rs.getInt("ID"));
				s.setUsername(rs.getString("USERNAME"));
				s.setPassword(rs.getString("PASSWORD"));

				superUsers.add(s);

			}

			return superUsers;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public List<String> viewAllSuperUsernames() {
		List<String> superUsernames = new ArrayList<String>();

		try {

			String sql = "SELECT username FROM users WHERE mod(id,2) = 1";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				String u = rs.getString("USERNAME");
				superUsernames.add(u);

			}

			return superUsernames;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean updateSuperUser(SuperUser s) {
		try {

			String sql = "UPDATE users SET username = ?, password = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s.getUsername());
			ps.setString(2, s.getPassword());
			ps.setString(3, Integer.toString(s.getId()));

			ps.executeQuery();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteSuperUser(int id) {
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