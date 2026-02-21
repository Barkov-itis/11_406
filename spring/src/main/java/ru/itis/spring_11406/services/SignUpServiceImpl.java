package ru.itis.spring_11406.services;

import ru.itis.spring_11406.dto.UserForm;
import ru.itis.spring_11406.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.spring_11406.repository.UsersRepository;

@Component
public class SignUpServiceImpl implements SignUpService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void addUser(UserForm userForm) {
        User user = User.builder()
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .build();
        usersRepository.save(user);
    }
}
