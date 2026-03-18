package ru.itis.spring_11406.services;

import ru.itis.spring_11406.dto.ServiceDto;
import ru.itis.spring_11406.dto.ServiceForm;

import java.util.List;

public interface ServiceService {
    ServiceDto addService(ServiceForm serviceForm);
    List<ServiceDto> search (Integer size, Integer page, String query, String sort, String direction);
}
