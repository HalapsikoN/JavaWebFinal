package by.epam.finalTask.service.validator;

import by.epam.finalTask.entity.User;
import by.epam.finalTask.entity.util.Role;

import java.util.regex.Pattern;

public class UserDataValidator {
    private final static int MIN_LOGIN_LENGTH=4;
    private final static int MIN_PASSWORD_LENGTH=6;
    private final static int MIN_NAME_LENGTH=2;

    private UserDataValidator(){
    }

    public static boolean isValidLogin(String login){
        return ((login!=null) && (!login.isEmpty()) && (login.length()>=MIN_LOGIN_LENGTH));
    }

    public static boolean isValidPassword(String password){
        return ((password!=null)&&(!password.isEmpty()) && (password.length()>=MIN_PASSWORD_LENGTH));
    }

    public static boolean isNameValid(String name){
        return ((name!=null)&&(!name.isEmpty()) && (name.length()>=MIN_NAME_LENGTH));
    }

    public static boolean isWalletValid(double wallet){
        return (wallet>=0);
    }

    public static boolean isRoleValid(String role){
        return ((role!=null)&&(!role.isEmpty())&&(Role.isRole(role)));
    }

    public static boolean isUserValid(User user){
        if(user==null) {
            return false;
        }

        boolean validName=isNameValid(user.getName());
        boolean validLogin=isValidLogin(user.getLogin());
        boolean validWallet=isWalletValid(user.getWallet());
        boolean validRole=isRoleValid(user.getRole().name());
        return (validLogin && validName && validWallet && validRole);
    }

}
