package com.cg.shopping.model;

import com.cg.shopping.model.dto.res.RoleResDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    public RoleResDto toRoleResDto(){
        return new RoleResDto(this.id, this.name);
    }
}
