package tech.codeguru.jobly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.codeguru.jobly.entity.RecruiterProfile;
import tech.codeguru.jobly.entity.UserProfile;

import java.util.Optional;

public interface RecruiterProfileRepository extends JpaRepository<RecruiterProfile,Long> {
    Optional<RecruiterProfile> findByEmail(String email);
    RecruiterProfile findByUser_Id(Long userId);
}
