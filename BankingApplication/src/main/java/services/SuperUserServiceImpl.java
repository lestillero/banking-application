package services;

import java.util.List;
import dao.SuperUserDAO;
import dao.SuperUserDAOImpl;
import models.Account;
import models.SuperUser;
import models.User;

public class SuperUserServiceImpl extends UserServiceImpl implements SuperUserService {
	
	public SuperUserDAO sdao = new SuperUserDAOImpl();

	/**
	 * Checks given username against list of SuperUsers
	 * (to give them access to different menu than regular users)
	 */
	public boolean checkSuperUsername(String username) {
		
		List<String> superUsernames = sdao.viewAllSuperUsernames();
		return superUsernames.contains(username);

	}
	
	/**
	 * Creates new superuser IF correct verification code is entered
	 */
	public boolean registerSuperUser(String username, String password, int code) {

		try {

			SuperUser s = new SuperUser();
			s.setUsername(username);
			s.setPassword(password);

			return sdao.createSuperUser(s);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public SuperUser viewSuperUser(int id) {
		return sdao.viewSuperUser(id);
	}

	
	public List<User> viewAllUsers() {
	
		return udao.viewAllUsers();
	}

	public List<Account> viewAllAccounts() {

		return adao.viewAllAccounts();

	}

	public boolean deleteUser(int userId) {

		return udao.deleteUser(userId);

	}

	public boolean deleteSuperUser(int userId) {
		
		return sdao.deleteSuperUser(userId);
		
	}

}
