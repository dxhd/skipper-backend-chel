package com.tinkoff.skipper.controller;

import com.tinkoff.skipper.dto.SearchFiltersDto;
import com.tinkoff.skipper.dto.SearchResultMentorDto;
import com.tinkoff.skipper.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j

@RestController
@RequestMapping(path = "api/search", produces = "application/json")
@RequiredArgsConstructor
@CrossOrigin(origins={ "http://localhost:8090", "http://localhost:3000" })
public class SearchController {

    private final SearchService searchService;


    @PostMapping("{category}")
    public Page<SearchResultMentorDto> searchWithFilters(@PathVariable String category,
                                                         @RequestBody SearchFiltersDto filters,
                                                         @PageableDefault(sort = { "price" }, direction = Sort.Direction.ASC) Pageable pageable) {
        filters.setCategory(category);
        //TODO: поиск по фильтрам, вернуть Pagination
        return searchService.findByFilters(filters, pageable);
    }

}
