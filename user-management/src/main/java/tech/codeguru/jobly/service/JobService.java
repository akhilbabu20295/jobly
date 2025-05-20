package tech.codeguru.jobly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.codeguru.jobly.entity.Job;
import tech.codeguru.jobly.entity.RecruiterProfile;
import tech.codeguru.jobly.repository.JobRepository;
import tech.codeguru.jobly.repository.RecruiterProfileRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private RecruiterProfileRepository recruiterProfileRepository;

    public Job postJob(Job job, Long recruiterId) {
        RecruiterProfile recruiter = recruiterProfileRepository.findById(recruiterId)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));
        job.setRecruiterProfile(recruiter);
        job.setPostedDate(LocalDate.now());
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
