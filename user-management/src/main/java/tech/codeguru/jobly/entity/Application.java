package tech.codeguru.jobly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate appliedDate;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private UserProfile candidate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

}
