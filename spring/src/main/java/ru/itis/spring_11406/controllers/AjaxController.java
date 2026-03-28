package ru.itis.spring_11406.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itis.spring_11406.dto.AjaxDto;

@Controller
public class AjaxController {
    @GetMapping("/ajax")
    public String getAjaxExample() {
        return "ajax_csrf_example";
    }

    @PostMapping("/ajax")
    public ResponseEntity<?> logAjax(@RequestBody AjaxDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok(dto);
    }
}
