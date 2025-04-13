package tech.codeguru.jobly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.codeguru.jobly.entity.UserProfileSkill;

public interface UserProfileSkillRepository extends JpaRepository<UserProfileSkill,Long> {
}
