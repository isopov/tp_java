package ru.mail.park.java.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import ru.mail.park.java.domain.UserProfile;

/**
 * @author esin88
 */
@Service
public class AccountService {
	private final Map<String, UserProfile> users = new ConcurrentHashMap<>();

	public boolean addUser(@NotNull String userName, @NotNull UserProfile userProfile) {
		UserProfile previous = users.putIfAbsent(userName, userProfile);
		return previous == null;
	}

	@Nullable
	public UserProfile getUser(String userName) {
		return users.get(userName);
	}
}