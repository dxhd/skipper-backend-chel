package com.tinkoff.skipper.dto;

import lombok.Data;

@Data
public class SearchFiltersDto {

    private String category;
    private final Integer minPrice;
    private final Integer maxPrice;
    private final Integer minRating;
    private final Integer maxRating;
    private final String[] tags;

    //TODO: изменить реализацию SearchFiltersDto

    public SearchFiltersDto(
            String category,
            Integer minPrice,
            Integer maxPrice,
            Integer minRating,
            Integer maxRating,
            String[] tags) {

        this.category = category;
        if (minPrice == null) {
            this.minPrice = 0;
        }
        else {
            this.minPrice = minPrice;
        }

        if (maxPrice == null) {
            this.maxPrice = 99999999;
        }
        else {
            this.maxPrice = maxPrice;
        }

        if (minRating == null) {
            this.minRating = 0;
        }
        else {
            this.minRating = minRating;
        }

        if (maxRating == null) {
            this.maxRating = 5;
        }
        else {
            this.maxRating = maxRating;
        }

        if (tags == null) {
            this.tags = new String[]{};
        }
        else {
            this.tags = tags;
        }
    }
}
