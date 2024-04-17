package com.chatop.chatopapi.domains.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int surface;

    private double price;

    private String picture;

    @Column(length = 1024) // To allow for a longer description
    private String description;

    @ManyToOne // Correct the relationship annotation
    @JoinColumn(name = "owner_id") // Correct the way to specify the foreign key column
    private User owner;

    @OneToMany(mappedBy = "rental_id")
    private List<Message> messages;

    @Column(name = "created_at") // Ensure the column name follows a consistent naming convention
    private LocalDateTime createdAt;

    @Column(name = "updated_at") // Ensure the column name follows a consistent naming convention
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
