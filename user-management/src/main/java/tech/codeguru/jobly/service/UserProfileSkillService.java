package tech.codeguru.jobly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.codeguru.jobly.entity.Skills;
import tech.codeguru.jobly.entity.UserProfile;
import tech.codeguru.jobly.entity.UserProfileSkill;
import tech.codeguru.jobly.entity.dto.request.UserProfileSkillDTO;
import tech.codeguru.jobly.repository.SkillRepository;
import tech.codeguru.jobly.repository.UserProfileRepository;
import tech.codeguru.jobly.repository.UserProfileSkillRepository;

@Service
public class UserProfileSkillService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserProfileSkillRepository userProfileSkillRepository;


    @Autowired
    private SkillRepository skillRepository;

    public UserProfileSkill addSkillToUserProfile(UserProfileSkillDTO userProfileSkillDTO) {

        UserProfile userProfile = userProfileRepository.findById(userProfileSkillDTO.getUserProfileId())
                .orElseThrow(() -> new RuntimeException("User profile not found"));

        Skills skill = skillRepository.findById(userProfileSkillDTO.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        UserProfileSkill userProfileSkill = new UserProfileSkill();
        userProfileSkill.setUserProfile(userProfile);
        userProfileSkill.setSkill(skill);
        userProfileSkill.setLevel(userProfileSkillDTO.getLevel());
        userProfileSkill.setYearsOfExperience(userProfileSkillDTO.getYearsOfExperience());
        return userProfileSkillRepository.save(userProfileSkill);
    }

    public void deleteUserProfileSkillById(Long id) {
        if (!userProfileSkillRepository.existsById(id)) {
            throw new RuntimeException("UserProfileSkill not found");
        }
        userProfileSkillRepository.deleteById(id);
    }
}
