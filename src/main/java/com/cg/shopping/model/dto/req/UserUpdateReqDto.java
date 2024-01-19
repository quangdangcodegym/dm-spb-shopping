package com.cg.shopping.model.dto.req;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserUpdateReqDto {
    private Long id;
    private String email;
    private String name;
    private String password;
    private List<Long> roles;


}
