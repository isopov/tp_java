package ru.mail.park.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.mail.park.java.domain.UserProfile;
import ru.mail.park.java.service.AccountService;

@RestController
// Реально для этого, конечно, используется POST
@RequestMapping(path = "/api/v1/auth/signup", method = RequestMethod.GET)
public class SignUpController {

	private final AccountService accountService;

	@Autowired
	public SignUpController(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping
	public ResponseEntity<SimpleResponce> signup(@RequestParam(required = false) String name,
			@RequestParam(required = false) String password) {

		if (StringUtils.isEmpty(name)) {
			return new ResponseEntity<>(new SimpleResponce("Name is empty"), HttpStatus.FORBIDDEN);
		}
		if (StringUtils.isEmpty(password)) {
			return new ResponseEntity<>(new SimpleResponce("Password is empty"), HttpStatus.FORBIDDEN);
		}

		if (accountService.addUser(name, new UserProfile(name, BCrypt.hashpw(password, BCrypt.gensalt())))) {
			return new ResponseEntity<>(new SimpleResponce("Ok"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SimpleResponce("User exists"), HttpStatus.FORBIDDEN);
		}
	}

}
