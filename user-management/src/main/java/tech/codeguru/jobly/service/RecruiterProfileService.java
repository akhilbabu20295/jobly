package tech.codeguru.jobly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.codeguru.jobly.entity.*;
import tech.codeguru.jobly.entity.dto.request.RecruiterProfileDTO;
import tech.codeguru.jobly.repository.CompanyRepository;
import tech.codeguru.jobly.repository.RecruiterProfileRepository;
import tech.codeguru.jobly.repository.UserRepository;

import java.util.List;

@Service
public class RecruiterProfileService {

    @Autowired
    private RecruiterProfileRepository recruiterRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RecruiterProfile createRecruiter(RecruiterProfileDTO recruiterProfileDTO) {
        if (recruiterRepo.findByEmail(recruiterProfileDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(recruiterProfileDTO.getEmail());
        user.setPassword(passwordEncoder.encode(recruiterProfileDTO.getPassword()));
        user.setRole(Role.RECRUITER);
        userRepository.save(user);

        // Fetch Company
        Company company = companyRepository.findById(recruiterProfileDTO.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + recruiterProfileDTO.getCompanyId()));

        RecruiterProfile recruiter = mapToEntity(recruiterProfileDTO);
        recruiter.setCompany(company);
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
        r.setYearsOfExperience(req.getYearsOfExperience());
        r.setBio(req.getBio());
        r.setActiveJobPostCount(req.getActiveJobPostCount());
        r.setLinkedInUrl(req.getLinkedInUrl());
        r.setIsVerified(req.getIsVerified());
    }
}
