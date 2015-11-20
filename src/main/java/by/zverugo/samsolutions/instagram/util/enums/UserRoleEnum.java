package by.zverugo.samsolutions.instagram.util.enums;

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
