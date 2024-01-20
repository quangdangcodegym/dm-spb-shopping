package com.cg.shopping.model.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RegisterResDto {
    private Long id;
    private String username;

    private List<RoleResDto> roles = new ArrayList<>();


}
