package dao;

import java.util.List;

import models.SuperUser;

public interface SuperUserDAO {
	
	// CREATE	
	public boolean createSuperUser(SuperUser s);

	// READ
	public SuperUser viewSuperUser(int id);
	public SuperUser viewSuperUser(String username);
	public List<SuperUser> viewAllSuperUsers();
	public List<String> viewAllSuperUsernames();

	// UPDATE
	public boolean updateSuperUser(SuperUser s);

	// DELETE
	public boolean deleteSuperUser(int id);

}
