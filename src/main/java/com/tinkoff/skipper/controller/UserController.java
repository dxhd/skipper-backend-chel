package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.dto.UserProfileDto;
import com.tinkoff.skipper.dto.UserSettingsDto;
import com.tinkoff.skipper.dto.authDto.RegisterRequest;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.service.UserService;
import com.tinkoff.skipper.utils.SkipperResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/users", produces = "application/json")
@RequiredArgsConstructor
@CrossOrigin(origins={ "http://localhost:8080", "http://localhost:3000" })
public class UserController {

    private final UserService userService;
    @GetMapping("{id}/mentee_profile")
    public ResponseEntity<UserProfileDto> getMenteeUserInfo(@PathVariable Long id) {
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                userService.getProfileInfoById(id)
        );
    }

    @PostMapping("register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<String> registerNewUser(@RequestBody RegisterRequest newUser) {
            userService.registerNewUser(newUser);
            return SkipperResponseBuilder.buildResponse(
                    HttpStatus.CREATED,
                    "Пользователь зарегистрирован."
            );
    }

    @PreAuthorize("isAuthenticated() and (authentication.details == #id)")
    @PutMapping("{id}/settings")
    public ResponseEntity<String> updateUserInfo(
            @PathVariable("id")Long id,
            @RequestBody UserSettingsDto updatedInfo) {
        userService.updateUserInfo(id, updatedInfo);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                "Информация пользователя обновлена."
        );
    }

    @PreAuthorize("isAuthenticated() and (authentication.details == #id)")
    @PatchMapping(value = "{id}/settings", produces = "image/jpg")
    public ResponseEntity<String> updateUserImage(
            @PathVariable("id")Long id) {
        //userService.updateUserImage(id);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                "Изображение обновлено."
        );
    }

    @PreAuthorize("isAuthenticated() and (authentication.details == #id)")
    @DeleteMapping("{id}/settings")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UserEntity user) {
        userService.deleteUser(user);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.NO_CONTENT,
                "Пользователь успешно удален."
        );
    }

    @PreAuthorize("isAuthenticated() and (authentication.details == #id)")
    @PatchMapping(path = "{id}/favourites/{mentorId}")
    public ResponseEntity<String> deleteFromFavourites(
            @PathVariable("id") Long id, @PathVariable Long mentorId) {
        userService.deleteFavourite(id, mentorId);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                "Ментор удалён из избранного."
        );
    }
    @PreAuthorize("isAuthenticated() and (authentication.details == #id)")
    @PatchMapping(path = "{id}/favourites")
    public ResponseEntity<String> clearFavourites(
            @PathVariable("id") Long id) {
        userService.clearFavourites(id);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                "Список избранных менторов очищен.."
        );
    }


}
