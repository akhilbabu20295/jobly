package tech.codeguru.jobly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.codeguru.jobly.entity.Job;

public interface JobRepository extends JpaRepository<Job,Long> {
}
