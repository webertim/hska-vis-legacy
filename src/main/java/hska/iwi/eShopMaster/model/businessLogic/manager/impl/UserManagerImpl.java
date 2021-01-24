package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.clients.RoleClient;
import hska.iwi.eShopMaster.clients.UserRoleClient;
import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.RoleDAO;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.UserDAO;
import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

/**
 * 
 * @author knad0001
 */

public class UserManagerImpl implements UserManager {

	UserRoleClient userRoleClient;
	RoleClient roleClient;
	
	public UserManagerImpl() {
		userRoleClient = new UserRoleClient();
		roleClient = new RoleClient();
	}


	public void registerUser(String username, String name, String lastname, String password, Role role) {

		User user = new User(username, name, lastname, password, role);

		userRoleClient.registerUser(user);
	}


	public User getUserByUsername(String username) {
		if (username == null || username.equals("")) {
			return null;
		}
		return userRoleClient.getUserByUsername(username);
	}


	public Role getRoleByName(String name) {
		Role role = roleClient.getRoleByName(name);
		return role;
	}

	public boolean doesUserAlreadyExist(String username) {
    	return userRoleClient.getUserByUsername(username) != null;
	}
	

	public boolean validate(User user) {
		if (user.getFirstname().isEmpty() || user.getPassword().isEmpty() || user.getRole() == null || user.getLastname() == null || user.getUsername() == null) {
			return false;
		}
		return true;
	}

}
