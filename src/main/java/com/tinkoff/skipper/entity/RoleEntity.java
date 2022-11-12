package com.tinkoff.skipper.entity;

import javax.persistence.*;

@Entity
public class RoleEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role name = Role.ROLE_USER;

    public enum Role {
        ROLE_ADMIN,
        ROLE_MODERATOR,
        ROLE_USER
    }


}
