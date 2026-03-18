package ru.itis.spring_11406.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceForm {
    private String name;
    private String cost;
    private String date;
}
