package ru.itis.spring_11406.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.spring_11406.dto.ServiceDto;
import ru.itis.spring_11406.dto.ServiceForm;
import ru.itis.spring_11406.services.ServiceService;

import java.util.List;

@Controller
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @PostMapping("/service")
    @ResponseBody
    public ResponseEntity<ServiceDto> addService(@RequestBody ServiceForm form) {
        return ResponseEntity.ok(serviceService.addService(form));
    }

    //localhost:8080/papers/service/search?page=0&size=3
    @GetMapping("/papers/service/search")
    @ResponseBody
    public ResponseEntity<List<ServiceDto>> search(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "direction", required = false) String direction
    ) {
        return ResponseEntity.ok(serviceService.search(page, size, query, sort, direction));
    }
}
