package ru.itis.services;

import ru.itis.models.FileInfo;
import ru.itis.repository.FilesRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.UUID;

public class FileServiceImpl implements FilesService {

    private FilesRepository filesRepository;

    public FileServiceImpl(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @Override
    public void saveFileToStorage(InputStream file, String orinalFileName, String contentType, Long size) {
        FileInfo fileInfo = FileInfo.builder()
                .originalFileName(orinalFileName)
                .storageFileName(UUID.randomUUID().toString())
                .type(contentType)
                .size(size)
                .build();

        try {
            Files.copy(file, Paths.get("C://files/" + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
            filesRepository.save(fileInfo);
        } catch (IOException e) {
            throw new IllegalStateException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void writeFileFromStorage(Long fileId, OutputStream outputStream) {
        // нашли файл в базе (инфа о файле)
        FileInfo fileInfo = filesRepository.findById(fileId);
        // нашли файл на серваке
        File file = new File("C://files/" + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]);
        try {
            // записали файл в ответ
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public FileInfo getFileInfo(Long fileId) {
        return filesRepository.findById(fileId);
    }
}
