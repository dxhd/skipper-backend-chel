package com.tinkoff.skipper.utils;

import com.tinkoff.skipper.auth.JwtAuthentication;
import com.tinkoff.skipper.entity.RoleEntity;
import com.tinkoff.skipper.entity.UserEntity;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        //jwtInfoToken.setPhoneNumber(claims.get("phoneNumber", String.class));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    //FIXME: настроить получение ролей
    private static Set<RoleEntity> getRoles(Claims claims) {
        final List<RoleEntity> roles = claims.get("roles", List.class);
        return roles.stream()
                .collect(Collectors.toSet());
    }

 }
