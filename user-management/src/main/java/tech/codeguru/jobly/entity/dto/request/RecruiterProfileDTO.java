package tech.codeguru.jobly.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterProfileDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String profilePictureUrl;
    private String location;
    private String jobTitle;
    private Integer yearsOfExperience;
    private String bio;
    private Integer activeJobPostCount;
    private String linkedInUrl;
    private String twitterUrl;
    private Boolean isVerified;
    private Long companyId;
}
