package tech.codeguru.jobly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.codeguru.jobly.entity.Skills;
import tech.codeguru.jobly.entity.dto.request.SkillDTO;
import tech.codeguru.jobly.service.SkillService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class BasicController {

    @Autowired
    private SkillService skillService;

    @PostMapping("/skills")
    public ResponseEntity<?> createSkill(@RequestBody SkillDTO request) {
        Skills skill = skillService.createSkill(request);
        return ResponseEntity.ok("Skill created with ID: " + skill.getId());
    }

    @GetMapping("/skills")
    public ResponseEntity<List<Skills>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    @PutMapping("/skills/{id}")
    public ResponseEntity<?> updateSkill(@PathVariable Long id, @RequestBody SkillDTO request) {
        Skills updated = skillService.updateSkill(id, request);
        return ResponseEntity.ok("Skill updated: " + updated.getName());
    }

    @DeleteMapping("/skills/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.ok("Skill deleted with ID: " + id);
    }
}
