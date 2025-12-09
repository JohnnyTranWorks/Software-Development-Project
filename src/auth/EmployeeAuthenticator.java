package auth;

import models.User;
import models.UserRole;

public class EmployeeAuthenticator implements Authenticator {

    private User loggedInUser;

    @Override
    public boolean login(String username, String password) {
        loggedInUser = new User(username, UserRole.GENERAL_EMPLOYEE);
        return true;
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
