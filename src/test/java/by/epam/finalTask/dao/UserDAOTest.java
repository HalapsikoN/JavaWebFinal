package by.epam.finalTask.dao;

import by.epam.finalTask.entity.User;
import by.epam.finalTask.entity.util.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserDAOTest {

    private static final UserDAO userDAO = DAOFactory.getInstance().getSqlUserDAO();
    private static final Logger logger = LogManager.getLogger(UserDAOTest.class);

    private static User testingUser;
    private static String testingPassword;

    @BeforeClass
    public static void setupUser() {
        User user = new User();

        String userName = "testUser";
        String userLogin = "testLogin";
        String userPassword = "$2a$10$OL8wUsYnhzmYexqRDlpqB.Xmh3jHlvJTN3xKcU/MztGc5RMuWdzx2";
        Role userRole = Role.USER;

        user.setName(userName);
        user.setLogin(userLogin);
        user.setRole(userRole);

        testingUser = user;
        testingPassword = userPassword;
    }

    @Test
    public void addUser() {

        boolean isAdded = false;

        try {
            isAdded = userDAO.addUser(testingUser, testingPassword);
        } catch (DAOException e) {
            logger.error(e);
        }

        Assert.assertTrue(isAdded);
    }

    @Test
    public void getUserByLogin() {

        User findUser = null;

        try {
            findUser = userDAO.getUserByLogin(testingUser.getLogin());
        } catch (DAOException e) {
            logger.error(e);
        }

        //because user id is user BD id
        if (findUser != null) {
            testingUser.setId(findUser.getId());
        }

        Assert.assertEquals(testingUser, findUser);
    }

    @Test
    public void updateUserPassword() {

        boolean isUpdated = false;
        String newPassword = "$2a$10$ut0cZEqGjr7EBAykTJBrAexHmyi72V5MeOqCWSzTcg/S.cPKnqU8C";

        try {
            User tempUser = userDAO.getUserByLogin(testingUser.getLogin());
            isUpdated = userDAO.updateUserPasswordById(tempUser.getId(), newPassword);
        } catch (DAOException e) {
            logger.error(e);
        }

        Assert.assertTrue(isUpdated);
    }

    @Test
    public void deleteUser() {
        boolean isDeleted = false;

        try {
            User tempUser = userDAO.getUserByLogin(testingUser.getLogin());
            isDeleted = userDAO.deleteUserById(tempUser.getId());
        } catch (DAOException e) {
            logger.error(e);
        }

        Assert.assertTrue(isDeleted);
    }
}
