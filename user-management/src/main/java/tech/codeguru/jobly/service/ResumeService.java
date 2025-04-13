package tech.codeguru.jobly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.codeguru.jobly.entity.Resume;
import tech.codeguru.jobly.entity.UserProfile;
import tech.codeguru.jobly.entity.dto.request.ResumeDTO;
import tech.codeguru.jobly.repository.ResumeRepository;
import tech.codeguru.jobly.repository.UserProfileRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class ResumeService {


    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;


    private ResumeDTO mapToDTO(Resume resume) {
        ResumeDTO dto = new ResumeDTO();
        dto.setFileName(resume.getFileName());
        dto.setFileType(resume.getFileType());
        dto.setFileData(resume.getFileData());
        dto.setUserProfileId(resume.getUserProfile().getId());
        return dto;
    }


    public Resume updateResume(Long Id, MultipartFile multipartFile) throws IOException {
        UserProfile userProfile = userProfileRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Resume resume = new Resume();
        resume.setFileName(multipartFile.getOriginalFilename());
        resume.setFileType(multipartFile.getContentType());
        resume.setFileData(multipartFile.getBytes());
        resume.setUserProfile(userProfile);
        resumeRepository.save(resume);
        return resume;
    }

    public ResumeDTO getResumeById(Long id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
        return mapToDTO(resume);
    }
}
