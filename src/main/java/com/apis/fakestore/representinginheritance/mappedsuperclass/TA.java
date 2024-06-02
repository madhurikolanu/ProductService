package com.apis.fakestore.representinginheritance.mappedsuperclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mp_ta")
public class TA extends User {
    private int numberOfSessions;
    private double avgrating;
}
