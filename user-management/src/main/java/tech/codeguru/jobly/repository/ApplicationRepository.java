package tech.codeguru.jobly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.codeguru.jobly.entity.Application;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
    List<Application> findByCandidateId(Long candidateId);
}
