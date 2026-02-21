package org.example.nimbus200.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class User extends BaseClass{
    private String firstName;
    private String lastName;
    private String email;

}
