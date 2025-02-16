package com.omkar.dao;

import java.util.List;

import com.omkar.model.User;

public interface UserDAO {
	void addUser(User user);
	User getUser(int userId);
	void updateUser(User user);
	void deleteUser(int userId);
	List<User> getAllUsers();
	User valideUser(String email, String password);

}
