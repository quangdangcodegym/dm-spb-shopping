package com.cg.shopping.model.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterReqDto {
    private Long id;
    private String email;
    private String name;
    private String password;

}
