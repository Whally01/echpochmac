package ru.itis.echpochmac.model;

import javax.persistence.*;

@Entity
@Table(name = "entities")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
