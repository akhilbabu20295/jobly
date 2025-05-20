package tech.codeguru.jobly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.codeguru.jobly.entity.Job;
import tech.codeguru.jobly.entity.Shortlist;
import tech.codeguru.jobly.entity.UserProfile;
import tech.codeguru.jobly.exception.ResourceNotFoundException;
import tech.codeguru.jobly.repository.JobRepository;
import tech.codeguru.jobly.repository.ShortlistRepository;
import tech.codeguru.jobly.repository.UserProfileRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ShortlistService {

    @Autowired
    private ShortlistRepository shortlistRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private JobRepository jobRepository;

    public Shortlist shortlistCandidate(Long candidateId, Long jobId) {
        UserProfile userProfile = userProfileRepository.findByUser_Id(candidateId);
        Optional<Job> job = jobRepository.findById(jobId);


        Shortlist shortlist = new Shortlist();
        shortlist.setCandidate(userProfile);
        shortlist.setJob(jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + jobId)));
        shortlist.setShortlistedDate(LocalDate.now());

        return shortlistRepository.save(shortlist);
    }

    public List<Shortlist> getShortlistedCandidatesByJob(Long jobId) {
        return shortlistRepository.findByJobId(jobId);
    }

    public List<Shortlist> getShortlistedJobsByCandidate(Long candidateId) {
        return shortlistRepository.findByCandidateId(candidateId);
    }

    public void removeShortlistedCandidate(Long candidateId, Long jobId) {
        Shortlist shortlist = shortlistRepository.findByCandidateIdAndJobId(candidateId, jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Shortlist entry not found"));
        shortlistRepository.delete(shortlist);
    }
}
