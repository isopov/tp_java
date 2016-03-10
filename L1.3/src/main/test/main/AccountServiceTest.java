package main;

import org.junit.Before;
import org.junit.Test;
import rest.UserProfile;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by esin on 10.03.2016.
 */
public class AccountServiceTest {
    private AccountService accountService;

    @Before
    public void setupAccountService(){
        accountService = new AccountService();
    }

    @Test
    public void testAddUser() throws Exception {
        final boolean result = accountService.addUser("test", new UserProfile("test", "testpass"));
        assertTrue(result);
    }

    @Test
    public void testAddSameUserFail(){
        accountService.addUser("test", new UserProfile("test", "testpass"));
        final boolean result = accountService.addUser("test", new UserProfile("test", "testpass"));
        assertFalse(result);
    }
}
