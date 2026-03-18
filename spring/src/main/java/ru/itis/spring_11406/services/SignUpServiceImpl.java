package ru.itis.spring_11406.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.spring_11406.dto.UserForm;
import ru.itis.spring_11406.models.Role;
import ru.itis.spring_11406.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.spring_11406.repository.UsersRepository;

@Component
public class SignUpServiceImpl implements SignUpService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void addUser(UserForm userForm) {
        User user = User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .role(Role.ADMIN)
                .password(passwordEncoder.encode(userForm.getPassword()))
                .confirmed("CONFIRMED")
                .build();
        usersRepository.save(user);
    }
}
