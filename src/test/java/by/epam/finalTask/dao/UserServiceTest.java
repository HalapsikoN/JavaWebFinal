package by.epam.finalTask.dao;

import by.epam.finalTask.entity.User;
import by.epam.finalTask.entity.util.Role;
import by.epam.finalTask.service.ServiceException;
import by.epam.finalTask.service.ServiceFactory;
import by.epam.finalTask.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserServiceTest {

    private static final UserService userService = ServiceFactory.getInstance().getUserService();
    private static final Logger logger = LogManager.getLogger(UserServiceTest.class);

    private static User testingUser;
    private static String testingPassword;

    @BeforeClass
    public static void setupUser() {
        User user = new User();

        String userName = "testUser";
        String userLogin = "testLoginTest";
        String userPassword = "abcdef";
        Role userRole = Role.USER;

        user.setName(userName);
        user.setLogin(userLogin);
        user.setRole(userRole);

        testingUser = user;
        testingPassword = userPassword;
    }

    @Test
    public void registerUserWithInvalidData() {
        boolean isRegistered = false;

        try {
            User tempUser = new User();
            isRegistered = userService.register(tempUser, testingPassword);
        } catch (ServiceException e) {
            logger.error(e);
        }

        Assert.assertFalse(isRegistered);
    }

    @Test
    public void registerUser() {
        boolean isRegistered = false;

        try {
            isRegistered = userService.register(testingUser, testingPassword);
        } catch (ServiceException e) {
            logger.error(e);
        }

        Assert.assertTrue(isRegistered);
    }

    @Test
    public void registerUserWithAlreadyTakenLogin() {
        boolean isRegistered = false;

        try {
            isRegistered = userService.register(testingUser, testingPassword);
        } catch (ServiceException e) {
            logger.error(e);
        }

        Assert.assertFalse(isRegistered);
    }

    @Test
    public void signInUser() {
        User userFromBD = null;

        try {
            userService.register(testingUser, testingPassword);
            userFromBD = userService.signIn(testingUser.getLogin(), testingPassword);
            User tempUser = userService.signIn(testingUser.getLogin(), testingPassword);
            userService.deleteUser(tempUser.getId());
        } catch (ServiceException e) {
            logger.error(e);
        }

        //because user id is user BD id
        if (userFromBD != null) {
            testingUser.setId(userFromBD.getId());
        }

        Assert.assertEquals(testingUser, userFromBD);
    }

    @Test
    public void deleteUser() {
        boolean isDeleted = false;

        try {
            User tempUser = userService.signIn(testingUser.getLogin(), testingPassword);
            isDeleted = userService.deleteUser(tempUser.getId());
        } catch (ServiceException e) {
            logger.error(e);
        }

        Assert.assertTrue(isDeleted);
    }
}
