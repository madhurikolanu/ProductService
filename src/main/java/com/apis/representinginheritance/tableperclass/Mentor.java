package com.apis.representinginheritance.tableperclass;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tp_mentor")
public class Mentor extends User {
    private double mentorRating;
}
