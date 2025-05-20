package tech.codeguru.jobly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.codeguru.jobly.entity.Job;
import tech.codeguru.jobly.entity.RecruiterProfile;
import tech.codeguru.jobly.entity.dto.request.RecruiterProfileDTO;
import tech.codeguru.jobly.service.JobService;
import tech.codeguru.jobly.service.RecruiterProfileService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recruiters")
public class RecruiterController {

    @Autowired
    private RecruiterProfileService recruiterService;

    @Autowired
    private JobService jobService;

    @PostMapping(value = "/registration")
    public ResponseEntity<?> createRecruiter(@RequestBody RecruiterProfileDTO request) {
        RecruiterProfile recruiter = recruiterService.createRecruiter(request);
        return ResponseEntity.ok("Recruiter created with ID: " + recruiter.getId());
    }

    @GetMapping
    public ResponseEntity<List<RecruiterProfile>> getAllRecruiters() {
        return ResponseEntity.ok(recruiterService.getAllRecruiters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecruiterProfile> getRecruiterById(@PathVariable Long id) {
        return ResponseEntity.ok(recruiterService.getRecruiterById(id));
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<?> updateRecruiter(@PathVariable Long id, @RequestBody RecruiterProfileDTO request) {
        RecruiterProfile updated = recruiterService.updateRecruiter(id, request);
        return ResponseEntity.ok("Recruiter updated: " + updated.getFirstName() + " " + updated.getLastName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecruiter(@PathVariable Long id) {
        recruiterService.deleteRecruiter(id);
        return ResponseEntity.ok("Recruiter deleted with ID: " + id);
    }

    @PostMapping("/jobs/{recruiterId}")
    public ResponseEntity<Job> postJob(@PathVariable Long recruiterId, @RequestBody Job job) {
        return ResponseEntity.ok(jobService.postJob(job, recruiterId));
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

}
