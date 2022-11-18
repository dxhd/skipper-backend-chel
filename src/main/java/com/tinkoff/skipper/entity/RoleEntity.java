package com.tinkoff.skipper.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
public class RoleEntity implements GrantedAuthority {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role name = Role.ROLE_USER;

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }

    public enum Role {
        ROLE_ADMIN,
        ROLE_MODERATOR,
        ROLE_USER
    }


}
