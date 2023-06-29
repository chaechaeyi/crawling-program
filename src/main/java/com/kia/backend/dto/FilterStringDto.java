package com.kia.backend.dto;

import lombok.Getter;

@Getter
public class FilterStringDto {
    private int status;
    private String merge;

    public FilterStringDto(int status, String merge){
        this.status = status;
        this.merge  = merge;
    }
}
