package tech.codeguru.jobly.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.codeguru.jobly.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByUser_Id(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE UserProfile u SET u.experience = :experience WHERE u.id = :id")
    Integer updateExperienceById(@Param("id") Long id, @Param("experience") Integer experience);
}
