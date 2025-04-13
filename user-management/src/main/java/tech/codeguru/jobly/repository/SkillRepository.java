package tech.codeguru.jobly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.codeguru.jobly.entity.Skills;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skills,Long> {
    Optional<Skills> findByName(String name);
}
