package com.tinkoff.skipper.utils;

import com.tinkoff.skipper.auth.JwtAuthentication;
import com.tinkoff.skipper.entity.RoleEntity;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<RoleEntity.Role> getRoles(Claims claims) {
        Set<RoleEntity.Role> roles = Arrays.stream(claims.get("roles", String.class)
                .split(","))
                .map(RoleEntity.Role::valueOf)
                .collect(Collectors.toSet());
        return roles;
    }
 }
