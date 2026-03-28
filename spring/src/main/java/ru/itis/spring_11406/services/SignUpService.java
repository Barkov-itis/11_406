package ru.itis.spring_11406.services;

import freemarker.template.TemplateException;
import ru.itis.spring_11406.dto.UserForm;

import java.io.IOException;

public interface SignUpService {
    public void addUser(UserForm userForm) throws TemplateException, IOException;
}
