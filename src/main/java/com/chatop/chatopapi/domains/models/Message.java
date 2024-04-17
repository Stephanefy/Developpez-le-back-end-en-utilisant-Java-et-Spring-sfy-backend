package com.chatop.chatopapi.domains.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental_id;


    public void setUserId(User user_id) {
        this.user_id = user_id;
    }

    public void setRentalId(Rental rental_id) {
        this.rental_id = rental_id;
    }

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
