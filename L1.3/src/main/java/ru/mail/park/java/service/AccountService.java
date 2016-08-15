package ru.mail.park.java.service;


import java.util.Collection;

import ru.mail.park.java.domain.UserProfile;

/**
 * Created by e.shubin on 24.03.2016.
 */
public interface AccountService {
    Collection<UserProfile> getAllUsers();

    boolean addUser(String userName, String password);

    UserProfile getUser(String userName);
}
