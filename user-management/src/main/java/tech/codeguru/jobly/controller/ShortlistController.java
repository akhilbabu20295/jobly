package tech.codeguru.jobly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.codeguru.jobly.entity.Shortlist;
import tech.codeguru.jobly.service.ShortlistService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shortlists")
public class ShortlistController {

    @Autowired
    private ShortlistService shortlistService;

    @PostMapping
    public ResponseEntity<Shortlist> shortlistCandidate(@RequestParam Long candidateId,
                                                        @RequestParam Long jobId) {
        Shortlist shortlist = shortlistService.shortlistCandidate(candidateId, jobId);
        return ResponseEntity.ok(shortlist);
    }

    @GetMapping("/jobs/{jobId}")
    public ResponseEntity<List<Shortlist>> getShortlistedCandidates(@PathVariable Long jobId) {
        List<Shortlist> shortlists = shortlistService.getShortlistedCandidatesByJob(jobId);
        return ResponseEntity.ok(shortlists);
    }

    @GetMapping("/candidates/{candidateId}")
    public ResponseEntity<List<Shortlist>> getShortlistedJobs(@PathVariable Long candidateId) {
        List<Shortlist> shortlists = shortlistService.getShortlistedJobsByCandidate(candidateId);
        return ResponseEntity.ok(shortlists);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeShortlistedCandidate(@RequestParam Long candidateId,
                                                           @RequestParam Long jobId) {
        shortlistService.removeShortlistedCandidate(candidateId, jobId);
        return ResponseEntity.noContent().build();
    }
}
