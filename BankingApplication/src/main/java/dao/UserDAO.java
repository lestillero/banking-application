package dao;

import java.util.List;

import models.User;

public interface UserDAO {
	
	// CREATE
	public boolean createUser(User u);
	
	// READ
	public User viewUser(int id);
	public User viewUser(String username);
	public List<User> viewAllUsers();
	public List<Integer> viewAllUserIds();
	public List<String> viewAllUsernames();
	
	// UPDATE
	public boolean updateUser(User u);
	
	// DELETE
	public boolean deleteUser(int id);

}
