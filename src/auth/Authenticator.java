package auth;

import models.User;

public interface Authenticator {
    boolean login(String username, String password);
    User getUser();
    void logout();
}
