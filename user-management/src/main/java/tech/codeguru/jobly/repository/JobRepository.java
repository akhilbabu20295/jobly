package tech.codeguru.jobly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.codeguru.jobly.entity.Job;
import tech.codeguru.jobly.entity.UserProfile;

public interface JobRepository extends JpaRepository<Job,Long> {

}
