//package com.tinkoff.skipper.utils;
//
//import com.tinkoff.skipper.entity.CategoryEntity;
//import com.tinkoff.skipper.entity.MentorInfoEntity;
//import com.tinkoff.skipper.entity.TagEntity;
//import com.tinkoff.skipper.entity.UserEntity;
//import com.tinkoff.skipper.exception.SkipperBadRequestException;
//import com.tinkoff.skipper.repository.CategoryRepo;
//import com.tinkoff.skipper.repository.TagRepo;
//import com.tinkoff.skipper.repository.UserRepo;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.util.HashSet;
//import java.util.Set;
//
////@RequiredArgsConstructor
//public class MentorInfoBuilder {
//
//    @Autowired
//    private UserRepo userRepo;
//    @Autowired
//    private CategoryRepo categoryRepo;
//    @Autowired
//    private TagRepo tagRepo;
//
//    private UserEntity user;
//    private BigDecimal price;
//    private String description;
//    private Float rating;
//    private String workExperience;
//    private String certificates;
//    private String education;
//    private CategoryEntity speciality;
//    private Set<TagEntity> tags = new HashSet<>();
//    private Integer studentNumber;
//
//    public MentorInfoBuilder user(Long userId) {
//        this.user = userRepo.findById(userId).orElseThrow(
//                () -> new SkipperBadRequestException("Невалидный пользователь."));
//        return this;
//    }
//
//    public MentorInfoBuilder price(BigDecimal price) {
//        this.price = price;
//        return this;
//    }
//
//    public MentorInfoBuilder description(String description) {
//        this.description = description;
//        return this;
//    }
//
//    public MentorInfoBuilder rating(Float rating) {
//        this.rating = rating;
//        return this;
//    }
//
//    public MentorInfoBuilder workExperience(String workExperience) {
//        this.workExperience = workExperience;
//        return this;
//    }
//
//    public MentorInfoBuilder certificates(String certificates) {
//        this.certificates = certificates;
//        return this;
//    }
//
//    public MentorInfoBuilder education(String education) {
//        this.education = education;
//        return this;
//    }
//
//    public MentorInfoBuilder speciality(String speciality) {
//        this.speciality = categoryRepo.findByName(speciality).orElseThrow(
//                () -> new SkipperBadRequestException("Такой категории не существует.")
//        );
//        return this;
//    }
//
//    public MentorInfoBuilder speciality(Set<String> tags) {
//
//        for (String tag :
//                tags) {
//            this.tags.add(
//                    tagRepo.findByName(tag).orElseThrow(
//                            () -> new SkipperBadRequestException("Такого тега не существует.")
//                    )
//            );
//        }
//        return this;
//    }
//
//    public MentorInfoEntity build() {
//        return new MentorInfoEntity(user, )
//    }
//
//}
