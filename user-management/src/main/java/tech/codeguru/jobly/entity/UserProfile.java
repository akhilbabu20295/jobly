package tech.codeguru.jobly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;
    private String firstName;
    private String lastName;
    private String designation;
    private String email;
    private String address;
    private String location;
    private String mobile;
    private String dateOfBirth;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")  // Foreign Key
    private User user;


}
