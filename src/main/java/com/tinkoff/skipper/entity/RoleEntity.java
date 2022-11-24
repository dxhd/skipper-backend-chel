package com.tinkoff.skipper.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Roles")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED, force=true)
public class RoleEntity {

    @Id
    @Enumerated(EnumType.STRING)
    private Role role;



    @RequiredArgsConstructor
    public enum Role implements GrantedAuthority {
        ADMIN("ADMIN"), MODERATOR("MODERATOR"), USER("USER");
        private final String value;

        @Override
        public String getAuthority() {
            return value;
        }

    }
}