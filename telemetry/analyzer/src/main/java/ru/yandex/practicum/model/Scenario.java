package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.checkerframework.common.aliasing.qual.Unique;

import java.util.logging.Level;

@Getter
@Setter
@Entity
@Table(name = "scenarios")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Scenario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Unique
    Long hudId;

    @ManyToOne
    @JoinTable(name = "scenario_conditions")
    String name;

    public Scenario(String hubId,String name) {
        this.hudId =(long) Integer.parseInt(hubId);
        this.name = name;

    }
}


