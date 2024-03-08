package com.chatop.chatopapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
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

    private List<String> picture;

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
        updatedAt = LocalDateTime.now(); // Set updatedAt on create to ensure it has an initial value
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now(); // Automatically update the timestamp when the entity is updated
    }


    // Getters and Setters
}
