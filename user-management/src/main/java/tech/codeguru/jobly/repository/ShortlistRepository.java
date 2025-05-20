package tech.codeguru.jobly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.codeguru.jobly.entity.Shortlist;

import java.util.List;
import java.util.Optional;

public interface ShortlistRepository extends JpaRepository<Shortlist,Long> {
    List<Shortlist> findByJobId(Long jobId);
    List<Shortlist> findByCandidateId(Long candidateId);
    Optional<Shortlist> findByCandidateIdAndJobId(Long candidateId, Long jobId);
}
