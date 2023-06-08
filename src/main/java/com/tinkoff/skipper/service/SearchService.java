package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.SearchFiltersDto;
import com.tinkoff.skipper.dto.SearchResultMentorDto;
import com.tinkoff.skipper.entity.CategoryEntity;
import com.tinkoff.skipper.exception.SkipperBadRequestException;
import com.tinkoff.skipper.repository.CategoryRepo;
import com.tinkoff.skipper.repository.MentorRepo;
import com.tinkoff.skipper.repository.TagRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final MentorRepo mentorRepo;
    private final TagRepo tagRepo;
    private final CategoryRepo categoryRepo;

    public Page<SearchResultMentorDto> findByFilters(SearchFiltersDto filters, Pageable pageable) {

        if (filters.getTags().length == 0) {
            return mentorRepo.findAllByFiltersWithoutTags(
                    filters.getCategory(),
//                    filters.getMinPrice(),
//                    filters.getMaxPrice(),
//                filters.getMinRating(),
//                filters.getMaxRating(),
                    pageable
            );
        }

        return mentorRepo.findAllByFilters(
                filters.getCategory(),
//                filters.getMinPrice(),
//                filters.getMaxPrice(),
//                filters.getMinRating(),
//                filters.getMaxRating(),
                filters.getTags(),
                pageable
        );
    }


    public Collection<String> getTagsByCategory(String category) {
        CategoryEntity categoryEntity = categoryRepo.findByName(category).orElseThrow(() -> new SkipperBadRequestException("None."));
        return tagRepo.findAllByCategoryId(categoryEntity.getId());
    }

}
