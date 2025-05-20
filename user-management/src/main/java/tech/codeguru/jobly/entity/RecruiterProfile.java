package tech.codeguru.jobly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recruiters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecruiterProfile {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Info
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    private String profilePictureUrl;
    private String location;
    private Integer yearsOfExperience;
    @Column(length = 2000)
    private String bio;
    // Activity Info
    private Integer activeJobPostCount;
    // Social Links
    private String linkedInUrl;
    private Boolean isVerified = false;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")  // Foreign Key
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
