package main;

import rest.UserProfile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author esin88
 */
public class AccountServiceMapImpl implements AccountService {
    private Map<String, UserProfile> users = new ConcurrentHashMap<>();

    public AccountServiceMapImpl() {
        users.put("admin", new UserProfile("admin", "admin"));
        users.put("guest", new UserProfile("guest", "12345"));
    }

    @Override
    public Collection<UserProfile> getAllUsers() {
        return users.values();
    }

    @Override
    public boolean addUser(String userName, UserProfile userProfile) {
        if (users.containsKey(userName))
            return false;
        users.put(userName, userProfile);
        return true;
    }

    @Override
    public UserProfile getUser(String userName) {
        return users.get(userName);
    }
}
