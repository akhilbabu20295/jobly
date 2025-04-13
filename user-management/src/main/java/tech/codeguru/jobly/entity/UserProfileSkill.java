package tech.codeguru.jobly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table(name = "user_profile_skills")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skills skill;
    private String level; // e.g., Beginner, Intermediate, Expert
    private Integer yearsOfExperience;
}
