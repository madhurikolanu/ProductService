package com.apis.fakestore.representinginheritance.tableperclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tp_instructor")
public class Instructor extends User {
    private int numberOfSessions;
}
