package tech.codeguru.jobly.service;

import org.springframework.stereotype.Service;
import tech.codeguru.jobly.entity.*;
import tech.codeguru.jobly.exception.ResourceNotFoundException;
import tech.codeguru.jobly.repository.ApplicationRepository;
import tech.codeguru.jobly.repository.JobRepository;
import tech.codeguru.jobly.repository.ResumeRepository;
import tech.codeguru.jobly.repository.UserProfileRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserProfileRepository candidateRepository;
    private final JobRepository jobRepository;
    private final ResumeRepository resumeRepository;
    // private final CoverLetterRepository coverLetterRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              UserProfileRepository candidateRepository,
                              JobRepository jobRepository,
                              ResumeRepository resumeRepository) {
        this.applicationRepository = applicationRepository;
        this.candidateRepository = candidateRepository;
        this.jobRepository = jobRepository;
        this.resumeRepository = resumeRepository;
    }

    public Application applyToJob(Long candidateId, Long jobId, Long resumeId, Long coverLetterId) {
        UserProfile candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id: " + candidateId));
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + jobId));

        Resume resume = null;
        if (resumeId != null) {
            resume = resumeRepository.findById(resumeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Resume not found with id: " + resumeId));
        }

        Application application = new Application();
        application.setCandidate(candidate);
        application.setResume(resume);
        application.setJob(job);
        application.setAppliedDate(LocalDate.now());
        application.setStatus(ApplicationStatus.APPLIED);
        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByCandidate(Long candidateId) {
        return applicationRepository.findByCandidateId(candidateId);
    }

    public Application getApplicationById(Long applicationId) {
        return applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + applicationId));
    }

    public void withdrawApplication(Long applicationId) {
        Application application = getApplicationById(applicationId);
        applicationRepository.delete(application);
    }

    public Application updateApplicationStatus(Long applicationId, ApplicationStatus status) {
        Application application = getApplicationById(applicationId);
        application.setStatus(status);
        return applicationRepository.save(application);
    }
}
