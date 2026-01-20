package org.Icet.springtask.entity;

import jakarta.persistence.*;
import lombok.*;
import org.Icet.springtask.enums.UserTier;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private UserTier tier;

    private String email;
}
