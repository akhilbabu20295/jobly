package tech.codeguru.jobly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.codeguru.jobly.entity.Application;
import tech.codeguru.jobly.entity.ApplicationStatus;
import tech.codeguru.jobly.service.ApplicationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<Application> applyToJob(@RequestParam Long candidateId,
                                                  @RequestParam Long jobId,
                                                  @RequestParam(required = false) Long resumeId,
                                                  @RequestParam(required = false) Long coverLetterId) {
        Application application = applicationService.applyToJob(candidateId, jobId, resumeId, coverLetterId);
        return ResponseEntity.ok(application);
    }

    @GetMapping
    public ResponseEntity<List<Application>> getApplicationsByCandidate(@RequestParam Long candidateId) {
        List<Application> applications = applicationService.getApplicationsByCandidate(candidateId);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long applicationId) {
        Application application = applicationService.getApplicationById(applicationId);
        return ResponseEntity.ok(application);
    }

   /* @DeleteMapping("/{applicationId}")
    public ResponseEntity<Void> withdrawApplication(@PathVariable Long applicationId) {
        applicationService.withdrawApplication(applicationId);
        return ResponseEntity.noContent().build();
    }*/

    @PatchMapping("/{applicationId}/status")
    public ResponseEntity<Application> updateApplicationStatus(@PathVariable Long applicationId,
                                                               @RequestParam ApplicationStatus status) {
        Application application = applicationService.updateApplicationStatus(applicationId, status);
        return ResponseEntity.ok(application);
    }
}
