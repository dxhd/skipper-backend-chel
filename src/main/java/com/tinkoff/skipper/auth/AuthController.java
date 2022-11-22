package com.tinkoff.skipper.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
@CrossOrigin(origins="http://localhost:8090")
public class AuthController {

    private final AuthService authService;

    //TODO: обработать исключение
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody JwtRequest authRequest) throws AuthException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("token")
    public ResponseEntity<?> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("refresh")
    public ResponseEntity<?> getNewRefreshToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }


}
