package com.cg.shopping.model;

import com.cg.shopping.model.dto.res.RegisterResDto;
import com.cg.shopping.model.dto.res.RoleResDto;
import com.cg.shopping.model.dto.res.UserResDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(length = 500)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    public void addRole(Role role) {
        roles.add(role);
    }

    public RegisterResDto toRegisterResDto(){
        List<RoleResDto> roleResDtos = this.roles.stream().map(role -> role.toRoleResDto()).collect(Collectors.toList());
        return new RegisterResDto(this.id, this.name, this.password, roleResDtos);
    }
    public UserResDto toUserResDto(){
        List<RoleResDto> roleResDtos = this.roles.stream().map(role -> role.toRoleResDto()).collect(Collectors.toList());
        return new UserResDto(this.id, this.name, this.password, roleResDtos);
    }
}
