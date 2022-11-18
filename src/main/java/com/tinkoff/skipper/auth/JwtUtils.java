package com.tinkoff.skipper.auth;

import com.tinkoff.skipper.entity.RoleEntity;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setPhoneNumber(claims.get("phoneNumber", String.class));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    //FIXME: настроить получение ролей
    private static Set<RoleEntity> getRoles(Claims claims) {
        final List<String> roles = claims.get("roles", List.class);
//        return roles.stream()
//                .map(RoleEntity::valueOf)
//                .collect(Collectors.toSet());
        return null;
    }

 }
