package by.zverugo.samsolutions.instagram.util.enums;

/**
 * Created by Alex on 22.10.2015.
 */
public enum UserRoleEnum {
    ADMIN("ADMIN"),
    USER("USER");

    //TODO Locale getter for log4j

    private String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
