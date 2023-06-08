package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.dto.MentorSettingsDto;
import com.tinkoff.skipper.dto.UserSettingsDto;
import com.tinkoff.skipper.dto.authDto.RegisterRequest;
import com.tinkoff.skipper.entity.CategoryEntity;
import com.tinkoff.skipper.entity.NotificationEntity;
import com.tinkoff.skipper.entity.UserEntity;
import com.tinkoff.skipper.service.MentorService;
import com.tinkoff.skipper.service.UserService;
import com.tinkoff.skipper.utils.SkipperResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "api/admin", produces = "application/json")
@RequiredArgsConstructor
@CrossOrigin(origins={ "http://localhost:8080", "http://localhost:3000" })
public class AdminController { //FIXME: исправить проблему с проверкой ролей

    private final UserService userService;
    private final MentorService mentorService;

    //Получение информации о пользователях
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("users")
    public Page<UserEntity> getAllUsers() {
        return (Page<UserEntity>) SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                userService.getAll()
        ); //todo: вернуть всех в пагинации
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("users/{id}")
    public ResponseEntity<UserEntity> getAllUserInfo(@PathVariable Long id) {
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                userService.getById(id)
        );
    }

    //Редактирование категорий
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("categories")
    public Page<CategoryEntity> getAllCategories() {
        return (Page<CategoryEntity>) SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                categoryService.getAll()
        ); //todo: вернуть всех в пагинации
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("categories/add")
    public ResponseEntity<String> addCategory(@RequestBody CategoryDto category) {
        categoryService.createCategory(category);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.CREATED,
                "Категория добавлена."
        );
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("categories/{id}/edit")
    public ResponseEntity<String> editCategory(@PathVariable Long id, @RequestBody CategoryDto category) {
        categoryService.updateCategory(id, category);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.CREATED,
                "Категория изменена."
        );
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("categories/{id}/delete")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.CREATED,
                "Категория удалена."
        );
    }

    //Редактирование тегов
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("categories/{categoryId}/tags")
    public Page<CategoryEntity> getAllTagsByCategory(@PathVariable Long categoryId) {
        return (Page<CategoryEntity>) SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                tagService.getAllByCategory(categoryId)
        ); //todo: вернуть всех в пагинации
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("categories/{categoryId}/tags/add")
    public ResponseEntity<String> addTag(@PathVariable Long categoryId, @RequestBody TagDto tag) {
        tagService.createTag(categoryId, tag);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.CREATED,
                "Тег создан."
        );
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("categories/{categoryId}/tags/{id}/edit")
    public ResponseEntity<String> editTag(@PathVariable Long categoryId, @PathVariable Long id, @RequestBody TagDto tag) {
        tagService.updateTag(categoryId, id, tag);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.CREATED,
                "Тег изменена."
        );
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("categories/{categoryId}/tags/{id}/delete")
    public ResponseEntity<String> deleteTag(@PathVariable Long categoryId, @PathVariable Long id) {
        tagService.deleteTag(categoryId, id);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.CREATED,
                "Тег удалена."
        );
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("notifications")
    public Page<NotificationEntity> getAdminNotifications() {

    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("notifications/all")
    public Page<NotificationEntity> getAllAdminNotifications() {

    }


    //Подтверждение данных ментора
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> applyCreationMentor(
            @RequestBody MentorSettingsDto newMentor) {
        mentorService.save(newMentor);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.CREATED,
                "Информация о менторе успешно добавлена."
        );
    }



}
