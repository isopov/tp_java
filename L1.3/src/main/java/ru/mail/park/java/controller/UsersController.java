package ru.mail.park.java.controller;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.mail.park.java.domain.UserProfile;
import ru.mail.park.java.service.AccountService;

/**
 * Created by e.shubin on 25.02.2016.
 */
@RestController
@RequestMapping("/api/user")
public class UsersController {
	@Autowired
	private AccountService service;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<UserProfileView> getAllUsers() {
		return service.getAllUsers()
				.stream()
				.map(UserProfileView::new)
				.collect(toList());
	}

	public static class UserProfileView {
		private final String login;

		public UserProfileView(UserProfile profile) {
			this.login = profile.getLogin();
		}

		public String getLogin() {
			return login;
		}
	}

	@RequestMapping(path = "/{name}", method = RequestMethod.GET)
	@ResponseBody
	public UserProfileView getUserByName(@PathVariable("name") String name) {
		final UserProfile user = service.getUser(name);
		if (user == null) {
			throw new UserNotFoundException("User " + name + " not found");
		} else {
			return new UserProfileView(user);
		}
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public static class UserNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public UserNotFoundException(String message) {
			super(message);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String createUser(@RequestBody UserProfileDTO user) {
		if (service.addUser(user.getLogin(), user.getPassword())) {
			return user.getLogin();
		} else {
			throw new UserAlreadyExistsException("User " + user.getLogin() + " already exists");
		}
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public static class UserAlreadyExistsException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public UserAlreadyExistsException(String message) {
			super(message);
		}
	}

	public static final class UserProfileDTO {
		private String login;
		private String password;

		public void setLogin(String login) {
			this.login = login;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getLogin() {
			return login;
		}

		public String getPassword() {
			return password;
		}
	}

}
