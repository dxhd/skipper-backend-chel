package com.tinkoff.skipper.service;

import com.tinkoff.skipper.dto.SearchFiltersDto;
import com.tinkoff.skipper.dto.SearchResultMentorDto;
import com.tinkoff.skipper.repository.MentorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final MentorRepo mentorRepo;
    public Page<SearchResultMentorDto> findByFilters(SearchFiltersDto filters, Pageable pageable) {
        return mentorRepo.findAllByFilters(
                filters.getCategory(),
                filters.getMinPrice(),
                filters.getMaxPrice(),
                filters.getMinRating(),
                filters.getMaxRating(),
                filters.getTags(),
                pageable
        );
    }
}
