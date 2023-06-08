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
            "u.id, u.username, u.user_pictureurl," +
            "mi.speciality, mi.description " +
            "from mentor_info mi " +
            "join users u on mi.user_id = u.id " +
            "join mentor_tags mt on mi.id = mt.mentor_id " +
            "where mi.category = :category " +
            //"and mi.price between :minPrice and :maxPrice " +
            "and mt.tag_id in (select t.id from tags t where t.name in :tags)",
            nativeQuery = true
    )
    Page<SearchResultMentorDto> findAllByFilters(String category,
                                                 //Integer minPrice,
                                                // Integer maxPrice,
//                                                 Integer minRating,
//                                                 Integer maxRating,
                                                 String[] tags,
                                                 Pageable pageable
                                                 );
    @Query(value = "select distinct " +
            "u.id, u.username, u.user_pictureurl," +
            "mi.speciality, mi.description " +
            "from mentor_info mi " +
            "join users u on mi.user_id = u.id " +
            "join mentor_tags mt on mi.id = mt.mentor_id " +
            "where mi.category = :category " /*+
            "and mi.price between :minPrice and :maxPrice "*/,
            nativeQuery = true
    )
    Page<SearchResultMentorDto> findAllByFiltersWithoutTags(String category,
                                                // Integer minPrice,
                                                 //Integer maxPrice,
//                                                 Integer minRating,
//                                                 Integer maxRating,
                                                 Pageable pageable
                                                 );
}
