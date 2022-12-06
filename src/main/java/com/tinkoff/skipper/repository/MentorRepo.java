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


    @Query(value = "select * from mentor_info where " +
            "category = 'programming' " +
            "price between 0 and 9999999",
            nativeQuery = true
    )
    Page<SearchResultMentorDto> findAllByFilters(String category,
                                                 Integer minPrice,
                                                 Integer maxPrice,
                                                 Integer minRating,
                                                 Integer maxRating,
                                                 String[] tags,
                                                 Pageable pageable
                                                 );

}
