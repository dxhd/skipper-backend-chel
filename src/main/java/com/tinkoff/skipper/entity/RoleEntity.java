package com.tinkoff.skipper.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Roles")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED, force=true)
public class RoleEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public String getAuthority() {
        return role.value;
    }

    @RequiredArgsConstructor
    public enum Role {
        ADMIN("ADMIN"), MODERATOR("MODERATOR"), USER("USER");
        private final String value;
    }

}