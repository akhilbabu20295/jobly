package tech.codeguru.jobly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.codeguru.jobly.entity.Resume;
import tech.codeguru.jobly.entity.UserProfile;

public interface ResumeRepository extends JpaRepository<Resume,Long> {
}
