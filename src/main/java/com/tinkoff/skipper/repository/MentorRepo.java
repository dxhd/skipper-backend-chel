package com.tinkoff.skipper.repository;

import com.tinkoff.skipper.dto.SearchResultMentorDto;
import com.tinkoff.skipper.entity.MentorInfoEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MentorRepo extends JpaRepository<MentorInfoEntity, Long> {
    // @Query("")
//    Optional<MentorInfoEntity> insert(MentorDTO dto);

    @Query(value = "select * from mentor_info where user_id = ?1", nativeQuery = true)
    Optional<MentorInfoEntity> findById(Long id);

    Optional<MentorInfoEntity> findByUserId(Long id);


    @Query(value = "select distinct " +
            "users.id, users.username, users.user_picture," +
            "mentor_info.speciality, mentor_info.description, mentor_info.rating, mentor_info.price " +
            "from mentor_info " +
            "join users on mentor_info.user_id = users.id " +
            "join mentor_tags on mentor_info.id = mentor_tags.mentor_id " +
            "where mentor_info.category = :category " +
            "and mentor_info.price between :minPrice and :maxPrice " +
            "and mentor_tags.tag in :tags",
            nativeQuery = true
    )
    Page<SearchResultMentorDto> findAllByFilters(String category,
                                                 Integer minPrice,
                                                 Integer maxPrice,
//                                                 Integer minRating,
//                                                 Integer maxRating,
                                                 String[] tags,
                                                 Pageable pageable
                                                 );
}
