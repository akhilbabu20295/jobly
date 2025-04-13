package tech.codeguru.jobly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String location;
    private Double salary;
    private String experienceLevel;
    private String description;
    private LocalDate postedDate;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private RecruiterProfile recruiterProfile;
}
