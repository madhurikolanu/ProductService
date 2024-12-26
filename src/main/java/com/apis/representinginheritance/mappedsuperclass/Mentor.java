package com.apis.representinginheritance.mappedsuperclass;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mp_mentor")
public class Mentor extends User {
    private double mentorRating;
}
