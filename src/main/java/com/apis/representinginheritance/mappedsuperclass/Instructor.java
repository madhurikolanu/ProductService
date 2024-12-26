package com.apis.representinginheritance.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mp_instructor")
public class Instructor extends User {
    private int numberOfSessions;
}
