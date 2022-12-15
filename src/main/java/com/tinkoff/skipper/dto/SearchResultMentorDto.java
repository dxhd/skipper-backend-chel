package com.tinkoff.skipper.dto;

import java.math.BigDecimal;

public interface SearchResultMentorDto {
    public Long getId(); 
    public String getUsername();
    public String getUserPicture(); 
    public String getSpeciality(); 
    public String getDescription();
    public Float getRating();
    public BigDecimal getPrice(); 
}