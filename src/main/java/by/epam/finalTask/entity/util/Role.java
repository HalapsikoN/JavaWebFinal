package by.epam.finalTask.entity.util;

public enum Role {
    USER,
    ADMIN;

    public static Role getRoleFromString(String element) {
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
}
