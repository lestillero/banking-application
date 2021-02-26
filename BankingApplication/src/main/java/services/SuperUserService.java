package services;

import java.util.List;
import models.Account;
import models.SuperUser;
import models.User;

public interface SuperUserService extends UserService {
	
	public boolean checkSuperUsername(String username);
	
	public boolean registerSuperUser(String username, String password, int code);
	
	public SuperUser viewSuperUser(int id);
	public List<User> viewAllUsers();
	
	public List<Account> viewAllAccounts();
	
	public boolean deleteUser(int userId);
	
	public boolean deleteSuperUser(int userId);

}
