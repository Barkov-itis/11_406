package ru.itis.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    private Long id;
    private String originalFileName;
    private String storageFileName;
    private Long size;
    private String type;
}
