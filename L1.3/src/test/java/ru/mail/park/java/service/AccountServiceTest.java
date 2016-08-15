package ru.mail.park.java.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by esin on 10.03.2016.
 */
public class AccountServiceTest {
	private AccountServiceMapImpl accountService;

	@Before
	public void setupAccountService() {
		accountService = new AccountServiceMapImpl();
	}

	@Test
	public void testAddUser() throws Exception {
		assertTrue(accountService.addUser("test", "testpass"));
	}

	@Test
	public void testAddSameUserFail() {
		assertTrue(accountService.addUser("test", "testpass"));
		assertFalse(accountService.addUser("test", "testpass"));
	}
}
