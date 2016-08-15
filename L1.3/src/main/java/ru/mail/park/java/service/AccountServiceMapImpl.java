package ru.mail.park.java.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ru.mail.park.java.domain.UserProfile;

/**
 * @author esin88
 */
@Service
public class AccountServiceMapImpl implements AccountService {
	private final Map<String, UserProfile> users = new ConcurrentHashMap<>();

	public AccountServiceMapImpl() {
		addUser("admin", "admin");
		addUser("guest", "12345");
	}

	public boolean addUser(@NotNull String userName, @NotNull String password) {
		UserProfile previous = users.putIfAbsent(userName,
				new UserProfile(userName, BCrypt.hashpw(password, BCrypt.gensalt())));
		return previous == null;
	}

	@Nullable
	public UserProfile getUser(String userName) {
		return users.get(userName);
	}

	@Override
	public Collection<UserProfile> getAllUsers() {
		return users.values();
	}
}