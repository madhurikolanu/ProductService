package com.apis.representinginheritance.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tp_ta")
public class TA extends User {
    private int numberOfSessions;
    private double avgrating;
}
