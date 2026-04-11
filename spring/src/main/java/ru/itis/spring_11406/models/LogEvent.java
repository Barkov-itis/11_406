package ru.itis.spring_11406.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long logEventId;

    private String text;
    private String method;

    @Column(updatable = false, nullable = false)
    private Data createdAt;

    @OneToOne(mappedBy = "name")
    private Type type;
}
