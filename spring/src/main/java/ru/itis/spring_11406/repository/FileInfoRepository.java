package ru.itis.spring_11406.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.spring_11406.models.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    FileInfo findByStorageFileName (String fileName);
}
