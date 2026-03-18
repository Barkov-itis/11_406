package ru.itis.spring_11406.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.spring_11406.models.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Query("select service " +
            "from Service service " +
            "where(:q = 'empty' or UPPER(service.name) like upper(concat('%', :q, '%')))")
    Page<Service> search(@Param("q") String q, Pageable pageable);
}
