package auth;

import models.User;
import models.UserRole;

public class HRAdminAuthenticator implements Authenticator {

    private User loggedInUser;

    @Override
    public boolean login(String username, String password) {
        if (username.equals("admin") && password.equals("password")) {
            loggedInUser = new User(username, UserRole.HR_ADMIN);
            return true;
        }
        return false;
    }

    @Override
    public User getUser() {
        return loggedInUser;
    }

    @Override
    public void logout() {
        loggedInUser = null;
    }
}
