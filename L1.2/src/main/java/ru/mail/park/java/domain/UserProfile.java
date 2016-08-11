package ru.mail.park.java.domain;

import org.jetbrains.annotations.NotNull;

/**
 * @author esin88
 */
public class UserProfile {
	@NotNull
	private final String login;
	@NotNull
	private final String passwordHash;

	public UserProfile(@NotNull String login, @NotNull String passwordHash) {
		this.login = login;
		this.passwordHash = passwordHash;
	}

	@NotNull
	public String getLogin() {
		return login;
	}

	@NotNull
	public String getPasswordHash() {
		return passwordHash;
	}
}