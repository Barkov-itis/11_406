package ru.itis.spring_11406.repository;

import ru.itis.spring_11406.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
}
