package com.tinkoff.skipper.utils;

import com.tinkoff.skipper.dto.authDto.JwtAuthentication;
import com.tinkoff.skipper.entity.RoleEntity;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setUsername(claims.getSubject());
        jwtInfoToken.setUserId(claims.get("userId", Long.class));
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
