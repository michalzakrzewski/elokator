package com.elokator.enums;

public enum CustomerRoleEnum {
    RESIDENT("Resident"),
    ADMINISTRATOR("Administrator");

    private final String roleName;

    CustomerRoleEnum(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public boolean isRole(String role) {
        return roleName.equalsIgnoreCase(role);
    }

    public CustomerRoleEnum getRoleEnum(String role) {
        for (CustomerRoleEnum customerRoleEnum : CustomerRoleEnum.values()) {
            if (customerRoleEnum.getRoleName().equalsIgnoreCase(role)) {
                return customerRoleEnum;
            }
        }
        return null;
    }
}
