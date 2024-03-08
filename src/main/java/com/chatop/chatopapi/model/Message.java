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

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private Rental rental_id;
}
