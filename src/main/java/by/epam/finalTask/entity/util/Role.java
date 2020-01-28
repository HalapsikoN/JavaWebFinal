package by.epam.finalTask.entity.util;

public enum Role {
    USER,
    ADMIN;

    public static Role getRoleFromString(String element) throws EnumConstantNotPresentException {
        switch (element) {
            case "user": {
                return USER;
            }
            case "admin": {
                return ADMIN;
            }
            default: {
                throw new EnumConstantNotPresentException(Role.class, element);
            }
        }
    }

    public static boolean isRole(String element){
        element.toLowerCase();
        switch (element) {
            case "user":
            case "admin": {
                return true;
            }
            default: {
                return false;
            }
        }
    }
}
