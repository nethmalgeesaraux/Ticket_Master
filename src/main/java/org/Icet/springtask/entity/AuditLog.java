package org.Icet.springtask.entity;

import jakarta.persistence.*;
import lombok.*;
import org.Icet.springtask.enums.AuditAction;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "audit_logs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuditAction action;

    @Column(name = "user_id")
    private Long userId;

    private String details;

    private LocalDateTime timestamp;
}
