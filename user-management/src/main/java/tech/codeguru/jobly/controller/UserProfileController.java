package tech.codeguru.jobly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.codeguru.jobly.entity.Resume;
import tech.codeguru.jobly.entity.UserProfile;
import tech.codeguru.jobly.entity.UserProfileSkill;
import tech.codeguru.jobly.entity.dto.request.ResumeDTO;
import tech.codeguru.jobly.entity.dto.request.UserProfileSkillDTO;
import tech.codeguru.jobly.entity.dto.request.UserRequest;
import tech.codeguru.jobly.service.ResumeService;
import tech.codeguru.jobly.service.UserProfileService;
import tech.codeguru.jobly.service.UserProfileSkillService;

import java.io.IOException;

@RestController()
@RequestMapping("/api/v1/user")
public class UserProfileController {


    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private UserProfileSkillService userProfileSkillService;

    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable Long userId) {
        UserProfile userProfile = userProfileService.getUsers(userId);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @PatchMapping("/profile/update/{userId}")
    public ResponseEntity<String> updateUserProfile(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        UserProfile user = userProfileService.updateUserProfile(userId, userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/profile/experience/{id}")
    public ResponseEntity<String> updateExperience(@PathVariable Long id, @RequestParam Integer experience) {
        userProfileService.updateExperience(id, experience);
        return ResponseEntity.ok("Experience updated successfully");
    }

    @PatchMapping("/profile/resume/upload/{userId}")
    public ResponseEntity<String> uploadResume(@PathVariable Long userId, @RequestParam("file") MultipartFile file) throws IOException {
        Resume resume = resumeService.updateResume(userId, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/profile/resume/{id}")
    public ResponseEntity<ResumeDTO> getResumeById(@PathVariable Long id) {
        ResumeDTO resume = resumeService.getResumeById(id);
        return ResponseEntity.ok(resume);
    }

    @PostMapping("/profile/skills/add")
    public ResponseEntity<?> addSkill(@RequestBody UserProfileSkillDTO request) {
        UserProfileSkill savedSkill = userProfileSkillService.addSkillToUserProfile(request);
        return ResponseEntity.ok("Skill added to user profile with ID: " + savedSkill.getId());
    }

    @DeleteMapping("/profile/skills/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        userProfileSkillService.deleteUserProfileSkillById(id);
        return ResponseEntity.ok("Skill mapping deleted by ID: " + id);
    }
}
