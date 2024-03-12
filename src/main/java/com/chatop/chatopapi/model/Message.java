package com.chatop.chatopapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
}
