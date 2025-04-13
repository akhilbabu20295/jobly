package tech.codeguru.jobly.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterProfileDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profilePictureUrl;
    private String location;
    private String jobTitle;
    private String companyName;
    private String companyLogoUrl;
    private String companyWebsite;
    private String industry;
    private Integer yearsOfExperience;
    private String bio;
    private Integer activeJobPostCount;
    private String linkedInUrl;
    private String twitterUrl;
    private Boolean isVerified;
}
