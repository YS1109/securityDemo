package com.ysoztf.security.entity;

public class SysPermission {
    private int id;
    private String permission;
    private String permissionName;
    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
