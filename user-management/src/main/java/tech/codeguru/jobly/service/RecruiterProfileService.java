package tech.codeguru.jobly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.codeguru.jobly.entity.RecruiterProfile;
import tech.codeguru.jobly.entity.UserProfile;
import tech.codeguru.jobly.entity.dto.request.RecruiterProfileDTO;
import tech.codeguru.jobly.repository.RecruiterProfileRepository;

import java.util.List;

@Service
public class RecruiterProfileService {

    @Autowired
    private RecruiterProfileRepository recruiterRepo;


    public RecruiterProfile createRecruiter(RecruiterProfileDTO recruiterProfileDTO) {
        if (recruiterRepo.findByEmail(recruiterProfileDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        RecruiterProfile recruiter = mapToEntity(recruiterProfileDTO);
        return recruiterRepo.save(recruiter);
    }


    public List<RecruiterProfile> getAllRecruiters() {
        return recruiterRepo.findAll();
    }


    public RecruiterProfile getRecruiterById(Long id) {
        return recruiterRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));
    }


    public RecruiterProfile updateRecruiter(Long id, RecruiterProfileDTO request) {
        RecruiterProfile existing = recruiterRepo.findByUser_Id(id);
        updateEntity(existing, request);
        return recruiterRepo.save(existing);
    }


    public void deleteRecruiter(Long id) {
        recruiterRepo.deleteById(id);
    }

    private RecruiterProfile mapToEntity(RecruiterProfileDTO req) {
        RecruiterProfile r = new RecruiterProfile();
        updateEntity(r, req);
        return r;
    }

    private void updateEntity(RecruiterProfile r, RecruiterProfileDTO req) {
        r.setFirstName(req.getFirstName());
        r.setLastName(req.getLastName());
        r.setEmail(req.getEmail());
        r.setPhone(req.getPhone());
        r.setProfilePictureUrl(req.getProfilePictureUrl());
        r.setLocation(req.getLocation());
        r.setJobTitle(req.getJobTitle());
        r.setCompanyName(req.getCompanyName());
        r.setCompanyLogoUrl(req.getCompanyLogoUrl());
        r.setCompanyWebsite(req.getCompanyWebsite());
        r.setIndustry(req.getIndustry());
        r.setYearsOfExperience(req.getYearsOfExperience());
        r.setBio(req.getBio());
        r.setActiveJobPostCount(req.getActiveJobPostCount());
        r.setLinkedInUrl(req.getLinkedInUrl());
        r.setTwitterUrl(req.getTwitterUrl());
        r.setIsVerified(req.getIsVerified());
    }
}
