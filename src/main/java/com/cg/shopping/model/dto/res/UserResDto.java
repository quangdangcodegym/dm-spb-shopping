package com.cg.shopping.model.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class UserResDto {
    private Long id;
    private String username;
    private String password;

    private List<RoleResDto> roles = new ArrayList<>();
}
