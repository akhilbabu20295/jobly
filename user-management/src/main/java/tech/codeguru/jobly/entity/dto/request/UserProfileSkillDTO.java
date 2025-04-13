package tech.codeguru.jobly.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileSkillDTO {
    private Long userProfileId;
    private Long skillId;
    private String level;
    private Integer yearsOfExperience;

}
