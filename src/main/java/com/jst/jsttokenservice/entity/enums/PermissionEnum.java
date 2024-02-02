package com.jst.jsttokenservice.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PermissionEnum {
    DOCUMENT_CREATE("document:create"),
    DOCUMENT_DELETE("document:delete"),
    DOCUMENT_READ("document:read"),
    DOCUMENT_UPDATE("document:update");
    private final String permissionEnum;
}
