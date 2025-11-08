package ru.itis.services;

import ru.itis.dto.AjaxDto;
import ru.itis.models.User;
import ru.itis.repository.UsersRepository;

import java.sql.SQLException;
import java.util.List;

public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;


    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return usersRepository.findAll();
    }

    @Override
    public void addUser(AjaxDto form) throws SQLException {
        System.out.println(form.getFirstName());
        User user = User.builder()
                .name(form.getFirstName())
                .password("qwerty007")
                .surname(form.getLastName())
                .login(form.getEmail())
                .build();
        usersRepository.save(user);
    }
}
