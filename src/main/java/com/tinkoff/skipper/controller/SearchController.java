package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.dto.SearchFiltersDto;
import com.tinkoff.skipper.dto.SearchResultMentorDto;
import com.tinkoff.skipper.service.SearchService;
import com.tinkoff.skipper.utils.SkipperResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j

@RestController
@RequestMapping(path = "api/search", produces = "application/json")
@RequiredArgsConstructor
@CrossOrigin(origins={ "http://localhost:8080", "http://localhost:3000" })
public class SearchController {

    private final SearchService searchService;

    @GetMapping("{category}")
    public ResponseEntity<?> getTagsByCategory(@PathVariable String category) {
        return SkipperResponseBuilder
                .buildResponse(HttpStatus.OK, searchService.getTagsByCategory(category));
    }

    @PostMapping("{category}")
    @PageableAsQueryParam
    public Page<SearchResultMentorDto> searchWithFilters(@PathVariable String category,
                                                         @RequestBody SearchFiltersDto filters,/*
                                                      @PageableDefault(size = 12, page = 0, sort = { "price" }, direction = Sort.Direction.ASC) */Pageable pageable) {
        filters.setCategory(category);
        //TODO: поиск по фильтрам, вернуть Pagination
        return searchService.findByFilters(filters, pageable);
    }

}
