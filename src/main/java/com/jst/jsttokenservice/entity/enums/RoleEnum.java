package com.jst.jsttokenservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
public enum RoleEnum {
    ACCEPTOR("acceptor"),
    SELLER("seller");
    private final String roleEnum;

}
