package main;

import rest.UserProfile;

import java.util.Collection;

/**
 * Created by e.shubin on 24.03.2016.
 */
public interface AccountService {
    Collection<UserProfile> getAllUsers();

    boolean addUser(String userName, UserProfile userProfile);

    UserProfile getUser(String userName);
}
